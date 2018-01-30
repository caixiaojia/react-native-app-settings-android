package com.coderge;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.location.LocationManager;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import android.os.Build;
import android.text.TextUtils;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNAppSettingsModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;

    public RNAppSettingsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "AndroidAppSettings";
    }


    @ReactMethod
    public void openAuthoritySetting() {
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + this.reactContext.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        this.reactContext.startActivity(i);
    }

    @ReactMethod
    public void openGPSSetting() {
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        this.reactContext.startActivity(i);
    }

    @ReactMethod
    public void isLocationEnabled(Callback callback) {
        String locationProviders = Settings.Secure.getString(this.reactContext.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        callback.invoke(!TextUtils.isEmpty(locationProviders));
    }

    @ReactMethod
    public void isCoarseLocation(Callback callback) {
        LocationManager lm = (LocationManager)this.reactContext.getSystemService(ReactApplicationContext.LOCATION_SERVICE);
        boolean gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        callback.invoke(gps_enabled && network_enabled);
    }
}
