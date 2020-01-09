package it.uniroma1.fillineditor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import it.uniroma1.fillineditor.models.DynamicDoc;
import it.uniroma1.fillineditor.models.DynamicDocLibrary;

public class DocPreviewAdapter extends RecyclerView.Adapter<DocPreviewAdapter.DocPreviewViewHolder> {

    private ArrayList<DynamicDoc> mDataset;

//    public DocPreviewAdapter(ArrayList<DynamicDoc> mDataset) {
//        this.mDataset = mDataset;
//    }
    public DocPreviewAdapter(DynamicDocLibrary mDataset) {
        this.mDataset = mDataset.getLibrary();
    }



    public static class DocPreviewViewHolder extends RecyclerView.ViewHolder{
        protected TextView mTextView;

        public DocPreviewViewHolder(final View v){
            super(v);
            mTextView = v.findViewById(R.id.card_name_text);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    v.getContext().startActivity(new Intent(v.getContext(), CompileDocActivity.class));
                }
            });
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
        docPreviewViewHolder.mTextView.setText(mDataset.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
