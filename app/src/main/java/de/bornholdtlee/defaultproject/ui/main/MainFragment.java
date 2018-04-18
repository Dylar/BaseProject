package de.bornholdtlee.defaultproject.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.bornholdtlee.defaultproject.R;
import de.bornholdtlee.defaultproject.controller.DefaultController;
import de.bornholdtlee.defaultproject.model.DefaultModel;
import de.bornholdtlee.defaultproject.ui.BaseFragment;
import de.bornholdtlee.defaultproject.utils.Logger;
import de.bornholdtlee.defaultproject.utils.SharedPreferencesUtils;
import io.objectbox.Box;

public class MainFragment extends BaseFragment implements DefaultController.Callback {

    @Inject
    Box<DefaultModel> defaultModelBox;

    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;

    @Inject
    DefaultController defaultController;

    @BindView(R.id.fragment_main_welcome_text)
    TextView welcomeText;

    private int counter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDefaultApplicationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_main, null, false);

        /*
         * Init ButterKnife binding in every class
         */
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        makeTestApiCall();
        checkDB();
        readPreferences();
    }

    private void readPreferences() {
        int test = sharedPreferencesUtils.getInt("TEST", -1);
        Logger.error("preferences: " + test);
    }

    private void checkDB() {
        List<DefaultModel> all = defaultModelBox.getAll();
        Logger.error("There are " + all.size() + " models in the database.");
    }

    private void makeTestApiCall() {
        defaultController.startDownload(this);
    }

    @OnClick(R.id.fragment_main_counter_button)
    public void incrementCounter() {
        welcomeText.setText(String.valueOf(++counter));
    }

    @Override
    public void onSuccess() {
        Logger.error("Test api call success");
    }

    @Override
    public void onClientError() {
        Logger.error("Test api call failure");
    }
}