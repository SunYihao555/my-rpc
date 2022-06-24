package com.learn.api.impl;

import com.learn.annotation.RpcService;
import com.learn.api.HelloService;

@RpcService
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "你好："+ name;
    }
}
