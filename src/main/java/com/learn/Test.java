package com.learn;

import com.learn.xml.XmlParser;

import java.io.InputStream;

public class Test {
    public static void main(String[] args) {
        String scanPackage = XmlParser.getScanPackage("rpc-config.xml");
        System.out.println(scanPackage);
    }
}
