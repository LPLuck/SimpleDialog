package com.liupeng.lp.simpledialog;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liupeng.lp.library.AlertDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 底部弹出
     *
     * @param view
     */
    public void bottom(View view) {
        final AlertDialog dialog = new AlertDialog.Builder(this).
                setContentView(R.layout.detail_comment_dialog).
                setFullWidth().
                setGravity(Gravity.BOTTOM, R.style.dialog_from_bottom_anim).
                show();
        dialog.setOnClickLlistener(R.id.submit_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView view1 = dialog.getView(R.id.comment_editor);
                Toast.makeText(MainActivity.this, view1.getText(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 顶部弹出
     *
     * @param view
     */
    public void head(View view) {
        AlertDialog dialog = new AlertDialog.Builder(this).
                setContentView(R.layout.detail_comment_dialog).
                setFullWidth().
                setOnClickLlistener(R.id.submit_btn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "发送", Toast.LENGTH_LONG).show();
                    }
                }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Toast.makeText(MainActivity.this, "Dismiss", Toast.LENGTH_LONG).show();

                }
        }).
                setGravity(Gravity.TOP, R.style.dialog_from_top_anim).
                show();
    }

    /**
     * 居中弹出
     *
     * @param view
     */
    public void center(View view) {
        AlertDialog dialog = new AlertDialog.Builder(this).
                setContentView(R.layout.detail_comment_dialog).
                setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_LONG).show();
                    }
                }).show();
    }

    /**
     * 全屏
     *
     * @param view
     */
    public void full(View view) {
        AlertDialog dialog = new AlertDialog.Builder(this).
                setContentView(R.layout.detail_comment_dialog).
                setFullWidth().
                setVisible(R.id.submit_btn, View.GONE).
                setFullHeight().show();
    }
}
