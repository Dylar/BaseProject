package de.bornholdtlee.baseproject.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction

import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.BaseActivity
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.base.BaseFragment
import de.bornholdtlee.baseproject.enums.AnimationType

class UiUtils(application: BaseApplication) : BaseUtils(application) {

    fun convertDpToPx(dp: Float): Float {
        val metrics = Resources.getSystem().displayMetrics
        return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun convertPxToDp(px: Float): Float {
        val metrics = Resources.getSystem().displayMetrics
        return px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun closeKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun openKeyboard(context: Context, view: View) {
        (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInputFromWindow(view.applicationWindowToken, InputMethodManager.SHOW_FORCED, 0)
        view.requestFocus()
    }

    ////Manipulate view

    fun startRotation(view: View) {
        val rotate = RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.repeatCount = Animation.INFINITE
        rotate.repeatMode = Animation.RESTART
        rotate.duration = 1000
        rotate.interpolator = LinearInterpolator()

        view.startAnimation(rotate)
    }

    ////Set view attribute

    fun setColor(imageView: ImageView, colorResId: Int) {
        imageView.setColorFilter(ContextCompat.getColor(imageView.context, colorResId), android.graphics.PorterDuff.Mode.SRC_IN)
    }

    fun setTextColor(textView: TextView, colorResId: Int) {
        textView.setTextColor(ContextCompat.getColor(textView.context, colorResId))
    }

    ////Animate transition

    fun setAnimation(activity: BaseActivity, slideIn: Boolean) {
        setAnimation(activity, activity.animationType, slideIn)
    }

    fun setAnimation(activity: BaseActivity, animationType: AnimationType, slideIn: Boolean) {
        if (AnimationType.NONE != animationType) {
            activity.overridePendingTransition(if (slideIn) animationType.slideIn else R.anim.stay, if (slideIn) R.anim.stay else animationType.slideOut)
        }
    }

    fun setAnimation(fragment: BaseFragment, fragmentTransaction: FragmentTransaction) {
        val animationType = fragment.animationType
        if (AnimationType.NONE != animationType) {
            fragmentTransaction.setCustomAnimations(animationType.slideIn, animationType.slideOut, animationType.popIn, animationType.popOut)
        }
    }
}