package com.HookApp.application;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    /** {包名, 应用名, 说明} */
    private static final String[][] APPS = {
        {"cn.ticktick.task", "滴答清单", "解锁本地VIP"},
        {"com.cosmos.tools", "宇宙工具箱", "去广告，解锁VIP"},
        {"com.shixin.toolbox", "神奇工具", "去广告，解锁VIP"},
        {"com.zhanlang.notes", "备忘录记事本", "解锁VIP，安装包比片难找"},
        {"com.shixin.toolmaster", "工具大师", "解锁VIP"},
        {"com.ZWSoft.ZWCAD", "CAD看图大师", "免登录，解锁VIP"},
        {"com.magicalstory.days", "朝花夕拾", "免登录，解锁VIP"},
        {"com.lp.diary.time.lock", "定格日记", "解锁VIP"},
        {"com.youqi.miaomiao", "喵喵记账", "解锁SVIP"},
        {"com.waterControl.administrator.wisdomschool", "智校园", "去广告"},
    };

    /**
     * 模块激活检测。装了 Xposed 并由 HookInit 替换成 return true；
     * 没装模块时走这里，返回 false。
     */
    public boolean isModuleActivated() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout container = findViewById(R.id.apps_container);
        container.setClipToOutline(true);   // 行内的水波纹按圆角裁切

        int installed = 0;
        for (int i = 0; i < APPS.length; i++) {
            String[] app = APPS[i];
            String version = getAppVersion(app[0]);
            if (version != null) {
                installed++;
            }
            container.addView(buildRow(app[0], app[1], app[2], version));
            if (i < APPS.length - 1) {
                container.addView(buildDivider());
            }
        }

        boolean activated = isModuleActivated();

        TextView statusText = findViewById(R.id.status_text);
        statusText.setText(activated ? R.string.status_active : R.string.status_inactive);

        GradientDrawable dot = new GradientDrawable();
        dot.setShape(GradientDrawable.OVAL);
        dot.setColor(c(activated ? R.color.live : R.color.dead));
        findViewById(R.id.status_dot).setBackground(dot);

        TextView countText = findViewById(R.id.count_text);
        countText.setText(getString(R.string.count_installed, installed, APPS.length));
    }

    private View buildRow(final String pkg, final String name, String desc, String version) {
        final boolean installed = version != null;

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(dp(14), dp(13), dp(14), dp(13));
        if (Build.VERSION.SDK_INT >= 21) {
            row.setBackground(new RippleDrawable(ColorStateList.valueOf(0x12000000), null, null));
        }

        ImageView icon = new ImageView(this);
        icon.setLayoutParams(new LinearLayout.LayoutParams(dp(40), dp(40)));
        if (installed) {
            try {
                icon.setImageDrawable(getPackageManager().getApplicationIcon(pkg));
            } catch (Exception e) {
                // 取图标失败就退回系统默认图标，不要让整行挂掉
                icon.setImageResource(android.R.drawable.sym_def_app_icon);
            }
        } else {
            icon.setImageResource(android.R.drawable.sym_def_app_icon);
            icon.setAlpha(0.3f);
        }

        LinearLayout col = new LinearLayout(this);
        col.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams colLp =
                new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        colLp.leftMargin = dp(13);
        colLp.rightMargin = dp(10);
        col.setLayoutParams(colLp);

        TextView nameView = new TextView(this);
        nameView.setText(name);
        nameView.setTextSize(15);
        nameView.setTypeface(null, Typeface.BOLD);
        nameView.setTextColor(c(installed ? R.color.ink : R.color.ink_3));

        TextView descView = new TextView(this);
        descView.setText(desc);
        descView.setTextSize(12);
        descView.setPadding(0, dp(3), 0, 0);
        descView.setTextColor(c(installed ? R.color.ink_2 : R.color.ink_3));

        col.addView(nameView);
        col.addView(descView);

        LinearLayout right = new LinearLayout(this);
        right.setOrientation(LinearLayout.VERTICAL);
        right.setGravity(Gravity.END);

        TextView tag = new TextView(this);
        tag.setText(installed ? R.string.tag_installed : R.string.tag_missing);
        tag.setTextSize(11);
        tag.setPadding(dp(9), dp(4), dp(9), dp(4));
        tag.setTextColor(c(installed ? R.color.ok : R.color.off));
        tag.setBackground(rounded(c(installed ? R.color.ok_bg : R.color.off_bg), dp(20)));
        right.addView(tag);

        if (installed) {
            TextView verView = new TextView(this);
            verView.setText("v" + version);
            verView.setTextSize(11);
            verView.setTypeface(Typeface.MONOSPACE);
            verView.setPadding(0, dp(4), 0, 0);
            verView.setTextColor(c(R.color.ink_3));
            right.addView(verView);
        }

        row.addView(icon);
        row.addView(col);
        row.addView(right);

        if (installed) {
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openAppDetails(pkg);
                }
            });
            row.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    launchApp(pkg, name);
                    return true;
                }
            });
        }
        return row;
    }

    /** 行之间的细分割线，左端与文字对齐（跳过图标宽度） */
    private View buildDivider() {
        View line = new View(this);
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        lp.leftMargin = dp(14) + dp(40) + dp(13);
        line.setLayoutParams(lp);
        line.setBackgroundColor(c(R.color.line));
        return line;
    }

    private void openAppDetails(String packageName) {
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        startActivity(intent);
    }

    private void launchApp(String packageName, String appName) {
        Intent launch = getPackageManager().getLaunchIntentForPackage(packageName);
        if (launch != null) {
            Toast.makeText(this, "正在启动 " + appName + "...", Toast.LENGTH_SHORT).show();
            startActivity(launch);
        } else {
            Toast.makeText(this, "无法启动 " + appName, Toast.LENGTH_SHORT).show();
        }
    }

    private String getAppVersion(String packageName) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(packageName, 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    private GradientDrawable rounded(int color, float radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    private int dp(float value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    private int c(int res) {
        return Build.VERSION.SDK_INT >= 23 ? getColor(res) : getResources().getColor(res);
    }
}
