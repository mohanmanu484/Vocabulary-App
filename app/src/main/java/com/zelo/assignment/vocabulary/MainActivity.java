package com.zelo.assignment.vocabulary;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
