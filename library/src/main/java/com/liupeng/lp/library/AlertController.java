package com.liupeng.lp.library;

import android.content.Context;
import android.content.DialogInterface;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建者：L.P
 * 创建时间：on 2018/1/5
 * 类描述：
 */

public class AlertController {

    private Window mWindow;
    private DialogViewHelper mViewHelper;

    public AlertController(Context context, Window window) {
        this.mWindow = window;
    }

    public <T extends View> T getView(int viewId) {
        return mViewHelper.getView(viewId);
    }

    public void setOnClickLlistener(int view, View.OnClickListener listener) {
        mViewHelper.setOnclickListener(view, listener);
    }

    //用来存对话框的参数
    public static class AlertParams {
        public int mContentViewId;
        public Context mContext;
        //主题
        public int themeResId;
        public SparseArray<CharSequence> mTextArray = new SparseArray<>();
        public SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();
        public SparseArray<Integer> mViewVisibleArray = new SparseArray<>();
        private DialogViewHelper mViewHelper;
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mGravity = Gravity.CENTER;
        public int mAnimations;
        //弹出后点击空白屏幕或物理返回键，dialog是否消失（true消失）
        public boolean mCancelable = true;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public DialogInterface.OnKeyListener mOnKeyListener;

        public AlertParams(Context context, int themeResId) {
            this.mContext = context;
            this.themeResId = themeResId;

        }

        public void apply(AlertController dialog) {
            Window mWindow = dialog.mWindow;
            if (mContentViewId != 0) {
                this.mViewHelper = new DialogViewHelper(mContext);
                mViewHelper.setContentView(mContentViewId);
                dialog.mWindow.setContentView(mViewHelper.getContentView());
                dialog.setViewHelper(mViewHelper);
            } else {
                throw new IllegalArgumentException("请设置布局setContentView()");
            }

            if (mAnimations != 0) {
                mWindow.setWindowAnimations(mAnimations);
            }

            int clickArraySize = mClickArray.size();
            if (clickArraySize != 0) {
                for (int i = 0; i < clickArraySize; i++) {
                    mViewHelper.setOnclickListener(mClickArray.keyAt(i), mClickArray.valueAt(i));
                }
            }

            int textArraySize = mTextArray.size();
            if (textArraySize != 0) {
                for (int i = 0; i < textArraySize; i++) {
                    mViewHelper.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
                }
            }
            int viewVisibleArraySize = mViewVisibleArray.size();
            if (viewVisibleArraySize != 0) {
                for (int i = 0; i < viewVisibleArraySize; i++) {
                    mViewHelper.setVisile(mViewVisibleArray.keyAt(i), mViewVisibleArray.valueAt(i));
                }
            }

            mWindow.setGravity(mGravity);

            // 设置宽高
            WindowManager.LayoutParams params = mWindow.getAttributes();
            params.width = mWidth;
            params.height = mHeight;
            mWindow.setAttributes(params);
        }
    }

    private void setViewHelper(DialogViewHelper viewHelper) {
        mViewHelper = viewHelper;
    }
}
