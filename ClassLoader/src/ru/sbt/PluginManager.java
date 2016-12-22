package ru.sbt;

import ru.plugins.Plugin;

import java.io.File;

public class PluginManager {

    public Plugin load(String name, String pluginClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException {
        File dir = new File(name);
        PluginClassLoader pluginClassLoader = new PluginClassLoader(dir, ClassLoader.getSystemClassLoader());
        for (File file : dir.listFiles()) {
            System.out.println(file.getName());
            if (file.getName().equals(pluginClass)) {
                try {
                    Class<?> clazz = pluginClassLoader.findClass(file.getName());
                    return (Plugin) clazz.newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
