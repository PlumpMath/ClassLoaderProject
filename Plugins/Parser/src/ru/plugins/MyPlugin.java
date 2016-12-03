package ru.plugins;


public class MyPlugin implements Plugin {

    public String pluginInfo() {
        return "Parser plugin, " + this.getClass().getName();
    }
}
