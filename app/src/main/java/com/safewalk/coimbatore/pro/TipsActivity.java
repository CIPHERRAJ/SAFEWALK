package com.safewalk.coimbatore.pro;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

/**
 * Screen displaying safety tips with Text-To-Speech (TTS) support.
 * Supports English and Tamil voice output if available on the device.
 */
public class TipsActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private Button btnPlayVoice;
    private TextView tvTips;
    private boolean isTtsInitialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        // Setup toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        tvTips = findViewById(R.id.textViewTips);
        btnPlayVoice = findViewById(R.id.btnPlayVoice);

        // Set the safety tips text from strings.xml
        tvTips.setText(R.string.safety_tips_text);

        // Initialize TextToSpeech
        tts = new TextToSpeech(this, this);

        btnPlayVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakTips();
            }
        });
    }

    /**
     * Called when TextToSpeech engine is initialized.
     */
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // Try setting language to Tamil first, then fall back to English
            int result = tts.setLanguage(new Locale("ta", "IN"));
            
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Tamil language not supported, defaulting to English.");
                tts.setLanguage(Locale.US);
            }
            isTtsInitialized = true;
        } else {
            Log.e("TTS", "Initialization failed!");
        }
    }

    /**
     * Speaks the safety tips in Tamil.
     */
    private void speakTips() {
        if (isTtsInitialized) {
            String tamilText = getString(R.string.safety_tips_tamil_text);
            // Use TextToSpeech.QUEUE_FLUSH to stop previous speech and start new
            tts.speak(tamilText, TextToSpeech.QUEUE_FLUSH, null, "TipsSpeechID");
        } else {
            Toast.makeText(this, "TTS not ready", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Clean up TextToSpeech resources when activity is destroyed.
     */
    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
