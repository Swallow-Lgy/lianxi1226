package com.example.dell.lianxi1226;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.lianxi1226.bean.DilaBean;
import com.example.dell.lianxi1226.bean.ShopMessages;
import com.facebook.drawee.view.SimpleDraweeView;
import com.recker.flybanner.FlyBanner;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

public class DalitActivity extends AppCompatActivity {
   private String url="http://www.zhaoapi.cn/product/getProductDetail";
   private FlyBanner flyBanner;
   private Button threelogin;
  private SimpleDraweeView simpleDraweeView;
  private TextView title,price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dalit);
        //获取id
        flyBanner = findViewById(R.id.fly);
        threelogin = findViewById(R.id.addshop);
        simpleDraweeView = findViewById(R.id.head);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        EventBus.getDefault().register(this);
        //获取数据
        initData();
        //第三方登录
        login();

    }
    //获取数据
    private void initData() {
        Intent intent = getIntent();
        int pid = intent.getIntExtra("pid", 0);
        HashMap<String,String> map = new HashMap<>();
        map.put("pid",pid+"");
        OkHttpUtil.getmIstance().postEqueue(url,map,DilaBean.class);
    }
    //发送消息
     @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(ShopMessages messages){
        if (messages.getFalg().equals("dail")){
            Object object = messages.getObject();
            DilaBean bean = (DilaBean) object;
            String imaes = bean.getData().getImaes();
            String[] split = imaes.split("\\|");
            List<String> list = new ArrayList<>();
            for (int i=0;i<split.length;i++){
                list.add(split[i]);
            }
            flyBanner.setImagesUrl(list);

            title.setText(bean.data.title);
            price.setText(bean.data.price+"");
        }
     }
     //第三方的登录
    public void login(){
        threelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI umShareAPI = UMShareAPI.get(DalitActivity.this);
                umShareAPI.getPlatformInfo(DalitActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {


                        Log.i("lgy",map+"");

                        simpleDraweeView.setImageURI("http://thirdqq.qlogo.cn/qqapp/100424468/3C0995B9C9B0200F6578C8F1BFBD4F9C/100");
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
            }
        });
    }
    //返回数据

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    //反注册
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
