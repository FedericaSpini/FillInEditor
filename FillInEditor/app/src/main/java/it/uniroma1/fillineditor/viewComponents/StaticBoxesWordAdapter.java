package it.uniroma1.fillineditor.viewComponents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import it.uniroma1.fillineditor.R;

public class StaticBoxesWordAdapter extends RecyclerView.Adapter<StaticBoxesWordAdapter.StaticBoxViewHolder> {

    private int length;
    public StaticBoxesWordAdapter (int length){
        setLength(length);
    }

    public static class StaticBoxViewHolder extends RecyclerView.ViewHolder{
        protected StaticCharBoxView box;

        public StaticBoxViewHolder(@NonNull View itemView) {
            super(itemView);
            box = (StaticCharBoxView) itemView;
        }
    }


    @NonNull
    @Override
    public StaticBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StaticCharBoxView v = (StaticCharBoxView) LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.sample_static_char_box, parent, false);
        return new StaticBoxViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StaticBoxViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {return length;}

    public int getLength() {return length;}

    public void setLength(int length) {this.length = length;}
}
