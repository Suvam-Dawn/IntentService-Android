package com.suvamdawn.intentService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText input;
    private Button submit;
    private TextView result;
    private IntentReceiver intentReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input=findViewById(R.id.input);
        submit=findViewById(R.id.submit);
        result=findViewById(R.id.result);
        registerIntentServiceReceiver();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cbIntent =  new Intent();
                cbIntent.setClass(MainActivity.this, IntentServiceReceiver.class);
                cbIntent.putExtra("input", input.getText().toString());
                startService(cbIntent);
            }
        });
    }
    private void registerIntentServiceReceiver(){
        intentReceiver = new IntentReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IntentServiceReceiver.Service_Info);
        registerReceiver(intentReceiver, intentFilter);
    }
    private class IntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String output = intent.getStringExtra("output");
            result.setText(output);
        }
    }
}
