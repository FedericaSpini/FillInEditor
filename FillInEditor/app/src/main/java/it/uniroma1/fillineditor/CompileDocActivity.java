package it.uniroma1.fillineditor;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.uniroma1.fillineditor.models.DynamicDoc;
import it.uniroma1.fillineditor.models.DynamicDocContent;



//TODO: Parti dal cambiare l'adapter perché deve gestire in modo differente i due diversi tipi di componenti

public class CompileDocActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private DynamicDoc doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_doc);
        doc = getIntent().getExtras().getParcelable("doc_to_compile");

        System.out.println("1-----###########################"+doc);
        System.out.println("2-----###########################"+doc.getContents());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.static_text);
        mTextMessage.setText(doc.getTitle());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.doc_preview_recycler);
        recyclerView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        try {
            DynamicDocContent[] contents = doc.getContents();
            System.out.println("###########################"+contents);
            AdapterDoc myAdapter = new AdapterDoc(this,contents);
            recyclerView.setAdapter(myAdapter);
        }
        catch (Exception e){
            System.out.println("This document has not contents!!!!!!!!!!");
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

}
