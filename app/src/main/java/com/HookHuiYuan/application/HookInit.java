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
                                showToast(appContext, "HookVIP: 模块状态已激活！");
                                break;

                            case "cn.ticktick.task":
                                XposedHelpers.findAndHookMethod(
                                        "com.ticktick.task.data.User",
                                        lp.classLoader,
                                        "isActiveTeamUser",
                                        XC_MethodReplacement.returnConstant(true)
                                );
                                showToast(appContext, "HookVIP: Hook 滴答清单 成功！");
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
                                showToast(appContext, "HookVIP: Hook 宇宙工具箱 成功！");
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
                                showToast(appContext, "HookVIP: Hook 神奇工具 成功！");
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
                                showToast(appContext, "HookVIP: Hook 备忘录记事本 成功！");
                                break;
                                
                            case "com.shixin.toolmaster":
                                XposedHelpers.findAndHookMethod(
                                        "com.shixin.toolbox.user.entity.UserInfo",
                                        lp.classLoader,
                                        "getVip",
                                        XC_MethodReplacement.returnConstant(1)
                                );
                                showToast(appContext, "HookVIP: Hook 工具大师 成功！");
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
                                showToast(appContext, "HookVIP: Hook CAD看图大师 成功！");
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
                                showToast(appContext, "HookVIP: Hook 朝花夕拾 成功！");
                                break;
                                
                            case "com.lp.diary.time.lock":
                                XposedHelpers.findAndHookMethod(
                                    "Gf.N",
                                    lp.classLoader,
                                    "x",
                                    XC_MethodReplacement.returnConstant(true)
                                );
                                showToast(appContext, "HookVIP: Hook 定格日记 成功！");
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
                                showToast(appContext, "HookVIP: Hook 喵喵记账 成功！");
                                break;
                        }
                    }
                }
        );
    }
}
