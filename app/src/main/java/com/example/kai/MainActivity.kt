package com.example.kai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"

//    @Inject
//    lateinit var fragmentFactory: NewsFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
//        inject()
//        setFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    private fun setFragmentFactory(){
//        supportFragmentManager.fragmentFactory = fragmentFactory
//    }
//
//    private fun inject(){
//        (application as Kai).appComponent
//            .inject(this)
//    }
}