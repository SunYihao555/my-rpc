package com.learn.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class XmlParser {
    public static String getScanPackage(String xml){
        SAXReader saxReader = new SAXReader();
        InputStream inputStream = XmlParser.class.getClassLoader().getResourceAsStream(xml);
        try {
            Document read = saxReader.read(inputStream);
            Element rootElement = read.getRootElement();
            Element scan = rootElement.element("scan");
            Attribute aPackage = scan.attribute("package");
            String text = aPackage.getText();
            return text;

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "";
    }
}
