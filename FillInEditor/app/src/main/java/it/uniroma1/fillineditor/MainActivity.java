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
import it.uniroma1.fillineditor.models.DynamicDocLibraryModel;

//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private RecyclerView doc_preview_recycler;
    private RecyclerView.Adapter doc_preview_adapter;
    private RecyclerView.LayoutManager doc_preview_layout_manager;

    private DynamicDocLibraryModel dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConfigurationReader configurationReader = new ConfigurationReader(this);
        JSONViewObject[] jsonViewObjects = configurationReader.read(getResources());

        dataset = new DynamicDocLibraryModel(jsonViewObjects);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        doc_preview_recycler = (RecyclerView)findViewById(R.id.doc_preview_recycler);
        doc_preview_recycler.setHasFixedSize(true); //opzionale

        doc_preview_layout_manager = new LinearLayoutManager(this);
        doc_preview_recycler.setLayoutManager(doc_preview_layout_manager);

        doc_preview_adapter = new DocPreviewAdapter(dataset);
        doc_preview_recycler.setAdapter(doc_preview_adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
