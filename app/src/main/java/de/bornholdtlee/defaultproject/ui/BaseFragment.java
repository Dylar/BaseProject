package de.bornholdtlee.defaultproject.ui;

import android.support.v4.app.Fragment;

import de.bornholdtlee.defaultproject.injection.components.DefaultApplicationComponent;

public abstract class BaseFragment extends Fragment {

    public DefaultApplicationComponent getDefaultApplicationComponent() {
        return ((BaseActivity) getActivity()).getDefaultApplicationComponent();
    }
}