package com.mkxq.lite
import com.alibaba.android.arouter.launcher.ARouter
import com.mkxq.common.base.BaseActivity
import com.mkxq.common.utils.ARoutePath
import com.mkxq.common.utils.ToastUtil
import com.mkxq.lite.databinding.ActivitySplashBinding

class SplashActivity :BaseActivity<ActivitySplashBinding>() {
    override fun getLayoutID(): Int = R.layout.activity_splash
    override fun init() {
        ToastUtil.init(MyApplication.instance())
        ARouter.getInstance().build(ARoutePath.PATH_MAIN).navigation()
        finish()
    }
}