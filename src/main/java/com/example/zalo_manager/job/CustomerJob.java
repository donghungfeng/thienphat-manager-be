package com.example.zalo_manager.job;

import com.example.zalo_manager.entity.ScheduleJob;
import com.example.zalo_manager.model.response.BaseResponse;
import com.example.zalo_manager.repository.ScheduleJobRepository;
import com.example.zalo_manager.service.TemplateService;
import com.example.zalo_manager.type.JobStatus;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerJob implements Job {
    @Autowired
    private ScheduleJobRepository scheduleJobRepository;

    @Autowired
    private TemplateService templateService;

    @Override
    public void execute(JobExecutionContext context) {
        Long jobId = context.getJobDetail().getJobDataMap().getLong("jobId");

        ScheduleJob job = scheduleJobRepository.findById(jobId).orElse(null);
        if (job == null) return;

        try {
            BaseResponse response = templateService.sendTemplate(job.getTemplate().getId(), job.getCustomer().getId());
            if (response.getCode() == 200){
                job.setStatus(JobStatus.DONE.getCode());
                scheduleJobRepository.save(job);
            }

        } catch (Exception e) {
            job.setRunTime(job.getRunTime().plusHours(1));
            scheduleJobRepository.save(job);
        }
    }
}

