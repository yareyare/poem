package com.ivy.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.List;

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
            System.out.println(type +" 子类型");
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


//        Iterator<Element> iterator =  typecont.iterator();
//        while (iterator.hasNext()){
//            Element next = iterator.next();
//            List<Node> nodes = next.childNodes();
//            for (Node node:nodes){
//                List<Node> nodes1 = node.childNodes();
//                if (nodes1 == null){
//                    continue;
//                }else {
//                    for (Node aTag : nodes1) {
//                        String type1 = "";
//                        String divClass = aTag.attr("class");
//                        if (divClass != null && divClass.equals("boolMl")){  //子类型
//                            type1 = ((Element) aTag).text();
//                        }else{
//                            List<Node> nodes2 = node.childNodes();
//                            if (nodes1 == null){
//                                continue;
//                            }else {
//                                for (Node poem_a_tag: nodes1) {
//                                    String poem_href = poem_a_tag.attr("href");
//                                    String poem_title = ((Element) poem_a_tag).text();
//                                    System.out.println(poem_title +"   "+poem_href);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }

    public static void main(String[] args) {
        PoemTypeCrawler.poemTypeCrawlerHandler();
    }
}
