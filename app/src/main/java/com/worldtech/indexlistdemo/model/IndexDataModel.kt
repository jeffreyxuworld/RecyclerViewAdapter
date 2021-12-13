package com.worldtech.indexlistdemo.model

import java.util.*

class IndexDataModel : BaseModel() {
    var data: DataBeanX? = null

    class DataBeanX {
        var is_show_sign = 0
        @JvmField
        var banners: ArrayList<BannersBean>? = null
        @JvmField
        var recmds: ArrayList<RecmdsBean>? = null
        @JvmField
        var novel_librarys: ArrayList<NovelLibraryBean>? = null

        class NovelLibraryBean {
            var titile: String? = null
            var cover: String? = null
            var novel_libary_id = 0
        }

        class BannersBean {
            var id = 0
            var name: String? = null
            var pic_url //1时为链接，2为小说id，3为书单id，4为帖子id，5为空
                    : String? = null
            var link_url: String? = null
            var position_id = 0
            var redirect_type //1跳转链接，2小说详情页，3书单详情页，4帖子动态详情页，5互动小说
                    = 0
        }

        class RecmdsBean {
            @JvmField
            var style_type = 0
            @JvmField
            var name: String? = null
            var recmd_id = 0
            @JvmField
            var data: ArrayList<DataBean>? = null

            class DataBean {
                var id = 0
                var recmd_id = 0
                var novel_id = 0
                var sort = 0
                var sex = 0
                @JvmField
                var title: String? = null
                var cover: String? = null
                var introduction: String? = null
                var is_end = 0
                @JvmField
                var length: String? = null
                var hot_num: String? = null
                var tag_name: String? = null
                @JvmField
                var author_name: String? = null
                @JvmField
                var desc: String? = null
                var score: String? = null
                var tags: List<TagsBean>? = null

                class TagsBean {
                    var id = 0
                    var parent_id = 0
                    var name: String? = null
                    var order = 0
                    var pivot: PivotBean? = null

                    class PivotBean {
                        var novel_id = 0
                        var tag_id = 0
                    }
                }
            }
        }
    }
}