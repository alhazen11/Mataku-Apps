package com.apps.mataku.ui.setting

interface SettingPresenter {
    fun setViewSetting(settingView: SettingView, data: Map<String, String>)

}