package com.worldtech.indexlistdemo.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.worldtech.indexlistdemo.R;
import com.worldtech.indexlistdemo.model.IndexDataModel;

import java.util.ArrayList;

/**
 * 首页数据Adapter
 */

public class IndexDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int SHOW_BANNER_AREA_TYPE = 0;  //banner区域
    private static final int SHOW_STYLE_TYPE_1 = 1;   //列表+横向平铺
    private static final int SHOW_STYLE_TYPE_2 = 2;   //纯列表
    private static final int SHOW_STYLE_TYPE_3 = 3;   //大图左右滑动
    private static final int SHOW_STYLE_TYPE_4 = 4;   //推荐书单列表
    private static final int SHOW_BOTTOM_AREA_TYPE = 5;   //底部到底了提示
    private Activity mContext;
    private ArrayList<IndexDataModel.DataBeanX.BannersBean> bannersBeans = new ArrayList<>();
    private ArrayList<IndexDataModel.DataBeanX.RecmdsBean> recmdsBeans = new ArrayList<>();
    private ArrayList<IndexDataModel.DataBeanX.NovelLibraryBean> novelLibraryBeans = new ArrayList<>();

    public IndexDataAdapter(Activity context){
        mContext = context;
    }

    public void updateShowTypeData(IndexDataModel.DataBeanX dataBeanX){
        this.bannersBeans = dataBeanX.banners;
        this.recmdsBeans = dataBeanX.recmds;
        this.novelLibraryBeans = dataBeanX.novel_librarys;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view;
        switch (viewType) {
            case SHOW_BANNER_AREA_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.fragment_index_banner_item, parent, false);
                holder = new ViewTypeBannerHolder(view);
                break;
            case SHOW_STYLE_TYPE_1:
                view = LayoutInflater.from(mContext).inflate(R.layout.fragment_index_list_item_1, parent, false);
                holder = new ViewTypeOneHolder(view);
                break;

            case SHOW_STYLE_TYPE_2:
                view = LayoutInflater.from(mContext).inflate(R.layout.fragment_index_list_item_2, parent, false);
                holder = new ViewTypeTwoHolder(view);
                break;

            case SHOW_STYLE_TYPE_3:
                view = LayoutInflater.from(mContext).inflate(R.layout.fragment_index_list_item_3, parent, false);
                holder = new ViewTypeThreeHolder(view);
                break;

            case SHOW_STYLE_TYPE_4:
                view = LayoutInflater.from(mContext).inflate(R.layout.fragment_index_list_item_4, parent, false);
                holder = new ViewTypeFourHolder(view);
                break;
            case SHOW_BOTTOM_AREA_TYPE:
                view = LayoutInflater.from(mContext).inflate(R.layout.fragment_index_bottom_tip_item, parent, false);
                holder = new ViewTypeBottomHolder(view);
                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewTypeBannerHolder) {
            setBannerAreaData((ViewTypeBannerHolder) holder);
        } else if (holder instanceof ViewTypeOneHolder) {
            setViewTypeOneData((ViewTypeOneHolder) holder, recmdsBeans.get(position - 1).data, recmdsBeans.get(position - 1).name, position);
        } else if (holder instanceof ViewTypeTwoHolder) {
            setViewTypeTwoData((ViewTypeTwoHolder) holder, recmdsBeans.get(position - 1).data, recmdsBeans.get(position - 1).name, position);
        } else if (holder instanceof ViewTypeThreeHolder) {
            setViewTypeThreeData((ViewTypeThreeHolder) holder, recmdsBeans.get(position - 1).data, recmdsBeans.get(position - 1).name, position);
        } else if (holder instanceof ViewTypeFourHolder) {
            setViewTypeFourData((ViewTypeFourHolder) holder, novelLibraryBeans, position);
        }
    }



    /**
     * banner头部数据
     * @param holder
     */
    private void setBannerAreaData(ViewTypeBannerHolder holder) {


    }


    private void setViewTypeOneData(ViewTypeOneHolder holder, ArrayList<IndexDataModel.DataBeanX.RecmdsBean.DataBean> dataBeans, String styleName, int position) {
        IndexDataModel.DataBeanX.RecmdsBean.DataBean firstDataBean = dataBeans.get(0);
        holder.tv_book_name.setText(firstDataBean.title);

        holder.tv_book_desc.setText(firstDataBean.desc);
        holder.tv_author.setText(firstDataBean.author_name);
        holder.tv_style_name.setText(styleName);

        float uv = Float.parseFloat(firstDataBean.length);
        String result = String.format("%.1f",uv/10000);
        holder.tv_word_num.setText((int)Math.ceil(Double.parseDouble(result)) + "万字");

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        holder.rv_book_list.setLayoutManager(manager);
    }

    private void setViewTypeTwoData(ViewTypeTwoHolder holder, ArrayList<IndexDataModel.DataBeanX.RecmdsBean.DataBean> dataBeans, String styleName, int position) {
        holder.tv_style_name.setText(styleName);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(RecyclerView.VERTICAL);
        holder.rv_book_list.setLayoutManager(manager);

    }

    private void setViewTypeThreeData(ViewTypeThreeHolder holder, ArrayList<IndexDataModel.DataBeanX.RecmdsBean.DataBean> dataBeans, String styleName, int position) {
        holder.tv_style_name.setText(styleName);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        holder.rv_book_big_horizontal.setLayoutManager(manager);


    }


    private void setViewTypeFourData(ViewTypeFourHolder holder, ArrayList<IndexDataModel.DataBeanX.NovelLibraryBean> dataBeans, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return SHOW_BANNER_AREA_TYPE;
        }else if(position == getItemCount() - 1){
            return SHOW_BOTTOM_AREA_TYPE;
        } else {
            if(novelLibraryBeans != null && novelLibraryBeans.size() > 0){
                if(position == getItemCount() - 2){
                    return SHOW_STYLE_TYPE_4;
                }else{
                    if(recmdsBeans.get(position - 1).style_type == 1){
                        return SHOW_STYLE_TYPE_1;
                    }else if(recmdsBeans.get(position - 1).style_type == 2){
                        return SHOW_STYLE_TYPE_2;
                    }else if(recmdsBeans.get(position - 1).style_type == 3){
                        return SHOW_STYLE_TYPE_3;
                    }
                }
            }else{
                if(recmdsBeans.get(position - 1).style_type == 1){
                    return SHOW_STYLE_TYPE_1;
                }else if(recmdsBeans.get(position - 1).style_type == 2){
                    return SHOW_STYLE_TYPE_2;
                }else if(recmdsBeans.get(position - 1).style_type == 3){
                    return SHOW_STYLE_TYPE_3;
                }
            }
        }
        return SHOW_BANNER_AREA_TYPE;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (bannersBeans != null && bannersBeans.size() > 0) {
            count++;
        }
        if (recmdsBeans != null && recmdsBeans.size() > 0) {
            count += recmdsBeans.size();
        }
        if(novelLibraryBeans != null && novelLibraryBeans.size() > 0){
            count++;
        }
        count++; //尾部到底了显示
        return count;
    }


    /**
     * banner头部布局
     */
    public class ViewTypeBannerHolder extends RecyclerView.ViewHolder{

        private LinearLayout ll_classification, ll_leaderboard, ll_booklist, ll_end_book, ll_label;

        public ViewTypeBannerHolder(View itemView) {
            super(itemView);
            ll_classification = itemView.findViewById(R.id.ll_classification);
            ll_leaderboard = itemView.findViewById(R.id.ll_leaderboard);
            ll_booklist = itemView.findViewById(R.id.ll_booklist);
            ll_end_book = itemView.findViewById(R.id.ll_end_book);
            ll_label = itemView.findViewById(R.id.ll_label);
        }
    }


    /**
     * 列表布局1： 列表+横向平铺
     */
    public class ViewTypeOneHolder extends RecyclerView.ViewHolder{

        LinearLayout ll_layout_item;
        ImageView iv_book;
        TextView tv_book_name;
        TextView tv_book_desc;
        TextView tv_author;
        TextView tv_book_status;
        TextView tv_word_num;
        RecyclerView rv_book_list;
        TextView tv_style_name;

        public ViewTypeOneHolder(View view) {
            super(view);
            ll_layout_item = view.findViewById(R.id.ll_layout_item);
            iv_book = view.findViewById(R.id.iv_book);
            tv_book_name = view.findViewById(R.id.tv_book_name);
            tv_book_desc = view.findViewById(R.id.tv_book_desc);
            tv_author = view.findViewById(R.id.tv_author);
            tv_book_status = view.findViewById(R.id.tv_book_status);
            tv_word_num = view.findViewById(R.id.tv_word_num);
            rv_book_list = view.findViewById(R.id.rv_book_list);
            tv_style_name = view.findViewById(R.id.tv_style_name);
        }

    }


    /**
     * 列表布局2： 纯列表
     */
    public class ViewTypeTwoHolder extends RecyclerView.ViewHolder{


        RecyclerView rv_book_list;
        TextView tv_style_name;

        public ViewTypeTwoHolder(View view) {
            super(view);
            rv_book_list = view.findViewById(R.id.rv_book_list);
            tv_style_name = view.findViewById(R.id.tv_style_name);
        }

    }

    /**
     * 列表布局3： 大图左右滑动
     */
    public class ViewTypeThreeHolder extends RecyclerView.ViewHolder{

        RecyclerView rv_book_big_horizontal;
        TextView tv_style_name;

        public ViewTypeThreeHolder(View view) {
            super(view);
            rv_book_big_horizontal = view.findViewById(R.id.rv_book_big_horizontal);
            tv_style_name = view.findViewById(R.id.tv_style_name);
        }
    }


    /**
     * 列表布局4： 推荐书单
     */
    public class ViewTypeFourHolder extends RecyclerView.ViewHolder{

        RecyclerView rv_book_list;

        public ViewTypeFourHolder(View view) {
            super(view);
            rv_book_list = view.findViewById(R.id.rv_book_list);
        }

    }


    /**
     * 底部提示
     */
    public class ViewTypeBottomHolder extends RecyclerView.ViewHolder{

        public ViewTypeBottomHolder(View view) {
            super(view);
        }

    }


}
