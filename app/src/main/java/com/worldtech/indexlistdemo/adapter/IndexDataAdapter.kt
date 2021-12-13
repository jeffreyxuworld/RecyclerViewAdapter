package com.worldtech.indexlistdemo.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.worldtech.indexlistdemo.R
import com.worldtech.indexlistdemo.model.IndexDataModel.DataBeanX
import com.worldtech.indexlistdemo.model.IndexDataModel.DataBeanX.*
import com.worldtech.indexlistdemo.model.IndexDataModel.DataBeanX.RecmdsBean.DataBean
import java.util.*
import kotlin.math.ceil

/**
 * 首页数据Adapter
 */
class IndexDataAdapter(private val mContext: Activity) : RecyclerView.Adapter<ViewHolder?>() {
    private var bannersBeans: ArrayList<BannersBean>? = ArrayList()
    private var recmdsBeans: ArrayList<RecmdsBean>? = ArrayList()
    private var novelLibraryBeans: ArrayList<NovelLibraryBean>? = ArrayList()
    fun updateShowTypeData(dataBeanX: DataBeanX) {
        bannersBeans = dataBeanX.banners
        recmdsBeans = dataBeanX.recmds
        novelLibraryBeans = dataBeanX.novel_librarys
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var holder: ViewHolder? = null
        val view: View
        when (viewType) {
            SHOW_BANNER_AREA_TYPE -> {
                view = LayoutInflater.from(mContext)
                    .inflate(R.layout.fragment_index_banner_item, parent, false)
                holder = ViewTypeBannerHolder(view)
            }
            SHOW_STYLE_TYPE_1 -> {
                view = LayoutInflater.from(mContext)
                    .inflate(R.layout.fragment_index_list_item_1, parent, false)
                holder = ViewTypeOneHolder(view)
            }
            SHOW_STYLE_TYPE_2 -> {
                view = LayoutInflater.from(mContext)
                    .inflate(R.layout.fragment_index_list_item_2, parent, false)
                holder = ViewTypeTwoHolder(view)
            }
            SHOW_STYLE_TYPE_3 -> {
                view = LayoutInflater.from(mContext)
                    .inflate(R.layout.fragment_index_list_item_3, parent, false)
                holder = ViewTypeThreeHolder(view)
            }
            SHOW_STYLE_TYPE_4 -> {
                view = LayoutInflater.from(mContext)
                    .inflate(R.layout.fragment_index_list_item_4, parent, false)
                holder = ViewTypeFourHolder(view)
            }
            SHOW_BOTTOM_AREA_TYPE -> {
                view = LayoutInflater.from(mContext)
                    .inflate(R.layout.fragment_index_bottom_tip_item, parent, false)
                holder = ViewTypeBottomHolder(view)
            }
        }
        return holder!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ViewTypeBannerHolder -> {
                setBannerAreaData(holder)
            }
            is ViewTypeOneHolder -> {
                setViewTypeOneData(
                    holder,
                    recmdsBeans!![position - 1].data,
                    recmdsBeans!![position - 1].name,
                    position
                )
            }
            is ViewTypeTwoHolder -> {
                setViewTypeTwoData(
                    holder,
                    recmdsBeans!![position - 1].data,
                    recmdsBeans!![position - 1].name,
                    position
                )
            }
            is ViewTypeThreeHolder -> {
                setViewTypeThreeData(
                    holder,
                    recmdsBeans!![position - 1].data,
                    recmdsBeans!![position - 1].name,
                    position
                )
            }
            is ViewTypeFourHolder -> {
                setViewTypeFourData(holder, novelLibraryBeans, position)
            }
        }
    }

    /**
     * banner头部数据
     * @param holder
     */
    private fun setBannerAreaData(holder: ViewTypeBannerHolder) {}
    private fun setViewTypeOneData(
        holder: ViewTypeOneHolder,
        dataBeans: ArrayList<DataBean>?,
        styleName: String?,
        position: Int
    ) {
        val firstDataBean = dataBeans!![0]
        holder.tv_book_name.text = firstDataBean.title
        holder.tv_book_desc.text = firstDataBean.desc
        holder.tv_author.text = firstDataBean.author_name
        holder.tv_style_name.text = styleName
        val uv = firstDataBean.length!!.toFloat()
        val result = String.format("%.1f", uv / 10000)
        holder.tv_word_num.text = ceil(result.toDouble()).toString() + "万字"
        val manager = LinearLayoutManager(mContext)
        manager.orientation = RecyclerView.HORIZONTAL
        holder.rv_book_list.layoutManager = manager
    }

    private fun setViewTypeTwoData(
        holder: ViewTypeTwoHolder,
        dataBeans: ArrayList<DataBean>?,
        styleName: String?,
        position: Int
    ) {
        holder.tv_style_name.text = styleName
        val manager = LinearLayoutManager(mContext)
        manager.orientation = RecyclerView.VERTICAL
        holder.rv_book_list.layoutManager = manager
    }

    private fun setViewTypeThreeData(
        holder: ViewTypeThreeHolder,
        dataBeans: ArrayList<DataBean>?,
        styleName: String?,
        position: Int
    ) {
        holder.tv_style_name.text = styleName
        val manager = LinearLayoutManager(mContext)
        manager.orientation = RecyclerView.HORIZONTAL
        holder.rv_book_big_horizontal.layoutManager = manager
    }

    private fun setViewTypeFourData(
        holder: ViewTypeFourHolder,
        dataBeans: ArrayList<NovelLibraryBean>?,
        position: Int
    ) {
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return SHOW_BANNER_AREA_TYPE
        } else if (position == itemCount - 1) {
            return SHOW_BOTTOM_AREA_TYPE
        } else {
            if (novelLibraryBeans != null && novelLibraryBeans!!.size > 0) {
                if (position == itemCount - 2) {
                    return SHOW_STYLE_TYPE_4
                } else {
                    if (recmdsBeans!![position - 1].style_type == 1) {
                        return SHOW_STYLE_TYPE_1
                    } else if (recmdsBeans!![position - 1].style_type == 2) {
                        return SHOW_STYLE_TYPE_2
                    } else if (recmdsBeans!![position - 1].style_type == 3) {
                        return SHOW_STYLE_TYPE_3
                    }
                }
            } else {
                if (recmdsBeans!![position - 1].style_type == 1) {
                    return SHOW_STYLE_TYPE_1
                } else if (recmdsBeans!![position - 1].style_type == 2) {
                    return SHOW_STYLE_TYPE_2
                } else if (recmdsBeans!![position - 1].style_type == 3) {
                    return SHOW_STYLE_TYPE_3
                }
            }
        }
        return SHOW_BANNER_AREA_TYPE
    }

    override fun getItemCount(): Int {
        var count = 0
        if (bannersBeans != null && bannersBeans!!.size > 0) {
            count++
        }
        if (recmdsBeans != null && recmdsBeans!!.size > 0) {
            count += recmdsBeans!!.size
        }
        if (novelLibraryBeans != null && novelLibraryBeans!!.size > 0) {
            count++
        }
        count++ //尾部到底了显示
        return count
    }

    /**
     * banner头部布局
     */
    inner class ViewTypeBannerHolder(itemView: View) : ViewHolder(itemView) {
        private val ll_classification: LinearLayout
        private val ll_leaderboard: LinearLayout
        private val ll_booklist: LinearLayout
        private val ll_end_book: LinearLayout
        private val ll_label: LinearLayout

        init {
            ll_classification = itemView.findViewById(R.id.ll_classification)
            ll_leaderboard = itemView.findViewById(R.id.ll_leaderboard)
            ll_booklist = itemView.findViewById(R.id.ll_booklist)
            ll_end_book = itemView.findViewById(R.id.ll_end_book)
            ll_label = itemView.findViewById(R.id.ll_label)
        }
    }

    /**
     * 列表布局1： 列表+横向平铺
     */
    inner class ViewTypeOneHolder(view: View) : ViewHolder(view) {
        var ll_layout_item: LinearLayout
        var iv_book: ImageView
        var tv_book_name: TextView
        var tv_book_desc: TextView
        var tv_author: TextView
        var tv_book_status: TextView
        var tv_word_num: TextView
        var rv_book_list: RecyclerView
        var tv_style_name: TextView

        init {
            ll_layout_item = view.findViewById(R.id.ll_layout_item)
            iv_book = view.findViewById(R.id.iv_book)
            tv_book_name = view.findViewById(R.id.tv_book_name)
            tv_book_desc = view.findViewById(R.id.tv_book_desc)
            tv_author = view.findViewById(R.id.tv_author)
            tv_book_status = view.findViewById(R.id.tv_book_status)
            tv_word_num = view.findViewById(R.id.tv_word_num)
            rv_book_list = view.findViewById(R.id.rv_book_list)
            tv_style_name = view.findViewById(R.id.tv_style_name)
        }
    }

    /**
     * 列表布局2： 纯列表
     */
    inner class ViewTypeTwoHolder(view: View) : ViewHolder(view) {
        var rv_book_list: RecyclerView
        var tv_style_name: TextView

        init {
            rv_book_list = view.findViewById(R.id.rv_book_list)
            tv_style_name = view.findViewById(R.id.tv_style_name)
        }
    }

    /**
     * 列表布局3： 大图左右滑动
     */
    inner class ViewTypeThreeHolder(view: View) : ViewHolder(view) {
        var rv_book_big_horizontal: RecyclerView
        var tv_style_name: TextView

        init {
            rv_book_big_horizontal = view.findViewById(R.id.rv_book_big_horizontal)
            tv_style_name = view.findViewById(R.id.tv_style_name)
        }
    }

    /**
     * 列表布局4： 推荐书单
     */
    inner class ViewTypeFourHolder(view: View) : ViewHolder(view) {
        var rv_book_list: RecyclerView

        init {
            rv_book_list = view.findViewById(R.id.rv_book_list)
        }
    }

    /**
     * 底部提示
     */
    inner class ViewTypeBottomHolder(view: View?) : ViewHolder(view!!)
    companion object {
        private const val SHOW_BANNER_AREA_TYPE = 0 //banner区域
        private const val SHOW_STYLE_TYPE_1 = 1 //列表+横向平铺
        private const val SHOW_STYLE_TYPE_2 = 2 //纯列表
        private const val SHOW_STYLE_TYPE_3 = 3 //大图左右滑动
        private const val SHOW_STYLE_TYPE_4 = 4 //推荐书单列表
        private const val SHOW_BOTTOM_AREA_TYPE = 5 //底部到底了提示
    }


}