package com.zelo.assignment.model;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by mohan on 3/01/17.
 */

public interface VocabularyDataSource {

    public static final String END_POINT="http://appsculture.com/vocab/words.json";
    public static final String IMAGES_END_POINT="http://appsculture.com/vocab/images/";

    interface LoadVocabularyCallback {

        void onVocabularyLoaded(List<Vocabulary> vocabularies);

        void onDataNotAvailable();

        void onNetworkError(String message);
    }

    void getVocabularyList(@NonNull LoadVocabularyCallback callback);

    void insertVocabularyList(@NonNull List<Vocabulary> vocabularyList);
}
