package com.example.dell.lianxi1226.bean;

public class ShopMessages {
    private Object object;
    private String falg;

    public ShopMessages(Object object, String falg) {
        this.object = object;
        this.falg = falg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getFalg() {
        return falg;
    }

    public void setFalg(String falg) {
        this.falg = falg;
    }
}
