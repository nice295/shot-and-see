package com.nice295.shotandsee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "ShotAndSee_MainActivity";
    private ListView lvEvents;
    private LinearLayout noEventsView;
    private ProgressBar progressBar;

    private EventsListAdapter eventsListAdapter;
    private List<ParseObject> mParseObjectList;

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvEvents = (ListView) findViewById(R.id.lvEvents);
        noEventsView = (LinearLayout) findViewById(R.id.llNoEvents);
        lvEvents.setEmptyView(noEventsView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mParseObjectList = new ArrayList<ParseObject>();
        eventsListAdapter = new EventsListAdapter(this, R.layout.layout_events_list_item, mParseObjectList);
        lvEvents.setAdapter(eventsListAdapter);

        lvEvents.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ParseObject event = eventsListAdapter.getItem(position);
                Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                i.putExtra("Data", event.getString("Data"));
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm MM/dd yyyy ", Locale.KOREA);
                i.putExtra("Date", sdf.format(event.getCreatedAt()));
                i.putExtra("URL", event.getParseFile("Image").getUrl());
                startActivity(i);
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
        query.orderByDescending("updatedAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    progressBar.setVisibility(View.INVISIBLE);

                    Log.d(LOG_TAG, "Count of events: " + mParseObjectList.size());

                    eventsListAdapter.clear();
                    eventsListAdapter.addAll(list);

                    eventsListAdapter.notifyDataSetChanged();

                    for (ParseObject event : list) {
                        Log.d(LOG_TAG, "Data: " + event.getString("Data"));
                    }

                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.d(LOG_TAG, "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private class EventsListAdapter extends ArrayAdapter<ParseObject> {

        private static final String LOG_TAG = "ShotAndSee_EventsListAdapter" ;
        private List<ParseObject> items;

        public EventsListAdapter(Context context, int resourceId, List<ParseObject> items) {
                super(context, resourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view == null) {
                view = inflater.inflate(R.layout.layout_events_list_item, parent, false);
                holder = new ViewHolder();
                holder.ivImage = (ImageView) view.findViewById(R.id.ivImage);
                holder.tvTime = (TextView) view.findViewById(R.id.tvTime);
                holder.tvData = (TextView) view.findViewById(R.id.tvData);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            ParseObject events = items.get(position);

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm MM/dd yyyy ", Locale.KOREA);
            holder.tvTime.setText(sdf.format(events.getCreatedAt()));

            holder.tvData.setText(events.getString("Data"));

            if ( events.getParseFile("Image").getUrl() != null ) {
                Picasso.with(getApplicationContext()).load(events.getParseFile("Image").getUrl()).into(holder.ivImage);
            }
            return view;
        }

        public void setItem(List<ParseObject> item) {
            this.items = item;
        }
    }

    private static class ViewHolder {
        ImageView ivImage;
        TextView tvData;
        TextView tvTime;
    }
}
