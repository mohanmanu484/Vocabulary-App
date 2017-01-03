package com.zelo.assignment;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zelo.assignment.model.Dbclass;

/**
 * Created by mohan on 3/01/17.
 */

public class App extends Application {

    private static Dbclass db;
    private static Context appContext;
    private static RequestQueue volleyQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext=this;
    }

    public static Context getAppContext() {
        return appContext;
    }
    public static synchronized Dbclass getDb() {
        if (db == null) {
            db = Dbclass.getInstance(getAppContext());
        }
        return db;
    }

    public static RequestQueue getVolleyQueue() {
        if (volleyQueue == null) {
            volleyQueue = Volley.newRequestQueue(getAppContext());
        }
        return volleyQueue;
    }
}
