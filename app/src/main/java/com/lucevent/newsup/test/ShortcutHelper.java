package com.lucevent.newsup.test;

public class ShortcutHelper {

 /*
    static Delegate sDelegate;


    final class C08071 extends AsyncTask {
        private  long val$backgroundColor;
        final  long val$callbackPointer;
        private  int val$displayMode;
        private  Bitmap val$icon;
        private  String val$iconUrl;
        private  String val$id;
        private  String val$name;
        private  int val$orientation;
        private  String val$scopeUrl;
        private  String val$shortName;
        private  int val$source;
        private  long val$themeColor;
        private  String val$url;
        private  String val$userTitle;


        final class C08061 implements FetchWebappDataStorageCallback {
            private  Intent val$resultIntent;

            C08061(Intent intent) {
                this.val$resultIntent = intent;
            }

            public final void onWebappDataStorageRetrieved(WebappDataStorage webappDataStorage) {
                webappDataStorage.updateFromShortcutIntent(this.val$resultIntent);
                ShortcutHelper.nativeOnWebappDataStored(C08071.this.val$callbackPointer);
            }
        }

        C08071(String str, String str2, String str3, String str4, String str5, Bitmap bitmap, int i, int i2, long j, long j2, String str6, int i3, String str7, long j3) {
            this.val$scopeUrl = str;
            this.val$url = str2;
            this.val$id = str3;
            this.val$name = str4;
            this.val$shortName = str5;
            this.val$icon = bitmap;
            this.val$displayMode = i;
            this.val$orientation = i2;
            this.val$themeColor = j;
            this.val$backgroundColor = j2;
            this.val$iconUrl = str6;
            this.val$source = i3;
            this.val$userTitle = str7;
            this.val$callbackPointer = j3;
        }

        protected final  void onPostExecute(Object obj) {
            Intent intent = (Intent) obj;
            Context context = ContextUtils.sApplicationContext;
            Delegate delegate = ShortcutHelper.sDelegate;
            context.sendBroadcast(ShortcutHelper.createAddToHomeIntent(this.val$userTitle, this.val$icon, intent));
            WebappRegistry.registerWebapp(context, this.val$id, new C08061(intent));
            ShortcutHelper.showAddedToHomescreenToast(this.val$userTitle);
        }

        protected final  Object doInBackground(Object[] objArr) {
            Context context = ContextUtils.sApplicationContext;
            String scopeFromUrl = TextUtils.isEmpty(this.val$scopeUrl) ? ShortcutHelper.getScopeFromUrl(this.val$url) : this.val$scopeUrl;
            String str = this.val$id;
            Delegate delegate = ShortcutHelper.sDelegate;
            Intent createWebappShortcutIntent = ShortcutHelper.createWebappShortcutIntent(str, "com.google.android.apps.chrome.webapps.WebappManager.ACTION_START_WEBAPP", this.val$url, scopeFromUrl, this.val$name, this.val$shortName, this.val$icon, 2, this.val$displayMode, this.val$orientation, this.val$themeColor, this.val$backgroundColor, this.val$iconUrl.isEmpty());
            createWebappShortcutIntent.putExtra("org.chromium.chrome.browser.webapp_mac", ShortcutHelper.getEncodedMac(context, this.val$url));
            createWebappShortcutIntent.putExtra("org.chromium.chrome.browser.webapp_source", this.val$source);
            createWebappShortcutIntent.setPackage(context.getPackageName());
            return createWebappShortcutIntent;
        }
    }


    final class C08082 implements FetchWebappDataStorageCallback {
        private  Bitmap val$splashImage;

        C08082(Bitmap bitmap) {
            this.val$splashImage = bitmap;
        }

        public final void onWebappDataStorageRetrieved(WebappDataStorage webappDataStorage) {
            if (webappDataStorage != null) {
                new C16065(this.val$splashImage).execute(new Void[0]);
            }
        }
    }

    public final class Delegate {
    }

    private static native void nativeOnWebappDataStored(long j);

    static {
        sDelegate = new Delegate();
    }

    private static void addWebapp(String str, String str2, String str3, String str4, String str5, String str6, String str7, Bitmap bitmap, int i, int i2, int i3, long j, long j2, long j3) {
        new C08071(str3, str2, str, str5, str6, bitmap, i, i2, j, j2, str7, i3, str4, j3).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static void addWebApkShortcut(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            String charSequence = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128)).toString();
            Bitmap createHomeScreenIconFromWebIcon = createHomeScreenIconFromWebIcon(((BitmapDrawable) packageManager.getApplicationIcon(str)).getBitmap());
            Intent intent = new Intent();
            intent.setClassName(str, "org.chromium.webapk.shell_apk.MainActivity");
            intent.addCategory("android.intent.category.LAUNCHER");
            context.sendBroadcast(createAddToHomeIntent(charSequence, createHomeScreenIconFromWebIcon, intent));
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Intent createAddToHomeIntent(String str, Bitmap bitmap, Intent intent) {
        Intent intent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        intent2.putExtra("android.intent.extra.shortcut.NAME", str);
        intent2.putExtra("android.intent.extra.shortcut.ICON", bitmap);
        return intent2;
    }

    public static Intent createWebappShortcutIntent(String str, String str2, String str3, String str4, String str5, String str6, Bitmap bitmap, int i, int i2, int i3, long j, long j2, boolean z) {
        String encodeBitmapAsString = encodeBitmapAsString(bitmap);
        Intent intent = new Intent();
        intent.setAction(str2)
                .putExtra("org.chromium.chrome.browser.webapp_id", str)
                .putExtra("org.chromium.chrome.browser.webapp_url", str3)
                .putExtra("org.chromium.chrome.browser.webapp_scope", str4)
                .putExtra("org.chromium.chrome.browser.webapp_name", str5)
                .putExtra("org.chromium.chrome.browser.webapp_short_name", str6)
                .putExtra("org.chromium.chrome.browser.webapp_icon", encodeBitmapAsString)
                .putExtra("org.chromium.chrome.browser.webapp_shortcut_version", i)
                .putExtra("org.chromium.chrome.browser.webapp_display_mode", i2)
                .putExtra("org.chromium.content_public.common.orientation", i3)
                .putExtra("org.chromium.chrome.browser.theme_color", j)
                .putExtra("org.chromium.chrome.browser.background_color", j2)
                .putExtra("org.chromium.chrome.browser.is_icon_generated", z);
        return intent;
    }

    public static boolean isAddToHomeIntentSupported(Context context) {
        return !context.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 32).isEmpty();
    }

    public static String encodeBitmapAsString(Bitmap bitmap) {
        if (bitmap == null) {
            return "";
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    public static Bitmap decodeBitmapFromString(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] decode = Base64.decode(str, 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    public static String getEncodedMac(Context context, String str) {
        return Base64.encodeToString(WebappAuthenticator.getMacForUrl(context, str), 0);
    }

    public static String getScopeFromUrl(String str) {
        Uri parse = Uri.parse(str);
        List pathSegments = parse.getPathSegments();
        int size = pathSegments.size();
        if (size > 0) {
            size--;
        }
        Builder buildUpon = parse.buildUpon();
        String str2 = "/" + TextUtils.join("/", pathSegments.subList(0, size));
        if (str2.length() > 1) {
            str2 = str2 + "/";
        }
        buildUpon.path(str2);
        buildUpon.fragment("");
        buildUpon.query("");
        return buildUpon.build().toString();
    }

    private static int getIdealSizeFromResourceInDp(Context context, int i) {
        return Math.round(context.getResources().getDimension(i) / context.getResources().getDisplayMetrics().density);
    }

    private static void addShortcut(String str, String str2, Bitmap bitmap, int i) {
        Context context = ContextUtils.sApplicationContext;
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
        intent.putExtra("org.chromium.chrome.browser.webapp_source", i);
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(createAddToHomeIntent(str2, bitmap, intent));
        showAddedToHomescreenToast(str2);
    }

    static void showAddedToHomescreenToast(String str) {
        Context context = ContextUtils.sApplicationContext;
        Toast.makeText(context, context.getString(C0737R.string.added_to_homescreen, new Object[]{str}), 0).mToast.show();
    }

    private static void storeWebappSplashImage(String str, Bitmap bitmap) {
        WebappRegistry.getWebappDataStorage(ContextUtils.sApplicationContext, str, new C08082(bitmap));
    }

    public static boolean isIconLargeEnoughForLauncher(int i, int i2) {
        int launcherLargeIconSize = ((ActivityManager) ContextUtils.sApplicationContext.getSystemService("activity")).getLauncherLargeIconSize() / 2;
        return i >= launcherLargeIconSize && i2 >= launcherLargeIconSize;
    }

    public static Bitmap createHomeScreenIconFromWebIcon(Bitmap bitmap) {
        int min = Math.min(Math.round(((float) ((ActivityManager) ContextUtils.sApplicationContext.getSystemService("activity")).getLauncherLargeIconSize()) * 1.25f), Math.max(bitmap.getWidth(), bitmap.getHeight()));
        int round = Math.round(0.045454547f * ((float) min));
        int i = min + (round * 2);
        try {
            boolean z;
            Rect rect;
            Bitmap createBitmap = Bitmap.createBitmap(i, i, Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint(1);
            paint.setFilterBitmap(true);
            min = bitmap.getWidth() - 1;
            int height = bitmap.getHeight() - 1;
            if (Color.alpha(bitmap.getPixel(0, 0)) == 0 || Color.alpha(bitmap.getPixel(min, height)) == 0 || Color.alpha(bitmap.getPixel(0, height)) == 0 || Color.alpha(bitmap.getPixel(min, 0)) == 0) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                rect = new Rect(round, round, i - round, i - round);
            } else {
                rect = new Rect(0, 0, i, i);
            }
            canvas.drawBitmap(bitmap, null, rect, paint);
            return createBitmap;
        } catch (OutOfMemoryError e) {
            Log.m6835w("ShortcutHelper", "OutOfMemoryError while creating bitmap for home screen icon.", new Object[0]);
            return bitmap;
        }
    }

    public static Bitmap generateHomeScreenIcon(String str, int i, int i2, int i3) {
        Context context = ContextUtils.sApplicationContext;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        int launcherLargeIconSize = activityManager.getLauncherLargeIconSize();
        int launcherLargeIconDensity = activityManager.getLauncherLargeIconDensity();
        try {
            Drawable drawableForDensity;
            Bitmap bitmap;
            Bitmap createBitmap = Bitmap.createBitmap(launcherLargeIconSize, launcherLargeIconSize, Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            int i4 = (int) (0.083333336f * ((float) launcherLargeIconSize));
            Rect rect = new Rect(0, 0, launcherLargeIconSize, launcherLargeIconSize);
            int i5 = C0737R.mipmap.shortcut_icon_shadow;
            Resources resources = context.getResources();
            if (VERSION.SDK_INT >= 21) {
                drawableForDensity = resources.getDrawableForDensity(i5, launcherLargeIconDensity, null);
            } else {
                drawableForDensity = resources.getDrawableForDensity(i5, launcherLargeIconDensity);
            }
            if (drawableForDensity instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) drawableForDensity).getBitmap();
            } else {
                bitmap = null;
            }
            canvas.drawBitmap(bitmap, null, rect, new Paint(2));
            int i6 = launcherLargeIconSize - (i4 * 2);
            int round = Math.round(0.0625f * ((float) launcherLargeIconSize));
            float round2 = (float) Math.round(0.33333334f * ((float) launcherLargeIconSize));
            bitmap = new RoundedIconGenerator(i6, i6, round, Color.rgb(i, i2, i3), round2).generateIconForUrl(str, false);
            if (bitmap == null) {
                return null;
            }
            canvas.drawBitmap(bitmap, (float) i4, (float) i4, null);
            return createBitmap;
        } catch (OutOfMemoryError e) {
            Log.m6835w("ShortcutHelper", "OutOfMemoryError while trying to draw bitmap on canvas.", new Object[0]);
            return null;
        }
    }

    private static boolean isWebApkInstalled(String str) {
        if (ChromeWebApkHost.isEnabledInPrefs() && WebApkValidator.queryWebApkPackage(ContextUtils.sApplicationContext, str) != null) {
            return true;
        }
        return false;
    }

    private static int[] getHomeScreenIconAndSplashImageSizes() {
        Context context = ContextUtils.sApplicationContext;
        r1 = new int[4];
        float dimension = context.getResources().getDimension(C0737R.dimen.webapp_home_screen_icon_size);
        float f = context.getResources().getDisplayMetrics().density;
        r1[1] = Math.round(((dimension / f) * (f - 1.0f)) / f);
        r1[2] = getIdealSizeFromResourceInDp(context, C0737R.dimen.webapp_splash_image_size_ideal);
        r1[3] = getIdealSizeFromResourceInDp(context, C0737R.dimen.webapp_splash_image_size_minimum);
        return r1;
    }*/
}
