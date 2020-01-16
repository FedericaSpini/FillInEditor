package it.uniroma1.fillineditor;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import it.uniroma1.fillineditor.models.DynamicDocContent;

class AdapterDoc extends RecyclerView.Adapter<AdapterDoc.DocViewHolder> {
    /**
     * Thi class fill with several cards (e.g. characteristics, life point, an so on)
     * the character sheet sections.
     */

    private DynamicDocContent[] dataset;
    private Context context;
    public AdapterDoc(Context context, DynamicDocContent[] config){
        this.context=context;
        this.dataset = config;
    }


    public class DocViewHolder extends RecyclerView.ViewHolder {
        protected TextView text;
//        protected RecyclerView componentRecycler;

        public DocViewHolder(TextView v) {
            super(v);
            text = v;
//            text = (TextView) v.findViewById(R.id.generic_card_title);
//            componentRecycler = (RecyclerView) v.findViewById(R.id.generic_card_content_recycler);
        }
    }

    @NonNull
    @Override
    public DocViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = new TextView(context);
        return new DocViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DocViewHolder holder, int position) {
        holder.text.setText("Testo numero "+position);
//        PaperCardStructure structure = dataset[position];
//        holder.card_title.setText(structure.getTitle());
//        CardComponentRepresentation[] contents = new CardComponentRepresentation[structure.getComponentRepresentations().size()];
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//        holder.componentRecycler.setLayoutManager(linearLayoutManager);
//        AdapterGenericCardContent myAdapter = new AdapterGenericCardContent(context, structure.getComponentRepresentations().toArray(contents));
//        holder.componentRecycler.setAdapter(myAdapter);
    }


    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public Context getContext(){return this.context;}
}
