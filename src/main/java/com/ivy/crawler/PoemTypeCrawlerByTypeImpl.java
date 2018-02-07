package com.ivy.crawler;

import com.ivy.Configurations;
import com.ivy.crawler.bo.PoemCrawl;
import com.ivy.crawler.bo.PoemDetailCrawl;
import com.ivy.crawler.bo.PoetCrawl;
import com.ivy.crawler.save.SavePoemService;
import com.ivy.tool.DownLoadPicture;
import com.ivy.tool.FileTool;
import com.ivy.tool.Return;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by ivy on 2017/10/11.
 */
@Service
public class PoemTypeCrawlerByTypeImpl implements PoemTypeCrawlerByType {

    private static final Logger LOG = Logger.getLogger(PoemTypeCrawlerByTypeImpl.class);

    private static boolean begin = false;


    @Autowired
    private SavePoemService savePoemService;

    //从诗歌右边的类型开始爬取
    public void poemTypeCrawlerHandler(String url,String id) {
        Crawler crawler = new Crawler();
        Document document = crawler.getHtmlTextByUrl(url);
        Elements right = document.getElementsByClass("right");
        Element cont = right.get(1).children().get(0).getElementsByClass("cont").get(0);
        Elements aTags = cont.getElementsByTag("a");
        for (Element aTag : aTags) {
            String href = aTag.attr("href");
            String type = aTag.text();
            System.out.println("********" + type + "   " + href);
            poemType1CrawlerHandler(type, "http://so.gushiwen.org/" + href,id);
            if (type.equals("更多>>")) {
                System.out.println("===================完成 更多>>==================");
            }
        }
    }

