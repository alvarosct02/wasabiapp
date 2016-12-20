package com.github.alvarosct02.wasabiapp.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alvarosct02.wasabiapp.R;
import com.github.alvarosct02.wasabiapp.models.User;

import java.util.List;
import java.util.Map;

/**
 * Created by rubymobile on 12/20/16.
 */


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_user, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);

        holder.tv_name.setText(user.getName());
        holder.tv_count.setText(""+user.getCount());
    }


    public void refreshData(List<User> users){
        this.users.clear();
        this.users.addAll(users);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_count;

        ViewHolder(View v) {
            super(v);

            tv_name = (TextView) v.findViewById(R.id.tv_name);
            tv_count = (TextView) v.findViewById(R.id.tv_count);
        }
    }
}

