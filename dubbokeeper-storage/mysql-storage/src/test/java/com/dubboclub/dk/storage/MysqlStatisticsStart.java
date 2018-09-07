package com.dubboclub.dk.storage;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class MysqlStatisticsStart {
    private static volatile boolean running=false;

    private static final String STOP_COMMAND="stop";

    private static final String START_COMMAND="start";

    public static void main(String[] args){
        start();
    }

    private static void stop(){
        running=false;
        MysqlStatisticsStart.class.notifyAll();
    }

    private static void start(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath*:/META-INF/spring/*.xml","classpath*:/META-INF/mybatis/*.xml"});
        classPathXmlApplicationContext.start();
        running=true;
        synchronized (MysqlStatisticsStart.class) {
            while (running) {
                try {
                    MysqlStatisticsStart.class.wait();
                } catch (Throwable e) {
                }
            }
            classPathXmlApplicationContext.stop();
        }
    }

}
