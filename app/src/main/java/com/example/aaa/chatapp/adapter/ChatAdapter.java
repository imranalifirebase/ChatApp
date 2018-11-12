package com.example.aaa.chatapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aaa.chatapp.R;
import com.example.aaa.chatapp.activites.SingleUserActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private int myImages [] = {R.drawable.icon_pin, R.drawable.icon_pin,R.drawable.icon_pin,
            R.drawable.ic_just_white_image,R.drawable.ic_just_white_image,R.drawable.ic_just_white_image,
            R.drawable.ic_just_white_image,R.drawable.ic_just_white_image,R.drawable.ic_just_white_image,};
    private Context context;

    String mData[];
    public ChatAdapter(String []mData , Context context) {
        this.mData = mData;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_row_chat, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txtChatMessage.setText(mData[i]);
        myViewHolder.imageView.setImageResource(myImages[i]);
        myViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SingleUserActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtChatMessage;
        public CircleImageView circleImageView;
        public ImageView imageView;
        public ConstraintLayout constraintLayout;
        public MyViewHolder(View v) {
            super(v);
            txtChatMessage = v.findViewById(R.id.txt_message_custom_row);
            circleImageView = v.findViewById(R.id.profile_image_custom_row);
            imageView = v.findViewById(R.id.image_pin);
            constraintLayout = v.findViewById(R.id.constraint_layout_custom_row_chat);

        }
    }
}
