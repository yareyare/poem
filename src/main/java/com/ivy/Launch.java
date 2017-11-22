package com.ivy;

import com.ivy.crawler.PoemTypeCrawlerByType;
import com.ivy.db.DB_client;
import com.ivy.jetty.JettyServer;
import org.apache.log4j.Logger;

/**
 * Created by admin on 2017/11/9.
 */
public class Launch {

    private static final Logger LOG = Logger.getLogger(PoemTypeCrawlerByType.class);

    private static Integer httpPort = 8888;

    public static void main(String[] args)throws Exception {
        LOG.info("************** server start begin *****************");
        Configurations.init();
        JettyServer.startJetty( httpPort );
        DB_client.init_pool();
        Thread.sleep(5000);
        LOG.info("************** server start end *****************");
    }

}
