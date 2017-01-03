package com.zelo.assignment.vocabulary;

import com.zelo.assignment.model.Vocabulary;

import java.util.List;

/**
 * Created by mohan on 3/01/17.
 */

public interface VocabularyContract {


    interface View extends BaseView<Presenter>{
        void showProgress();
        void hideProgress();
        void setTitle(String title);
        void showVocabularyList(List<Vocabulary> vocabularies);
        boolean isActive();
        void showNetworkError(String message);
    }
    interface Presenter extends BasePresenter{
        void refreshList();
    }


}
