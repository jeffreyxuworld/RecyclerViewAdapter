# IndexListDemo
[![Platform Android](https://img.shields.io/badge/platform-Android-brightgreen)](https://developer.android.com/)

#怎么对稍微复杂点的RecyclerView.Adapter的开发

**很多时候APP都会有些页面，需要展示多个不同类型的数据都在一个页面，而且数据是服务器动态返回的，顺序也是不确定的。**

**这时候我们就需要在RecyclerView的Adapter做一些对应的处理。**

例子1：利用 getItemViewType，做不同 position 位置的处理。
```
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
```

![Demo 截图1](https://upload-images.jianshu.io/upload_images/633041-91db93b3dc27783b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


例子2：自定义 GridLayoutManager 的 getSpanSize，实现每行不同个数的布局。

```
        val imageAdapter = BannerAdapter(getData(6))
        val gridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (imageAdapter.getItemViewType(position) == 2) {
                    2 //如果是长的图，占2份的位置
                } else {
                    1
                }
            }
        }
        currentBinding.rvGrid.layoutManager = gridLayoutManager
        currentBinding.rvGrid.adapter = imageAdapter
```

![Demo 截图2](https://upload-images.jianshu.io/upload_images/633041-d246929bf7437dc6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

简书文章链接：
https://www.jianshu.com/p/f296502b91cc
