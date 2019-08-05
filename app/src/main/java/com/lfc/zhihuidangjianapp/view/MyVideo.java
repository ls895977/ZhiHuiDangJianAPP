package com.lfc.zhihuidangjianapp.view;

import android.content.Context;
import android.util.AttributeSet;

import cn.jzvd.JzvdStd;


public class MyVideo extends JzvdStd {
    public MyVideo(Context context) {
        super(context);
    }

    public MyVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public onBackProgress onBackProgress;

    public void setOnBackProgress(MyVideo.onBackProgress onBackProgress) {
        this.onBackProgress = onBackProgress;
    }

    @Override
    public void onProgress(int progress, long position, long duration) {
        super.onProgress(progress, position, duration);
        if (onBackProgress != null) onBackProgress.onBackprogress(position);
        if (progress != 0) bottomProgressBar.setProgress(progress);
    }
    @Override
    public  void onStatePreparing(){
        super.onStatePreparing();
        if(loadingProgressBar!=null){
            loadingProgressBar.setVisibility(INVISIBLE);
        }
    }
    @Override
    public void setBufferProgress(int bufferProgress) {//预加载判断
        super.setBufferProgress(bufferProgress);
        if (onBackProgress != null) onBackProgress.onBufferProgress(bufferProgress);
        if (bufferProgress != 0) bottomProgressBar.setSecondaryProgress(bufferProgress);
    }

    public interface onBackProgress {
        void onBackprogress(long progress);

        void onBufferProgress(int bufferProgress);
    }
}
