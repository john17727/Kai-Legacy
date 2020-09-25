package com.example.kai.framework.presentation.common

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.kai.Kai
import com.example.kai.di.AppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
abstract class BaseNewsFragment
constructor(
    @LayoutRes private val layoutRes: Int
) : Fragment(layoutRes) {

    abstract fun inject()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    fun getAppComponent(): AppComponent {
        return activity?.run {
            (application as Kai).appComponent
        }?: throw  Exception("AppComponent is null")
    }
}