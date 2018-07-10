package com.example.wrap.company.weChart.test;

import com.example.wrap.company.weChart.BigDecimalUtil;
import com.example.wrap.company.weChart.chain.ShortChainName;
import com.example.wrap.company.weChart.test.bean.ChainsBean;
import com.example.wrap.company.weChart.test.bean.CnemasBean;
import com.example.wrap.company.weChart.test.bean.FilmsBean;
import com.example.wrap.company.weChart.test.bean.ProvineBean;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;

/**
 * Created by 12232 on 2018/1/2.
 */
@SuppressWarnings("Duplicates")
@Slf4j
public class GenWeChartHtml {


    @Test
    public void makeHtml() throws IOException {
        int index = 1;

        final StringBuffer html = new StringBuffer();

        html.append("<html>");

        html.append("<br/>");
        //全国各省票房周报
//      final DecimalFormat df = new DecimalFormat("#,###");
        html.append("<table style='border-collapse:collapse;font-size:12px;border:0;'>");
        html.append("<thead>");
        html.append("<tr><caption style='font-size:14px'>2018全国各省票房上半年报告(含服务费)</caption></tr>");
        html.append("<tr><th style='width:8%;background:#06587a;color:#fff;font-weight:normal;border:0;'>排名</th>");
        html.append("<th style='width:20%;background:#06587a;color:#fff;font-weight:normal;border:0;'>省份</th>");
        html.append(
                "<th style='width:25%;background:#06587a;color:#fff;font-weight:normal;border:0;'>总票房<br/>/万元</th>");
        html.append(
                "<th style='width:20%;background:#06587a;color:#fff;font-weight:normal;border:0;'>场次<br/>/万场</th>");
        html.append(
                "<th style='width:20%;background:#06587a;color:#fff;font-weight:normal;border:0;'>人次<br/>/万人</th>");
      /*html.append(
              "<th style='width:14%;background:#06587a;color:#fff;font-weight:normal;border:0;'>柜台售票<br/>占比</th>");
      html.append(
              "<th style='width:14%;background:#06587a;color:#fff;font-weight:normal;border:0;'>网络售票<br/>占比</th>");*/
        html.append("</tr></thead>");
        for (final ProvineBean.DataBean province : getProvince().getData()) {
            String provinceName = null;
            String provinceNameO = province.getProvinceName();
            if (provinceNameO.equals("广西壮族自治区")) {
                provinceName = "广西";
            } else if (provinceNameO.equals("新疆维吾尔自治区")) {
                provinceName = "新疆";
            } else if (provinceNameO.equals("内蒙古自治区")) {
                provinceName = "内蒙古";
            } else if (provinceNameO.equals("宁夏回族自治区")) {
                provinceName = "宁夏";
            } else if (provinceNameO.equals("西藏自治区")) {
                provinceName = "西藏";
            } else {
                provinceName = provinceNameO.substring(0, province.getProvinceName().length() - 1);
            }

            html.append("<tr>");
            html.append("<td style='");
            if (!((index % 2) == 0)) {
                html.append("background:#f6f6f6;");
            }
            html.append("border:0;padding:5px;'>");
            html.append(index);
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;'>");
            html.append(provinceName);
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;text-align: right;'>");
            //html.append(province.getTotalSales());
//          html.append(BigDecimalUtil.percentCompute(province.getTotalSales(), new BigDecimal(1L), 0));
            html.append(BigDecimalUtil.percentCompute(new BigDecimal(province.getTotalBoxoffice()), new BigDecimal(10000L), 2));
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;text-align: right;'>");
            //html.append(province.getTotalSession());
//          html.append(BigDecimalUtil.percentCompute(province.getTotalSession(), new BigDecimal(10000L), 0));
            html.append(BigDecimalUtil.percentCompute(new BigDecimal(province.getTotalSession()), new BigDecimal(10000L), 2));
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;text-align: right;'>");
            //html.append(province.getTotalAudience());
//          html.append(BigDecimalUtil.percentCompute(province.getTotalAudience(), new BigDecimal(10000L), 0));
            html.append(BigDecimalUtil.percentCompute(new BigDecimal(province.getTotalAudience()), new BigDecimal(10000L), 2));
            html.append("</td>");
          /* html.append("<td style='border:0;padding:5px;'>");
          html.append(df.format(province.getLocalDaySalesPer()) + "%");
          html.append("</td>");
          html.append("<td style='border:0;padding:5px;'>");
          html.append(df.format(province.getOnlineDaySalesPer()) + "%");
          html.append("</td>");*/
            html.append("</tr>");
            index++;
        }
        index = 1;

        html.append("</table><br  /><br  />");
        html.append("<table style='border-collapse:collapse;font-size:12px;border:0;'>");
        html.append("<thead><tr> <caption style='font-size:14px'>2018全国院线票房上半年报告(含服务费)</caption></tr>");
        html.append("<tr><th style='width:8%;background:#06587a;color:#fff;font-weight:normal;border:0;'>排名</th>");
        html.append("<th style='width:20%;;background:#06587a;color:#fff;font-weight:normal;border:0;'>院线</th>");
        html.append(
                "<th style='width:25%;background:#06587a;color:#fff;font-weight:normal;border:0;'>总票房<br/>/万元</th>");
        html.append(
                "<th style='width:20%;background:#06587a;color:#fff;font-weight:normal;border:0;'>场次<br/>/万场</th>");
        html.append(
                "<th style='width:20%;background:#06587a;color:#fff;font-weight:normal;border:0;'>人次<br/>/万人</th>");
     /* html.append(
              "<th style='width:14%;background:#06587a;color:#fff;font-weight:normal;border:0;'>柜台售票<br/>占比</th>");
      html.append(
              "<th style='width:14%;background:#06587a;color:#fff;font-weight:normal;border:0;'>网络售票<br/>占比</th>");*/
        html.append("</tr></thead>");
        ShortChainName shortName = new ShortChainName();
        for (final ChainsBean.DataBean chain : getChains().getData()) {
            //查询院线简称
            String cinemaViewName = shortName.shortChainNameMap.get(chain.getCinemaChainName());

            html.append("<tr>");
            html.append("<td style='");
            if (!((index % 2) == 0)) {
                html.append("background:#f6f6f6;");
            }
            html.append("border:0;padding:5px;'>");
            html.append(index);
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;'>");
            html.append(cinemaViewName);
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;text-align: right;'>");
            //html.append(cinemaChain.getTotalSales());
            html.append(BigDecimalUtil.percentCompute(new BigDecimal(chain.getBoxoffice()), new BigDecimal(10000L), 2));
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;text-align: right;'>");
            //html.append(cinemaChain.getTotalSession());
            html.append(BigDecimalUtil.percentCompute(new BigDecimal(chain.getSessions()), new BigDecimal(10000L), 2));
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;text-align: right;'>");
            //html.append(cinemaChain.getTotalAudience());
            html.append(BigDecimalUtil.percentCompute(new BigDecimal(chain.getAudiences()), new BigDecimal(10000L), 2));
            html.append("</td>");
        /*  html.append("<td style='border:0;padding:5px;'>");
          html.append(df.format(cinemaChain.getLocalDaySalesPer()) + "%");
          html.append("</td>");
          html.append("<td style='border:0;padding:5px;'>");
          html.append(df.format(cinemaChain.getOnlineDaySalesPer()) + "%");
          html.append("</td>");*/
            html.append("</tr>");
            index++;
        }
        index = 1;
        html.append("</table><br  /><br  />");

        html.append("<table style='border-collapse:collapse;font-size:12px;border:0;'>");
        html.append("<thead><tr> <caption style='font-size:14px'>2018全国排名前100名影片票房上半年报告(含服务费)</caption></tr>");
        html.append("<tr><th style='width:8%;background:#06587a;color:#fff;font-weight:normal;border:0;'>排名</th>");
        html.append("<th style='width:25%;background:#06587a;color:#fff;font-weight:normal;border:0;'>影片名称</th>");
        html.append(
                "<th style='width:23%;background:#06587a;color:#fff;font-weight:normal;border:0;'>总票房<br/>/万元</th>");
        html.append(
                "<th style='width:22%;background:#06587a;color:#fff;font-weight:normal;border:0;'>场次<br/>/万场</th>");
        html.append(
                "<th style='width:22%;background:#06587a;color:#fff;font-weight:normal;border:0;'>人次<br/>/万人</th>");
   /*   html.append(
              "<th style='width:14%;background:#06587a;color:#fff;font-weight:normal;border:0;'>柜台售票<br/>占比</th>");
      html.append(
              "<th style='width:14%;background:#06587a;color:#fff;font-weight:normal;border:0;'>网络售票<br/>占比</th>");*/
        html.append("</tr></thead>");
        for (final FilmsBean.DataBean film : getFilm().getData()) {
            html.append("<tr>");
            html.append("<td style='");
            if (!((index % 2) == 0)) {
                html.append("background:#f6f6f6;");
            }
            html.append("border:0;padding:5px;'>");
            html.append(index);
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;'>");
            html.append(film.getFilmName());
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;text-align: right;'>");
            //html.append(film.getTotalSales());
            html.append(BigDecimalUtil.percentCompute(new BigDecimal(film.getBoxOffice()), new BigDecimal(10000L), 2));
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;text-align: right;'>");
            //html.append(film.getTotalSession());
            html.append(BigDecimalUtil.percentCompute(new BigDecimal(film.getSessions()), new BigDecimal(10000L), 2));
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;text-align: right;'>");
            //html.append(film.getTotalAudience());
            html.append(BigDecimalUtil.percentCompute(new BigDecimal(film.getAudiences()), new BigDecimal(10000L), 2));
            html.append("</td>");
       /*   html.append("<td style='border:0;padding:5px;'>");
          html.append(df.format(film.getLocalDaySalesPer()) + "%");
          html.append("</td>");
          html.append("<td style='border:0;padding:5px;'>");
          html.append(df.format(film.getOnlineDaySalesPer()) + "%");
          html.append("</td>");*/
            html.append("</tr>");
            index++;
        }
        index = 1;
        html.append("</table><br  /><br  />");
        html.append("<table style='border-collapse:collapse;font-size:12px;border:0;'>");
        html.append("<thead><tr> <caption style='font-size:14px'>2018全国排名前100名影院票房上半年报告(含服务费)</caption></tr>");
        html.append("<tr><th style='width:8%;background:#06587a;color:#fff;font-weight:normal;border:0;'>排名</th>");
        html.append("<th style='width:25%;background:#06587a;color:#fff;font-weight:normal;border:0;'>影院名称</th>");
        html.append(
                "<th style='width:23%;background:#06587a;color:#fff;font-weight:normal;border:0;'>总票房<br/>/万元</th>");
        html.append(
                "<th style='width:22%;background:#06587a;color:#fff;font-weight:normal;border:0;'>场次<br/>/万场</th>");
        html.append(
                "<th style='width:22%;background:#06587a;color:#fff;font-weight:normal;border:0;'>人次<br/>/万人</th>");
    /*  html.append(
              "<th style='width:14%;background:#06587a;color:#fff;font-weight:normal;border:0;'>柜台售票<br/>占比</th>");
      html.append(
              "<th style='width:14%;background:#06587a;color:#fff;font-weight:normal;border:0;'>网络售票<br/>占比</th>");*/
        html.append("</tr></thead>");

        for (final CnemasBean.DataBean cinema : getCinemas().getData()) {
            html.append("<tr>");
            html.append("<td style='");
            if (!((index % 2) == 0)) {
                html.append("background:#f6f6f6;");
            }
            html.append("border:0;padding:5px;'>");
            html.append(index);
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;'>");
            html.append(cinema.getCinemaName());
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;text-align: right;'>");
            html.append(BigDecimalUtil.percentCompute(new BigDecimal(cinema.getBoxOffice()), new BigDecimal(10000L), 2));
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;text-align: right;'>");
            html.append(BigDecimalUtil.percentCompute(new BigDecimal(cinema.getSessions()), new BigDecimal(10000L), 2));
            html.append("</td>");
            html.append("<td style='border:0;padding:5px;text-align: right;'>");
            html.append(BigDecimalUtil.percentCompute(new BigDecimal(cinema.getAudiences()), new BigDecimal(10000L), 2));
            html.append("</td>");
            html.append("</tr>");
            index++;
        }
        html.append("</table><br  /><br  />");
        html.append("</html>");
        log.info("length:{}",html.length());
        log.info("生成周报html" + html.toString());

    }

