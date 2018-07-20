package com.example.androidchat;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.fasterxml.jackson.databind.JsonNode;
import com.scaledrone.lib.Listener;
import com.scaledrone.lib.Member;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;

public class MainActivity extends AppCompatActivity implements RoomListener {

    private final String channelId = "5gycViNMcLSNOJTh";
    private final String roomName = "observable-room";
    private EditText editText;
    private Scaledrone scaledrone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        MemberData memberData = new MemberData("user", String.format("#%06X", Color.GREEN & 0x00FFFFFF));

        scaledrone = new Scaledrone(channelId, memberData);
        scaledrone.connect(new Listener() {
            @Override
            public void onOpen() {
                System.out.println("Scaledrone connection open");
                scaledrone.subscribe(roomName, MainActivity.this);
            }

            @Override
            public void onOpenFailure(Exception ex) {
                ex.printStackTrace();
            }

            @Override
            public void onFailure(Exception ex) {
                ex.printStackTrace();
            }

            @Override
            public void onClosed(String reason) {
                System.err.println(reason);
            }
        });
    }

    @Override
    public void onOpen(Room room) {
        System.out.println("Connected to room");
    }

    @Override
    public void onOpenFailure(Room room, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onMessage(Room room, JsonNode message, Member member) {

    }

    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() < 1) return;

        scaledrone.publish(roomName, message);
        editText.getText().clear();
    }
}
