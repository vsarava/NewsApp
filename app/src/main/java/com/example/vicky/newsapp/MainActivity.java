package com.example.vicky.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "My Tag";
    private TextView newsTextView;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsTextView = (TextView) findViewById(R.id.news_data);
        progress = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itenNumber = item.getItemId();

        if(itenNumber == R.id.search){
            new NewsTask().execute();
        }
        return true;
    }

    class  NewsTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls){
            String result = null;
            URL url = NetworkUtils.makeURL("the-next-web", "latest", "ADD YOUR API KEY HERE" );
            Log.d(TAG, "url:" + url.toString());
            try {
                result = NetworkUtils.getResponseFromHttpUrl(url);
            }
            catch(IOException e){
                e.printStackTrace();
            }
            return result;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progress.setVisibility(View.GONE);
            if(s == null){
                newsTextView.setText("Sorry, no text was received");
            }else{
                newsTextView.setText(s);
            }
        }
    }
}
