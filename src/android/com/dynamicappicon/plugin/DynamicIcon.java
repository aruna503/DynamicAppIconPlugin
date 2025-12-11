package com.dynamicappicon.plugin;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

public class DynamicIcon extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("changeIcon".equals(action)) {
            try {
                String iconName = args.getString(0);
                changeIcon(iconName);
                callbackContext.success();
            } catch (Exception ex) {
                callbackContext.error("Error: " + ex.getMessage());
            }
            return true;
        }
        return false;
    }

    private void changeIcon(String iconName) {

        Context context = cordova.getActivity();
        PackageManager pm = context.getPackageManager();

        ComponentName iconNormal  = new ComponentName(context, "com.outsystemscloud.personalyybrgx5w.TestPlugin.IconNormal");
        ComponentName iconPremium = new ComponentName(context, "com.outsystemscloud.personalyybrgx5w.TestPlugin.IconPremium");
        ComponentName iconPrivate = new ComponentName(context, "com.outsystemscloud.personalyybrgx5w.TestPlugin.IconPrivate");

        int flags = PackageManager.DONT_KILL_APP | PackageManager.SYNCHRONOUS;

        // Disable all
        pm.setComponentEnabledSetting(iconNormal,  PackageManager.COMPONENT_ENABLED_STATE_DISABLED, flags);
        pm.setComponentEnabledSetting(iconPremium, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, flags);
        pm.setComponentEnabledSetting(iconPrivate, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, flags);

        // Enable selected
        if ("normal".equalsIgnoreCase(iconName)) {
            pm.setComponentEnabledSetting(iconNormal, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, flags);
        }
        else if ("premium".equalsIgnoreCase(iconName)) {
            pm.setComponentEnabledSetting(iconPremium, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, flags);
        }
        else if ("private".equalsIgnoreCase(iconName)) {
            pm.setComponentEnabledSetting(iconPrivate, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, flags);
        }
    }
}
