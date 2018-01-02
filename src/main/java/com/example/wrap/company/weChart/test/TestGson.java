package com.example.wrap.company.weChart.test;

import com.example.wrap.company.weChart.JsonStr;
import com.example.wrap.company.weChart.test.bean.ProvineBean;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by 12232 on 2018/1/2.
 */
public class TestGson {

    @Test
    public void parse() throws IOException {
        Resource resource = new ClassPathResource("/jsonData/province.json");
        Reader reader = new FileReader(resource.getFile());
        Gson gson = new Gson();
        ProvineBean provine =  gson.fromJson(reader,ProvineBean.class);
        System.out.println(provine.getStatus());
        provine.getData().forEach(dataBean -> {
            System.out.println(dataBean.getTotalBoxoffice());
        });

        System.out.println(JsonStr.chains);

    }

}
