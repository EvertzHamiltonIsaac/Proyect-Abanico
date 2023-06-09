package com.example.whatsapp_clone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp_clone.Models.MessageModel;
import com.example.whatsapp_clone.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends  RecyclerView.Adapter{

    ArrayList<MessageModel> messageModelArrayList;
    Context context;

    int SENDER_VIEW_TYPE =  1;
    int RECEIVER_VIEW_TYPE =  2;



    public ChatAdapter(ArrayList<MessageModel> messageModelArrayList, Context context) {
        this.messageModelArrayList = messageModelArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender, parent, false);
            return new SenderViewVolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciever, parent, false);
            return new RecieverViewVolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (messageModelArrayList.get(position).getUid().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else {
            return RECEIVER_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel messageModel = messageModelArrayList.get(position);
        if(holder.getClass() == SenderViewVolder.class){
            ((SenderViewVolder)holder).senderMsg.setText(messageModel.getMessage());
        }else {
            ((RecieverViewVolder)holder).recieverMsg.setText(messageModel.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageModelArrayList.size();
    }


    public class RecieverViewVolder extends RecyclerView.ViewHolder{
        TextView recieverMsg , recieverTime;
        public RecieverViewVolder(@NonNull View itemView) {

            super(itemView);
            recieverMsg = itemView.findViewById(R.id.recieiverText);
            recieverTime = itemView.findViewById(R.id.recieiverTime);

        }
    }

    public class SenderViewVolder extends RecyclerView.ViewHolder{

        TextView senderMsg , senderTime;
        public SenderViewVolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.senderText);
            senderTime = itemView.findViewById(R.id.senderTime);
        }
    }


}
