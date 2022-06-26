package com.learn.proxy;

import com.learn.remoting.dto.RpcRequest;
import com.learn.remoting.dto.RpcResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class ProxyFactory  {
    public static <T> T getProxy(Class<?> clazz){
        Object o = Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket("127.0.0.1",8080);
                RpcRequest request = new RpcRequest();
                request.setRequestId("123");
                request.setMethodName(method.getName());

                request.setInterfaceName(clazz.getName().split("\\.")[clazz.getName().split("\\.").length-1]);
                System.out.println(request.getInterfaceName());
                request.setVersion("0");
                request.setGroup("0");
                request.setParameters(args);
                if(args!=null){
                    Class<?>[] parameterTypes = new Class[args.length];
                    for(int i = 0;i<args.length;i++){
                        parameterTypes[i] = args[i].getClass();
                    }
                    request.setParameterTypes(parameterTypes);
                }
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(request);
                objectOutputStream.flush();
                ObjectInputStream objectInputStream  = new ObjectInputStream(socket.getInputStream());
                RpcResponse rpcResponse = (RpcResponse) objectInputStream.readObject();
                Object date = rpcResponse.getDate();
                return date;


            }
        });

        return (T)o;

    }
}
