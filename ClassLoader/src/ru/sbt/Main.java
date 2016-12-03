package ru.sbt;

import java.io.File;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {
            PluginManager pm = new PluginManager();
            // Выгружаем первый плагин
            Plugin plugin = pm.load("/home/irisha/Projects/Plugins/Mobile/out/production/Mobile/ru/plugins/", "MyPlugin.class");
            System.out.println(plugin.pluginInfo());
            System.out.println(Plugin.class.getClassLoader());

    }
}
