package com.example.wrap.cawer;

import com.google.common.io.Files;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;

/**
 * Created by 12232 on 2017/12/21.
 */
@Slf4j
public class Sample {

    public static void main(String[] args) throws IOException {
        byte[] bytes = Request.Get("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=159707508,107597770&fm=173&s=1C935897060025495E2C36F10300D014&w=550&h=366&img.JPEG")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent().asBytes();
        Files.write(bytes,new File("D:\\pic\\123.JPEG"));
    }

    static  final Map<String,String> map = new HashMap<>(16);

    static {
        map.put("&#xe32d;","0");
        map.put("&#xe022;","1");
        map.put("&#xec81;","2");
        map.put("&#xee72;","3");
        map.put("&#xf1a0;","4");
        map.put("&#xf1f0;","5");
        map.put("&#xe8b1;","6");
        map.put("&#xe4aa;","8");
        map.put("&#xe87c;","9");
    }

    @Test
    public void t(){
        final String str = "&#xee72;&#xec81;.&#xe4aa;&#xe8b1;亿";
        String str1 = replace(str);
        System.out.println(str1);
    }

    private String replace( String str) {
        for (String key :map.keySet()){
            str =  str.replaceAll(key,map.get(key));
        }
        return  str;
    }

    @Test
    public void sample1() throws IOException {
        Request.Get("http://piaofang.maoyan.com/?ver=normal")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .setHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1")
                .execute().saveContent(new File("D:\\pic\\result.dump"));
        File file = new File("D:\\\\pic\\\\result.dump");

        Document doc = Jsoup.parse(file,"utf8");

        Elements elements = doc.select("#ticket_tbody ul");
        elements.stream().forEach(ul->{
            String filmName = ul.select(".c1 b").text();
            System.out.print(filmName);
            ul.select("i").forEach(p->{
                String str = replace(p.text());
                System.out.print("::"+p.text());
            });
            System.out.println();
        });

    }

    @Test
    public void baidu() throws IOException {
        String html = Request.Get("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=%E6%9E%81%E9%81%93%E5%A4%A9%E9%AD%94&oq=12%2526lt%253B&rsv_pq=a03553f80004b853&rsv_t=b213jVngsnHtt2DD6AGlQikDu5t4zezlVLZ0jaig4YWsavpIEtoGYkAPvjE&rqlang=cn&rsv_enter=0&inputT=370&rsv_sug3=24&bs=123")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .setHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1")
                .execute().returnContent().asString();//.saveContent(new File("D:\\pic\\resultB.dump"));
        this.parseBaiduHtml(html);
    }

    public void parseBaiduHtml(String  html ) throws IOException {
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("#content_left");
        List<FictionSearchFromBaidu> fromBaiduList = new ArrayList<>();
        elements.stream().forEach(element -> {
            fromBaiduList.addAll(element.select("h3 a").stream().map(convert1).collect(toList()));
        });
        fromBaiduList.stream().forEach(p->log.info("【{}】的url链接为[{}]",p.getTitle(),p.getUrl()));
    }

    Function<Element,FictionSearchFromBaidu> convert1 = element -> new FictionSearchFromBaidu(element.text(),element.absUrl("href"));

    @Data
    class FictionSearchFromBaidu{
        String title;
        String url;

        public FictionSearchFromBaidu(String text, String href) {
            this.title = text;
            this.url = href;
        }
    }

    @Test
    public void parseDingDianSiteHtml() throws IOException {
        Document doc = Jsoup.connect("http://www.baidu.com/link?url=iCM1EbZNNcJAH2E5PrI_BR-v9y4KjGfoCi1y74w8adN6d3U-pDkRqkHwa2gRB8j-").get();
        Elements elements = doc.select(".cat_box dd a");
        List<FictionSearchFromBaidu> list = elements.stream().map(convert1).collect(toList());
        String url = list.get(0).getUrl();
        Document article = Jsoup.connect(url).get();
        article.select(".entry p").stream().forEach(element -> {
            System.out.println(element.text());
        });
    }

    public final static String SITE_BAIDU_SEARCH = "http://zhannei.baidu.com/cse/search";

    /**
     * 笔趣阁 百度站内搜索
     * 笔趣阁 百度站码：5256649918672294880
     */
    @Test
    public void biqugeSearch() throws UnsupportedEncodingException {
        String result = URLDecoder.decode("%D4%AA%D7%F0","gbk");
        System.out.println(result);
    }

}
