package com.example.zalo_manager.config;

import com.example.zalo_manager.entity.ScheduleJob;
import com.example.zalo_manager.job.CustomerJob;
import com.example.zalo_manager.repository.ScheduleJobRepository;
import com.example.zalo_manager.service.ScheduleJobService;
import com.example.zalo_manager.type.JobStatus;
import jakarta.annotation.PostConstruct;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class JobInitializer {
    @Autowired
    private ScheduleJobRepository scheduleJobRepository;

    @Autowired
    private ScheduleJobService scheduleService;

    @Autowired
    private Scheduler scheduler;

    /**
     * Phương thức này sẽ chạy ngay khi Spring Boot khởi động xong context
     * Nó reschedule tất cả job còn PENDING hoặc quá hạn
     */
    @PostConstruct
    public void init() {
        // Lấy tất cả job có status = PENDING (0)
        List<ScheduleJob> jobs = scheduleJobRepository.findAllByStatusAndIsActive(JobStatus.PENDING.getCode(), 1);

        for (ScheduleJob job : jobs) {
            if (job.getRunTime().isBefore(LocalDateTime.now())) {
                // Job quá hạn → chạy ngay
                runNow(job);
            } else {
                // Job chưa tới giờ → đăng ký Quartz bình thường
                scheduleService.schedule(job);
            }
        }
    }

    /**
     * Chạy job ngay lập tức bằng Quartz
     */
    private void runNow(ScheduleJob job) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(CustomerJob.class)
                    .withIdentity("job_now_" + job.getId(), "immediate_jobs")
                    .usingJobData("jobId", job.getId())
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .startNow() // trigger ngay lập tức
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
