package com.cjy.fullscreenpopupwindow

import android.app.Activity

class OsUtil {

    companion object{
        public fun getScreenHeight(activity: Activity):Int{
            activity.run {

            }
            val display = activity.windowManager.defaultDisplay
            var realHeight = 0
            when{

            }
            return 0
        }
    }

//    public static int getScreenHeight(Activity activity) {
//        if (null == activity) {
//            return 0;
//        }
//        Display display = activity.getWindowManager().getDefaultDisplay();
//        int realHeight = 0;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            final DisplayMetrics metrics = new DisplayMetrics();
//            display.getRealMetrics(metrics);
//            realHeight = metrics.heightPixels;
//        } else {
//            try {
//                Method mGetRawH = Display.class.getMethod("getRawHeight");
//                realHeight = (int) mGetRawH.invoke(display);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//        return realHeight;
//    }
//
//    public static int getScreenWidth(Activity activity) {
//        if (null == activity) {
//            return 0;
//        }
//        Display display = activity.getWindowManager().getDefaultDisplay();
//        int realWidth = 0;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            final DisplayMetrics metrics = new DisplayMetrics();
//            display.getRealMetrics(metrics);
//            realWidth = metrics.widthPixels;
//        } else {
//            try {
//                Method mGetRawH = Display.class.getMethod("getRawWidth");
//                realWidth = (int) mGetRawH.invoke(display);
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//        return realWidth;
//    }
//
//    /**
//     * 状态栏高度
//     *
//     * @param activity
//     * @return
//     */
//    public static int getStatusBarHeight(Activity activity) {
//        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
//        return activity.getResources().getDimensionPixelSize(resourceId);
//    }
//
//    /**
//     * 获取虚拟键高度（无论是否隐藏）
//     */
//    public static int getNavigationBarHeight(Context context) {
//        int result = 0;
//        Resources resources = context.getResources();
//        int resourceId = resources.getIdentifier(isPortrait(context) ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
//        if (resourceId > 0) {
//            result = context.getResources().getDimensionPixelSize(resourceId);
//        }
//        return result;
//    }
//
//    /**
//     * 手机具有底部导航栏时，底部导航栏是否可见
//     *
//     * @param activity
//     * @return
//     */
//    private static boolean isNavigationBarVisible(Activity activity) {
//        boolean show = false;
//        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                Display display = activity.getWindow().getWindowManager().getDefaultDisplay();
//                Point point = new Point();
//                display.getRealSize(point);
//                View decorView = activity.getWindow().getDecorView();
//                Configuration conf = activity.getResources().getConfiguration();
//                if (Configuration.ORIENTATION_LANDSCAPE == conf.orientation) {
//                    View contentView = decorView.findViewById(R.id.content);
//                    show = (point.x != contentView.getWidth());
//                } else {
//                    Rect rect = new Rect();
//                    decorView.getWindowVisibleDisplayFrame(rect);
//                    show = (rect.bottom != point.y);
//                }
//            }
//        } catch (Exception e) {
//            KLog.e(e);
//        }
//        return show;
//    }
//
//    /**
//     * 检测是否具有底部导航栏
//     */
//    private static boolean checkDeviceHasNavigationBar(Activity activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            WindowManager windowManager = activity.getWindowManager();
//            Display display = windowManager.getDefaultDisplay();
//            DisplayMetrics realDisplayMetrics = new DisplayMetrics();
//            display.getRealMetrics(realDisplayMetrics);
//            int realHeight = realDisplayMetrics.heightPixels;
//            int realWidth = realDisplayMetrics.widthPixels;
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            display.getMetrics(displayMetrics);
//            int displayHeight = displayMetrics.heightPixels;
//            int displayWidth = displayMetrics.widthPixels;
//            return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
//        } else {
//            boolean hasNavigationBar = false;
//            Resources resources = activity.getResources();
//            int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
//            if (id > 0) {
//                hasNavigationBar = resources.getBoolean(id);
//            }
//            try {
//                Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
//                Method m = systemPropertiesClass.getMethod("get", String.class);
//                String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
//                if ("1".equals(navBarOverride)) {
//                    hasNavigationBar = false;
//                } else {
//                    hasNavigationBar = true;
//                }
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//            return hasNavigationBar;
//        }
//    }
//
//    /**
//     * 获取当前底部栏高度（隐藏后高度为0）
//     *
//     * @param activity
//     * @return
//     */
//    public static int getCurrentNavigationBarHeight(Activity activity) {
//        int navigationBarHeight = 0;
//        Resources resources = activity.getResources();
//        int resourceId = resources.getIdentifier(isPortrait(activity) ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
//        if (resourceId > 0 && checkDeviceHasNavigationBar(activity) && isNavigationBarVisible(activity)) {
//            navigationBarHeight = resources.getDimensionPixelSize(resourceId);
//        }
//        return navigationBarHeight;
//    }
//
//    /**
//     * 是否为竖屏
//     *
//     * @param context
//     * @return
//     */
//    private static boolean isPortrait(Context context) {
//        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
//    }
//
//    /**
//     * 获取可用屏幕高度，排除虚拟键
//     *
//     * @param activity
//     * @return
//     */
//    public static int getContentHeight(Activity activity) {
//        int contentHeight = getScreenHeight(activity) - getCurrentNavigationBarHeight(activity);
//        return contentHeight;
//    }
//
//    /**
//     * 标题栏高度，如果隐藏了标题栏则返回0
//     *
//     * @param activity
//     * @return
//     */
//    public static int getTitleHeight(Activity activity) {
//        return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
//    }
}