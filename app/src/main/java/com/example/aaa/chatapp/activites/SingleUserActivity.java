package com.example.aaa.chatapp.activites;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.aaa.chatapp.R;
import com.textinput.emoji.Actions.EmojIconActions;
import com.textinput.emoji.Helper.EmojiconEditText;

public class SingleUserActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageFileAttach;
    private EmojiconEditText emojiconEditText;
    private EmojIconActions emojIcon;
    private ConstraintLayout rootConstraintLayout;
    private ImageView imageSmile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_user);
        toolbar = findViewById(R.id.toolbar_single_user_activity);
        toolbar.setTitle("Message");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rootConstraintLayout = findViewById(R.id.rootLayout_single_user_activity);
        imageSmile = findViewById(R.id.smile_image_single_user_activity);
        emojiconEditText = findViewById(R.id.edit_Enter_message_single_user_activity);

        emojIcon = new EmojIconActions(this, rootConstraintLayout, emojiconEditText, imageSmile);
        emojIcon.ShowEmojIcon();
        emojIcon.setKeyboardListener(new EmojIconActions.KeyboardListener() {
            @Override
            public void onKeyboardOpen() {
                Log.e("Keyboard", "open");
            }

            @Override
            public void onKeyboardClose() {
                Log.e("Keyboard", "close");
            }
        });

        imageFileAttach = findViewById(R.id.attach_file_image_new_message_activity);
        imageFileAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),55);
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

}
