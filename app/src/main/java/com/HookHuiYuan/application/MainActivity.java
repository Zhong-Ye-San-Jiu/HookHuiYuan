package com.HookHuiYuan.application;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView statusText = findViewById(R.id.status);
        statusText.setText("支持Hook的应用");
        statusText.setTextColor(Color.BLUE);

        LinearLayout appsContainer = findViewById(R.id.apps_container);
        addAppStatus(appsContainer, "cn.ticktick.task", "滴答清单", "解锁本地VIP");
        addAppStatus(appsContainer, "com.cosmos.tools", "宇宙工具箱", "跳过广告，解锁VIP");
        addAppStatus(appsContainer, "com.shixin.toolbox", "神奇工具", "跳过广告，解锁VIP");
        addAppStatus(appsContainer, "com.zhanlang.notes","备忘录记事本","解锁VIP，安装包比片难找");
        addAppStatus(appsContainer, "com.shixin.toolmaster","工具大师","解锁VIP");
        addAppStatus(appsContainer, "com.ZWSoft.ZWCAD","CAD看图大师","免登录，解锁VIP");
        addAppStatus(appsContainer, "com.magicalstory.days","朝花夕拾","免登录，解锁VIP");
        addAppStatus(appsContainer, "com.lp.diary.time.lock","定格日记","解锁VIP");
        addAppStatus(appsContainer, "com.youqi.miaomiao","喵喵记账","解锁SVIP");
    }

    private String getAppVersion(String packageName) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(packageName, 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    private void addAppStatus(LinearLayout container, final String packageName, final String appName, String desc) {
        TextView appText = new TextView(this);
        appText.setTextSize(15);

        String version = getAppVersion(packageName);
        if (version != null) {
            appText.setText(appName + " —— 已安装 v" + version + " ✅");
            appText.setTextColor(Color.parseColor("#4CAF50"));

            appText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + packageName));
                    startActivity(intent);
                }
            });

            appText.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
                    if (launchIntent != null) {
                        Toast.makeText(MainActivity.this, "正在启动 " + appName + "...", Toast.LENGTH_SHORT).show();
                        startActivity(launchIntent);
                    } else {
                        Toast.makeText(MainActivity.this, "无法启动 " + appName, Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });

        } else {
            appText.setText(appName + " —— 未安装 ❌");
            appText.setTextColor(Color.DKGRAY);
        }
        container.addView(appText);

        if (desc != null) {
            TextView descText = new TextView(this);
            descText.setTextSize(12);
            descText.setTextColor(Color.GRAY);
            descText.setText(desc);
            container.addView(descText);
        }
    }
}
