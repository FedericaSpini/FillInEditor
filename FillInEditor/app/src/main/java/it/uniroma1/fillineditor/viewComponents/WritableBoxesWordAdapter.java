package it.uniroma1.fillineditor.viewComponents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import it.uniroma1.fillineditor.R;



public class WritableBoxesWordAdapter extends RecyclerView.Adapter<WritableBoxesWordAdapter.WritableBoxViewHolder> {

    private int length;
    private Context context;
    public WritableBoxesWordAdapter (int length, Context context){
        setLength(length);
        this.context=context;
    }



    public static class WritableBoxViewHolder extends RecyclerView.ViewHolder{
        protected WritableCharBoxView box;

        public WritableBoxViewHolder(@NonNull View itemView) {
            super(itemView);
            box = (WritableCharBoxView) itemView;
        }
    }


    @NonNull
    @Override
    public WritableBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WritableCharBoxView v = (WritableCharBoxView) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.sample_char_box, parent, false);
        return new WritableBoxViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WritableBoxesWordAdapter.WritableBoxViewHolder holder, int position) {
//        holder.box.setActivity(context);

    }

    @Override
    public int getItemCount() {return length;}

    public int getLength() {return length;}

    public void setLength(int length) {this.length = length;}
}
