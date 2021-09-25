package com.andneo.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-24 21:54
 **/
public class URLResource implements Resource {
    private URL url;

    public URLResource(URL url) {
        Assert.notNull(url, " url must not null");
        this.url = url;
    }

    public InputStream getInputStream() throws IOException {
        URLConnection connection = this.url.openConnection();

        return connection.getInputStream();
    }
}
