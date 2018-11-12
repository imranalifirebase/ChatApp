package com.example.aaa.chatapp.activites;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.aaa.chatapp.R;
import com.textinput.emoji.Actions.EmojIconActions;
import com.textinput.emoji.Helper.EmojiconEditText;
import com.textinput.emoji.Helper.EmojiconTextView;

import java.io.IOException;

public class NewMessageActivity extends AppCompatActivity {

    private static final int PICK_CONTACT = 101;
    private static final String TAG = "HTAG";
    private Toolbar toolbar;
    private ImageView pickContactImage;
    private EditText editPhoneNumber;
    private ImageView imageFileAttach;
    private EmojiconEditText emojiconEditText;
    private EmojIconActions emojIcon;
    private ConstraintLayout rootConstraintLayout;
    private ImageView imageSmile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        toolbar = findViewById(R.id.toolbar_new_message_activity);
        toolbar.setTitle("New Message");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        rootConstraintLayout = findViewById(R.id.rootLayout_main_activity);
        imageSmile = findViewById(R.id.smile_image_new_message_activity);
        emojiconEditText = findViewById(R.id.edit_Enter_message_new_message_activity);

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
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 55);
            }
        });

        pickContactImage = findViewById(R.id.contact_image_new_message_activity);
        pickContactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(NewMessageActivity.this, "Contact", Toast.LENGTH_SHORT).show();
                pickContactMethod();
            }
        });
        editPhoneNumber = findViewById(R.id.edit_contact_new_message_activity);
    }

    private void pickContactMethod() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        // TODO Fetch other Contact details as you want to use
                        editPhoneNumber.setText(name);
                        editPhoneNumber.setSelection(editPhoneNumber.getText().length());

                    }
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    //////////////////////////////////////////////////////////////////////

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 55) {
//            if (resultCode == Activity.RESULT_OK) {
//                if (data != null) {
//                    try {
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } else if (resultCode == Activity.RESULT_CANCELED)  {
//                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}
