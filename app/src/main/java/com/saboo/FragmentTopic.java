package com.saboo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentTopic extends Fragment {
    private Button mFirebasebutton;
    private DatabaseReference reference;
    RecyclerView linearView;
    TopicAdapter adapter;
    ArrayList<TopicModal> list = new ArrayList<>();
    EditText topic;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_topic,container,false);

        topic = (EditText) rootview.findViewById(R.id.topic);
        linearView = rootview.findViewById(R.id.recyclerView);
        linearView.setLayoutManager(new LinearLayoutManager(rootview.getContext()));

        adapter = new TopicAdapter(rootview.getContext(),list,getActivity());
        linearView.setAdapter(adapter);
        mFirebasebutton = (Button) rootview.findViewById(R.id.submittopic);
        reference = FirebaseDatabase.getInstance().getReference();
        mFirebasebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String did1 = reference.child("Topics").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().getKey();
                HashMap<String,Object> map = new HashMap<>();
                map.put("text",topic.getText().toString());
                map.put("solved",false);
                reference.child("Topics").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(did1).setValue(map);
            }
        });

        FirebaseDatabase.getInstance().getReference().child("Topics").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TopicModal modal = dataSnapshot.getValue(TopicModal.class);
                list.add(modal);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return rootview;
    }
}