package com.joseph.api.dto;

public class GoodsDTO extends  BaseDTO {

    private String goodsName;

    private Integer goodsStockNum;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsStockNum() {
        return goodsStockNum;
    }

    public void setGoodsStockNum(Integer goodsStockNum) {
        this.goodsStockNum = goodsStockNum;
    }
}
