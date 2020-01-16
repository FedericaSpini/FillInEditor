package it.uniroma1.fillineditor;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.uniroma1.fillineditor.models.DynamicDoc;

//import android.support.annotation.NonNull;

//import android.support.v7.widget.RecyclerView;

public class CompileDocActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private DynamicDoc doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_doc);
        doc = getIntent().getExtras().getParcelable("doc_to_compile");
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.static_text);
        mTextMessage.setText(doc.getStaticText());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.doc_preview_recycler);
//        recyclerView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
//
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        AdapterDoc myAdapter = new AdapterDoc(this, new DynamicDocContent[]{new DynamicDocContent(),new DynamicDocContent(),new DynamicDocContent(),new DynamicDocContent(),new DynamicDocContent(),new DynamicDocContent()});
//        recyclerView.setAdapter(myAdapter);
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
