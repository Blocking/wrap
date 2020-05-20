package com.example.wrap.example;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Maps;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class GmsDict {
    private long id;
    private long pid;
    private String name;
    private String value;
    private byte status;
    private int sort;
    private String addUser;
    private String updateUser;
    private Date createTime;
    private Timestamp updateTime;

    public static void main(String[] args) {
        List<GmsDict> dictList = Lists.newArrayList();
        Map<String,Long> dictMap1 = Maps.newHashMap();
        Map<String,GmsDict> dictMap2 = Maps.newHashMap();
        for (GmsDict gmsDict : dictList) {
            dictMap1.put(gmsDict.getValue(),gmsDict.getId());
            dictMap2.put(gmsDict.getPid()+""+gmsDict.getValue(),gmsDict);
        }

    }
}
