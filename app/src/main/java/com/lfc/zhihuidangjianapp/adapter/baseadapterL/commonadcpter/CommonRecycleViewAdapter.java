package com.lfc.zhihuidangjianapp.adapter.baseadapterL.commonadcpter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Super简单 on 16/4/9.
 */
public abstract class CommonRecycleViewAdapter<T> extends RecyclerView.Adapter<ViewHolderHelper> implements DataIO<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas = new ArrayList<>();
    protected LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;
    //动画
    private int mLastPosition = -1;
    private boolean mOpenAnimationEnable = true;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mDuration = 300;
    private PageBean pageBean;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public CommonRecycleViewAdapter(Context context, int layoutId, List<T> mDatass) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        // 使用的为new Arraylist 建立的对象
        mDatas = mDatass;
        pageBean = new PageBean();
    }

    // 刷新recyclerView 中的某个控件
    @Override
    public void onBindViewHolder(ViewHolderHelper holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (payloads != null && payloads.size() != 0) {

        }

    }

    public CommonRecycleViewAdapter(Context context, int layoutId) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        pageBean = new PageBean();
    }

    @Override
    public ViewHolderHelper onCreateViewHolder(final ViewGroup parent, int viewType) {
        ViewHolderHelper viewHolder = ViewHolderHelper.get(mContext, null, parent, mLayoutId, -1);
        setListener(parent, viewHolder, viewType);
        return viewHolder;
    }

    protected int getPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }


    protected void setListener(final ViewGroup parent, final ViewHolderHelper viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    mOnItemClickListener.onItemClick(parent, v, mDatas.get(position), position);
                }
            }
        });


        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    return mOnItemClickListener.onItemLongClick(parent, v, mDatas.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolderHelper holder, int position) {
        holder.updatePosition(position);
        //添加动画
        convert(holder, mDatas.get(position), position);
    }

    public abstract void convert(ViewHolderHelper helper, T t, int position);

    /**
     * set anim to start when loading
     * 开启动画的执行效果
     *
     * @param anim
     * @param index
     */
    protected void startAnim(Animator anim, int index) {
        anim.setDuration(mDuration).start();
        anim.setInterpolator(mInterpolator);
    }


    /**
     * 关闭动画
     */
    public void closeLoadAnimation() {
        this.mOpenAnimationEnable = false;
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void add(T elem) {
        mDatas.add(elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAt(int location, T elem) {
        mDatas.add(location, elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(List<T> elements) {
        mDatas.addAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public void addAllAt(int location, List<T> elements) {
        mDatas.addAll(location, elements);
//        notifyItemRangeChanged(location,elements.size());
//        notifyItemRangeInserted(location, elements.size());
        notifyDataSetChanged();
    }

    @Override
    public void remove(T elem) {
        mDatas.remove(elem);

        notifyDataSetChanged();
    }

    @Override
    public void removeAt(int index) {
        mDatas.remove(index);
        notifyDataSetChanged();
//        notifyItemRemoved(index);

    }

    @Override
    public void removeAll(List<T> elements) {
        mDatas.removeAll(elements);

        notifyDataSetChanged();

    }

    @Override
    public void clear() {
        if (mDatas != null && mDatas.size() > 0) {
            mDatas.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void clearData() {
        if (mDatas != null && mDatas.size() > 0) {
            mDatas.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void replace(T oldElem, T newElem) {
        replaceAt(mDatas.indexOf(oldElem), newElem);
    }

    @Override
    public void replaceAt(int index, T elem) {
        mDatas.set(index, elem);
        notifyDataSetChanged();
    }

    @Override
    public void replaceAll(List<T> elements) {
        if (mDatas.size() > 0) {
            mDatas.clear();
        }
        mDatas.addAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public T get(int position) {
        if (position >= mDatas.size())
            return null;
        return mDatas.get(position);
    }

    @Override
    public List<T> getAll() {
        return mDatas;
    }

    @Override
    public int getSize() {
        return mDatas.size();
    }

    @Override
    public boolean contains(T elem) {
        return mDatas.contains(elem);
    }

    /**
     * 分页
     *
     * @return
     */
    public PageBean getPageBean() {
        return pageBean;
    }

    public List<T> getDataList() {
        return mDatas;
    }

}
