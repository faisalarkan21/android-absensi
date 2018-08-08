package com.naurah.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
//import com.example.naurahhidayah.absensigundar.Mhs_LocationFragment;
import com.example.naurahhidayah.absensigundar.Mhs_LocationFragment;
import com.example.naurahhidayah.absensigundar.R;
import com.naurah.model.Schedule;
import com.naurah.utils.SessionManager;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Schedule> scheduleItems;
    private Context mContext;
    SessionManager session;
    //Constuctor
    public CustomListAdapter(Activity activity, List<Schedule> scheduleItems) {
        this.activity = activity;
        this.scheduleItems = scheduleItems;
        session = new SessionManager(activity.getApplication());
    }


    @Override
    public int getCount() {
        return scheduleItems.size();
    }

    @Override
    public Object getItem(int location) {
        return scheduleItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        Log.d("test4", "disini posisi" + position );

        Intent i = new Intent(getmContext(), Mhs_LocationFragment.class);
        getmContext().startActivity(i);
        session.setIdJadwal(Integer.toString(position));
        i.putExtra("id_jadwal", position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        Log.d("test3", "disini posisi" + position );

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);


        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView dosen = (TextView) convertView.findViewById(R.id.dosen);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        // getting movie data for the row
        Schedule m = scheduleItems.get(position);

        // thumbnail image
//        thumbNail.setImageUrl(m.getThumbnailUrl());

        // title
        title.setText(m.getTitle());

        // dosen
        dosen.setText("Dosen: " +m.getDosen());

        // genre
        genre.setText(m.getPlaceAndTime());


        // release year
        year.setText(m.getYear());

        return convertView;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}