package com.ivy.crawler.save;

import com.ivy.model.BaseException;
import com.ivy.crawler.bo.PoemCrawl;
import com.ivy.crawler.bo.PoemDetailCrawl;
import com.ivy.crawler.bo.PoetCrawlDetail;
import com.ivy.model.po.*;
import com.ivy.service.*;
import com.ivy.serviceImpl.*;
import com.ivy.tool.Code;
import com.ivy.tool.EnumUtils;
import com.ivy.tool.Return;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/11/8.
 */
@Service
public class SavePoemServiceImpl implements SavePoemService {

    private static final Logger LOG = Logger.getLogger(SavePoemServiceImpl.class.getName());

    @Autowired
    private DynastyService dynastyService ;

    @Autowired
    private PoemTypeService poemTypeService ;

    @Autowired
    private PoemTypeRefService poemTypeRefService;

    @Autowired
    private PoemService poemService ;

    @Autowired
    private PoemDetailService poemDetailService ;

    @Autowired
    private PoetService poetService ;

    @Autowired
    private PoetDetailService poetDetailService ;

    public Return save(PoemCrawl poemCrawl) {

        //保存dynasty并返回Id， 如果存在则直接返回id；
        Integer dynastyId = null;
        Return dynastyReturn = saveDynasty(poemCrawl);
        if ((Integer) dynastyReturn.get("code") != 10200) {
            return dynastyReturn;
        } else {
            dynastyId = (Integer) dynastyReturn.get("dynastyId");
        }

        //保存诗歌类型
        Integer poemTypeId = null;
        Return typeReturn = saveType(poemCrawl);
        if ((Integer) typeReturn.get("code") != 10200) {
            return typeReturn;
        } else {
            poemTypeId = (Integer) typeReturn.get("poemTypeId");
        }

        //保存诗人
        Integer poetId = null;
        Return poetReturn = savePoet(poemCrawl, dynastyId);
        if ((Integer) poetReturn.get("code") != 10200) {
            return poetReturn;
        } else {
            poetId = (Integer) poetReturn.get("poetId");
        }


        //保存诗歌
        Integer poemId = null;
        Return poemReturn = savePoem(poemCrawl, dynastyId, poetId);
        if ((Integer) poemReturn.get("code") != 10200) {
            return poemReturn;
        } else {
            poemId = (Integer) poemReturn.get("poemId");
        }

        //是与类型的关系保存
        Return typeRefReturn = saveTypeRef(poemId, poemTypeId);
        if ((Integer) typeRefReturn.get("code") != 10200) {
            return typeRefReturn;
        }

        //保存诗歌详情
        Return poemDetailReturn = savePoemDetail(poemCrawl, poemId);
        if ((Integer) poemDetailReturn.get("code") != 10200) {
            return poemDetailReturn;
        }

        //保存作者详情
        Return poetDetailReturn = savePoetDetail(poemCrawl, poetId);
        if ((Integer) poetDetailReturn.get("code") != 10200) {
            return poetDetailReturn;
        }
        return Return.SUCCESS(Code.SUCCESS);


    }

    private Return saveDynasty(PoemCrawl poemCrawl) {
        //保存dynasty并返回Id， 如果存在则直接返回id；
        Dynasty dynasty = new Dynasty();
        dynasty.setName(poemCrawl.getChaodai());
        dynasty.setCreateDate(new Date());
        Integer dynastyId = null;
        try {
            dynastyId = dynastyService.save(dynasty);
        } catch (BaseException e) {
            Return.FAIL(Integer.parseInt(e.getMessage()), EnumUtils.getNameByType(Code.class, Integer.parseInt(e.getMessage())));
        }
        if (dynastyId == null) {
            Return.FAIL(Code.SAVE_DYNASTY_ERROR);
        }
        return Return.SUCCESS(Code.SUCCESS).put("dynastyId", dynastyId);
    }

    private Return saveType(PoemCrawl poemCrawl) {
        //保存诗歌类型并返回Id， 如果存在则直接返回id；
        PoemType poemType = new PoemType();
        poemType.setType(poemCrawl.getType());
        poemType.setType1(poemCrawl.getType1());
        Integer poemTypeId = null;
        try {
            poemTypeId = poemTypeService.save(poemType);
        } catch (BaseException e) {
            Return.FAIL(Integer.parseInt(e.getMessage()), EnumUtils.getNameByType(Code.class, Integer.parseInt(e.getMessage())));
        }
        if (poemTypeId == null) {
            Return.FAIL(Code.SAVE_TYPE_ERROR);
        }
        return Return.SUCCESS(Code.SUCCESS).put("poemTypeId", poemTypeId);
    }

