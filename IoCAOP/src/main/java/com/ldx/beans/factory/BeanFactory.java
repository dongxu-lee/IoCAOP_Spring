package com.ldx.beans.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工厂类，生产对象（使用反射技术）
 */
public class BeanFactory {

    /**
     * 任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
     * 任务二：对外提供获取实例对象的接口（根据id获取）
     */
    private static Map<String, Object> map = new HashMap<>(); //存储对象

    static {
        //任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
        //加载xml
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        //解析xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> beanList = rootElement.selectNodes("//bean");
            for (int i = 0; i < beanList.size(); i++) {
                Element element = beanList.get(i);
                //处理每个bean元素，获取id和class
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");
                //通过反射技术，实例化对象
                Class<?> aClass = Class.forName(clazz);
                Object o = aClass.newInstance(); //实例化之后的对象

                //存储到map待用
                map.put(id, o);

            }

            /**
             * 对于依赖bean的实例化，这里只是实现了set方法的实例化，其他的如构造函数，工厂等没有实现
             * 其实也可以参考上面的，使用反射实例化
             * */

            // 实例化完成之后需要维护对象的依赖关系
            //检查哪些对象需要传值输入，根据他的配置，传入相应的值
            List<Element> propertyList = rootElement.selectNodes("//property");
            //解析property，获取父元素
            for (int i = 0; i < propertyList.size(); i++) {
                Element element = propertyList.get(i);
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");

                //找到当前需要被处理依赖关系的bean
                Element parent = element.getParent();

                //调用父元素对象的反射功能
                String parentId = parent.attributeValue("id");
                Object parentObject = map.get(parentId);
                //遍历父对象中的所有方法，找到"set"+name
                Method[] methods = parentObject.getClass().getMethods();
                for (int k = 0; k < methods.length; k++) {
                    Method method = methods[k];
                    if(("set" + name).equalsIgnoreCase(method.getName())) { //该方法就是setUserDao
                        method.invoke(parentObject,map.get(ref));
                    }
                }

                //把处理之后的parentObject重新放到map中
                map.put(parentId, parentObject);

            }

        } catch (DocumentException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //任务二：对外提供获取实例对象的接口（根据id获取）
    public static Object getBean(String id) {
        return map.get(id);
    }














}
