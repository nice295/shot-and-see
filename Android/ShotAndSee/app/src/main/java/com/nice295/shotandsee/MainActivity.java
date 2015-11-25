package com.nice295.shotandsee;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
//import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvEvents;
    private LinearLayout noEventsView;

    //private ParseQueryAdapter<Events> eventsListAdapter;

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        lvEvents = (ListView) findViewById(R.id.lvEvents);
        noEventsView = (LinearLayout) findViewById(R.id.llNoEvents);
        //lvEvents.setEmptyView(noEventsView);

        // Set up the Parse query to use in the adapter
        ParseQueryAdapter.QueryFactory<Events> factory = new ParseQueryAdapter.QueryFactory<Events>() {
            public ParseQuery<Events> create() {
                ParseQuery<Events> query = Events.getQuery();
                query.orderByDescending("createdAt");
                query.fromLocalDatastore();
                return query;
            }
        };

        // Set up the adapter
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        eventsListAdapter = new EventsListAdapter(this, factory);

        lvEvents.setAdapter(eventsListAdapter);

        */
    }

    @Override
    protected void onResume() {
        super.onResume();

       // loadFromParse();
    }

    /*
    private void loadFromParse() {
        ParseQuery<Events> query = Events.getQuery();
        query.findInBackground(new FindCallback<Events>() {
            public void done(List<Events> todos, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Events>) todos,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        if (!isFinishing()) {
                                            eventsListAdapter.loadObjects();
                                        }
                                    } else {
                                        Log.i("EventsListActivity",
                                                "Error pinning evetns: "
                                                        + e.getMessage());
                                    }
                                }
                            });
                } else {
                    Log.i("TodoListActivity",
                            "loadFromParse: Error finding pinned todos: "
                                    + e.getMessage());
                }
            }
        });
    }

    private class EventsListAdapter extends ParseQueryAdapter<Events> {

        private static final String LOG_TAG = "EventsListAdapter" ;

        public EventsListAdapter(Context context,
                               ParseQueryAdapter.QueryFactory<Events> queryFactory) {
            super(context, queryFactory);
        }

        @Override
        public View getItemView(Events events, View view, ViewGroup parent) {
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
            holder.tvTime.setText(events.getData());
            holder.tvData.setText(events.getData());

            if ( events.getImageUrl() != null ) {
                Picasso.with(getApplicationContext()).load(events.getImageUrl()).into(holder.ivImage);
            }
            return view;
        }
    }
*/
    private static class ViewHolder {
        ImageView ivImage;
        TextView tvData;
        TextView tvTime;
    }
}
