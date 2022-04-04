package com.example.netflixremotecontroller;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import static com.example.netflixremotecontroller.R.id.ipText;


/**
 * Codice relativo alla home activity.
 *
 * @author Mattia Tritto
 */

public class HomeActivity extends AppCompatActivity {

    /**
     * Attributi
     */

    private String IP_WEB_BROWSER = "192.168.1.2";
    private int PORT_WEB_BROWSER = 5000;

    private boolean isPlaying = true;
    private boolean isFullscreen = false;
    private boolean isMute = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    /**
     * Metodo che aggiorna l'IP del Netflix server.
     *
     * @param view
     */

    public void updateIP(View view){
        EditText edit   = (EditText)findViewById(ipText);
        IP_WEB_BROWSER = edit.getText().toString();
    }



    /**
     * Metodo asincrono che comunica con il PC.
     *
     * @param message
     */

    private void sendMessage(String message) {
        new AsyncRequest(message).execute();
    }

    private class AsyncRequest extends AsyncTask<Void, Void, Void>
    {
        private String messagge;

        public AsyncRequest(String message){this.messagge = message;}

        @Override
        protected Void doInBackground(Void... params) {
            Client client = new Client(IP_WEB_BROWSER, PORT_WEB_BROWSER);
            client.sendMessage(messagge);
            client.sendMessage("close");
            return null;
        }
    }



    /**
     * Metodo eseguito quando viene premuto il pulsante PLAY/PAUSE.
     *
     * @param view
     */

    public void playPausePressed(View view){

        if (isPlaying){
            Button button = (Button) this.findViewById(R.id.play_pause_button);
            Resources res = getResources();
            Drawable pause = ResourcesCompat.getDrawable(res, R.drawable.pause, null);
            button.setBackground(pause);
            isPlaying = false;

            sendMessage("pause");
        }
        else {
            Button button = (Button) this.findViewById(R.id.play_pause_button);
            Resources res = getResources();
            Drawable play = ResourcesCompat.getDrawable(res, R.drawable.play, null);
            button.setBackground(play);
            isPlaying = true;

            sendMessage("play");
        }
    }

    /**
     * Metodo eseguito quando viene premuto il pulsante audio ON/OFF.
     *
     * @param view
     */

    public void audioPressed(View view){
        if (!isMute){
            Button button = (Button) this.findViewById(R.id.audio_button);
            Resources res = getResources();
            Drawable mute = ResourcesCompat.getDrawable(res, R.drawable.audio_off, null);
            button.setBackground(mute);

            sendMessage("mute");
            isMute = true;
        }
        else {
            Button button = (Button) this.findViewById(R.id.audio_button);
            Resources res = getResources();
            Drawable audio_on = ResourcesCompat.getDrawable(res, R.drawable.audio_on, null);
            button.setBackground(audio_on);

            sendMessage("audio_on");
            isMute = false;
        }
    }

    /**
     * Metodo eseguito quando viene premuto il pulsante fullscreen ON/OFF.
     *
     * @param view
     */

    public void fullscreenPressed(View view){
        if (!isFullscreen){
            Button button = (Button) this.findViewById(R.id.fullscreen_button);
            Resources res = getResources();
            Drawable fullscreen = ResourcesCompat.getDrawable(res, R.drawable.fullscreen_exit, null);
            button.setBackground(fullscreen);

            sendMessage("fullscreen");
            isFullscreen = true;
        }
        else {
            Button button = (Button) this.findViewById(R.id.fullscreen_button);
            Resources res = getResources();
            Drawable fullscreen = ResourcesCompat.getDrawable(res, R.drawable.fullscreen, null);
            button.setBackground(fullscreen);

            sendMessage("no_fullscreen");
            isFullscreen = false;
        }
    }

    /**
     * Metodo eseguito quando viene premuto il pulsante BACK.
     *
     * @param view
     */

    public void backPressed(View view){
        sendMessage("back");
    }

    /**
     * Metodo eseguito quando viene premuto il pulsante FORWARD.
     *
     * @param view
     */

    public void forwardPressed(View view){
        sendMessage("forward");
    }
}