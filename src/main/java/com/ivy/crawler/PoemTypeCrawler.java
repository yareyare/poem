package com.ivy.crawler;

import com.ivy.crawler.bo.PoemCrawl;
import com.ivy.crawler.bo.PoemDetailCrawl;
import com.ivy.crawler.bo.PoetCrawl;
import com.ivy.tool.DownLoadPicture;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Created by ivy on 2017/10/11.
 */
public class PoemTypeCrawler {

    private static final Logger LOG = Logger.getLogger(PoemTypeCrawler.class);

    public static void poemTypeCrawlerHandler() {
        String url = "http://so.gushiwen.org/shiwen/tags.aspx";
        String dir = "~/temp";
        Crawler crawler = new Crawler();
        Document document = crawler.getHtmlTextByUrl(url);
        Elements sright = document.getElementsByClass("bookcont");

        Iterator<Element> iterator = sright.iterator();
        while (iterator.hasNext()) {
            Element next = iterator.next();
            List<Node> nodes = next.childNodes();
            for (Node node : nodes) {
                List<Node> nodes1 = node.childNodes();
                if (nodes1 == null) {
                    continue;
                } else {
                    for (Node aTag : nodes1) {
                        String href = aTag.attr("href");
                        String type = ((Element) aTag).text();
                        System.out.println(type + "   " + href);
                        poemType1CrawlerHandler(type, href);
                        if (type.equals("深秋")) {
                            System.out.println("===================完成 深秋==================");
                        }
                    }
                }
            }
        }
    }

    public static void poemType1CrawlerHandler(String type, String url) {
        String dir = "~/temp";
        Crawler crawler = new Crawler();
        Document type1Document = crawler.getHtmlTextByUrl(url);

        Elements main3 = type1Document.getElementsByClass("main3");
        Elements typecont = main3.get(0).getElementsByClass("left").get(0).getElementsByClass("typecont");
        if (typecont != null && typecont.size() > 0) {
            System.out.println("======================" + type);
            Iterator<Element> iterator = typecont.iterator();
            while (iterator.hasNext()) {
                Element next = iterator.next();
                String type1 = next.getElementsByTag("strong").text();
                System.out.println("================================" + type1);
                Elements poemNameAtags = next.getElementsByTag("a");
                for (Node poemAtag : poemNameAtags) {
                    String href = poemAtag.attr("href");
                    String poemName = ((Element) poemAtag).text();
                    System.out.println(poemName + "   " + href);

                    //根据链接爬诗歌
                    crawlPoemByHref(href);

                    if (type.equals("深秋")) {
                        System.out.println("===================完成 深秋==================");
                    }
                }

            }


        }
        String titletype = main3.get(0).getElementsByClass("left").get(0).getElementsByClass("sons").text();
        Elements elementsByTag = main3.get(0).getElementsByClass("left").get(0).getElementsByClass("sons").get(0).getElementsByTag("a");
        Iterator<Element> poemDetail = elementsByTag.iterator();
        int i = 0;
        String types = "";
        String poem_title = "";
        String dynastd = "";
        String poet = "";
        while (poemDetail.hasNext()) {
            Element element = poemDetail.next();
            if (i == 0) {
                poem_title = element.text();
            }
            if (i == 1) {
                dynastd = element.text();
            }
            if (i == 2) {
                poet = element.text();
            }
            if (i == 3 || i == 4 || i == 5) {

            }
            if (i > 5) {
                types = types.concat(element.text()).concat(",");
            }
            i++;
        }

    }

