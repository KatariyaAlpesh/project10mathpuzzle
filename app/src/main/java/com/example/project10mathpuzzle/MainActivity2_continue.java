package com.example.project10mathpuzzle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2_continue extends AppCompatActivity implements View.OnClickListener
{

    Button buttonSubmit;

    Button b[] = new Button[10];

    ImageView delete,puzzle,imageViewHint,imageViewSkip;

    TextView levelBoard,answer;

    // three type of DialogBox :
    // 1. upload from camera & gallery type || make String Array
    // 2. custom layout xml file
    // 3. yes - no - cancel

    String[] uploadFromArray = {"Camera","Gallery"};    // for DialogBox type 1
    AlertDialog.Builder builderSkip;
    AlertDialog.Builder builderHint;

    int level = 0,lastLevel;

    String[] puzzleAnswerArray = {"10","20","30","40","50","60","70","80","90","100"};

    int[] puzzleImagesArray = { R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4,
                                R.drawable.p5, R.drawable.p6, R.drawable.p7,
                                R.drawable.p8, R.drawable.p9, R.drawable.p10 };

    SharedPreferences preferences;   // Get Value

    SharedPreferences.Editor editor;    // Value add /update /delete

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main2_continue);

            preferences = getSharedPreferences("db",MODE_PRIVATE);
            editor = preferences.edit();

            buttonSubmit = findViewById(R.id.buttonSubmit);
            delete = findViewById(R.id.delete);
            puzzle = findViewById(R.id.puzzle);
            imageViewHint = findViewById(R.id.imageViewHint);
            imageViewSkip = findViewById(R.id.imageViewSkip);
            levelBoard = findViewById(R.id.levelBoard);
            answer = findViewById(R.id.answer);

            for (int i = 0; i < 10; i++) {

                int id = getResources().getIdentifier("b" + i,"id",getPackageName());
                b[i] = findViewById(id);

                b[i].setOnClickListener(this);  // OnCLick method called here - Located outside of OnCreate

            }

            if (getIntent().getExtras() != null) {

                level = getIntent().getIntExtra("level", 0);

            } else {

                if(preferences.contains("winningLevel")) {

                    level = preferences.getInt("winningLevel",0) + 1;

                } else {

                    level = preferences.getInt("winningLevel",0);
                }
            }


            levelBoard.setText("Puzzle " + (level + 1));
            puzzle.setImageResource(puzzleImagesArray[level]);


            builderSkip = new AlertDialog.Builder(this);
            builderHint = new AlertDialog.Builder(this);


            imageViewHint.setOnClickListener(v -> {

    /*       Type 1  : upload from camera & gallery type - Alert Dialog Box

                builderHint.setCancelable(false);   // outside click will not work
                builderHint.setTitle("Upload from");

                builderHint.setItems(uploadFromArray,(dialog, which) -> {

                    if (which == 0) {

                        Toast.makeText(this, "Camera", Toast.LENGTH_SHORT).show();
                    }

                    if (which == 1) {

                        Toast.makeText(this, "Gallery", Toast.LENGTH_SHORT).show();
                    }
                });

                builderHint.show();
    */

    /*       Type 2  : custom layout xml file type - Alert Dialog Box

                View view = LayoutInflater.from(this).inflate(R.layout.activity_main2_puzzles_1_page,
                                                                    null,false);
                ImageView imageView = view.findViewById(R.id.puzzleLevels);
                imageView.setImageResource(R.drawable.hint);

                builderHint.show();
    */

    //       Type 3  : yes - no - cancel type - Alert Dialog Box

                builderHint.setCancelable(false);
                builderHint.setTitle("Hint");
                builderHint.setMessage("Hint As per levels - string array of hint & if-else timer pending");
                builderHint.setIcon(R.drawable.hint);

                builderHint.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builderHint.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builderHint.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builderHint.show();

            });


            imageViewSkip.setOnClickListener(v -> {

                builderSkip.setCancelable(false);
                builderSkip.setTitle("Skip");
                builderSkip.setMessage("Are You Sure You Want to Skip this Level ? & if-else timer pending");
                builderSkip.setIcon(R.drawable.hint);

                builderSkip.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int lastLevel = preferences.getInt("winningLevel",0);

                        if (level >= lastLevel) {

                            editor.putInt("winningLevel",level); // Data store until app uninstall / clear data
                        }

                        editor.putString("levelStatus" + level,"skip");
                        editor.commit();

                        Intent intent = new Intent(MainActivity2_continue.this,MainActivity2_continue.class);
                        intent.putExtra("level",level + 1);
                        startActivity(intent);
                        finish();

                    }
                });

                builderSkip.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builderSkip.show();

            });


            delete.setOnClickListener(v -> {

                String ans = answer.getText().toString();

                if (ans.length() > 0) { answer.setText( ans.substring(0,ans.length()-1) ); }

            });


            buttonSubmit.setOnClickListener(v -> {

                if ( answer.getText().toString().equals(puzzleAnswerArray[level]) ) {

                    int lastLevel = preferences.getInt("winningLevel",0);

                    if (level >= lastLevel) {

                        editor.putInt("winningLevel",level); // Data store until app uninstall / clear data
                    }

                    editor.putString("levelStatus" + level,"win");
                    editor.commit();

                    Intent intent = new Intent(this,MainActivity3_RightAnswer.class);
                    intent.putExtra("level",level);
                    startActivity(intent);
                    finish();

                } else { Toast.makeText(this, "Wrong!!!", Toast.LENGTH_SHORT).show(); }

            });

        }

        @Override
        public void onClick(View v) {

            for (int i = 0; i <10; i++) {

                if (b[i].getId() == v.getId()) { answer.setText(answer.getText() + "" + b[i].getText()); }

            }

        }
}