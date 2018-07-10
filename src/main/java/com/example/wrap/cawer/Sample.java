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
import java.util.*;
import java.util.concurrent.*;
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
    String videoUrl = "http://gslb.miaopai.com/stream/BkOTopK30phCw1RW0b7AKF61Uv53bKS4Ia00xw__.mp4?yx=&Expires=1530091521&ssig=%2F40983h3dq&KID=unistore,video";
    ThreadLocal<Integer> index = ThreadLocal.withInitial(() -> 0);
    @Test
    public void sample1() throws IOException, ExecutionException, InterruptedException {
        String [] arrUrl = {"http://gslb.miaopai.com/stream/Wsli4c6mxTrndU8Qh7KVdMKMdx5xCSK1G5ji~A__.mp4?yx=&Expires=1530177848&ssig=PMFimJA%2FFd&KID=unistore,video"
                ,"http://gslb.miaopai.com/stream/pm~sVkpVLh27Ld-y~6X9cB2qpftKvzLAvj70XQ__.mp4?yx=&Expires=1530177848&ssig=h2Dny%2F6bf1&KID=unistore,video"
        ,"http://gslb.miaopai.com/stream/ExMJqVu55YRKFqSNDrXxRdklrkmR9SY0~ZPSaA__.mp4?yx=&Expires=1530177849&ssig=J7xCsNNmtG&KID=unistore,video"
        ,"http://gslb.miaopai.com/stream/VK5TOgO1UuqUVhL2lAfpN5qO0E5zArZuz-Ln5A__.mp4?yx=&refer=weibo_app"};

        String [] newUrl ={"http://gslb.miaopai.com/stream/Mp1tE88wuPQKNDVJ-EscLeFvme3nY2R9s3FFVg__.mp4?yx=&refer=weibo_app",
        "http://gslb.miaopai.com/stream/mvC5oJpdPXWOKq9Jpm4e~Jv-xb-P9ML1K~uP0A__.mp4?yx=&refer=weibo_app",
        "http://gslb.miaopai.com/stream/lT~ERKZt8eaJJ5fBsjCUs-6V2qTwtsTtGnzFDw__.mp4?yx=&refer=weibo_app",
        "http://gslb.miaopai.com/stream/X7kubCWrXnVJVKWeslUQG0e8SUvHr59TUbLKIA__.mp4?yx=&refer=weibo_app"};
        for (int i = 2; i < arrUrl.length; i++) {
            Request.Get(arrUrl[i])
                    .connectTimeout(1000)
                    .socketTimeout(1000)
                    .setHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1")
                    .execute().saveContent(new File("D:\\pic\\oVideo"+i+".mp4"));
        }


    }
    @Test
    public void tt1(){
        float m = 1.55f;
        int im = (int)(m*100);
        System.out.println(im);
    }
    @Test
    public void setConvert1() throws IOException {
        String u = "http://k.sina.cn/article_6337272266_v179bb19ca00100bkuh.html?sinawapsharesource=newsapp";
        String html = Request.Get(u)
                .connectTimeout(1000)
                .socketTimeout(1000)
                .setHeader("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1")
                .execute().returnContent().asString();
        log.info("html:{}",html);
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

    String tempUrl = "https://k.sina.cn/article_6337272266_m179bb19ca03300bel5.html?fromsinago=1&wm=3200_0001&http=fromhttp";
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

    @Test
    public void parsePiaotianSiteHtml() throws IOException {
        Document doc = Jsoup.connect("https://www.piaotian.com/html/9/9011/6190965.html").get();
        Elements elements = doc.select("br");
       elements.stream().forEach(p->{
            System.out.println(p.text());
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
