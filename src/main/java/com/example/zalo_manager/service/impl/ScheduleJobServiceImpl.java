package com.example.zalo_manager.service.impl;

import com.example.zalo_manager.entity.Customer;
import com.example.zalo_manager.entity.ScheduleJob;
import com.example.zalo_manager.entity.Template;
import com.example.zalo_manager.job.CustomerJob;
import com.example.zalo_manager.model.request.ScheduleJobCreateReq;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.BaseRepository;
import com.example.zalo_manager.repository.CustomerRepository;
import com.example.zalo_manager.repository.ScheduleJobRepository;
import com.example.zalo_manager.repository.TemplateRepository;
import com.example.zalo_manager.service.ScheduleJobService;
import com.example.zalo_manager.type.JobStatus;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.ZoneId;

@Service
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJob> implements ScheduleJobService {
    @Autowired
    ScheduleJobRepository scheduleJobRepository;

    @Autowired
    TemplateRepository templateRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private Scheduler scheduler;

    @Override
    protected BaseRepository<ScheduleJob> getRepository() {
        return scheduleJobRepository;
    }


    @Override
    public BaseResponse create(ScheduleJobCreateReq req) {
        Template template = templateRepository.findAllById(req.getTemplateId());
        if (template == null){
            return BaseResponse.fail("template không tồn tại");
        }
        Customer customer = customerRepository.findAllById(req.getCustomerId());
        if (customer == null){
            return BaseResponse.fail("customer không tồn tại");
        }
        ScheduleJob job = ScheduleJob
                .builder()
                .template(template)
                .customer(customer)
                .runTime(req.getTime())
                .status(JobStatus.PENDING.getCode())
                .build();
        job = scheduleJobRepository.save(job);
        schedule(job);
        return BaseResponse.success(job);
    }

    /** Tạo JobDetail + Trigger */
    public void schedule(ScheduleJob job){
        try {
            JobDetail jobDetail = JobBuilder.newJob(CustomerJob.class)
                    .withIdentity("job_" + job.getId(), "customer_jobs")
                    .usingJobData("jobId", job.getId())
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger_" + job.getId(), "customer_triggers")
                    .startAt(Date.from(job.getRunTime().atZone(ZoneId.systemDefault()).toInstant()))
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withMisfireHandlingInstructionFireNow()) // Nếu miss → chạy ngay
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e){
            e.printStackTrace();
        }
    }
}
