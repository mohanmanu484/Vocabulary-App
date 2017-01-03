package com.zelo.assignment.vocabulary;

import android.support.annotation.NonNull;

import com.zelo.assignment.model.Vocabulary;
import com.zelo.assignment.model.VocabularyDataSource;
import com.zelo.assignment.model.VocabularyRepository;

import java.util.List;


/**
 * Created by mohan on 4/10/16.
 */

public class VocabularyPresenter implements VocabularyContract.Presenter {

    private final VocabularyRepository mVocabularyRepository;
    private final VocabularyContract.View mVocabularyView;

    public VocabularyPresenter(@NonNull VocabularyRepository vocabularyRepository, @NonNull VocabularyContract.View mVocabularyView) {

        if (null == vocabularyRepository) {
            throw new IllegalArgumentException("vocabularyRepository cannot be null");
        }
        if (null == mVocabularyView) {
            throw new IllegalArgumentException("mVocabularyView cannot be null!");
        }
        mVocabularyRepository = vocabularyRepository;
        this.mVocabularyView = mVocabularyView;

        this.mVocabularyView.setPresenter(this);
    }



    @Override
    public void start() {
        mVocabularyView.setTitle("Vocabulary");
        loadVocabularyList();
    }

    private void loadVocabularyList() {
        mVocabularyView.showProgress();
        mVocabularyRepository.getVocabularyList(new VocabularyDataSource.LoadVocabularyCallback() {

            @Override
            public void onVocabularyLoaded(List<Vocabulary> vocabularies) {
                if(!mVocabularyView.isActive()){
                    return;
                }

                mVocabularyView.hideProgress();
                mVocabularyView.showVocabularyList(vocabularies);
            }

            @Override
            public void onDataNotAvailable() {

                if(!mVocabularyView.isActive()){
                    return;
                }
                mVocabularyView.hideProgress();
            }

            @Override
            public void onNetworkError(String message) {

                if(!mVocabularyView.isActive()){
                    return;
                }
                mVocabularyView.hideProgress();
                mVocabularyView.showNetworkError(message);
            }
        });
    }

    @Override
    public void refreshList() {
        loadVocabularyList();
    }
}