    private ProvineBean getProvince() throws IOException {
        Resource resource = new ClassPathResource("/jsonData/provinceNew.json");
        Reader reader = new FileReader(resource.getFile());
        Gson gson = new Gson();
        ProvineBean provine =  gson.fromJson(reader,ProvineBean.class);
        return  provine;
    }
    private FilmsBean getFilm() throws IOException {
        Resource resource = new ClassPathResource("/jsonData/filmsNew.json");
        Reader reader = new FileReader(resource.getFile());
        Gson gson = new Gson();
        FilmsBean film =  gson.fromJson(reader,FilmsBean.class);
        return  film;
    }
    private CnemasBean getCinemas() throws IOException {
        Resource resource = new ClassPathResource("/jsonData/cinemasNew.json");
        Reader reader = new FileReader(resource.getFile());
        Gson gson = new Gson();
        CnemasBean cnemasBean =  gson.fromJson(reader,CnemasBean.class);
        return  cnemasBean;
    }
    private ChainsBean getChains() throws IOException {
        Resource resource = new ClassPathResource("/jsonData/chainsNew.json");
        Reader reader = new FileReader(resource.getFile());
        Gson gson = new Gson();
        ChainsBean chainsBean =  gson.fromJson(reader,ChainsBean.class);
        chainsBean.getData().remove(48);
        return  chainsBean;
    }

}
