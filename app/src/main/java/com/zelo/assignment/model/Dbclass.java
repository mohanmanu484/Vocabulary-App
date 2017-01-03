package com.zelo.assignment.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by mohan on 3/01/17.
 */

public class Dbclass extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "mvp.db";


    public void initialize() {
        db = this.getWritableDatabase();
    }

    private static Dbclass dbInstance;

    public static synchronized Dbclass getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = new Dbclass(context);
            dbInstance.initialize();
        }
        return dbInstance;
    }

    public Dbclass(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * TABLE FOR PREFILL RESPONSE
     */
    public static final String TABLE_VOCABULARY = "vocabulary_table";
    public static final String VOCAB_ID = "_id";
    public static final String VOCAB_WORD = "word";
    public static final String VOCAB_MEANING = "meaning";
    public static final String VOCAB_RATIO = "ratio";

    public static final String CREATE_VOCABULARY_TABLE ="CREATE TABLE "
            + TABLE_VOCABULARY + " ("
            + VOCAB_ID + " INTEGER PRIMARY KEY, "
            + VOCAB_WORD + " text, "
            + VOCAB_MEANING+ " text, "
            + VOCAB_RATIO + " FLOAT)";
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_VOCABULARY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXIST "+TABLE_VOCABULARY);
    }


    public List<Vocabulary> getVocabularyList(){

        Cursor cursor=db.query(TABLE_VOCABULARY,null,null,null,null,null,null,null);

        List<Vocabulary> vocabularyList=new ArrayList<>();

        if(cursor.moveToFirst()){

            for (int i = 0; i < cursor.getCount(); i++) {

                Vocabulary vocabulary=new Vocabulary(cursor.getInt(cursor.getColumnIndex(VOCAB_ID)),cursor.getString(cursor.getColumnIndex(VOCAB_WORD)));
                vocabulary.setMeaning(cursor.getString(cursor.getColumnIndex(VOCAB_MEANING)));
                vocabulary.setRatio(cursor.getFloat(cursor.getColumnIndex(VOCAB_RATIO)));
                vocabularyList.add(vocabulary);
                cursor.moveToNext();
            }
        }
        return vocabularyList;
    }


    public void insertVocabulary(List<ContentValues> contentValues,int flag){

        try {
            db.beginTransaction();
            for (ContentValues entry : contentValues) {
                db.insertWithOnConflict(TABLE_VOCABULARY, null, entry, flag);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            Log.d(TAG, "insertVocabulary: " + "successful");
        } catch (Exception e) {
            Log.e(TAG, "insertVocabulary: " + "error inserting relations");
            e.printStackTrace();
        }

    }


}
