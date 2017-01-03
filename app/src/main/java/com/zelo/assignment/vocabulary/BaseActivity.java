package com.zelo.assignment.vocabulary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mohan on 3/01/17.
 */

public class BaseActivity extends AppCompatActivity {


    public void addFragmentToActivity(int containerId, Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(containerId,fragment).commit();
    }
}
