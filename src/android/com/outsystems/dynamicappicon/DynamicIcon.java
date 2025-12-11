package com.outsystemscloud.personalyybrgx5w.TestPlugin;
 
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
 
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
 
public class DynamicIcon extends CordovaPlugin {
 
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("changeIcon".equals(action)) {
 
            String iconName = args.getString(0);
            changeIcon(iconName);
 
            callbackContext.success("Icon switch executed");
            return true;
        }
        return false;
    }
 
    private void changeIcon(String iconName) {
 
        Context context = cordova.getActivity().getApplicationContext();
        PackageManager pm = context.getPackageManager();
 
        String pkg = context.getPackageName();
 
        ComponentName normal = new ComponentName(pkg, pkg + ".IconNormal");
        ComponentName premium = new ComponentName(pkg, pkg + ".IconPremium");
        ComponentName privateIcon = new ComponentName(pkg, pkg + ".IconPrivate");
 
        pm.setComponentEnabledSetting(normal,
                iconName.equalsIgnoreCase("normal")
                        ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                        : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
 
        pm.setComponentEnabledSetting(premium,
                iconName.equalsIgnoreCase("premium")
                        ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                        : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
 
        pm.setComponentEnabledSetting(privateIcon,
                iconName.equalsIgnoreCase("private")
                        ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                        : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
 
