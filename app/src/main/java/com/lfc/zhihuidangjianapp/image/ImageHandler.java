package com.lfc.zhihuidangjianapp.image;

import android.widget.ImageView;

/**
 */
public interface ImageHandler {

    /**
     * 通过 url 加载普通图片
     */
    void loadImage(Object object, ImageView imageView, String url);

    /**
     * 通过资源 id 加载普通图片
     */
    void loadImage(Object object, ImageView imageView, int resourceId);

    /**
     * 通过 url 加载圆形图片
     */
    void loadCircleImage(Object object, ImageView imageView, String url);

    /**
     * 通过资源 id 加载圆形图片
     */
    void loadCircleImage(Object object, ImageView imageView, int resourceId);

    /**
     * 通过 url 加载圆角图片
     */
    void loadRoundImage(Object object, ImageView imageView, String url, float radius);

    /**
     * 通过资源 id 加载圆角图片
     */
    void loadRoundImage(Object object, ImageView imageView, int resourceId, float radius);
}