package de.bornholdtlee.defaultproject.ui;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import de.bornholdtlee.defaultproject.DefaultApplication;
import de.bornholdtlee.defaultproject.injection.components.DefaultApplicationComponent;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public static void showFragment(BaseFragment fragment, FragmentManager fragmentManager, int containerViewResId, boolean shouldAddToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String fragmentName = fragment.getClass().getName();
        BaseFragment fragmentByTag = (BaseFragment) fragmentManager.findFragmentByTag(fragmentName);

        if (null == fragmentByTag) {
            fragmentTransaction.replace(containerViewResId, fragment, fragmentName);
        } else {
            fragmentTransaction.replace(containerViewResId, fragmentByTag, fragmentName);
        }

        if (shouldAddToBackStack) {
            fragmentTransaction.addToBackStack(fragmentName);
        }
        fragmentTransaction.commit();
    }

    public DefaultApplicationComponent getDefaultApplicationComponent() {
        return ((DefaultApplication) getApplication()).getDefaultApplicationComponent();
    }
}