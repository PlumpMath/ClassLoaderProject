package ru.sbt;

/**
 * Created by Irina Kamalova on 03.12.16.
 */
public class MyPlugin implements Plugin {
    public String pluginInfo() {
        return "Plugin test, " + this.getClass();
    }
}
