package ru.sbt;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

public class ServiceClassLoader extends URLClassLoader {
    public ServiceClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public ServiceClassLoader(URL[] urls) {
        super(urls);
    }

    public ServiceClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }
}
