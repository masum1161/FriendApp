package com.appdevs.friendapp.adpters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appdevs.friendapp.ChatActivity;
import com.appdevs.friendapp.R;
import com.appdevs.friendapp.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder>{
     private Context context;
     private List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }



    @NonNull
    @Override
    public UserAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_list_row, parent, false);
        return new UserAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterViewHolder holder, int position) {
        User user = userList.get(position);
        Picasso.get().load(user.getPhoto()).placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round).into(holder.profilePic);
        holder.userName.setText(user.getName());
        holder.userEmail.setText(user.getEmail());
        if (user.isActive()){
            holder.activeStatus.setColorFilter(context.getResources().getColor((R.color.green)));
        }else{
            holder.activeStatus.setColorFilter(context.getResources().getColor((R.color.red)));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("name", user.getName());
                intent.putExtra("uid", user.getUid());
                intent.putExtra("photo", user.getPhoto());
                intent.putExtra("active", user.isActive());
                intent.putExtra("email", user.getEmail());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserAdapterViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView profilePic;
        private CircleImageView activeStatus;
        private TextView userName;
        private TextView userEmail;

        public UserAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePic = itemView.findViewById(R.id.profilePicture);
            activeStatus = itemView.findViewById(R.id.activeStatusIV);
            userName = itemView.findViewById(R.id.user_name_tv);
            userEmail = itemView.findViewById(R.id.user_email_tv);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

        });
        super.onAttachedToRecyclerView(recyclerView);
    }
}
