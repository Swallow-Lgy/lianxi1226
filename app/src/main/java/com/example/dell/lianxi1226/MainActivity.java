package com.example.dell.lianxi1226;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dell.lianxi1226.adpter.GoodsAdpter;
import com.example.dell.lianxi1226.bean.GoodsBean;
import com.example.dell.lianxi1226.bean.ShopMessages;
import com.example.dell.lianxi1226.green.DaoMaster;
import com.example.dell.lianxi1226.green.DaoSession;
import com.example.dell.lianxi1226.green.DataBeanDao;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
      private RecyclerView recyclerView;
      private String url="http://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1";
      private GoodsAdpter adpter;
      private DataBeanDao mdataBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle);
        adpter = new GoodsAdpter(this);
        initData();
        //点击删除
        adpter.setOnclickLister(new GoodsAdpter.onClick() {
            @Override
            public void onClickListren(int postions) {
                adpter.del(postions);
            }
        });
        //上按跳转
        adpter.setOnLongclickLister(new GoodsAdpter.onLongClick() {
            @Override
            public void onLongClickListren(int postions) {
                Intent intent = new Intent(MainActivity.this,DalitActivity.class);
                intent.putExtra("pid",postions);
                startActivity(intent);
            }
        });
        //数据库
        initDB();
       //判断网络
        isNet();
     }
    //数据库
    private void initDB() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"goodsDB");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        mdataBean = daoSession.getDataBeanDao();
    }
    public void isNet(){
        if (!OkHttpUtil.getmIstance().hasNetWork(this)){
            List<DataBean> mlist = new ArrayList<>();
            QueryBuilder<DataBean> dataBeanQueryBuilder = mdataBean.queryBuilder();
            List<DataBean> list = mdataBean.queryBuilder().list();
            for (int i=0;i<list.size();i++){
                DataBean bean = new DataBean();
                bean.setPid(list.get(i).getPid());
                bean.setImages(list.get(i).getImages());
                bean.setPrice(list.get(i).getPrice());
                bean.setTitle(list.get(i).getTitle());
                mlist.add(bean);
            }
            adpter.setmList(mlist);
        }
    }
    public void initData(){
        EventBus.getDefault().register(this);
        OkHttpUtil.getmIstance().postEqueue(url,new HashMap<String, String>(),GoodsBean.class);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adpter);
    }
     @Subscribe(threadMode = ThreadMode.MAIN)
     public void getData(ShopMessages messages){
        if (messages.getFalg().equals("shop")){
            Object object = messages.getObject();
            GoodsBean bean = (GoodsBean) object;
            adpter.setmList(bean.getData());
            //添加数据
            for (int i=0;i<bean.getData().size();i++){
                long id = bean.getData().get(i).getId();
                String images = bean.getData().get(i).getImages();
                int pid = bean.getData().get(i).getPid();
                double price = bean.getData().get(i).getPrice();
                String title = bean.getData().get(i).getTitle();
                mdataBean.insertOrReplace(new DataBean(i+1,pid,price,images,title));
            }
        }
     }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
