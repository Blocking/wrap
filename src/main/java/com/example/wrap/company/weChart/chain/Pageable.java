
package com.example.wrap.company.weChart.chain;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
public class Pageable {

    @SerializedName("first")
    private Boolean mFirst;
    @SerializedName("fromNumber")
    private Long mFromNumber;
    @SerializedName("last")
    private Boolean mLast;
    @SerializedName("number")
    private Long mNumber;
    @SerializedName("numberOfElements")
    private Long mNumberOfElements;
    @SerializedName("size")
    private Long mSize;
    @SerializedName("toNumber")
    private Long mToNumber;
    @SerializedName("totalElements")
    private Long mTotalElements;
    @SerializedName("totalPages")
    private Long mTotalPages;

    public Boolean getFirst() {
        return mFirst;
    }

    public void setFirst(Boolean first) {
        mFirst = first;
    }

    public Long getFromNumber() {
        return mFromNumber;
    }

    public void setFromNumber(Long fromNumber) {
        mFromNumber = fromNumber;
    }

    public Boolean getLast() {
        return mLast;
    }

    public void setLast(Boolean last) {
        mLast = last;
    }

    public Long getNumber() {
        return mNumber;
    }

    public void setNumber(Long number) {
        mNumber = number;
    }

    public Long getNumberOfElements() {
        return mNumberOfElements;
    }

    public void setNumberOfElements(Long numberOfElements) {
        mNumberOfElements = numberOfElements;
    }

    public Long getSize() {
        return mSize;
    }

    public void setSize(Long size) {
        mSize = size;
    }

    public Long getToNumber() {
        return mToNumber;
    }

    public void setToNumber(Long toNumber) {
        mToNumber = toNumber;
    }

    public Long getTotalElements() {
        return mTotalElements;
    }

    public void setTotalElements(Long totalElements) {
        mTotalElements = totalElements;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

}
