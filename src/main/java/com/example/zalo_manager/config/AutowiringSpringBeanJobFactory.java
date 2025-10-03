package com.example.zalo_manager.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory {

    private final AutowireCapableBeanFactory beanFactory;

    public AutowiringSpringBeanJobFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // Tạo job instance
        Object job = super.createJobInstance(bundle);
        // Autowire các bean vào job
        beanFactory.autowireBean(job);
        return job;
    }
}
