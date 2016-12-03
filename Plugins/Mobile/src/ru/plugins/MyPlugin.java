package ru.plugins;


public class MyPlugin implements Plugin {
    public String pluginInfo() {
        return "Mobile plugin, " + this.getClass().getName();
    }
}
