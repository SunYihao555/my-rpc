package com.learn;

import com.learn.container.RpcApplicationContext;
import com.learn.xml.XmlParser;

import java.io.InputStream;
import java.net.URL;

public class Test {
    public static void main(String[] args) {
       RpcApplicationContext rpcApplicationContext = new RpcApplicationContext("rpc-config.xml");
       rpcApplicationContext.refresh();
        System.out.println(rpcApplicationContext.iocMap);

    }
}
