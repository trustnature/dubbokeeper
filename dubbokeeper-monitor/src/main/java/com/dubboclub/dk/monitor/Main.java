package com.dubboclub.dk.monitor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static volatile boolean running=false;

    private static final String STOP_COMMAND="stop";

    private static final String START_COMMAND="start";

    public static void main(String[] args){
        start();
    }

    private static void stop(){
        running=false;
        Main.class.notifyAll();
    }

    private static void start(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:/META-INF/spring/*.xml");
        classPathXmlApplicationContext.start();
        running=true;
        synchronized (Main.class) {
            while (running) {
                try {
                    Main.class.wait();
                } catch (Throwable e) {
                }
            }
            classPathXmlApplicationContext.stop();
        }
    }

}
