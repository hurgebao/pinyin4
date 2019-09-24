package com.sinoteif.py.vo;

import java.util.Date;

/**
 * Created by admin on 2019/9/23.
 */
public class StockIndex {
    private Integer id;

    private String stockNameFirstLetter;

    private String stockCode;

    private String stockName;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStockNameFirstLetter() {
        return stockNameFirstLetter;
    }

    public void setStockNameFirstLetter(String stockNameFirstLetter) {
        this.stockNameFirstLetter = stockNameFirstLetter;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
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
