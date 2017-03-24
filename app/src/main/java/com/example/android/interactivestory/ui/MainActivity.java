package com.example.android.interactivestory.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.interactivestory.R;

public class MainActivity extends AppCompatActivity {

    EditText mEditTextName;
    Button mButtonConfirmName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextName = (EditText) findViewById(R.id.nameEditText);
        mButtonConfirmName = (Button) findViewById(R.id.startButton);

        View.OnClickListener clickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = mEditTextName.getText().toString();
                // Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
                startSecondActivity(name);
            }
        };

        mButtonConfirmName.setOnClickListener(clickListener);
    }

    private void startSecondActivity(String sended) {

        Intent intent = new Intent(this, Story_activity.class);
        intent.putExtra(getString(R.string.name_key), sended);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mEditTextName.setText("");
    }
}