    private Return savePoem(PoemCrawl poemCrawl, Integer dynastyId, Integer poetId) {
        //保存诗歌并返回Id， 如果存在则直接返回id；
        Poem poem = new Poem();
        poem.setDynastyId(dynastyId);
        poem.setPoetId(poetId);
        poem.setTitle(poemCrawl.getTitle());
        poem.setContent(poemCrawl.getContent());
        poem.setTypes(poemCrawl.getTag());
        poem.setCreateDate(new Date());
        Integer poemId = null;
        try {
            poemId = poemService.save(poem);
        } catch (BaseException e) {
            Return.FAIL(Integer.parseInt(e.getMessage()), EnumUtils.getNameByType(Code.class, Integer.parseInt(e.getMessage())));
        }
        if (poemId == null) {
            Return.FAIL(Code.SAVE_TYPE_ERROR);
        }
        return Return.SUCCESS(Code.SUCCESS).put("poemId", poemId);
    }

    private Return saveTypeRef(Integer poemId, Integer poemTypeId) {
        PoemTypeRef poemTypeRef = new PoemTypeRef();
        poemTypeRef.setPoemTypeId(poemTypeId);
        poemTypeRef.setPoemId(poemId);
        poemTypeRef.setCreateDate(new Date());
        boolean retFlag = false;
        try {
            retFlag = poemTypeRefService.add(poemId, poemTypeId);
        } catch (BaseException e) {
            Return.FAIL(Integer.parseInt(e.getMessage()), EnumUtils.getNameByType(Code.class, Integer.parseInt(e.getMessage())));
        }
        if (!retFlag){
            return Return.SUCCESS(Code.SUCCESS);
        }else{
            return Return.FAIL(Code.SAVE_TYPE_REF_ERROR);
        }
    }

    private Return savePoet(PoemCrawl poemCrawl, Integer dynastyId) {
        //保存诗人并返回Id， 如果存在则直接返回id；
        Poet poet = new Poet();
        poet.setDynastyId(dynastyId);
        poet.setName(poemCrawl.getZuozhe());
        poet.setCreateDate(new Date());
        Integer poetId = null;
        try {
            poetId = poetService.add(poet);
        } catch (BaseException e) {
            Return.FAIL(Integer.parseInt(e.getMessage()), EnumUtils.getNameByType(Code.class, Integer.parseInt(e.getMessage())));
        }
        if (poetId == null) {
            Return.FAIL(Code.SAVE_TYPE_ERROR);
        }
        return Return.SUCCESS(Code.SUCCESS).put("poetId", poetId);
    }

    private Return savePoemDetail(PoemCrawl poemCrawl, Integer poemId) {
        //保存诗歌并返回Id， 如果存在则直接返回id；
        List<PoemDetailCrawl> detailList = poemCrawl.getDetailList();
        PoemDetail poemDetail = null;
        for (PoemDetailCrawl poemDetailCrawl : detailList) {
            new PoemDetail();
            poemDetail.setPoemId(poemId);
            poemDetail.setContent(poemDetailCrawl.getDetail());
            poemDetail.setReference(poemDetailCrawl.getCankao());
            poemDetail.setSort(poemDetailCrawl.getIndex());
            poemDetail.setType(poemDetailCrawl.getType());
            try {
                poemDetailService.save(poemDetail);
            } catch (BaseException e) {
                Return.FAIL(Integer.parseInt(e.getMessage()), EnumUtils.getNameByType(Code.class, Integer.parseInt(e.getMessage())));
            }
        }
        return Return.SUCCESS(Code.SUCCESS);
    }

    private Return savePoetDetail(PoemCrawl poemCrawl, Integer poetId) {
        //保存诗歌并返回Id， 如果存在则直接返回id；
        List<PoetCrawlDetail> poetCrawlDetails = poemCrawl.getPoetCrawl().getPoetCrawlDetails();
        PoetDetail poetDetail = null;
        for (PoetCrawlDetail poetDetailCrawl : poetCrawlDetails) {
            new PoetDetail();
            poetDetail.setType(poetDetailCrawl.getType());
            poetDetail.setContent(poetDetailCrawl.getContent());
            poetDetail.setReference(poetDetailCrawl.getCankao());
            poetDetail.setSort(poetDetailCrawl.getSort());
            try {
                poetDetailService.save(poetDetail);
            } catch (BaseException e) {
                Return.FAIL(Integer.parseInt(e.getMessage()), EnumUtils.getNameByType(Code.class, Integer.parseInt(e.getMessage())));
            }
        }
        return Return.SUCCESS(Code.SUCCESS);
    }
}
