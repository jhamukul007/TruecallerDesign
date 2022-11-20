package com.tc.console;

public class ConsoleLogger implements Logger{

    @Override
    public void log(Object obj) {
        System.out.println(obj);
        System.out.println();
    }
}
