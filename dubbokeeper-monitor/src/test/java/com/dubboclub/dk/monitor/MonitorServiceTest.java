package com.dubboclub.dk.monitor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class MonitorServiceTest {
    private static volatile boolean running=false;

    private static final String STOP_COMMAND="stop";

    private static final String START_COMMAND="start";

    public static void main(String[] args){
        start();
    }

    private static void stop(){
        running=false;
        MonitorServiceTest.class.notifyAll();
    }

    private static void start(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:/META-INF/spring/*.xml");
        running=true;
        synchronized (MonitorServiceTest.class) {
            while (running) {
                try {
                    MonitorServiceTest.class.wait();
                } catch (Throwable e) {
                }
            }
            classPathXmlApplicationContext.stop();
        }
    }

}
