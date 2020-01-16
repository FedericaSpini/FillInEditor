package it.uniroma1.fillineditor;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import it.uniroma1.fillineditor.configuration.ConfigurationReader;
import it.uniroma1.fillineditor.configuration.JSONViewObject;
import it.uniroma1.fillineditor.models.DynamicDocLibrary;

//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private RecyclerView doc_preview_recycler;
    private RecyclerView.Adapter doc_preview_adapter;
    private RecyclerView.LayoutManager doc_preview_layout_manager;

    private DynamicDocLibrary dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConfigurationReader configurationReader = new ConfigurationReader(this);
        JSONViewObject[] jsonViewObjects = configurationReader.read(getResources());

        dataset = new DynamicDocLibrary(jsonViewObjects);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
        doc_preview_recycler = (RecyclerView)findViewById(R.id.doc_preview_recycler);
        doc_preview_recycler.setHasFixedSize(true); //opzionale

        doc_preview_layout_manager = new LinearLayoutManager(this);
        doc_preview_recycler.setLayoutManager(doc_preview_layout_manager);

        doc_preview_adapter = new DocPreviewAdapter(dataset);
        doc_preview_recycler.setAdapter(doc_preview_adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
