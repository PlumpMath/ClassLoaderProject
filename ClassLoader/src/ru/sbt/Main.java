package ru.sbt;

import java.io.File;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        PluginManager pm = new PluginManager();
        // Выгружаем первый плагин
        Plugin pluginMobile = pm.load("/home/irisha/Projects/ClassLoaderProject/ClassLoader/out/production/untitled/plugins/mobile", "MyPlugin.class");
        System.out.println(pluginMobile.pluginInfo());
        Plugin pluginParser = pm.load("/home/irisha/Projects/ClassLoaderProject/ClassLoader/out/production/untitled/plugins/parsers", "MyPlugin.class");
        System.out.println(pluginParser.pluginInfo());
        Plugin pluginTest = pm.load("/home/irisha/Projects/ClassLoaderProject/ClassLoader/out/production/untitled/ru/sbt/", "MyPlugin.class");
        System.out.println(pluginTest.pluginInfo());
        pluginMobile = pluginTest;
        System.out.println("New mobile plugin is: " + pluginMobile.pluginInfo());


    }
}
