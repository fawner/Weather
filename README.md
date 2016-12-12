# Weather
    项目为Android应用，最低版本为Android 4.4
##项目来源
    项目来源于本人的课程设计
##项目说明
    项目为天气预报软件
###使用软件
    Android Studio
###测试环境
    真机测试（华为H30-U10）及模拟器测试（Nexus 5 API 19  Resolution:1080*1920:xxdpi  Target:Android 4.4 GoogleAPI  CPU:*86 ）
##设计说明
###应用入口：SplashActivity
    入口实现功能：监测是否第一次进入应用以及是否设置城市信息
    逻辑跳转：第一次进入应用或没有设置城市信息→真 设置界面（SetActivity），
    否则进入主界面（MainActivity）
###设置界面：SetActivity
    界面实现功能：搜索城市信息并保存所选城市
    逻辑跳转：监测文本框是否有信息→否 退出Activity并提示（Toast），
    否则调用网络查询类（QueryGet）查询城市→加载返回信息，
    异步显示查询列表→点击列表项保存城市ID，跳回MainActivity
###主界面：MainActivity
    实现功能：加载界面并显示查询数据
    逻辑跳转：调用网络查询类（QueryGet）查询数据并保存
###主界面加载界面：FragmentWeatherNow、FragmentWeatherDaily、FragmentLifeSuggestion
    实现功能：显示查询数据
    整体加载数据逻辑：监测数据是否有效→否 显示错误信息，否则加载数据（设置城市后需重新启动应用）
##天气数据来源
    心知天气，免费
###声明
    所有人可参与并对其进行修改，由于本人为初学者，技术有限，有不足的地方希望能理解。
    对其进行发布时希望您使用自己的数据获取来源，请删除我的心知天气的ID，我将感激不尽
