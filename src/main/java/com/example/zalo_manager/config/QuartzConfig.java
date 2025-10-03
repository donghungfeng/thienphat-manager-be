package com.example.zalo_manager.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    private final AutowireCapableBeanFactory beanFactory;

    public QuartzConfig(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Bean
    public Scheduler scheduler() throws SchedulerException {
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();

        // Dùng job factory hỗ trợ autowire
        scheduler.setJobFactory(new AutowiringSpringBeanJobFactory(beanFactory));

        scheduler.start();
        return scheduler;
    }
}
