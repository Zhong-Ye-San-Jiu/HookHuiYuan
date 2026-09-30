package com.HookHuiYuan.application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class HookInit implements IXposedHookLoadPackage {

    private void showToast(final Context ctx, final String msg) {
        if (ctx != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }

    @Override
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        XposedHelpers.findAndHookMethod(
            "android.app.Application",
            lpparam.classLoader,
            "onCreate",
            new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    final Context appContext = (Context) param.thisObject;
                    final LoadPackageParam lp = lpparam;

                    switch (lp.packageName) {
                        case "com.HookHuiYuan.application":
                            XposedHelpers.findAndHookMethod(
                                "com.HookHuiYuan.application.MainActivity",
                                lp.classLoader,
                                "isModuleActivated",
                                XC_MethodReplacement.returnConstant(true)
                            );
                            showToast(appContext, "HookHuiYuan: 模块状态已激活！");
                            break;

                        case "cn.ticktick.task":
                            XposedHelpers.findAndHookMethod(
                                "com.ticktick.task.data.User",
                                lp.classLoader,
                                "isActiveTeamUser",
                                XC_MethodReplacement.returnConstant(true)
                            );
                            showToast(appContext, "HookHuiYuan: Hook 滴答清单 成功！");
                            break;

                        case "com.cosmos.tools":
                            XposedHelpers.findAndHookMethod(
                                "com.xieqing.yfoo.advertising.theme.OooO0o$OooO0OO$OooO00o",
                                lp.classLoader,
                                "run",
                                XC_MethodReplacement.returnConstant(null)
                            );
                            XposedHelpers.findAndHookMethod(
                                "com.cosmos.tools.entity.UserData",
                                lp.classLoader,
                                "isVip",
                                XC_MethodReplacement.returnConstant(1)
                            );
                            showToast(appContext, "HookHuiYuan: Hook 宇宙工具箱 成功！");
                            break;

                        case "com.shixin.toolbox":
                            XposedHelpers.findAndHookMethod(
                                "com.xieqing.yfoo.advertising.theme.OooO0o$OooO0OO$OooO00o",
                                lp.classLoader,
                                "run",
                                XC_MethodReplacement.returnConstant(null)
                            );
                            XposedHelpers.findAndHookMethod(
                                "com.cosmos.tools.entity.UserData",
                                lp.classLoader,
                                "isVip",
                                XC_MethodReplacement.returnConstant(1)
                            );
                            showToast(appContext, "HookHuiYuan: Hook 神奇工具 成功！");
                            break;

                        case "com.zhanlang.notes":
                            XposedHelpers.findAndHookMethod(
                                "com.wm.common.user.UserInfoManager",
                                lp.classLoader,
                                "isVip",
                                XC_MethodReplacement.returnConstant(true)
                            );
                            XposedHelpers.findAndHookMethod(
                                "com.wm.common.user.UserInfoManager",
                                lp.classLoader,
                                "isPermanentVip",
                                XC_MethodReplacement.returnConstant(true)
                            );
                            XposedHelpers.findAndHookMethod(
                                "com.wm.common.user.UserInfoManager",
                                lp.classLoader,
                                "isLogin",
                                XC_MethodReplacement.returnConstant(true)
                            );
                            showToast(appContext, "HookHuiYuan: Hook 备忘录记事本 成功！");
                            break;

                        case "com.shixin.toolmaster":
                            XposedHelpers.findAndHookMethod(
                                "com.shixin.toolbox.user.entity.UserInfo",
                                lp.classLoader,
                                "getVip",
                                XC_MethodReplacement.returnConstant(1)
                            );
                            showToast(appContext, "HookHuiYuan: Hook 工具大师 成功！");
                            break;

                        case "com.ZWSoft.ZWCAD":
                            XposedHelpers.findAndHookMethod(
                                "com.ZWSoft.ZWCAD.Utilities.i",
                                lp.classLoader,
                                "t",
                                XC_MethodReplacement.returnConstant(true)
                            );
                            XposedHelpers.findAndHookMethod(
                                "com.ZWSoft.ZWCAD.Utilities.i",
                                lp.classLoader,
                                "isLogined",
                                XC_MethodReplacement.returnConstant(true)
                            );
                            showToast(appContext, "HookHuiYuan: Hook CAD看图大师 成功！");
                            break;

                        case "com.magicalstory.days":
                            XposedHelpers.findAndHookMethod(
                                "bb.h",
                                lp.classLoader,
                                "j",
                                XC_MethodReplacement.returnConstant(true)
                            );
                            XposedHelpers.findAndHookMethod(
                                "bb.h",
                                lp.classLoader,
                                "i",
                                XC_MethodReplacement.returnConstant(true)
                            );
                            showToast(appContext, "HookHuiYuan: Hook 朝花夕拾 成功！");
                            break;

                        case "com.lp.diary.time.lock":
                            XposedHelpers.findAndHookMethod(
                                "Gf.N",
                                lp.classLoader,
                                "x",
                                XC_MethodReplacement.returnConstant(true)
                            );
                            showToast(appContext, "HookHuiYuan: Hook 定格日记 成功！");
                            break;

                        case "com.youqi.miaomiao":
                            XposedHelpers.findAndHookMethod(
                                "com.lanniser.kittykeeping.data.model.User",
                                lp.classLoader,
                                "isVip",
                                XC_MethodReplacement.returnConstant(true)
                            );
                            XposedHelpers.findAndHookMethod(
                                "com.lanniser.kittykeeping.data.model.User",
                                lp.classLoader,
                                "isForeverVip",
                                XC_MethodReplacement.returnConstant(true)
                            );
                            XposedHelpers.findAndHookMethod(
                                "com.lanniser.kittykeeping.data.model.User",
                                lp.classLoader,
                                "isSVip",
                                XC_MethodReplacement.returnConstant(true)
                            );
                            XposedHelpers.findAndHookMethod(
                                "com.lanniser.kittykeeping.data.model.User",
                                lp.classLoader,
                                "isForeverSVip",
                                XC_MethodReplacement.returnConstant(true)
                            );
                            showToast(appContext, "HookHuiYuan: Hook 喵喵记账 成功！");
                            break;

                        case "com.waterControl.administrator.wisdomschool":
                            /*
                             * 智校园 3.0.6 开屏广告链路（实测）：
                             *   MainActivity.<init>(MainActivity.java:98) 里 new 出 cj.mobile.CJSplash
                             *     -> CJSplash 内部 Runnable (cj.mobile.CJSplash$o.run) 发起穿山甲请求
                             *        -> cj.mobile.a.j.a(TTSDK.java:90) -> AdSlot.Builder.build()（广告位 891847404）
                             * App 自己的导航也由广告关闭回调驱动：
                             *   CJSplash$a$e.run -> MainActivity$3.onClose -> VersionUpgradeUtils.toHomePage -> YongHuMainActivity
                             */
                            // 1) 关键：跳过开屏页（立刻跳走并 finish，不依赖广告回调）
                            XposedHelpers.findAndHookMethod(
                                "com.waterControl.administrator.wisdomschool.activity.MainActivity",
                                lp.classLoader,
                                "onCreate",
                                android.os.Bundle.class,
                                new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        android.app.Activity activity = (android.app.Activity) param.thisObject;
                                        android.content.Intent intent = new android.content.Intent();
                                        intent.setClassName(activity,
                                            "com.waterControl.administrator.wisdomschool.activity.YongHuMainActivity");
                                        activity.startActivity(intent);
                                        activity.finish();
                                    }
                                }
                            );

                            // 2) 掐掉广告加载任务：请求根本不发出。放在后面并单独 try，
                            //    万一混淆名变了也不影响上面的跳转。
                            try {
                                XposedHelpers.findAndHookMethod(
                                    "cj.mobile.CJSplash$o",
                                    lp.classLoader,
                                    "run",
                                    XC_MethodReplacement.returnConstant(null)
                                );
                            } catch (Throwable ignored) {
                            }

                            showToast(appContext, "智校园: 已跳过开屏页！");
                            break;

                    }
                }
            }
        );
    }
}
