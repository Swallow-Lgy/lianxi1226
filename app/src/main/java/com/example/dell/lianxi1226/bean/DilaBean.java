package com.example.dell.lianxi1226.bean;

public class DilaBean {
    public String code;
    public String msg;
    public DataBean data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public class  Seller {

    }


    public class DataBean{
        public String images;
        public String title;
        public double price;

        public String getImaes() {
            return images;
        }

        public void setImaes(String imaes) {
            this.images = imaes;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
