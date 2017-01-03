package com.zelo.assignment.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zelo.assignment.App;
import com.zelo.assignment.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan on 3/01/17.
 */

public class VocabularyRepository implements VocabularyDataSource {

    private static final String TAG = "VocabularyRepository";


    @Override
    public void getVocabularyList(@NonNull LoadVocabularyCallback callback) {

        fetchVocabularyList(callback);

    }

    private void fetchVocabularyList(final LoadVocabularyCallback callback) {

        VocabularyApi vocabularyApi=new VocabularyApi(Request.Method.GET, END_POINT, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if(error instanceof NetworkError){
                    callback.onNetworkError(Constants.INTERNET_CONNECTION);
                }else {
                    callback.onNetworkError(Constants.TRY_AGAIN);
                }

            }
        }, new Response.Listener() {
            @Override
            public void onResponse(Object response) {

                List<Vocabulary> vocabularyList=App.getDb().getVocabularyList();
                if(vocabularyList.size()>0) {
                    callback.onVocabularyLoaded(vocabularyList);
                }else {
                    callback.onDataNotAvailable();
                }

            }
        },this);
        App.getVolleyQueue().add(vocabularyApi);


    }

    @Override
    public void insertVocabularyList(@NonNull List<Vocabulary> vocabularyList) {


        List<ContentValues> contentValuesList=new ArrayList<>();
        for (Vocabulary vocabulary : vocabularyList) {

            ContentValues contentValues=new ContentValues();
            contentValues.put(Dbclass.VOCAB_ID,vocabulary.getId());
            contentValues.put(Dbclass.VOCAB_WORD,vocabulary.getWord());
            contentValues.put(Dbclass.VOCAB_MEANING,vocabulary.getMeaning());
            contentValues.put(Dbclass.VOCAB_RATIO,vocabulary.getRatio());
            contentValuesList.add(contentValues);
        }
        App.getDb().insertVocabulary(contentValuesList, SQLiteDatabase.CONFLICT_REPLACE);
    }


}
