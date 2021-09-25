package com.andneo.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-24 22:43
 **/
public class DefaultResourceLoader implements ResourceLoader {

    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }

        try {
            URL url = new URL(location);
            return new URLResource(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return new FileSystemResource(location);
        }

    }
}
