package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ClassName: MyTask
 * Package: com.sky.task
 * Description:
 *
 * @Author Jingran Liu
 * @Create 2025/1/30 17:32
 * @Version 1.0
 */
@Component
@Slf4j
public class MyTask {
    /**
     * 定时任务 每隔5秒触发一次
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void executeTask(){
        log.info("Start task: {}", new Date());
    }
}
