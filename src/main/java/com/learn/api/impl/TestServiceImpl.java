package com.learn.api.impl;

import com.learn.annotation.RpcService;
import com.learn.api.TestService;

@RpcService
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        return "简单测试实现";
    }
}
