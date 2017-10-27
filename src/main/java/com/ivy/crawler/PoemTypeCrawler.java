package com.ivy.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ivy on 2017/10/11.
 */
public class PoemTypeCrawler {

    public static void poemTypeCrawlerHandler(){
        String url = "http://so.gushiwen.org/shiwen/tags.aspx";
        String dir = "~/temp";
        Crawler crawler = new Crawler();
        Document document = crawler.getHtmlTextByUrl(url);
        Elements sright = document.getElementsByClass( "bookcont");

        Iterator<Element> iterator =  sright.iterator();
        while (iterator.hasNext()){
            Element next = iterator.next();
            List<Node> nodes = next.childNodes();
            for (Node node:nodes){
                List<Node> nodes1 = node.childNodes();
                if (nodes1 == null){
                    continue;
                }else {
                    for (Node aTag : nodes1) {
                        String href = aTag.attr("href");
                        String type = ((Element) aTag).text();
                        System.out.println(type +"   "+href);
                        poemType1CrawlerHandler(type,href);
                        if (type.equals("深秋")){
                            System.out.println("===================完成 深秋==================");
                        }
                    }
                }
            }
        }
    }
    public static void poemType1CrawlerHandler(String type , String url){
        String dir = "~/temp";
        Crawler crawler = new Crawler();
        Document type1Document = crawler.getHtmlTextByUrl(url);

        Elements main3 = type1Document.getElementsByClass("main3");
        Elements typecont = main3.get(0).getElementsByClass("left").get(0).getElementsByClass("typecont");
        if (typecont != null && typecont.size()>0){
            System.out.println("======================"+type );
            Iterator<Element> iterator = typecont.iterator();
            while (iterator.hasNext()){
                Element next = iterator.next();
                String type1 = next.getElementsByTag("strong").text();
                System.out.println("================================"+type1);
                Elements poemNameAtags = next.getElementsByTag("a");
                for (Node poemAtag : poemNameAtags) {
                    String href = poemAtag.attr("href");
                    String poemName = ((Element) poemAtag).text();
                    System.out.println(poemName +"   "+href);

                    //根据链接爬诗歌
                    crawlPoemByHref(href);

                    if (type.equals("深秋")){
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
        while (poemDetail.hasNext()){
            Element element = poemDetail.next();
            if (i==0){
                poem_title = element.text();
            }
            if (i==1){
                 dynastd = element.text();
            }
            if (i==2){
                poet = element.text();
            }
            if (i==3 || i==4 || i==5){

            }
            if (i>5){
                 types = types.concat(element.text()).concat(",");
            }
            i++;
        }
        System.out.println(poem_title+" "+dynastd+" "+poet+" "+types);

    }


    public static boolean crawlPoemByHref(String href){
        String url = "http://so.gushiwen.org/";
        url = url.concat(href);
        Crawler crawler = new Crawler();
        Document document = crawler.getHtmlTextByUrl(url);
        Elements main3 = document.getElementsByClass("main3");
        Elements divs = main3.get(0).getElementsByClass("left");

        int sort = 0;
        for (Element div : divs){
            sort ++;
            String title = null;    //标题
            String chaodai;  //朝代
            String zuozhe;   //作者
            String content;  //正文
            String tag;      //类别
            String yiwen;    //译文
            String zhushi;   //注释
            String shangxi;  //赏析
            String cankao;   //参考
            Map<String,String> qita; //其他

            if (div.attr("class")!= null && div.attr("class").equals("title")){//本诗歌爬取完毕
                System.out.println(title+"爬取完毕");
                break;
            }
            if (div.attr("class")!= null && div.attr("class").equals("sons")) {
                Element son = div;
                if (son.getElementsByClass("yizhu") != null) { //译 注 赏  说明是正文
                    if (son.getElementsByTag("h1").hasText()) {
                        title = son.getElementsByTag("h1").text();
                    }
                    if (son.getElementsByClass("source") != null) {
                        Elements elementsByTag = son.getElementsByClass("source").get(0).getElementsByTag("a");
                        chaodai = elementsByTag.get(0).text();
                        zuozhe = elementsByTag.get(1).text();
                    }
                    if (son.getElementsByClass("contson") != null) {
                        content = son.getElementsByClass("contson").text();
                    }
                    if (son.attr("tag") != null) {
                        tag = son.getElementsByClass("tag").text();
                    }
                } else if (son.getElementsByClass("contyishang") != null && son.getElementsByClass("dingpai")!=null) { //译文及注释 赏析 解析 等等   有dingpai 表示全文
                    if (son.getElementsByTag("h2").hasText()) {
                        String yizhuTitle = son.getElementsByTag("h2").text();
                        if (yizhuTitle != null && yizhuTitle.equals("译文及注释")) {
                            Elements pTags = son.getElementsByTag("p");
                            for (Element p : pTags) {
                                if (p.getElementsByTag("strong") != null && p.getElementsByTag("strong").text() != null) {
                                    if (p.getElementsByTag("strong").text().equals("译文")) {
                                        yiwen = p.text();
                                    }
                                    if (p.getElementsByTag("strong").text().equals("注释")) {
                                        zhushi = p.text();
                                    }
                                }
                                if (p.getElementsByTag("strong").text().equals("注释")) {
                                    zhushi = p.text();
                                }

                            }
                        }else {

                        }

                    }
                    if (son.getElementsByClass("cankao")!=null ){
                        cankao = son.getElementsByClass("cankao").text();
                    }
                }
            }
            if (div.attr("class")!= null && div.attr("class").equals("sonspic")){//作者详情

            }

        }

        return false;

    }

    public static void main(String[] args) {
        PoemTypeCrawler.poemTypeCrawlerHandler();
    }
}
