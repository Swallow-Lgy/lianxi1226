package com.example.dell.lianxi1226.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.lianxi1226.DataBean;
import com.example.dell.lianxi1226.R;
import com.example.dell.lianxi1226.bean.GoodsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class GoodsAdpter extends RecyclerView.Adapter<GoodsAdpter.ViewHolder> {
    private List<DataBean> mList;
    private Context mContext;

    public GoodsAdpter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<DataBean> list) {
        mList.clear();
        if (list!=null){
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = View.inflate(mContext, R.layout.itemgoods,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String images = mList.get(i).getImages();
        String[] split = images.split("\\|");
        viewHolder.simpleDraweeView.setImageURI(split[0]);
        viewHolder.price.setText(mList.get(i).getPrice()+"");
        viewHolder.title1.setText(mList.get(i).getTitle());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClick!=null){
                    mOnClick.onClickListren(i);
                }
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnLongClick!=null){
                    mOnLongClick.onLongClickListren(mList.get(i).getPid());
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       public SimpleDraweeView simpleDraweeView;
       public TextView title1,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title1 = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.pice);
            simpleDraweeView = itemView.findViewById(R.id.image);
        }
    }
    //删除
    public void del(int i){
        mList.remove(i);
        notifyDataSetChanged();
    }
    //点击接口
    public onClick mOnClick;
    public void setOnclickLister(onClick onClick){
        mOnClick = onClick;
    }
    public interface onClick{
        void onClickListren(int postions);
    }
    //长按接口
    public onLongClick mOnLongClick;
    public void setOnLongclickLister(onLongClick onLongClick){
        mOnLongClick = onLongClick;
    }
    public interface onLongClick{
        void onLongClickListren(int postions);
    }
}
