package com.example.dell.lianxi1226;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DataBean {

        @Id(autoincrement = true)
        public long id;
        public   int pid;
        public  double price;
        public String images;
        public String title;
        @Generated(hash = 513578872)
        public DataBean(long id, int pid, double price, String images, String title) {
            this.id = id;
            this.pid = pid;
            this.price = price;
            this.images = images;
            this.title = title;
        }
        @Generated(hash = 908697775)
        public DataBean() {
        }
        public long getId() {
            return this.id;
        }
        public void setId(long id) {
            this.id = id;
        }
        public int getPid() {
            return this.pid;
        }
        public void setPid(int pid) {
            this.pid = pid;
        }
        public double getPrice() {
            return this.price;
        }
        public void setPrice(double price) {
            this.price = price;
        }
        public String getImages() {
            return this.images;
        }
        public void setImages(String images) {
            this.images = images;
        }
        public String getTitle() {
            return this.title;
        }
        public void setTitle(String title) {
            this.title = title;
        }

       
}
