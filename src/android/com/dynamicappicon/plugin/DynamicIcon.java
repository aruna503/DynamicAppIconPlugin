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

        String pkg = context.getPackageName();  // com.outsystemscloud.xx.xx

        // Dynamic launcher aliases
        ComponentName iconNormal  = new ComponentName(pkg, pkg + ".IconNormal");
        ComponentName iconPremium = new ComponentName(pkg, pkg + ".IconPremium");
        ComponentName iconPrivate = new ComponentName(pkg, pkg + ".IconPrivate");

        int enable  = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
        int disable = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        int flags   = PackageManager.DONT_KILL_APP;

        // Disable all icons first
        pm.setComponentEnabledSetting(iconNormal, disable, flags);
        pm.setComponentEnabledSetting(iconPremium, disable, flags);
        pm.setComponentEnabledSetting(iconPrivate, disable, flags);

        // Enable selected icon
        if ("premium".equalsIgnoreCase(iconName)) {
            pm.setComponentEnabledSetting(iconPremium, enable, flags);
        }
        else if ("private".equalsIgnoreCase(iconName)) {
            pm.setComponentEnabledSetting(iconPrivate, enable, flags);
        }
        else {
            // Default fallback → normal icon
            pm.setComponentEnabledSetting(iconNormal, enable, flags);
        }
    }
}
