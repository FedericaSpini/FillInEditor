package it.uniroma1.fillineditor;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import it.uniroma1.fillineditor.models.DynamicDocContent;

class AdapterDoc extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private DynamicDocContent[] dataset;
    private Context context;

    public AdapterDoc(Context context, DynamicDocContent[] config){
        this.context=context;
        this.dataset = config;
    }


    public class ViewHolderStaticText extends RecyclerView.ViewHolder {
        protected TextView text;

        public ViewHolderStaticText(TextView v) {
            super(v);
            text = v;
        }
    }

    public class ViewHolderDynamicText extends RecyclerView.ViewHolder {
        protected TextView text;

        public ViewHolderDynamicText(TextView v) {
            super(v);
            text = v;
        }
    }

    @NonNull
    @Override
    public ViewHolderStaticText onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                TextView v = new TextView(context);
                return new ViewHolderStaticText(v);
            case 1:
                TextView v2 = new TextView(context);
                return new ViewHolderStaticText(v2);
        }
        throw new RuntimeException("ATTENZIONE! NUMERO DI COMPONENTE NON VALIDO");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 0:
                ViewHolderStaticText holderStaticText = (ViewHolderStaticText) holder;
                holderStaticText.text.setText("Testo numero "+position);
                break;
            case 1:
                ViewHolderDynamicText holderDynamicText = (ViewHolderDynamicText) holder;
                holderDynamicText.text.setText("Testo numero "+position);
                break;
        }
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
