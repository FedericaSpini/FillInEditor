package it.uniroma1.fillineditor.viewComponents;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import it.uniroma1.fillineditor.R;

/**
 * TODO: document your custom view class.
 */
public class StaticBoxesWord extends RelativeLayout implements DynamicDocViewComponents {
    private int index;
    private int length;
    private RecyclerView boxesRecycler;
    private RecyclerView.Adapter boxesRecyclerAdapter;
    private RecyclerView.LayoutManager boxesRecyclerLayoutManager;

    private AppCompatActivity activity;


    public StaticBoxesWord(Context context) {
        super(context);
        init(null, 0);
    }

    public StaticBoxesWord(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public StaticBoxesWord(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {}

    @Override
    public int getViewType() {
        return STATIC_BOXES_WORD_ID;
    }

    @SuppressLint("Assert")
    @Override
    public void setContents(String s) {
        try {
            s = s.substring(1);
            String[] indexLength = s.split(" ");
            assert indexLength.length==2;
            index = Integer.parseInt(indexLength[0]);
            length = Integer.parseInt(indexLength[1]);

            this.boxesRecycler = (RecyclerView)findViewById(R.id.boxes_recycler);
            this.boxesRecycler.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.lightGrayCyan));
            this.boxesRecyclerLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            this.boxesRecycler.setLayoutManager(this.boxesRecyclerLayoutManager);


            this.boxesRecyclerAdapter = new WritableBoxesWordAdapter(length, activity);
            this.boxesRecycler.setAdapter(this.boxesRecyclerAdapter);

        }
        catch (Exception e){
            System.out.println("!!!Not valid data" + e.toString());
        }
    }

    public AppCompatActivity getActivity() {return activity;}

    public void setActivity(AppCompatActivity activity) {this.activity = activity;}
}
