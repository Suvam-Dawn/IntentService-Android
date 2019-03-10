package com.suvamdawn.intentService;

import android.app.IntentService;
import android.content.Intent;

public class IntentServiceReceiver extends IntentService{
    final static String Service_Info = "Service_Info";
    public IntentServiceReceiver() {
        super("IntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        String input = intent.getStringExtra("input");
        processing(input);

    }
    private void processing(final String input){
        StringBuilder outputResult = new StringBuilder();
        outputResult.append(input);
        outputResult = outputResult.reverse();
        resultSendToMainThread(outputResult.toString());
    }
    private void resultSendToMainThread(String msg){
        Intent intent = new Intent();
        intent.setAction(Service_Info);
        intent.putExtra("output",msg);
        sendBroadcast(intent);
    }
}