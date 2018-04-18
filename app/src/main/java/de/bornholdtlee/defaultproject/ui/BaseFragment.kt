package de.bornholdtlee.defaultproject.ui

import android.support.v4.app.Fragment

import de.bornholdtlee.defaultproject.injection.components.DefaultApplicationComponent

abstract class BaseFragment : Fragment() {

    val defaultApplicationComponent: DefaultApplicationComponent
        get() = (activity as BaseActivity).defaultApplicationComponent
}