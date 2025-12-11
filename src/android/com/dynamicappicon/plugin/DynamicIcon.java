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

        // Fully-qualified alias names
        String base = "com.outsystemscloud.personalyybrgx5w.TestPlugin.";

        ComponentName normal  = new ComponentName(context, base + "IconNormal");
        ComponentName premium = new ComponentName(context, base + "IconPremium");
        ComponentName priv    = new ComponentName(context, base + "IconPrivate");

        int enable  = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
        int disable = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        int flags   = PackageManager.DONT_KILL_APP;

        // Disable all
        pm.setComponentEnabledSetting(normal, disable, flags);
        pm.setComponentEnabledSetting(premium, disable, flags);
        pm.setComponentEnabledSetting(priv, disable, flags);

        // Enable selected
        if ("premium".equalsIgnoreCase(iconName)) {
            pm.setComponentEnabledSetting(premium, enable, flags);
        }
        else if ("private".equalsIgnoreCase(iconName)) {
            pm.setComponentEnabledSetting(priv, enable, flags);
        }
        else {
            pm.setComponentEnabledSetting(normal, enable, flags);
        }
    }
}
