package it.uniroma1.fillineditor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import it.uniroma1.fillineditor.models.DynamicDoc;
import it.uniroma1.fillineditor.models.DynamicDocLibrary;

public class DocPreviewAdapter extends RecyclerView.Adapter<DocPreviewAdapter.DocPreviewViewHolder> {

    private ArrayList<DynamicDoc> mDataset;

    public DocPreviewAdapter(DynamicDocLibrary mDataset) {
        this.mDataset = mDataset.getLibrary();
    }



    public static class DocPreviewViewHolder extends RecyclerView.ViewHolder{
        protected TextView mTextView;
        protected DynamicDoc dynamicDoc;

        public DocPreviewViewHolder(final View v){
            super(v);
            mTextView = (TextView) v.findViewById(R.id.card_name_text);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(v.getContext(), CompileDocActivity.class);
                    intent.putExtra("doc_to_compile", dynamicDoc);
                    v.getContext().startActivity(intent);
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
        return new DocPreviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DocPreviewViewHolder docPreviewViewHolder, int i) {
        docPreviewViewHolder.mTextView.setText(mDataset.get(i).getTitle());
        docPreviewViewHolder.dynamicDoc = mDataset.get(i);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
