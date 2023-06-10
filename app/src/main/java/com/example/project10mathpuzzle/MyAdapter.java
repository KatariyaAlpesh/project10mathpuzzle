package com.example.project10mathpuzzle;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

int lastLevel;
SharedPreferences preferences;
SharedPreferences.Editor editor;
MainActivity2_puzzles c;

    public MyAdapter(MainActivity2_puzzles c) {

        this.c = c;
        preferences = c.getSharedPreferences("db",MODE_PRIVATE);
        editor = preferences.edit();
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(c).inflate(R.layout.activity_main2_puzzles_1_page_level, parent, false);

        TextView textView = convertView.findViewById(R.id.puzzleLevelsText);
        ImageView imageView = convertView.findViewById(R.id.puzzleLevelsImage);
        RelativeLayout relativeLayout = convertView.findViewById(R.id.relativeLayoutPuzzle);

        String status = preferences.getString("levelStatus" + position,"pending");

        if ( status.equals("win") || status.equals("skip") ) {

            lastLevel = position + 1;
        }


        if (status.equals("win")) {

            relativeLayout.setBackgroundResource(R.drawable.background_transparent_to_light);
            imageView.setImageResource(R.drawable.tick);
            textView.setVisibility(View.VISIBLE);
            textView.setText("" + (position + 1) );

            relativeLayout.setOnClickListener(v -> {

                Intent intent = new Intent(c,MainActivity2_continue.class);
                intent.putExtra("level",position);
                c.startActivity(intent);
                c.finish();

            });

        }

        if (status.equals("skip") || position == lastLevel) {

            relativeLayout.setBackgroundResource(R.drawable.background_transparent_to_light);
            imageView.setImageResource(0);
            textView.setVisibility(View.VISIBLE);
            textView.setText("" + (position + 1) );

            relativeLayout.setOnClickListener(v -> {

                Intent intent = new Intent(c,MainActivity2_continue.class);
                intent.putExtra("level",position);
                c.startActivity(intent);
                c.finish();

            });
        }

        return convertView;
    }
}
