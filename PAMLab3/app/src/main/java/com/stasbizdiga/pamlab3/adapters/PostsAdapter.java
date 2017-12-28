package com.stasbizdiga.pamlab3.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.stasbizdiga.pamlab3.R;
import com.stasbizdiga.pamlab3.models.Post;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {

    private Context context;
    private List<Post> postList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Button title;
        public TextView description;
        public TextView author;
        public TextView pubDate;

        public MyViewHolder(View view) {
            super(view);
            title = (Button) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            author = (TextView) view.findViewById(R.id.author);
            pubDate = (TextView) view.findViewById(R.id.time);
            }
    }


    public PostsAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Post post = postList.get(position);
        holder.title.setText(post.getTitle());
        holder.pubDate.setText(post.getPubDate());
        holder.author.setText(post.getAuthor());
        holder.description.setText(post.getDescription());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = post.getLink();

                Uri uriUrl = Uri.parse(url);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                v.getContext().startActivity(launchBrowser);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}