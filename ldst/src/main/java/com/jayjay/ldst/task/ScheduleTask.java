package com.jayjay.ldst.task;

import com.jayjay.ldst.service.ReptilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @author jayjay
 * @date 2020/11/4
 * @Description:
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ScheduleTask {

    @Autowired
    private ReptilesService reptilesService;

    //3.添加定时任务
//    每隔5秒执行一次
//    @Scheduled(cron = "0/5 * * * * ?")
//    每隔分钟执行一次
//    @Scheduled(cron = "0 */1 * * * ?")
    //每2小时执行一次
//    @Scheduled(cron = "0 0 0/2 * * ? *")
    //每天23点执行一次
    @Scheduled(cron = "0 0 23 * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() throws Exception {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        reptilesService.reptliesNewest();

    }
}
