package com.leeonscoding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties p = new Properties();
        String fileName = "example.properties";
        FileInputStream fis = new FileInputStream(fileName);

        try(fis) {
            //loads properties file
            p.load(fis);

            String url = p.getProperty("user.name");
            System.out.println(url);
            String nothing = p.getProperty("nothing.key", "nothing value");
            System.out.println(nothing);

            System.out.println("=====================================");

            //Sets a property in java property object.
            p.setProperty("user.motorcycle", "Honda");
            /*
             * We must open the FileOutputStream after the load() method.
             * Otherwise, the original properties will be lost.
             */
            try(FileOutputStream fos = new FileOutputStream(fileName)) {
                p.store(fos, "updated motorcycle info");
            }

            //Iterate all keys
            p.propertyNames()
                    .asIterator()
                    .forEachRemaining(element-> {
                        String result = MessageFormat.format(
                                "key is=> {0} and value is=> {1}",
                                element,
                                p.getProperty(element.toString())
                        );
                        System.out.println(result);
                    });
            //For testing purpose. Without compile it will not reflect
            int i = 100;
            System.out.println(i);

        }
    }
}