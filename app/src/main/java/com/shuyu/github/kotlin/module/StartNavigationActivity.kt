package com.shuyu.github.kotlin.module

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.shuyu.github.kotlin.BuildConfig
import com.shuyu.github.kotlin.R
import com.shuyu.github.kotlin.module.service.LocalService
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * 通过 navigation 实现的首页
 * Created by guoshuyu
 * Date: 2018-11-02
 */
class StartNavigationActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_navigation)

        if(BuildConfig.DEBUG) {
            //如果是调试版本，启动后台服务测试AIDL
            startService(Intent(this, LocalService::class.java))
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onBackPressed() {
        val fragment = supportFragmentManager.primaryNavigationFragment
        if (fragment is NavHostFragment) {
            if (fragment.navController.currentDestination?.id == R.id.loginFragment) {
                super.onBackPressed()
            }
        }
    }
}