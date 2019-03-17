package com.saboo;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private Context context;
    private List<TopicModal> list;
    private Activity activity;

    public TopicAdapter(Context context, List<TopicModal> list, Activity activity) {
        this.context = context;
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.single_topic_row,viewGroup,false);
        return new TopicAdapter.TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder topicViewHolder, int i) {
        final TopicModal modal = list.get(i);
        topicViewHolder.textView.setText(modal.getText());
        topicViewHolder.imageView.setImageResource(modal.isSolved()?R.drawable.right:R.drawable.wrong);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_topic);
            imageView = itemView.findViewById(R.id.solved_topic);
        }
    }
}