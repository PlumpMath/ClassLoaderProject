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
        Class<ClassLoader> c = ClassLoader.class;
        define = c.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
        resolve = c.getDeclaredMethod("resolveClass", Class.class);
        define.setAccessible(true);//методы - protected
        resolve.setAccessible(true);
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            /*
            // Проверяем, что класс лоадер не пытается выгрузить "чужой" интерфейс Plugin
            if (className.endsWith(".Plugin")) {
                // Если пытается - отдаем ему наш интерфейс
                Class<?> c = getSystemClassLoader().loadClass("ru.sbt.Plugin");
                c.r
                return ;
            }
            */
            ClassLoader c = ClassLoader.getSystemClassLoader();//Системный ClassLoader
            Class<?> cl;
            System.out.println("dir - file - dir - dir");
            System.out.println(dir);
            File file = new File(dir + "/" + className);
            System.out.println(file.getName());
            System.out.println(file.getPath());
            System.out.println(file.getAbsolutePath());
            byte[] b = loadClassData(file);

            cl = (Class<?>) define.invoke(c, null, b, 0, b.length);

            return cl;
        } catch (FileNotFoundException ex) {
            return super.findClass(className);
        } catch (IOException ex) {
            return super.findClass(className);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
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
