<<<<<<< HEAD
package com.ivy.crawler;

import com.ivy.crawler.bo.PoetCrawl;
import com.ivy.crawler.bo.PoetCrawlDetail;
import com.ivy.tool.DownLoadPicture;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by ivy on 2017/11/5.
 */
public class PoetCrawler {

    private static  final  Logger LOG = Logger.getLogger(PoetCrawler.class);

    //根据url 爬取一个诗人的基本信息和详细介绍
    public static PoetCrawl poetCrawl(String url, String chaodai, String name) {
        Crawler crawler = new Crawler();
        Document document = crawler.getHtmlTextByUrl(url);
        Elements main3 = document.getElementsByClass("main3");
        Element leftElement = main3.get(0).getElementsByClass("left").get(0);

        PoetCrawl poet = new PoetCrawl();
        int sort = 0;
        for (Element div : leftElement.children()) {
            sort ++ ;
            if (div.attr("class") != null && div.attr("class").equals("sonspic")) {//基本信息
                Element cont = div.getElementsByClass("cont").get(0);
                Elements img = cont.getElementsByTag("img");
                if (img.size() > 0) {
                    Element element = img.get(0);
                    poet.setPoetUrl(element.attr("src"));  // 诗人详情地址
                    poet.setName(element.attr("alt"));
                    poet.setIntroduce(cont.getElementsByTag("p").text());
                    try {
                        poet.setPicture(DownLoadPicture.downloadPicture(element.attr("src"), chaodai.concat("_").concat(name)));
                    } catch (Exception e) {
                        LOG.error("诗人图片获取下载转码失败" + chaodai.concat("_").concat(name), e);
                    }
                }
            }
            if (div.attr("class") != null && div.attr("class").equals("title")) {//诗人介绍解析完毕
                System.out.println("诗人"+name+"解析完毕");
                return poet;
            }


            if (div.attr("class") != null && div.attr("class").equals("sons")) {//诗人的详细信息
                PoetCrawlDetail poetCrawlDetail = crawlPoetDetail(div, poet, sort);
                if (poetCrawlDetail != null) {
                    poet.getPoetCrawlDetails().add(poetCrawlDetail);
                }
            }
        }
        return poet;
    }


    //解析诗人的基本信息
    public static PoetCrawlDetail crawlPoetDetail(Element div, PoetCrawl poet, Integer sort) {
        Elements sonChildren = div.children();
        if (MatchId.matchId(div.attr("id")) && div.attr("id").indexOf("quan") < 0) { //赏析或者其他的不是全文的不解析
            return null;
        }
        PoetCrawlDetail poetDetail = new PoetCrawlDetail();
        Elements divChildrens = div.children();

        for (Element divChildren: divChildrens) {
            String type = null;
            if (divChildren.attr("class").equals("contyishang")) { //类型
                Element h2 = divChildren.getElementsByTag("h2").get(0);
                type = h2.text();
                if (type == null || type.equals("")) {
                    return null;
                }
                Elements pTags = divChildren.getElementsByTag("p");
                String content = "";
                for (Element p : pTags) {
                    content = content.concat(p.text()).concat("\n");
                }
                if (pTags.size() <=0 ){
                    content = divChildren.text();
                }
                if (content != null && content.length()>0) {
                    content = content.replace("▲", "");
                    content = content.substring(content.indexOf(" ") == -1 ? 0 : content.indexOf(" "),content.length());
                }
                poetDetail.setPoetId(poet.getId());
                poetDetail.setType(type);
                poetDetail.setContent(content);
                poetDetail.setSort(sort);

            }
            if (div.attr("class").equals("cankao")) { // 标签
                StringBuffer cankaoContent = new StringBuffer();
                Elements cankaoChildren = divChildren.getAllElements();
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

                poetDetail.setCankao(cankaoContent.toString());
            }
        }
        return poetDetail;
    }

    public static void main(String[] args) {
        String url = "http://so.gushiwen.org/author_510.aspx";
        String chaodai = "宋代";
        String name = "范仲淹";
        PoetCrawl poetCrawl = poetCrawl(url, chaodai, name);
        System.out.println(poetCrawl.toJson());
    }
}
=======
package com.ivy.crawler;

