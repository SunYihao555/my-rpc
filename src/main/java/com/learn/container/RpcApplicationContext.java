package com.learn.container;

import com.learn.annotation.RpcService;
import com.learn.xml.XmlParser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
        executeInstance();

    }
    public void executeScanPackage(String aPackage){

        URL resource = this.getClass().getClassLoader().getResource(aPackage.replaceAll("\\.", "/"));

        String path = resource.getFile();
        File file = new File(path);
        for (File listFile : file.listFiles()) {
            if(listFile.isDirectory()){
                executeScanPackage(aPackage+"."+listFile.getName());
            }else{
                classNameList.add(aPackage+"."+listFile.getName().replaceAll(".class",""));
            }
        }

    }
    public void executeInstance(){
        if(classNameList.isEmpty()){
            throw new RuntimeException("没有要实例化的类");
        }
        try {
            for (String className : classNameList) {
                System.out.println(className);
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(RpcService.class)){
                    RpcService rpcService =clazz.getAnnotation(RpcService.class);
                    String group = rpcService.group();
                    String version = rpcService.version();
                    if (group.equals("")){
                        group += "0";
                    }
                    if(version.equals("")){
                        version += "0";
                    }
                    for (Class<?> anInterface : clazz.getInterfaces()) {
                        String beanName = anInterface.getSimpleName().substring(0,1).toLowerCase()+anInterface.getSimpleName().substring(1)+version+group;
                        iocMap.put(beanName,clazz.newInstance());
                    }

                }



            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
