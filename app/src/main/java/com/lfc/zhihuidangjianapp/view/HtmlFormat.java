package com.lfc.zhihuidangjianapp.view;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlFormat {
    public static String getNewContent(String htmltext){

        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {

            element.attr("style","width:100%;height:auto;display:block");
        }

        return doc.toString();
    }
}
