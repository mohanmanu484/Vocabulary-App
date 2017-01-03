package com.zelo.assignment.vocabulary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zelo.assignment.R;
import com.zelo.assignment.model.Vocabulary;
import com.zelo.assignment.model.VocabularyDataSource;

import java.util.List;

/**
 * Created by mohan on 3/1/17.
 */

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.VocabularyHolder> {


    private List<Vocabulary> vocabularyList;

    public VocabularyAdapter(List<Vocabulary> vocabularyList) {
        this.vocabularyList = vocabularyList;
    }

    public void updateList(List<Vocabulary> vocabularyList) {
        this.vocabularyList = vocabularyList;
        notifyDataSetChanged();
    }

    private Context context;

    public Context getContext() {
        return context;
    }

    public void update(int pos){
       // notifyItemChanged(pos);
    }

    @Override
    public VocabularyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_vocabulary_item,parent,false);
        return new VocabularyHolder(view);
    }

    @Override
    public void onBindViewHolder(VocabularyHolder holder, int position) {

       // holder.editTextListener.updatePosition(holder.getAdapterPosition());
        holder.bindViewWithVocabulary(vocabularyList.get(position),getContext(),position);

    }

    @Override
    public int getItemCount() {
        return vocabularyList.size();
    }

    public  class VocabularyHolder extends RecyclerView.ViewHolder{

        private EditText mWord;
        private TextView meaning;
        private ImageView vocabularyImage;


        public VocabularyHolder(View itemView) {
            super(itemView);

            mWord= (EditText) itemView.findViewById(R.id.etVocaburayWord);
            meaning= (TextView) itemView.findViewById(R.id.tvVocabularyMeaning);
            vocabularyImage= (ImageView) itemView.findViewById(R.id.ivVocabulary);
        }

        public void bindViewWithVocabulary(final Vocabulary vocabulary, Context context, final int pos){
            mWord.setText(vocabularyList.get(pos).getWord());
            mWord.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                    vocabularyList.get(getAdapterPosition()).setWord(""+charSequence);
                    //notifyDataSetChanged();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            meaning.setText(vocabularyList.get(pos).getMeaning());
            Picasso.with(context)
                    .load(VocabularyDataSource.IMAGES_END_POINT+vocabulary.getId()+".png")
                    .placeholder(R.drawable.ic_place_holder)
                    .into(vocabularyImage);
        }


    }


}