    public void poemType1CrawlerHandler(String type, String url,String id) {
        Crawler crawler = new Crawler();
        Document type1Document = crawler.getHtmlTextByUrl(url);
        Elements main3 = type1Document.getElementsByClass("main3");
        Element leftElement = main3.get(0).getElementsByClass("left").get(0);
        //说明还有小分类 比如唐诗三百首
        if (leftElement.getElementsByClass("typecont") != null && leftElement.getElementsByClass("typecont").size() > 0) {
            System.out.println("======================" + type);
            Iterator<Element> iterator = leftElement.getElementsByClass("typecont").iterator();
            while (iterator.hasNext()) {
                Element next = iterator.next();
                String type1 = next.getElementsByTag("strong").text();
                System.out.println("================================" + type1);
                Elements poemNameAtags = next.getElementsByTag("a");
                int i = 0;
                for (Node poemAtag : poemNameAtags) {
                    i++;
                    if (i % 50 == 0) {
                        try {
                            System.out.println("sleep(10)~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String href = poemAtag.attr("href");
                    String poemName = ((Element) poemAtag).text();
                    System.out.println(poemName + "   " + href);

                    if (href.indexOf(id) > 0){
                        begin = true;
                    }
                    if (!begin){
                        continue;
                    }
                    if (poemName.indexOf("今佚")>0){
                        continue;
                    }
                    //根据链接爬诗歌
                    PoemCrawl poemCrawl = null;
                    poemCrawl = crawlPoemByHref(href, type, type1);
                    Return ret = savePoemService.save(poemCrawl);
                    if ((Integer) ret.get("code") != 10200) {
                        LOG.error("**********" + poemCrawl.getTitle() + " 入库错误 " + ret.get_code() + ":" + ret.get_note());
                        FileTool.writeToFile(Configurations.logPath,poemCrawl.getTitle() + " 入库错误 " + ret.get_code() + ":" + ret.get_note());
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(poemCrawl.toJson());
                    if (type.equals("深秋")) {
                        System.out.println("===================完成 深秋==================");
                    }
                }

            }
        }
        // 没有小分类，分页查询
        else if (leftElement.getElementsByClass("pages") != null && leftElement.getElementsByClass("pages").size() > 0) {//分页诗歌爬取
            System.out.println("分页查询类别\"" + type + "\"的诗");
            //获取分页信息
            Integer total = 0;
            Integer index = 0;
            Integer totalPages = 0;
            Elements pages = leftElement.getElementsByClass("pages");
            for (Element pageE : pages) {
                Elements span = pageE.getElementsByTag("span");
                if (span.size() > 0) {
                    index = Integer.parseInt(span.get(0).text());
                    total = Integer.parseInt(span.get(1).text().substring(1, span.get(1).text().length() - 1));
                    totalPages = total / 10 + 1;
                }
            }
            System.out.println("page info :index=" + index + " total=" + total + " totalPages=" + totalPages);
            int cnt_page = 10;
            while (cnt_page < totalPages) {
                cnt_page++;
                String onePageUrl = "http://so.gushiwen.org/type.aspx?p=" + cnt_page + "&t=" + type;
                List<PoemCrawl> poemCrawls = crawlOnePage(onePageUrl, type);
                pringPoemList(poemCrawls, cnt_page);
            }
        }
    }

    /**
     * 每一页的诗歌打印
     */
    public void pringPoemList(List<PoemCrawl> list, Integer pageNum) {
        int cont = 0;
        for (PoemCrawl poemCrawl : list) {
            cont++;
            System.out.println("第" + pageNum + "页,第" + cont + "首" + " " + poemCrawl.getTitle());
            System.out.println(poemCrawl.toJson());
        }
    }

    /**
     * index 第几页
     * totalPage 总共有几页
     */
    public List<PoemCrawl> crawlOnePage(String url, String type) {
        List<PoemCrawl> list = new ArrayList<>();

        Crawler crawler = new Crawler();
        Document type1Document = crawler.getHtmlTextByUrl(url);
        Elements main3 = type1Document.getElementsByClass("main3");
        Element leftElement = main3.get(0).getElementsByClass("left").get(0);
        Elements sons = leftElement.getElementsByClass("sons");//分页查询中的没有收诗的基础部分
        for (Element son : sons) {
            Element element = son.getElementsByClass("cont").get(0).getElementsByTag("p").get(0);// 诗的标题
            String href = element.getElementsByTag("a").get(0).attr("href");
            PoemCrawl poemCrawl = crawlPoemByHref(href, type, null);
            list.add(poemCrawl);
        }
        return list;
    }

    /**
     * 根据url 爬取一首诗
     */
    public PoemCrawl crawlPoemByHref(String href, String type1, String type2) {
        String url = "http://so.gushiwen.org/";
        url = url.concat(href);
        Crawler crawler = new Crawler();
        Document document = crawler.getHtmlTextByUrl(url);
        Elements main3 = document.getElementsByClass("main3");
        Elements sons = main3.get(0).getElementsByClass("left").get(0).children();
        PoemCrawl poemCrawl = new PoemCrawl();
        poemCrawl.setType(type1);
        poemCrawl.setType1(type2);
        int sort = 0;
        for (Element sonsDiv : sons) {
            sort++;
            Map<String, PoemDetailCrawl> otherDetail = new HashMap<String, PoemDetailCrawl>(); //其他
            if (sonsDiv.attr("class") != null && sonsDiv.attr("class").equals("title")) {//本诗歌爬取完毕
                System.out.println(poemCrawl.getTitle() + "爬取完毕");
                break;
            }

            if (sonsDiv.attr("class") != null && sonsDiv.attr("class").equals("sonspic")) {//诗人介绍

                if (sonsDiv.getElementsByClass("cont").get(0).getElementsByClass("divimg").size() > 0) {
                    String poetUrl = sonsDiv.getElementsByClass("cont").get(0).getElementsByClass("divimg").get(0).getElementsByTag("a").get(0).attr("href");
                    //PoetCrawl poet = poet(sonsDiv, poemCrawl);//图片 名字 简介
                    PoetCrawl poet = PoetCrawler.poetCrawl("http://so.gushiwen.org/" + poetUrl, poemCrawl.getChaodai(), poemCrawl.getZuozhe());
                    poemCrawl.setPoetCrawl(poet);
                } else {
                    System.out.println(poemCrawl.getZuozhe() + "无图片");
                }

            }

            if (sonsDiv.attr("class") == null || !"sons".equals(sonsDiv.attr("class"))) {//不需要的内容
                continue;
            }

            Elements sonChildren = sonsDiv.children();
            for (Element children : sonChildren) {
                if (children.attr("class").equals("cont")) {
                    Element element = children.children().get(0);
                    if (element.attr("class").equals("yizhu")) {
                        poemCrawl = poem(sonsDiv, poemCrawl); //诗歌正文
                    }
                }
                if (children.attr("class").equals("contyishang")) {// 诗歌译注赏
                    PoemDetailCrawl otherCrawl = new PoemDetailCrawl();
                    String type;
                    Element h2 = children.getElementsByTag("h2").get(0);
                    type = h2.text();
                    if (type != null && type.indexOf("译文及注释") > -1) {
                        poemCrawl = poemYiZhu(sonsDiv, poemCrawl, sort);
                    } else {
                        poemCrawl = poemOther(sonsDiv, poemCrawl, sort);
                    }
                }
            }
        }
        return poemCrawl;
    }

    // 诗人查看数据库，有就返回id，没有就插入并返回id
    private PoetCrawl poet(Element sonsDiv, PoemCrawl poemCrawl) {
        Elements sonChildren = sonsDiv.children();
        PoetCrawl poetCrawl = new PoetCrawl();
        poetCrawl.setName(poemCrawl.getZuozhe());
        poetCrawl.setDynastyId(0);
        for (Element children : sonChildren) {
            if (children.attr("class").equals("cont")) { //诗人图片，名字，简介
                Elements img = children.getElementsByTag("img");
                if (img.size() > 0) {
                    Element element = img.get(0);
                    String authorUrl = element.parent().getElementsByTag("a").attr("href");
                    poetCrawl.setPoetUrl(authorUrl);  // 诗人详情地址
                    String pictureUrl = element.attr("src");
                    try {
                        poetCrawl.setPicture(DownLoadPicture.downloadPicture(pictureUrl, poemCrawl.getChaodai().concat("_").concat(poemCrawl.getZuozhe())));
                    } catch (Exception e) {
                        LOG.error("诗人图片获取下载转码失败" + poemCrawl.getChaodai().concat("_").concat(poemCrawl.getZuozhe()), e);
                    }
                }
            }
        }
        return poetCrawl;
    }

    private PoemCrawl poem(Element sonsDiv, PoemCrawl poemCrawl) {
        Elements sonChildren = sonsDiv.children();
        for (Element children : sonChildren) {
            if (children.attr("class").equals("cont")) { //标题
                poemCrawl.setTitle(children.getElementsByTag("h1").text());
                Elements elementsByTag = children.getElementsByClass("source").get(0).getElementsByTag("a");
                poemCrawl.setChaodai(elementsByTag.get(0).text()); //朝代
                poemCrawl.setZuozhe(elementsByTag.get(1).text()); //作者
                poemCrawl.setContent(children.getElementsByClass("contson").get(0).text()); //正文
            }
            if (children.attr("class").equals("tag")) { // 标签
                poemCrawl.setTag(children.text());
            }
        }
        return poemCrawl;
    }

    private PoemCrawl poemYiZhu(Element sonsDiv, PoemCrawl poemCrawl, Integer sort) {
        Elements sonChildren = sonsDiv.children();
        if (MatchId.matchId(sonsDiv.attr("id")) && sonsDiv.attr("id").indexOf("quan") < 0) { //赏析或者其他的不是全文的不解析
            return poemCrawl;
        }
        Map<String, PoemDetailCrawl> otherCrawlMap = new HashMap<String, PoemDetailCrawl>();
        for (Element children : sonChildren) {
            PoemDetailCrawl otherCrawl;
            String type;
            if (children.attr("class").equals("contyishang")) { //类型
                Element h2 = children.getElementsByTag("h2").get(0);
                type = h2.text();
                if (type != null && type.indexOf("译文及注释") > -1) {
                    Elements pTags = children.getElementsByTag("p");
                    String yiwen = "";
                    String zhushi = "";
                    boolean yiwenFlag = true;
                    for (Element p : pTags) {
                        sort++;
                        String content = p.text();
                        Element element = null;

                        try {
                            if (p.getElementsByTag("strong") != null && p.getElementsByTag("strong").text() != null && !"".equals(p.getElementsByTag("strong").text())) { //译文 注释
                                if (p.getElementsByTag("strong").text().indexOf("译文") > -1) {
                                    yiwenFlag = true;
                                } else if (p.getElementsByTag("strong").text().indexOf("注释") > -1) {
                                    yiwenFlag = false;
                                }
                            }
                            if (yiwenFlag) {
                                yiwen = yiwen.concat(content.substring(content.indexOf("译文")+2,content.length()));
                            } else {
                                zhushi = zhushi.concat(content.substring(content.indexOf("注释")+2,content.length()));
                            }

                        } catch (Exception e) {
                            System.out.println("译文及注释 解析失败");
                        }
                    }

                    if (yiwen != null) {
                        yiwen = yiwen.replace("▲", "");
                    }

                    if (zhushi != null) {
                        zhushi = zhushi.replace("▲", "");
                    }
                    otherCrawl = new PoemDetailCrawl();
                    otherCrawl.setDetail(yiwen.replaceAll("</br>", "\\n"));
                    otherCrawl.setType("译文");
                    otherCrawl.setIndex(sort);
                    otherCrawlMap.put("译文", otherCrawl);
//                  System.out.println(otherCrawl.toJson());

                    otherCrawl = new PoemDetailCrawl();
                    otherCrawl.setDetail(zhushi.replaceAll("</br>", "\\n"));
                    otherCrawl.setType("注释");
                    otherCrawl.setIndex(sort);
                    otherCrawlMap.put("注释", otherCrawl);
//                  System.out.println(otherCrawl.toJson());

                }

            }
            if (children.attr("class").equals("cankao")) { // 标签
                Elements elementsByTag = children.getElementsByTag("div");
                StringBuffer cankaoContent = new StringBuffer();
                for (Element cankaoItem : elementsByTag) {
                    Elements span = cankaoItem.getElementsByTag("span");
                    if (span.size() > 0) {
                        String concat = span.get(0).text().concat(span.get(1).text());
                        cankaoContent = cankaoContent.append(concat).append("\\n");
                    }
                }
                if (otherCrawlMap.get("译文") != null) {
                    otherCrawlMap.get("译文").setCankao(cankaoContent.toString());
                }
                if (otherCrawlMap.get("注释") != null) {
                    otherCrawlMap.get("注释").setCankao(cankaoContent.toString());
                }
            }


        }
        for (String key : otherCrawlMap.keySet()) {
            poemCrawl.getDetailList().add(otherCrawlMap.get(key));
        }
        return poemCrawl;
    }

    private PoemCrawl poemOther(Element sonsDiv, PoemCrawl poemCrawl, Integer sort) {
        Elements sonChildren = sonsDiv.children();
        if (MatchId.matchId(sonsDiv.attr("id")) && sonsDiv.attr("id").indexOf("quan") < 0) { //赏析或者其他的不是全文的不解析
            return poemCrawl;
        }
        PoemDetailCrawl otherCrawl = new PoemDetailCrawl();
        for (Element children : sonChildren) {
            String type;
            if (children.attr("class").equals("contyishang")) { //类型
                Element h2 = children.getElementsByTag("h2").get(0);
                type = h2.text();
                if (type == null || type.equals("")) {
                    return poemCrawl;
                }
                Elements pTags = children.getElementsByTag("p");
                String content = "";
                for (Element p : pTags) {
                    sort++;
                    content = content.concat(p.text()).concat("\\n");
                }
                if (type != null && type.equals("成语")) {
                    children.removeClass("h2");
                    content = children.getElementsByClass("contyishang").text();
                    content = content.substring(content.indexOf(type), content.length());
                }
                if (content != null) {
                    content = content.replace("▲", "");
                }
                try { //赏析 创作背景 等
                    otherCrawl.setDetail(content.replaceAll("</br>", "\\n"));
                    otherCrawl.setType(type);
                    otherCrawl.setIndex(sort);
                } catch (Exception e) {
                    System.out.println("其他项爬去失败");
                }

            }
            if (children.attr("class").equals("cankao")) { // 标签
                StringBuffer cankaoContent = new StringBuffer();
                Elements cankaoChildren = children.getAllElements();
                for (Element e : cankaoChildren) {
                    if (e.tagName().equals("p")) {
                        cankaoContent.append(e.text());
                    }
                    if (e.tagName().equals("span")) {
                        cankaoContent.append(e.text());
                    }
                    if (e.tagName().equals("div")) {
                        for (Element span : e.getAllElements()) {
                            if (span.tagName().equals("p")) {
                                cankaoContent.append(span.text().replace("站务邮箱：service@gushiwen.org", ""));
                            }
                            if (span.tagName().equals("span")) {
                                cankaoContent.append(span.text());
                            }
                        }
                    }
                }

                otherCrawl.setCankao(cankaoContent.toString());

            }
        }
        poemCrawl.getDetailList().add(otherCrawl);
        return poemCrawl;
    }


}
