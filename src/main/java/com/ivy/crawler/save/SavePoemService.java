package com.ivy.crawler.save;

import com.ivy.crawler.bo.PoemCrawl;
import com.ivy.tool.Return;

/**
 * Created by admin on 2017/11/9.
 */
public interface SavePoemService {

    Return save(PoemCrawl poemCrawl);
}
