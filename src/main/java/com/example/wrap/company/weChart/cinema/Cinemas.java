
package com.example.wrap.company.weChart.cinema;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
public class Cinemas {

    @SerializedName("data")
    private List<Datum> mData;
    @SerializedName("pageable")
    private Pageable mPageable;
    @SerializedName("status")
    private String mStatus;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public Pageable getPageable() {
        return mPageable;
    }

    public void setPageable(Pageable pageable) {
        mPageable = pageable;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
