package com.example.dell.lianxi1226.bean;

import com.example.dell.lianxi1226.DataBean;

import java.util.List;

public class GoodsBean {
    public String code;
    public List<DataBean> data;
    public String msg;
    public String page;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

}
