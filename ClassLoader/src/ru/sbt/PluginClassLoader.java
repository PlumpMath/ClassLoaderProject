package ru.sbt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PluginClassLoader extends ClassLoader {

    private File dir;
    private Method resolve, define;

    public PluginClassLoader(File dir, ClassLoader parent) throws NoSuchMethodException {
        super(parent);
        this.dir = dir;
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            File file = new File(dir + "/" + className);
            byte[] b = loadClassData(file);
            return defineClass(null, b, 0, b.length);
        } catch (FileNotFoundException ex) {
            return super.findClass(className);
        } catch (IOException ex) {
            return super.findClass(className);
        }
    }

    private byte[] loadClassData(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            System.out.println("File is too large");
        }

        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = inputStream.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + dir.getPath());
        }

        inputStream.close();
        return bytes;

    }
}
