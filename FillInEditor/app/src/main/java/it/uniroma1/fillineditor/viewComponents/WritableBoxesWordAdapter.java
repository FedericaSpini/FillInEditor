package it.uniroma1.fillineditor.viewComponents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import it.uniroma1.fillineditor.R;
import it.uniroma1.fillineditor.data.ItemData;
import it.uniroma1.fillineditor.data.SessionData;


public class WritableBoxesWordAdapter extends RecyclerView.Adapter<WritableBoxesWordAdapter.WritableBoxViewHolder> {

    private int length;
    private AppCompatActivity activity;
    public WritableBoxesWordAdapter (int length, AppCompatActivity activity){
        setLength(length);
        this.activity=activity;
    }



    public static class WritableBoxViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected WritableCharBoxView box;

        public WritableBoxViewHolder(@NonNull View itemView) {
            super(itemView);
            box = (WritableCharBoxView) itemView;
            this.setIsRecyclable(true);
        }

        @Override
        public void onClick(View v) {
            System.out.println("Ho cliccato su un oggetto "+this);
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
        holder.box.setActivity(activity);
        holder.box.setItemData(new ItemData(new SessionData(), 4));
    }

    @Override
    public int getItemCount() {return length;}

    public int getLength() {return length;}

    public void setLength(int length) {this.length = length;}
}
