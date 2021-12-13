package com.worldtech.indexlistdemo.model;

import java.util.ArrayList;
import java.util.List;

public class IndexDataModel extends BaseModel {


    public DataBeanX data;

    public static class DataBeanX {

        public int is_show_sign;
        public ArrayList<BannersBean> banners;
        public ArrayList<RecmdsBean> recmds;
        public ArrayList<NovelLibraryBean> novel_librarys;

        public static class NovelLibraryBean {
            public String titile;
            public String cover;
            public int novel_libary_id;
        }

        public static class BannersBean {
            public int id;
            public String name;
            public String pic_url; //1时为链接，2为小说id，3为书单id，4为帖子id，5为空
            public String link_url;
            public int position_id;
            public int redirect_type;   //1跳转链接，2小说详情页，3书单详情页，4帖子动态详情页，5互动小说
        }

        public static class RecmdsBean {
            public int style_type;
            public String name;
            public int recmd_id;
            public ArrayList<DataBean> data;

            public static class DataBean {
                public int id;
                public int recmd_id;
                public int novel_id;
                public int sort;
                public int sex;
                public String title;
                public String cover;
                public String introduction;
                public int is_end;
                public String length;
                public String hot_num;
                public String tag_name;
                public String author_name;
                public String desc;
                public String score;
                public List<TagsBean> tags;

                public static class TagsBean {

                    public int id;
                    public int parent_id;
                    public String name;
                    public int order;
                    public PivotBean pivot;

                    public static class PivotBean {

                        public int novel_id;
                        public int tag_id;
                    }
                }
            }
        }
    }
}