    public static PoemCrawl crawlPoemByHref(String href) {
        String url = "http://so.gushiwen.org/";
        url = url.concat(href);
        Crawler crawler = new Crawler();
        Document document = crawler.getHtmlTextByUrl(url);
        Elements main3 = document.getElementsByClass("main3");
        Elements sons = main3.get(0).getElementsByClass("left").get(0).children();
        PoemCrawl poemCrawl = new PoemCrawl();
        int sort = 0;
        for (Element sonsDiv : sons) {
            sort++;
            Map<String, PoemDetailCrawl> otherDetail = new HashMap<String, PoemDetailCrawl>(); //其他
            if (sonsDiv.attr("class") != null && sonsDiv.attr("class").equals("title")) {//本诗歌爬取完毕
                System.out.println(poemCrawl.getTitle() + "爬取完毕");
                break;
            }

            if (sonsDiv.attr("class") != null && sonsDiv.attr("class").equals("sonspic")) {//诗人介绍

            }

            if (sonsDiv.attr("class") == null || !"sons".equals(sonsDiv.attr("class"))){//不需要的内容
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
        System.out.println(poemCrawl.toJson());
        return poemCrawl;
    }

    // 查看数据库，有就返回id，没有就插入并返回id
    private static Integer poet(Element sonsDiv, PoemCrawl poemCrawl) {
        Elements sonChildren = sonsDiv.children();
        PoetCrawl poetCrawl = new PoetCrawl();
        poetCrawl.setName(poemCrawl.getZuozhe());
        poetCrawl.setDynastyId(0);
        for (Element children : sonChildren) {
            if (children.attr("class").equals("cont")) { //诗人图片，名字，简介
                Elements img = children.getElementsByTag("img");
                if (img.size()>0){
                    Element element = img.get(0);
                    String pictureUrl = element.attr("src");
                    try {
                        poetCrawl.setPicture(DownLoadPicture.encodeBase64File(pictureUrl,poemCrawl.getChaodai().concat("_").concat(poemCrawl.getZuozhe())));
                    } catch (Exception e) {
                        LOG.error("诗人图片获取下载转码失败"+poemCrawl.getChaodai().concat("_").concat(poemCrawl.getZuozhe()),e);
                    }
                }
                poetCrawl.setName(children.getElementsByTag("h1").text());
            }
        }
        return null;
    }

    private static PoemCrawl poem(Element sonsDiv, PoemCrawl poemCrawl) {
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

    private static PoemCrawl poemYiZhu(Element sonsDiv, PoemCrawl poemCrawl, Integer sort) {
        Elements sonChildren = sonsDiv.children();
        Map<String, PoemDetailCrawl> otherCrawlMap = new HashMap<String, PoemDetailCrawl>();
        for (Element children : sonChildren) {
            PoemDetailCrawl otherCrawl ;
            String type;
            if (children.attr("class").equals("contyishang")) { //类型
                Element h2 = children.getElementsByTag("h2").get(0);
                type = h2.text();
                if (type != null && type.indexOf("译文及注释") > -1) {
                    Elements pTags = children.getElementsByTag("p");
                    for (Element p : pTags) {
                        sort++;
                        String content = p.text();
                        Element element = null;
                        try {
                            element = (Element) p.childNodes().get(0);
                            String type1 = element.text();
                            content = (p.text().indexOf(type1) > -1 && p.text().indexOf(type1) < 2) ? p.text().substring(3, p.text().length()) : p.text();
                            if (p.getElementsByTag("strong") != null && p.getElementsByTag("strong").text() != null && !"".equals(p.getElementsByTag("strong").text())) { //译文 注释
                                if (type1.indexOf("译文") > -1) {
                                    otherCrawl = new PoemDetailCrawl();
                                    otherCrawl.setDetail(content);
                                    otherCrawl.setType(type1);
                                    otherCrawl.setIndex(sort);
                                    otherCrawlMap.put("译文", otherCrawl);
                                    System.out.println(otherCrawl.toJson());
                                } else if (type1.indexOf("注释") > -1) {
                                    otherCrawl = new PoemDetailCrawl();
                                    otherCrawl.setDetail(content);
                                    otherCrawl.setType(type1);
                                    otherCrawl.setIndex(sort);
                                    otherCrawlMap.put("注释", otherCrawl);
                                    System.out.println(otherCrawl.toJson());
                                }
                            } else {
                            }
                        } catch (Exception e) {
                            System.out.println("译文及注释 解析失败");
                        }
                    }

                }

            }
            if (children.attr("class").equals("cankao")) { // 标签
                Elements elementsByTag = children.getElementsByTag("div");
                StringBuffer cankaoContent = new StringBuffer();
                for (Element cankaoItem : elementsByTag) {
                    Elements span = cankaoItem.getElementsByTag("span");
                    if (span.size() > 0) {
                        String concat = span.get(0).text().concat(span.get(1).text());
                        cankaoContent.append(concat).append("<\br>");
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

    private static PoemCrawl poemOther(Element sonsDiv, PoemCrawl poemCrawl, Integer sort) {
        Elements sonChildren = sonsDiv.children();
        if (MatchId.matchId(sonsDiv.attr("id")) && sonsDiv.attr("id").indexOf("quan") < 0) { //赏析或者其他的不是全文的不解析
            return poemCrawl;
        }
        for (Element children : sonChildren) {
            PoemDetailCrawl otherCrawl = new PoemDetailCrawl();
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
                    content = content.concat( p.text());
                }
                try { //赏析 创作背景 等
                    otherCrawl.setDetail(content);
                    otherCrawl.setType(type);
                    otherCrawl.setIndex(sort);
                    poemCrawl.getDetailList().add(otherCrawl);
                } catch (Exception e) {
                    System.out.println("其他项爬去失败");
                }

            }
            if (children.attr("class").equals("cankao")) { // 标签
                Elements elementsByTag = children.getElementsByTag("div");
                StringBuffer cankaoContent = new StringBuffer();
                for (Element cankaoItem : elementsByTag) {
                    Elements span = cankaoItem.getElementsByTag("span");
                    if (span.size() > 0) {
                        String concat = span.get(0).text().concat(span.get(1).text());
                        cankaoContent.append(concat).append("<\br>");
                    }
                }
                otherCrawl.setCankao(cankaoContent.toString());
            }


        }
        return poemCrawl;
    }

    public static void main(String[] args) {
        PoemTypeCrawler.poemTypeCrawlerHandler();
    }
}
