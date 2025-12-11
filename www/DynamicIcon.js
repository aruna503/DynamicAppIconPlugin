var DynamicIcon = {
    changeIcon: function(iconName, success, error) {
        cordova.exec(success, error, "DynamicIcon", "changeIcon", [iconName]);
    }
};
 
module.exports = DynamicIcon;