import com.ivy.crawler.bo.PoetCrawl;
import com.ivy.crawler.bo.PoetCrawlDetail;
import com.ivy.tool.DownLoadPicture;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by ivy on 2017/11/5.
 */
public class PoetCrawler {

    private static  final  Logger LOG = Logger.getLogger(PoetCrawler.class);

    //根据url 爬取一个诗人的基本信息和详细介绍
    public static PoetCrawl poetCrawl(String url, String chaodai, String name) {
        Crawler crawler = new Crawler();
        Document document = crawler.getHtmlTextByUrl(url);
        Elements main3 = document.getElementsByClass("main3");
        Element leftElement = main3.get(0).getElementsByClass("left").get(0);

        PoetCrawl poet = new PoetCrawl();
        int sort = 0;
        for (Element div : leftElement.children()) {
            sort ++ ;
            if (div.attr("class") != null && div.attr("class").equals("sonspic")) {//基本信息
                Element cont = div.getElementsByClass("cont").get(0);
                Elements img = cont.getElementsByTag("img");
                if (img.size() > 0) {
                    Element element = img.get(0);
                    poet.setPoetUrl(element.attr("src"));  // 诗人详情地址
                    poet.setName(element.attr("alt"));
                    poet.setIntroduce(cont.getElementsByTag("p").text());
                    try {
                        poet.setPicture(DownLoadPicture.downloadPicture(element.attr("src"), chaodai.concat("_").concat(name)));
                    } catch (Exception e) {
                        LOG.error("诗人图片获取下载转码失败" + chaodai.concat("_").concat(name), e);
                    }
                }
            }
            if (div.attr("class") != null && div.attr("class").equals("title")) {//诗人介绍解析完毕
                System.out.println("诗人"+name+"解析完毕");
                return poet;
            }


            if (div.attr("class") != null && div.attr("class").equals("sons")) {//诗人的详细信息
                PoetCrawlDetail poetCrawlDetail = crawlPoetDetail(div, poet, sort);
                if (poetCrawlDetail != null) {
                    poet.getPoetCrawlDetails().add(poetCrawlDetail);
                }
            }
        }
        return poet;
    }


    //解析诗人的基本信息
    public static PoetCrawlDetail crawlPoetDetail(Element div, PoetCrawl poet, Integer sort) {
        Elements sonChildren = div.children();
        if (MatchId.matchId(div.attr("id")) && div.attr("id").indexOf("quan") < 0) { //赏析或者其他的不是全文的不解析
            return null;
        }
        PoetCrawlDetail poetDetail = new PoetCrawlDetail();
        Elements divChildrens = div.children();

        for (Element divChildren: divChildrens) {
            String type = null;
            if (divChildren.attr("class").equals("contyishang")) { //类型
                Element h2 = divChildren.getElementsByTag("h2").get(0);
                type = h2.text();
                if (type == null || type.equals("")) {
                    return null;
                }
                Elements pTags = divChildren.getElementsByTag("p");
                String content = "";
                for (Element p : pTags) {
                    content = content.concat(p.text()).concat("\n");
                }
                if (pTags.size() <=0 ){
                    content = divChildren.text();
                }
                if (content != null && content.length()>0) {
                    content = content.replace("▲", "");
                    content = content.substring(content.indexOf(" ") == -1 ? 0 : content.indexOf(" "),content.length());
                }
                poetDetail.setPoetId(poet.getId());
                poetDetail.setType(type);
                poetDetail.setContent(content);
                poetDetail.setSort(sort);

            }
            if (div.attr("class").equals("cankao")) { // 标签
                StringBuffer cankaoContent = new StringBuffer();
                Elements cankaoChildren = divChildren.getAllElements();
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

                poetDetail.setCankao(cankaoContent.toString());
            }
        }
        return poetDetail;
    }

    public static void main(String[] args) {
        String url = "http://so.gushiwen.org/author_510.aspx";
        String chaodai = "宋代";
        String name = "范仲淹";
        PoetCrawl poetCrawl = poetCrawl(url, chaodai, name);
        System.out.println(poetCrawl.toJson());
    }
}
>>>>>>> 89d25ae277b5268f2f17e3118aea6fb035093629
