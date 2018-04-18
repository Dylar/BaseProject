package de.bornholdtlee.defaultproject.ui

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

import de.bornholdtlee.defaultproject.DefaultApplication
import de.bornholdtlee.defaultproject.injection.components.DefaultApplicationComponent
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

abstract class BaseActivity : AppCompatActivity() {

    val defaultApplicationComponent: DefaultApplicationComponent
        get() = (application as DefaultApplication).defaultApplicationComponent

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    companion object {

        fun showFragment(fragment: BaseFragment, fragmentManager: FragmentManager, containerViewResId: Int, shouldAddToBackStack: Boolean) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragmentName = fragment.javaClass.name
            val fragmentByTag = fragmentManager.findFragmentByTag(fragmentName) as BaseFragment

            if (null == fragmentByTag) {
                fragmentTransaction.replace(containerViewResId, fragment, fragmentName)
            } else {
                fragmentTransaction.replace(containerViewResId, fragmentByTag, fragmentName)
            }

            if (shouldAddToBackStack) {
                fragmentTransaction.addToBackStack(fragmentName)
            }
            fragmentTransaction.commit()
        }
    }
}