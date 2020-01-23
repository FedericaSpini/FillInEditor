package it.uniroma1.fillineditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import it.uniroma1.fillineditor.models.DocContentManager;
import it.uniroma1.fillineditor.models.StaticText;
import it.uniroma1.fillineditor.viewComponents.StaticCharBox;

class AdapterDoc extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    private DynamicDocContent[] datasetContent;
    private Context context;
    private String[] datasetContent;


//    public AdapterDoc(Context context, DynamicDocContent[] config){
//        this.context=context;
//        this.datasetContent = config;
//    }
public AdapterDoc(Context context, String[] config){
    this.context=context;
    this.datasetContent = config;
}

    public class ViewHolderStaticText extends RecyclerView.ViewHolder {
        protected TextView text;

        public ViewHolderStaticText(TextView v) {
            super(v);
            text = v;
        }
    }

    public class ViewHolderDynamicText extends RecyclerView.ViewHolder {
        protected StaticCharBox charBox;

        public ViewHolderDynamicText(StaticCharBox v) {
            super(v);
            charBox = v;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                TextView v = new TextView(context);
                return new ViewHolderStaticText(v);
            case 1:
                StaticCharBox v2 = (StaticCharBox) LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.sample_static_char_box, parent, false);

                return new ViewHolderDynamicText(v2);
        }
        throw new RuntimeException("ATTENZIONE! NUMERO DI COMPONENTE NON VALIDO");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 0:
                StaticText t = (StaticText) DocContentManager.getInstance().generateDocComponent(0, datasetContent[position]);
                ViewHolderStaticText holderStaticText = (ViewHolderStaticText) holder;
                holderStaticText.text.setText(t.getText());
                break;
            case 1:
                ViewHolderDynamicText holderDynamicText = (ViewHolderDynamicText) holder;
//                holderDynamicText.text.setText("Testo numero "+position);
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
