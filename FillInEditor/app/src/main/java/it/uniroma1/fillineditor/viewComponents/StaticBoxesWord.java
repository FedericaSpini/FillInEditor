package it.uniroma1.fillineditor.viewComponents;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.uniroma1.fillineditor.R;
import it.uniroma1.fillineditor.models.DocCompilationModality;

/**
 * TODO: document your custom view class.
 */
public class StaticBoxesWord extends RelativeLayout implements  DynamicDocViewComponents {
    private int index;
    private int length;
    private RecyclerView boxesRecycler;
    private RecyclerView.Adapter boxesRecyclerAdapter;
    private RecyclerView.LayoutManager boxesRecyclerLayoutManager;

    private AppCompatActivity activity;

//    private Button clearButton;
//    private Button confirmButton;


    public StaticBoxesWord(Context context) {
        super(context);
//        clearButton = (Button)findViewById(R.id.clear_button);
//        confirmButton = (Button)findViewById(R.id.confirm_button);
        init(null, 0);
    }

    public StaticBoxesWord(Context context, AttributeSet attrs) {
        super(context, attrs);
//        clearButton = (Button)findViewById(R.id.clear_button);
//        confirmButton = (Button)findViewById(R.id.confirm_button);
        init(attrs, 0);
    }

    public StaticBoxesWord(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        clearButton = (Button)findViewById(R.id.clear_button);
//        confirmButton = (Button)findViewById(R.id.confirm_button);
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
//            clearButton.setClickable(true);
//            confirmButton.setClickable(true);
//
//            clearButton.setOnClickListener(this);
//            confirmButton.setOnClickListener(this);

//            this.clearButton.setOnClickListener(x -> {
//                for(int i = 0; i<boxesRecycler.getAdapter().getItemCount(); i++) {
//                    WritableCharBoxView charBoxView = (WritableCharBoxView) boxesRecycler.getChildAt(i);
//                    if (charBoxView != null) {
//                        charBoxView.restart();
//                    }
//                }
//            });
//
//            this.confirmButton.setOnClickListener(x -> {
//                for(int i = 0; i<boxesRecycler.getAdapter().getItemCount(); i++) {
//                    WritableCharBoxView charBoxView = (WritableCharBoxView) boxesRecycler.getChildAt(i);
//                    if (charBoxView != null) {
//                        charBoxView.restart();
//                    }
//                }
//            });

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
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.save_doc:
//                    saveContent();
                    return true;
                case R.id.delete_doc:
                    deleteContent();
                    return true;
            }
            return false;
        }
    };

    public AppCompatActivity getActivity() {return activity;}

    public void setActivity(AppCompatActivity activity) {this.activity = activity;}

    public void turnContent(DocCompilationModality mod){
        for(int i = 0; i<boxesRecycler.getAdapter().getItemCount(); i++) {
            WritableCharBoxView charBoxView = (WritableCharBoxView) boxesRecycler.getChildAt(i);
            if (charBoxView != null) {
                charBoxView.turnContent(mod);
            }
        }
    }

    public void deleteContent(){
        for(int i = 0; i<boxesRecycler.getAdapter().getItemCount(); i++) {
            WritableCharBoxView charBoxView = (WritableCharBoxView) boxesRecycler.getChildAt(i);
            if (charBoxView != null) {
                charBoxView.restart();
            }
        }
    }

    public void saveContent(){
        for(int i = 0; i<boxesRecycler.getAdapter().getItemCount(); i++) {
            WritableCharBoxView charBoxView = (WritableCharBoxView) boxesRecycler.getChildAt(i);
            if (charBoxView != null) {
//                charBoxView.turnContent(mod);
            }
        }
    }


}
