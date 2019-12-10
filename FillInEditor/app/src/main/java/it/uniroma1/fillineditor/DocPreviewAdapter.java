package it.uniroma1.fillineditor;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DocPreviewAdapter extends RecyclerView.Adapter<DocPreviewAdapter.DocPreviewViewHolder> {

    private String[] mDataset;

    public DocPreviewAdapter(String[] mDataset) {
        this.mDataset = mDataset;
    }



    public static class DocPreviewViewHolder extends RecyclerView.ViewHolder{
        protected TextView mTextView;

        public DocPreviewViewHolder(View v){
            super(v);
            mTextView = v.findViewById(R.id.card_name_text);
        }
    }

    @NonNull
    @Override
    public DocPreviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_doc_preview, viewGroup, false);
        DocPreviewViewHolder vh = new DocPreviewViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DocPreviewViewHolder docPreviewViewHolder, int i) {
        docPreviewViewHolder.mTextView.setText(mDataset[i]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
