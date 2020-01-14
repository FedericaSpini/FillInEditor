package it.uniroma1.fillineditor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;

import it.uniroma1.fillineditor.data.ItemData;
import it.uniroma1.fillineditor.data.SessionData;
import it.uniroma1.fillineditor.data.ToastManager;
import it.uniroma1.fillineditor.viewComponents.WritableCharBox;

public class DrawingActivity extends AppCompatActivity {

    public static final String SESSION_KEY = "session_data_key";
    public static final String ITEM_NUMBER_KEY = "word_number_key";
    public static final String TIMESTAMP_KEY = "timestamp_key";

    private String timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawing);

        WritableCharBox drawingView = findViewById(R.id.drawing_view_id);
        drawingView.setActivity(this);


        Bundle b = getIntent().getExtras();
        String session;
        ItemData itemData;
        SessionData sessionData = null;

        int item_index = -1;
        if (b != null) {
            session = b.getString(SESSION_KEY);
            item_index = b.getInt(ITEM_NUMBER_KEY);
            timestamp = b.getString(TIMESTAMP_KEY);
            sessionData = new Gson().fromJson(session, SessionData.class);
        }

        if (b == null || sessionData == null || item_index == -1) {
            throw new RuntimeException("Extraction of bundle failed!");
        }


//        if (sessionData.configuration == null)
//            throw new RuntimeException("Configuration is null!");

        itemData = new ItemData(sessionData, item_index);
        drawingView.setItemData(itemData);

        TextView counterView = findViewById(R.id.current_number_label);
        counterView.setText(String.valueOf(item_index + 1));

        TextView totalNumberView = findViewById(R.id.total_number_label);
//        totalNumberView.setText(String.valueOf(DataProvider.getInstance()
//                .getItems_provider().getNumberOfItems()));

        TextView title = findViewById(R.id.title_label);
//        title.setText(DataProvider.getInstance().getTitle());
        title.setText("Title");

        TextView itemText = findViewById(R.id.item_text);
//        itemText.setText(DataProvider.getInstance().getItems_provider().get(item_index));
        itemText.setText("itemText");
    }

    public void setTimerTextView(String s) {
        TextView timerText = findViewById(R.id.timer);
        timerText.setText(s);
    }

    public void clear(View view) {

        WritableCharBox drawView = (WritableCharBox) findViewById(R.id.drawing_view_id);
        drawView.restart();
    }


    private Thread thread;
    private File path_file;

    public synchronized void next(View view) {
        view.setEnabled(false);

        WritableCharBox drawView = (WritableCharBox) findViewById(R.id.drawing_view_id);
        final ItemData data = drawView.getItemData();

        if (data.touch_up_points.isEmpty()) {
            ToastManager.getInstance().toastNoWord(this);

            view.setEnabled(true);
            return;
        }
        ToastManager.getInstance().resetNoWord();


//        Saver.takeScreenshot(this, NamesManager.sessionDirectory(data.session_data, data.item_index, timestamp), NamesManager.getScreenshotName(data));

        thread = new Thread() {
            @Override
            public void run() {
                synchronized (DrawingActivity.this) {
//                    path_file = Saver.saveItemData(data, timestamp);
                }
            }
        };
        thread.start();

//        int next_index = DataProvider.getInstance().getItems_provider().nextIndex(data.item_index);
//        int total_number_items = DataProvider.getInstance().getItems_provider().getNumberOfItems();
//        if (next_index >= total_number_items) {
//            Toast.makeText(this, "Session completed! Thank you!", Toast.LENGTH_SHORT).show();
//            finish();
//            return;
//        }

        ToastManager.getInstance().toastSavedWord(this);

//        String sessionData = new Gson().toJson(data.session_data);
//        Intent intent = new Intent(this, DrawingActivity.class);
//        Bundle b = new Bundle();
//        b.putString(SESSION_KEY, sessionData);
//        b.putInt(ITEM_NUMBER_KEY, next_index); //Your id
//        b.putString(TIMESTAMP_KEY, timestamp);

//        intent.putExtras(b); //Put your id to your next Intent
//        startActivity(intent);
//        finish();
    }

    public void samples(View view) {
        WritableCharBox drawView = (WritableCharBox) findViewById(R.id.drawing_view_id);
        drawView.drawExtractSampling(drawView.extractSampling());
    }

    @Override
    protected void onDestroy() {
//        if (thread != null) {
//            try {
//                thread.join();
//                NamesManager.getInstance().scanFile(this, path_file);
//                ToastManager.getInstance().resetSaveWord();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        DrawingView drawView = (DrawingView) findViewById(R.id.drawing_view_id);
//        drawView.finish();
        super.onDestroy();
    }
}