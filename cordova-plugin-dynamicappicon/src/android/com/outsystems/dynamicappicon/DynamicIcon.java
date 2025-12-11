package com.outsystems.dynamicappicon;
 
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.ComponentName;
 
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
 
public class DynamicIcon extends CordovaPlugin {
 
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("changeIcon".equals(action)) {
            String icon = args.optString(0);
            switchIcon(icon, callbackContext);
            return true;
        }
        return false;
    }
 
    private void switchIcon(String icon, CallbackContext callback) {
 
        Context context = cordova.getActivity().getApplicationContext();
        PackageManager pm = context.getPackageManager();
        String pkg = context.getPackageName();
 
        String normal = pkg + ".IconNormal";
        String prem = pkg + ".IconPremium";
        String priv = pkg + ".IconPrivate";
 
        disableAll(pm, pkg);
 
        if ("premium".equalsIgnoreCase(icon)) {
            enable(pm, prem);
        } else if ("private".equalsIgnoreCase(icon)) {
            enable(pm, priv);
        } else {
            enable(pm, normal);
        }
 
        callback.success("Icon changed");
    }
 
    private void disableAll(PackageManager pm, String pkg) {
        enable(pm, pkg + ".IconNormal", false);
        enable(pm, pkg + ".IconPremium", false);
        enable(pm, pkg + ".IconPrivate", false);
    }
 
    private void enable(PackageManager pm, String name) {
        enable(pm, name, true);
    }
 
    private void enable(PackageManager pm, String name, boolean state) {
        ComponentName cn = new ComponentName(name.substring(0, name.lastIndexOf(".")), name);
        pm.setComponentEnabledSetting(
                cn,
                state ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                        : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
        );
    }
}
 
