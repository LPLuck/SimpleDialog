package com.liupeng.lp.library;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * 创建者：L.P
 * 创建时间：on 2018/1/12
 * 类描述：Dialog View的辅助处理类
 */

public class DialogViewHelper {

    private int mContentViewId;
    private Context mContext;
    private View mContentView;
    private LayoutInflater mInflater;

    public DialogViewHelper(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setContentView(int mContentViewId) {
        mContentView = mInflater.inflate(mContentViewId,null);
    }

    public View getContentView(){
        return mContentView;
    }

    public void setOnclickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
    }

    public  <T extends View> T getView(int viewId) {

        View view = mContentView.findViewById(viewId);

        return (T) view;
    }

    public void setText(int viewId, CharSequence charSequence) {
        TextView view = getView(viewId);
        view.setText(charSequence);
    }

    public void setVisile(int viewId, Integer integer) {
        View view = getView(viewId);
        view.setVisibility(integer);
    }
}
