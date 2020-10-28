package com.ldx.proxy;

import com.ldx.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyFactory {

//    private ProxyFactory() {}
//
//    private static ProxyFactory proxyFactory = new ProxyFactory();
//
//    public static ProxyFactory getInstance() {
//        return proxyFactory;
//    }

    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * 使用jdk动态代理
     * @param obj
     * @return
     */
    public Object getJdkProxy(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try {
                    //开启事务
                    transactionManager.beginTransaction();
                    result = method.invoke(obj, args);
                    transactionManager.commit();
                }catch (Exception e) {
                    e.printStackTrace();
                    transactionManager.rollback();
                    throw e;
                }
                return result;
            }
        });
    }

    /**
     * 使用cglib动态代理生成代理对象
     * @param obj
     * @return
     */
    public Object getCglibProxy(Object obj) {
        return Enhancer.create(obj.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                Object result = null;
                try {
                    //开启事务
                    transactionManager.beginTransaction();
                    result = method.invoke(obj, args);
                    transactionManager.commit();
                }catch (Exception e) {
                    e.printStackTrace();
                    transactionManager.rollback();
                    throw e;
                }
                return result;
            }
        });
    }
}
