package it.uniroma1.fillineditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import it.uniroma1.fillineditor.viewComponents.CustomStaticTextView;
import it.uniroma1.fillineditor.viewComponents.StaticBoxesWord;

import static it.uniroma1.fillineditor.viewComponents.DynamicDocViewComponents.STATIC_BOXES_WORD_ID;
import static it.uniroma1.fillineditor.viewComponents.DynamicDocViewComponents.STATIC_TEXT_ID;

class AdapterDoc extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private String[] datasetContent;

public AdapterDoc(Context context, String[] config){
    this.context=context;
    this.datasetContent = config;
}

    public class ViewHolderStaticText extends RecyclerView.ViewHolder {
        protected CustomStaticTextView text;

        public ViewHolderStaticText(CustomStaticTextView v) {
            super(v);
            text = v;
        }
    }

    public class ViewHolderStaticBoxesWord extends RecyclerView.ViewHolder{
        StaticBoxesWord word;
        public ViewHolderStaticBoxesWord(View v){
            super(v);
            this.word = (StaticBoxesWord)v;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case STATIC_TEXT_ID:
                CustomStaticTextView v = (CustomStaticTextView) LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.sample_static_text, parent, false);
                return new ViewHolderStaticText(v);
            case STATIC_BOXES_WORD_ID:
                StaticBoxesWord staticBoxesWord = (StaticBoxesWord) LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.sample_static_boxes_word, parent, false);
                return new ViewHolderStaticBoxesWord(staticBoxesWord);
        }
        throw new RuntimeException("Component number is not valid");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case STATIC_TEXT_ID:
                ViewHolderStaticText holderStaticText = (ViewHolderStaticText) holder;
                holderStaticText.text.setContents(datasetContent[position]);
                break;
            case STATIC_BOXES_WORD_ID:
                ViewHolderStaticBoxesWord holderStaticBoxesWord = (ViewHolderStaticBoxesWord)holder;
                holderStaticBoxesWord.word.setActivity((AppCompatActivity)context);
                holderStaticBoxesWord.word.setContents(datasetContent[position]);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return datasetContent.length;
    }

    public Context getContext(){return this.context;}

    @Override
    public int getItemViewType(int position){
    return Integer.parseInt(String.valueOf(datasetContent[position].charAt(0)));
    }
}
