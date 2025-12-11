package com.dynamicappicon.plugin;   // CHANGE THIS LINE

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

public class DynamicIcon extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if (action.equals("changeIcon")) {
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

        // Define all alias components
        ComponentName iconNormal   = new ComponentName(context, "com.dynamicappicon.plugin.IconNormal");
        ComponentName iconPremium  = new ComponentName(context, "com.dynamicappicon.plugin.IconPremium");
        ComponentName iconPrivate  = new ComponentName(context, "com.dynamicappicon.plugin.IconPrivate");

        // Disable all icons first
        pm.setComponentEnabledSetting(iconNormal, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(iconPremium, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(iconPrivate, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        // Enable the selected one
        if (iconName.equalsIgnoreCase("normal")) {
            pm.setComponentEnabledSetting(iconNormal, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        }
        else if (iconName.equalsIgnoreCase("premium")) {
            pm.setComponentEnabledSetting(iconPremium, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        }
        else if (iconName.equalsIgnoreCase("private")) {
            pm.setComponentEnabledSetting(iconPrivate, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        }
    }
}
