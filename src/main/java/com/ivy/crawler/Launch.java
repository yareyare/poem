package com.ivy.crawler;

import com.ivy.Configurations;
import com.ivy.jetty.JettyServer;
import org.apache.log4j.Logger;

/**
 * Created by admin on 2017/11/9.
 */
public class Launch {

    private static final Logger LOG = Logger.getLogger(PoemTypeCrawlerByType.class);

    private static Integer httpPort = 8888;

    public static void main(String[] args)throws Exception {
        LOG.info("************** crawler server start *****************");
        Configurations.init();
        JettyServer.startJetty( httpPort );
        //DB_client.init_pool();
        Thread.sleep(5000);
//        Thread.currentThread().join();
        LOG.info("************** crawler server started *****************");

        //String url = "http://so.gushiwen.org/shiwen/tags.aspx";
//        String url = "http://so.gushiwen.org/gushi/tangshi.aspx";
//        PoemTypeCrawlerByType.poemTypeCrawlerHandler(url);

    }

}
