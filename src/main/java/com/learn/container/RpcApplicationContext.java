package com.learn.container;

import com.learn.xml.XmlParser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RpcApplicationContext {
    public Map<String,Object> iocMap = new ConcurrentHashMap<>();
    private String parsePath;
    private List<String> classNameList = new ArrayList<>();
    public RpcApplicationContext(String parsePath){
        this.parsePath = parsePath;

    }
    public void refresh(){
        String scanPackage = XmlParser.getScanPackage(parsePath);
        String[] packages = scanPackage.split(",");
        if(packages.length!=0){
            for (String aPackage : packages) {
                executeScanPackage(aPackage);
            }
        }else{
            throw new RuntimeException("没有要解析的包");
        }

    }
    public void executeScanPackage(String aPackage){
        URL resource = this.getClass().getClassLoader().getResource("/" + aPackage.replaceAll("\\.", "/"));
        String path = resource.getFile();
        File file = new File(path);
        for (File listFile : file.listFiles()) {
            if(listFile.isDirectory()){
                executeScanPackage(aPackage+"."+listFile.getName());
            }else{
                classNameList.add(listFile.getName());
            }
        }

    }
    public void executeInstance(){
        if(classNameList.isEmpty()){
            throw new RuntimeException("没有要实例化的类");
        }
        try {
            for (String className : classNameList) {
                Class<?> clazz = Class.forName(className);
                

            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
