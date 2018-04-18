package de.bornholdtlee.defaultproject.ui.main;

import android.os.Bundle;

import de.bornholdtlee.defaultproject.R;
import de.bornholdtlee.defaultproject.ui.BaseActivity;
import de.bornholdtlee.defaultproject.utils.HockeyAppUtils;

public class MainActivity extends BaseActivity {

    public static final int CONTAINER = R.id.activity_main_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showFragment(new MainFragment(), getSupportFragmentManager(), CONTAINER, true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /**
         * Info:
         * Diese Abfrage sollte nicht in der Splashscreen-Activity ausgeführt werden,
         * sondern erst in der darauf folgenden Activity
         */
        HockeyAppUtils.checkForCrashes(this);
    }
}