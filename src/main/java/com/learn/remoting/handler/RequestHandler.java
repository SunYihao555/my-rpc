package com.learn.remoting.handler;

import com.learn.common.enums.RpcCommonEnum;
import com.learn.container.RpcApplicationContext;
import com.learn.remoting.dto.RpcRequest;
import com.learn.remoting.dto.RpcResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RequestHandler {

    public static RpcResponse handle(RpcRequest request){
        String methodName = request.getMethodName();
        String group = request.getGroup();
        String version = request.getVersion();
        String interfaceName = request.getInterfaceName();
        Object[] parameters = request.getParameters();
        String method = interfaceName+version+group;
        System.out.println(method);
        Class<?> aClass = RpcApplicationContext.iocMap.get(method);
        System.out.println(aClass);
        Method declaredMethod;
        Object result = null;
        try {
            declaredMethod = aClass.getDeclaredMethod(methodName, request.getParameterTypes());
            result = declaredMethod.invoke(aClass.newInstance(), parameters);

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("方法名称错误");
        }
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setDate(result);
        rpcResponse.setRequestId(request.getRequestId());
        rpcResponse.setCode(RpcCommonEnum.SUCCESS.getCode());
        rpcResponse.setMessage(RpcCommonEnum.SUCCESS.getMessage());
        return rpcResponse;

    }
}
