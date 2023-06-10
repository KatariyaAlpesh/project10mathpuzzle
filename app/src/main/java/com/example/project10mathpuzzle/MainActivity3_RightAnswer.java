package com.example.project10mathpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity3_RightAnswer extends AppCompatActivity {

TextView completedPuzzleAnswerPage;

Button buttonContinueAnswerPage,buttonMainMenuAnswerPage,buttonBuyProAnswerPage;

ImageButton buttonShareAnswerPage;

int level;

int[] puzzleAnswerImageArray = { R.drawable.share1, R.drawable.share2, R.drawable.share3, R.drawable.share4,
                                    R.drawable.share5, R.drawable.share6, R.drawable.share7,
                                    R.drawable.share8, R.drawable.share9, R.drawable.share10 };


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main3_right_answer);

        completedPuzzleAnswerPage = findViewById(R.id.completedPuzzleAnswerPage);
        buttonContinueAnswerPage = findViewById(R.id.buttonContinueAnswerPage);
        buttonMainMenuAnswerPage = findViewById(R.id.buttonMainMenuAnswerPage);
        buttonBuyProAnswerPage = findViewById(R.id.buttonBuyProAnswerPage);
        buttonShareAnswerPage = findViewById(R.id.buttonShareAnswerPage);

        level = getIntent().getIntExtra("level",0);   ///  Come From My Adapter

        completedPuzzleAnswerPage.setText("PUZZLE " + (level + 1) + " COMPLETED");


        buttonContinueAnswerPage.setOnClickListener(v -> {

            Intent intent = new Intent(this,MainActivity2_continue.class);
            intent.putExtra("level",level + 1);
            startActivity(intent);
            finish();

        });


        buttonMainMenuAnswerPage.setOnClickListener(v -> {

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();

        });

        buttonShareAnswerPage.setOnClickListener(v -> {

            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/png");


            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),puzzleAnswerImageArray[level]);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);

            File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) +
                    "/demo1/temp.png");

            try {

                f.createNewFile();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(bytes.toByteArray());
                fo.close();

            } catch (IOException e) {

                e.printStackTrace();
            }

            share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
            startActivity(Intent.createChooser(share, "Share Image"));

        });

    }

}