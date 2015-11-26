package com.nice295.shotandsee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private static final String LOG_TAG = "ShotAndSee_DetailActivity";

    private String date = null;
    private String data = null;
    private String url = null;

    private ImageView ivImage;
    private TextView tvData;
    private TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }

        Intent intent = getIntent();
        date = intent.getExtras().getString("Date");
        data = intent.getExtras().getString("Data");
        url = intent.getExtras().getString("URL");
        Log.d(LOG_TAG, "date: " + date);
        Log.d(LOG_TAG, "data: " + data);
        Log.d(LOG_TAG, "url: " + url);

        ivImage = (ImageView) findViewById(R.id.ivImage);
        tvData = (TextView) findViewById(R.id.tvData);
        tvDate = (TextView) findViewById(R.id.tvDate);

        Picasso.with(this).load(url).into(ivImage);
        tvData.setText(data);
        tvDate.setText(date);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

