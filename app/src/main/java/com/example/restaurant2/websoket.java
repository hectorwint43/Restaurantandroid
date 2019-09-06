package com.example.restaurant2;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

public class websoket  extends AsyncTask<Void, Integer, Void> {


    @Override
    protected void onPreExecute() {
        String url = "ws://192.168.43.146:3333";

    }


    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

    }

}
