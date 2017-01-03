package com.zelo.assignment.vocabulary;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zelo.assignment.R;
import com.zelo.assignment.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan on 3/1/17.
 */

public class VocabularyFragment extends BaseFragment implements VocabularyContract.View {

    private VocabularyContract.Presenter mPresenter;
    private ActionBar mActionBar;
    private VocabularyAdapter mVocabularyAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVocabularyAdapter = new VocabularyAdapter(new ArrayList<Vocabulary>(0));
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vocabulary_list_fragment, container, false);
        RecyclerView vocabularyList = (RecyclerView) view.findViewById(R.id.rvVocabularyList);
        vocabularyList.setLayoutManager(new LinearLayoutManager(getContext()));
        vocabularyList.setHasFixedSize(true);
        vocabularyList.setAdapter(mVocabularyAdapter);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.action_settings){
            mPresenter.refreshList();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setTitle(String title) {
        ((BaseActivity) getContext()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void showVocabularyList(List<Vocabulary> vocabularies) {
        mVocabularyAdapter.updateList(vocabularies);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showNetworkError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(VocabularyContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public static VocabularyFragment newInstance() {
        return new VocabularyFragment();
    }
}
