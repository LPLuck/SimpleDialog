package com.lp.library;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

/**
 * 创建者：L.P
 * 创建时间：on 2018/1/5
 * 类描述：
 */

public class SmartDialog extends Dialog {

    private SmartController mAlert;

    protected SmartDialog(Context context) {
        this(context, 0);
    }

    protected SmartDialog(Context context, int themeResId) {
        super(context, themeResId);
        mAlert = new SmartController(getContext(), getWindow());
    }

    public <T extends View> T getView(int viewId) {
        return mAlert.getView(viewId);
    }

    public void setOnClickLlistener(int view, View.OnClickListener listener) {
        mAlert.setOnClickLlistener(view, listener);
    }

    public static class Builder {

        private final SmartController.AlertParams P;

        public Builder(Context context) {
            this(context, R.style.dialog);
        }

        public Builder(Context context, int themeResId) {
            P = new SmartController.AlertParams(context, themeResId);
        }

        public Builder setText(int textId, CharSequence text) {
            P.mTextArray.put(textId, text);
            return this;
        }

        public Builder setContentView(@LayoutRes int layoutId) {
            P.mContentViewId = layoutId;
            return this;
        }

        public Builder setFullWidth() {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        public Builder setFullHeight() {
            P.mHeight = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        public Builder setGravity(int gravity) {
            setGravity(gravity, 0);
            return this;
        }

        /*
        *
        * */
        public Builder setGravity(int gravity, int animation) {
            P.mGravity = gravity;
            if (animation != 0) {
                P.mAnimations = animation;
            }
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }

        public SmartDialog create() {
            final SmartDialog dialog = new SmartDialog(P.mContext, P.themeResId);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        public SmartDialog show() {
            final SmartDialog dialog = create();
            dialog.show();
            return dialog;
        }


        public Builder setOnClickLlistener(int view, View.OnClickListener listener) {
            P.mClickArray.put(view, listener);
            return this;
        }

        public Builder setVisible(int viewId, int visible) {
            P.mViewVisibleArray.put(viewId, visible);
            return this;
        }

        public Builder setOnCancelListener( DialogInterface.OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }


        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }
    }
}
