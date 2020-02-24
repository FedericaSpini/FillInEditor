package it.uniroma1.fillineditor;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.uniroma1.fillineditor.models.DocCompilationModality;
import it.uniroma1.fillineditor.models.DynamicDocModel;
import it.uniroma1.fillineditor.viewComponents.StaticBoxesWord;

import static it.uniroma1.fillineditor.viewComponents.DynamicDocViewComponents.STATIC_BOXES_WORD_ID;


public class CompileDocActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private DynamicDocModel doc;

    private AdapterDoc adapterDoc;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_doc);
        doc = getIntent().getExtras().getParcelable("doc_to_compile");

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.static_text);
        mTextMessage.setText(doc.getTitle());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recyclerView = (RecyclerView) findViewById(R.id.doc_recycler);
//        recyclerView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        try {
            String[] contents = doc.getStringContents();
            adapterDoc = new AdapterDoc(this,contents);
            recyclerView.setAdapter(adapterDoc);
        }
        catch (Exception e){
            System.out.println("This document has not contents!!!!!!!!!!");
        }
//        WritableCharBoxView prova = findViewById(R.id.prova);
//        prova.setItemData(new ItemData(new SessionData(), 4));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.pen_mod:
                    turnContent(DocCompilationModality.PEN);
//                    adapterDoc.turnContent(DocCompilationModality.PEN);
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.graphometric_mod:
                    turnContent(DocCompilationModality.GRAPHOMETRIC);
//                    adapterDoc.turnContent(DocCompilationModality.GRAPHOMETRIC);
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.debug_mod:
                    turnContent(DocCompilationModality.DEBUG);
//                    adapterDoc.turnContent(DocCompilationModality.DEBUG);
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.save_doc:
                    return true;
                case R.id.delete_doc:
                    return true;
            }
            return false;
        }
    };

    public void turnContent(DocCompilationModality mod){
        for(int i = 0; i<recyclerView.getAdapter().getItemCount(); i++){
            if (recyclerView.getAdapter().getItemViewType(i) == STATIC_BOXES_WORD_ID){
                StaticBoxesWord word = (StaticBoxesWord) recyclerView.getChildAt(i);
                word.turnContent(mod);
            }
        }

    }

}
