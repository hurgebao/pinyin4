package com.sinoteif.py.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
 * 字典数据映射VO
 * @author Tinker 2018.07.03
 *
 */


public class DictVO implements Serializable{
    
	private static final long serialVersionUID = 7305532222304667253L;

    /**
     * 主键
     */
    private String id;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 类型描述
     */    
    private String dictTypeDesc;
    /**
     * 码�??
     */   
    private String dictCode;
    /**
     * 码�?�名�?
     */
    private String dictName;
    /**
     * 状�?? 0无效 1有效
     */
    private String status;
    /**
     * 备注
     */
    private String remark;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictTypeDesc() {
        return dictTypeDesc;
    }

    public void setDictTypeDesc(String dictTypeDesc) {
        this.dictTypeDesc = dictTypeDesc;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString(){
    	return JSON.toJSONString(this);
    }
}