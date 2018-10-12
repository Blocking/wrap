package com.example.wrap.cawer;

import com.example.wrap.cawer.bean.CinemaReponse;
import com.example.wrap.cawer.bean.ResultDTO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Create by zhangxy on 2018/10/10 14:46
 */
@Slf4j
public class MaskRequest {

    public static String SESSION = "SESSION=e2633e85-cd68-43dd-8684-badce4ebf577";
//    public static String SESSION = "SESSION=c2117f25-031c-4b00-8f10-a8cb1fe3a629";

    static final Header header = new BasicHeader("Cookie",SESSION);

    static final Path configFilePath = Paths.get("F:\\test\\compensate");

    static final String domin = "http://111.205.151.13:9000";
//    static final String domin = "https://gjdyzjb.cn";

    Gson gson = new Gson();

    @Test
    public void ttt(){
        log.info("123456");
    }

    @Test
    public void report() throws IOException {
        Files.newDirectoryStream(configFilePath)
                .forEach(path -> {
                    final File file = path.toFile();
                    if(file.isDirectory()){
                        try {
                            String cinemaCode = file.getName();
                            String shortName = getCinemaShortNameByCode(cinemaCode);
                            Files.newDirectoryStream(path,path1 -> path1.toString().endsWith(".xls"))
                                    .forEach(path1 -> {
                                        String businessDate = path1.getFileName().toString().split("\\.")[0];
                                        try {
                                            log.info("start=====文件夹名称：[{}] 影院名称[{}],影院编码[{}],文件名称[{}],businessDate[{}]"
                                                    ,file.getName()
                                                    ,shortName,cinemaCode,path1.getFileName(),businessDate);
                                            rawDataReportedManually(file.getName(),path1.toFile(),cinemaCode,shortName,businessDate);
                                            log.info("end=====文件夹名称：[{}] 影院名称[{}],影院编码[{}],文件名称[{}],businessDate[{}]"
                                                    ,file.getName()
                                                    ,shortName,cinemaCode,path1.getFileName(),businessDate);
                                        } catch (IOException e) {
                                            log.error("文件夹名称：[{}] 影院名称[{}],影院编码[{}],文件名称[{}],businessDate[{}]"
                                                    ,file.getName()
                                                    ,shortName,cinemaCode,path1.getFileName(),businessDate
                                                    ,e);
                                        }
                                    });
                        } catch (IOException e) {
                            log.error("文件夹名称：[{}]",file.getName(),e);
                        }
                    }
                });
    }

    /**
     * 原始数据手工上报
     * @throws IOException
     */
    public void rawDataReportedManually(final String folderName,final File file,String cinemaCode ,String shortName,String businessDate) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost post = new HttpPost(domin+"/boms/w/manualReportingTicketDataForCinemas" +
                "/importData/"+cinemaCode+"/"+shortName+"/"+businessDate);

        FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addPart("file", fileBody);

        HttpEntity entity = builder.build();
        post.setEntity(entity);
        post.setHeader(header);
        HttpResponse response = client.execute(post);

        ResponseHandler<String> handler = new BasicResponseHandler();
        String body = handler.handleResponse(response);

        ResultDTO resultDTO = gson.fromJson(body,ResultDTO.class);
        if( "failure".equals(resultDTO.getStatus())){
            log.error("文件夹名称：[{}],影院名称[{}],影院编码[{}],文件名称[{}],营业日期[{}],上传结果:{}",folderName
                    ,shortName,cinemaCode,file.getName(),businessDate,body);
        }
    }

    /**
     * 根据影院编码获取影院名称
     * @param code
     * @return
     * @throws IOException
     */
    public String getCinemaShortNameByCode(String code) throws IOException {
        String json = Request.Get(domin+"/bits/w/cinemas/api/"+code+"/code")
                .setHeader(header)
                .execute().returnContent().asString();
        Gson gson = new Gson();
        CinemaReponse cinemaReponse = gson.fromJson(json, CinemaReponse.class);
        return cinemaReponse.getData().getShortName();
    }


}
