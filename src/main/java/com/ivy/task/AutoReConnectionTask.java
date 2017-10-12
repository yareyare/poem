package com.ivy.task;

import com.ivy.service.ReConnectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/4/27.
 */
@Component
@Lazy(false)
public class AutoReConnectionTask {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    ReConnectService reConnectService;

    @Scheduled(cron = "0/5 * * * * ?") //秒 分 时 日 月 星期 年
    public void taskUserOrgBrankingList() {
        try {
            int ret = reConnectService.reConnection();
            if (ret == 1) {
                logger.info("AutoReConnectionTask 刷新成功");
            } else {
                logger.info("AutoReConnectionTask 刷新异常");
            }
        } catch (Exception e) {
            logger.error("定时器执行失败【AutoReConnectionTask】", e);
        }
    }
}
