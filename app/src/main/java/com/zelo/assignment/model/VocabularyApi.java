package com.zelo.assignment.model;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.zelo.assignment.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan on 3/1/17.
 */

public class VocabularyApi extends Request<JSONObject> {

    private Response.ErrorListener errorListener;
    private Response.Listener listener;
    VocabularyDataSource vocabularyDataSource;

    public VocabularyApi(int method, String url, Response.ErrorListener errorListener, Response.Listener listener,VocabularyRepository vocabularyDataSource) {
        super(method, url, errorListener);
        this.listener = listener;
        this.errorListener = errorListener;
        this.vocabularyDataSource=vocabularyDataSource;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            JSONObject respObj = new JSONObject(jsonString);

            JSONArray vocabularyArray = respObj.optJSONArray(Constants.WORDS);
            List<Vocabulary> vocabularyList=new ArrayList<>();
            if (vocabularyArray != null) {

                for (int i = 0; i < vocabularyArray.length(); i++) {

                    JSONObject vocabularyObj = vocabularyArray.optJSONObject(i);
                    if (vocabularyObj != null) {

                        Vocabulary vocabulary = new Vocabulary(vocabularyObj.getInt(Constants.ID), vocabularyObj.getString(Constants.WORD));
                        vocabulary.setMeaning(vocabularyObj.getString(Constants.MEANING));
                        float ratio=(float) vocabularyObj.getDouble(Constants.RATIO);
                        vocabulary.setRatio((float) vocabularyObj.getDouble(Constants.RATIO));
                        if(ratio!=-1){
                            vocabularyList.add(vocabulary);
                        }
                    }
                }
            }
            vocabularyDataSource.insertVocabularyList(vocabularyList);
            return Response.success(respObj, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            je.printStackTrace();
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {

        listener.onResponse(response);
    }
}
