package com.example.android.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.interactivestory.R;
import com.example.android.interactivestory.model.Page;
import com.example.android.interactivestory.model.Story;

public class Story_activity extends AppCompatActivity {

    private Page mPage;
    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoice1;
    private Button mChoice2;
    private Story myStory = new Story();
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_activity);
        Intent received = getIntent();
        mName = received.getStringExtra(getString(R.string.name_key));
        Log.d("StoryActivity", received.getStringExtra(getString(R.string.name_key)));

        mImageView = (ImageView) findViewById(R.id.storyImageView);
        mTextView = (TextView) findViewById(R.id.storyTextView);
        mChoice1 = (Button) findViewById(R.id.buttonChoice1);
        mChoice2 = (Button) findViewById(R.id.buttonChoice2);
        loadPage(0);
    }

    private void loadPage(int userChoice) {
        mPage = myStory.getPage(userChoice);
        Drawable drawable = getResources().getDrawable(mPage.getImageId());
        mImageView.setImageDrawable(drawable);

        String formatted = String.format(mPage.getText(), mName);

        mTextView.setText(formatted);

        if (mPage.isFinal()) {
            mChoice1.setVisibility(View.INVISIBLE);
            mChoice2.setText("Play again");
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });


        } else {
        mChoice1.setText(mPage.getChoice1().getText());
        mChoice2.setText(mPage.getChoice2().getText());

        mChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = mPage.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });

        mChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = mPage.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });

        }
    }
}
