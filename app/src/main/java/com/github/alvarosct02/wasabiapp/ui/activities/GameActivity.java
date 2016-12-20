package com.github.alvarosct02.wasabiapp.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.alvarosct02.wasabiapp.R;
import com.github.alvarosct02.wasabiapp.models.Session;
import com.github.alvarosct02.wasabiapp.models.User;
import com.github.alvarosct02.wasabiapp.ui.adapters.UserAdapter;
import com.github.alvarosct02.wasabiapp.utils.FirebaseRefs;
import com.github.alvarosct02.wasabiapp.utils.UtilMethods;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameActivity extends BaseActivity{

    RecyclerView rv_leaderboard;
    UserAdapter userAdapter;
    Button bt_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(R.layout.activity_game);
    }

    @Override
    public void setupVariables() {
        super.setupVariables();
        userAdapter = new UserAdapter(new ArrayList<User>());
    }

    @Override
    public void setupViews() {
        super.setupViews();
        bt_add = (Button) findViewById(R.id.bt_add);
        rv_leaderboard = (RecyclerView) findViewById(R.id.rv_leaderboard);
        rv_leaderboard.setHasFixedSize(true);
//        rv_leaderboard.addItemDecoration(new SimpleDividerItemDecoration(context, R.drawable.line_divider_black));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GameActivity.this);
        rv_leaderboard.setLayoutManager(layoutManager);
        rv_leaderboard.setAdapter(userAdapter);

    }

    @Override
    public void setupActions() {
        super.setupActions();
        UtilMethods.showLoadingDialog(this);
        startListening();

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseRefs.getInstance().getUser().runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {
                        User p = mutableData.getValue(User.class);
                        if (p == null) {
                            return Transaction.success(mutableData);
                        }

                        int count = p.getCount();
                        p.setCount(count + 1);

                        mutableData.setValue(p);
                        return Transaction.success(mutableData);
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b,
                                           DataSnapshot dataSnapshot) {
                        // Transaction completed
                        Log.d(TAG, "postTransaction:onComplete:" + databaseError);
                    }
                });
            }
        });
    }

    private void startListening(){
        FirebaseRefs.getInstance().getSession().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UtilMethods.hideLoadingDialog();
                Session session = dataSnapshot.getValue(Session.class);
                List<User> users = new ArrayList<>();
                for (User user: session.getUsers().values()) {
                    users.add(user);
                }
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User t1, User t2) {
                        return t2.getCount() - t1.getCount();
                    }
                });
                userAdapter.refreshData(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(GameActivity.this, "OnCancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
