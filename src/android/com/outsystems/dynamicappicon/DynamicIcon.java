<?xml version="1.0" encoding="utf-8"?>
<plugin id="cordova-plugin-dynamicappicon" version="1.0.0"
        xmlns="http://apache.org/cordova/ns/plugins"
        xmlns:android="http://schemas.android.com/apk/res/android">
 
    <name>Dynamic App Icon</name>
 
    <js-module src="www/DynamicIcon.js" name="DynamicIcon">
        <clobbers target="DynamicIcon" />
    </js-module>
 
    <platform name="android">
 
        <config-file target="config.xml" parent="/*">
            <feature name="DynamicIcon">
                <param name="android-package" value="com.outsystemscloud.personalyybrgx5w.TestPlugin.DynamicIcon" />
            </feature>
        </config-file>
 
        <config-file target="AndroidManifest.xml" parent="/manifest/application">
            
            <activity-alias
                android:name="com.outsystemscloud.personalyybrgx5w.TestPlugin.IconNormal"
                android:enabled="true"
                android:exported="true"
                android:icon="@mipmap/ic_launcher"
                android:label="@string/app_name"
                android:targetActivity=".MainActivity">
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />
                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </activity-alias>
 
            <activity-alias
                android:name="com.outsystemscloud.personalyybrgx5w.TestPlugin.IconPremium"
                android:enabled="false"
                android:exported="true"
                android:icon="@mipmap/ic_launcher"
                android:label="@string/app_name"
                android:targetActivity=".MainActivity" />
 
            <activity-alias
                android:name="com.outsystemscloud.personalyybrgx5w.TestPlugin.IconPrivate"
                android:enabled="false"
                android:exported="true"
                android:icon="@mipmap/ic_launcher"
                android:label="@string/app_name"
                android:targetActivity=".MainActivity" />
 
        </config-file>
 
        <source-file
            src="src/android/DynamicIcon.java"
            target-dir="src/com/outsystemscloud/personalyybrgx5w/TestPlugin" />
 
    </platform>
 
</plugin>
