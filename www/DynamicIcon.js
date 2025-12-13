var DynamicIcon = {
    changeIcon: function (iconName, success, error) {
        cordova.exec(
            success || function () {},
            error || function () {},
            "DynamicIcon",
            "changeIcon",
            [iconName]
        );
    }
};

module.exports = DynamicIcon;
