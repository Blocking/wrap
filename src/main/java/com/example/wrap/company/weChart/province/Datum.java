
package com.example.wrap.company.weChart.province;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
public class Datum {

    @SerializedName("audiencePercent")
    private Double mAudiencePercent;
    @SerializedName("boxoffice")
    private Double mBoxoffice;
    @SerializedName("boxofficePercent")
    private Double mBoxofficePercent;
    @SerializedName("monthList")
    private List<Object> mMonthList;
    @SerializedName("provinceName")
    private String mProvinceName;
    @SerializedName("serviceSum")
    private Double mServiceSum;
    @SerializedName("sessionPercent")
    private Double mSessionPercent;
    @SerializedName("totalAudience")
    private Long mTotalAudience;
    @SerializedName("totalBoxoffice")
    private Double mTotalBoxoffice;
    @SerializedName("totalSession")
    private Long mTotalSession;

    public Double getAudiencePercent() {
        return mAudiencePercent;
    }

    public void setAudiencePercent(Double audiencePercent) {
        mAudiencePercent = audiencePercent;
    }

    public Double getBoxoffice() {
        return mBoxoffice;
    }

    public void setBoxoffice(Double boxoffice) {
        mBoxoffice = boxoffice;
    }

    public Double getBoxofficePercent() {
        return mBoxofficePercent;
    }

    public void setBoxofficePercent(Double boxofficePercent) {
        mBoxofficePercent = boxofficePercent;
    }

    public List<Object> getMonthList() {
        return mMonthList;
    }

    public void setMonthList(List<Object> monthList) {
        mMonthList = monthList;
    }

    public String getProvinceName() {
        return mProvinceName;
    }

    public void setProvinceName(String provinceName) {
        mProvinceName = provinceName;
    }

    public Double getServiceSum() {
        return mServiceSum;
    }

    public void setServiceSum(Double serviceSum) {
        mServiceSum = serviceSum;
    }

    public Double getSessionPercent() {
        return mSessionPercent;
    }

    public void setSessionPercent(Double sessionPercent) {
        mSessionPercent = sessionPercent;
    }

    public Long getTotalAudience() {
        return mTotalAudience;
    }

    public void setTotalAudience(Long totalAudience) {
        mTotalAudience = totalAudience;
    }

    public Double getTotalBoxoffice() {
        return mTotalBoxoffice;
    }

    public void setTotalBoxoffice(Double totalBoxoffice) {
        mTotalBoxoffice = totalBoxoffice;
    }

    public Long getTotalSession() {
        return mTotalSession;
    }

    public void setTotalSession(Long totalSession) {
        mTotalSession = totalSession;
    }

}
