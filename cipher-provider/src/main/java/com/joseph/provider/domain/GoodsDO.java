package com.joseph.provider.domain;

import com.sun.xml.internal.stream.events.DTDEvent;

import java.util.Date;

public class GoodsDO {

    private Integer goodsId;

    private String goodsName;

    private Integer goodsStockNum;

    private Date createTime;

    private Date updateTime;

    public Integer getGoodsStockNum() {
        return goodsStockNum;
    }

    public void setGoodsStockNum(Integer goodsStockNum) {
        this.goodsStockNum = goodsStockNum;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
