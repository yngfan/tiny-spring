package com.andneo.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源加载
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
