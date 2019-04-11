package com.example.perceptive;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.perceptive.fetch_details_artists.a1;
import static com.example.perceptive.fetch_similar_artists.arr;

public class searchActivity extends AppCompatActivity {

    Button search_popup;
    public static ListView search_lv;
    public int type = -1;
    Button voice;

    static ProgressBar pb;

    static Context context;

    EditText search_topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().hide();

        context = searchActivity.this;

        final SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() { }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                if (matches != null)
                    search_topic.setText(matches.get(0));
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
        voice = findViewById(R.id.search_voice_btn);
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_topic.setText("Listening...");
                speechRecognizer.startListening(speechRecognizerIntent);
            }
        });
        voice.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                search_topic.setText("");
                return true;
            }
        });


        search_lv = findViewById(R.id.search_lv);
        search_lv.setVisibility(View.GONE);

        pb = findViewById(R.id.search_progressBar);
        pb.setVisibility(View.GONE);

        search_topic = findViewById(R.id.search_et_topic_et);

        search_popup = findViewById(R.id.search_popup);

        search_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu type_popup = new PopupMenu(searchActivity.this, search_popup);
                type_popup.getMenuInflater().inflate(R.menu.type_popupmenu,type_popup.getMenu());

                type_popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.type_movies:
                                search_popup.setText("MOVIES");
                                type = 0;
                                search_popup.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_movie_black_24dp, 0,0,0);
                                return true;
                            case R.id.type_songs:
                                search_popup.setText("SONGS");
                                type = 1;
                                search_popup.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_music_note_black_24dp, 0,0,0);
                                return true;
                            case R.id.type_books:
                                search_popup.setText("BOOKS");
                                type = 2;
                                search_popup.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_book_black_24dp, 0,0,0);
                                return true;
                            default:
                                return false;
                        }
                    }

                });

                type_popup.show();
            }
        });

        search_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                search_topic.setText(arr[position]);
                return true;
            }
        });

        search_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fetch_details_artists fda = null;
                fda = new fetch_details_artists(arr[position]);
                fda.execute();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(searchActivity.this, DetailsActivity.class);
                i.putExtra("Name", a1.name);
                i.putExtra("Desc", a1.summary);
                i.putExtra("Extra", a1.listeners);
                i.putExtra("Img", a1.image_url);
                i.putExtra("Mode", "1");

                if (type == 0) {
                    i.putExtra("Type", "0");
                }
                else if (type == 1) {
                    i.putExtra("Type", "1");
                }
                else {
                    i.putExtra("Type", "-1");
                }

                startActivity(i);
                pb.setVisibility(View.GONE);

            }
        });

    }

    public void search_bclk(View v) {

        fetch_similar_artists fd = null;
        pb.setVisibility(View.VISIBLE);
        if (type == -1) {
            Toast.makeText(context, "Set Type", Toast.LENGTH_SHORT).show();
            return;
        }
        if (type == 1) {
            fd = new fetch_similar_artists(search_topic.getText().toString().trim());
        }
        if (type == 0) {
            return;
        }
        if (type == 2) {
            return;
        }
        fd.execute();

    }

    public static void update_list(String arr[]) {
        ArrayAdapter<String> searchAA = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, arr);
        search_lv.setAdapter(searchAA);
        pb.setVisibility(View.GONE);
        search_lv.setVisibility(View.VISIBLE);
    }


}
