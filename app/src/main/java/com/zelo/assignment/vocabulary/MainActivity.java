package com.zelo.assignment.vocabulary;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.WindowManager;

import com.zelo.assignment.R;
import com.zelo.assignment.model.VocabularyRepository;

public class MainActivity extends BaseActivity {

    VocabularyPresenter mVocabularyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        VocabularyFragment vocabularyFragment =
                (VocabularyFragment) getSupportFragmentManager().findFragmentById(R.id.content_main);
        if (vocabularyFragment == null) {
            // Create the fragment
            vocabularyFragment = VocabularyFragment.newInstance();
            addFragmentToActivity(
                     R.id.content_main,vocabularyFragment);
        }

        // Create the presenter
        mVocabularyPresenter = new VocabularyPresenter(
                new VocabularyRepository(), vocabularyFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
