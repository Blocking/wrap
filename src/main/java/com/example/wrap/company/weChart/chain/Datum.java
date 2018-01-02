
package com.example.wrap.company.weChart.chain;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
public class Datum {

    @SerializedName("audiences")
    private Long mAudiences;
    @SerializedName("boxoffice")
    private Double mBoxoffice;
    @SerializedName("cinemaChainName")
    private String mCinemaChainName;
    @SerializedName("count")
    private Long mCount;
    @SerializedName("serviceoffice")
    private Double mServiceoffice;
    @SerializedName("sessions")
    private Long mSessions;
    @SerializedName("totaldays")
    private Long mTotaldays;

    public Long getAudiences() {
        return mAudiences;
    }

    public void setAudiences(Long audiences) {
        mAudiences = audiences;
    }

    public Double getBoxoffice() {
        return mBoxoffice;
    }

    public void setBoxoffice(Double boxoffice) {
        mBoxoffice = boxoffice;
    }

    public String getCinemaChainName() {
        return mCinemaChainName;
    }

    public void setCinemaChainName(String cinemaChainName) {
        mCinemaChainName = cinemaChainName;
    }

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public Double getServiceoffice() {
        return mServiceoffice;
    }

    public void setServiceoffice(Double serviceoffice) {
        mServiceoffice = serviceoffice;
    }

    public Long getSessions() {
        return mSessions;
    }

    public void setSessions(Long sessions) {
        mSessions = sessions;
    }

    public Long getTotaldays() {
        return mTotaldays;
    }

    public void setTotaldays(Long totaldays) {
        mTotaldays = totaldays;
    }

}
