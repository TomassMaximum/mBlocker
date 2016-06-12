package com.demo.tom.mblocker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tom on 16/6/9.
 */
public class PostFragment extends Fragment {

    private static final String TAG = PostFragment.class.getSimpleName();

    RecyclerView postRecyclerView;

    PostAdapter postAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate被调用");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,"onActivityCreated被调用");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.e(TAG,"onCreateView被调用");
        View rootView = inflater.inflate(R.layout.fragment_all,container,false);

        postRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_post);

        postAdapter = new PostAdapter();

        postRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        postRecyclerView.setAdapter(postAdapter);

        return rootView;
    }

    private class PostAdapter extends RecyclerView.Adapter {

        NewsFragment newsFragment;

        public PostAdapter(){
            newsFragment = new NewsFragment();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {

            CardView cardView;

            ImageView authorAvatar;
            TextView authorName;
            TextView postTime;
            TextView viewsCount;
            TextView commentsCount;

            TextView postTitle;
            TextView postDemoContent;

            TextView replyTime;
            TextView likesCount;
            ImageView likesIcon;

            public ViewHolder(View itemView) {
                super(itemView);

                cardView = (CardView) itemView.findViewById(R.id.post_card_view);

                authorAvatar = (ImageView) itemView.findViewById(R.id.author_avatar);
                authorName = (TextView) itemView.findViewById(R.id.author_name);
                postTime = (TextView) itemView.findViewById(R.id.post_time);
                viewsCount = (TextView) itemView.findViewById(R.id.views_count);
                commentsCount = (TextView) itemView.findViewById(R.id.comments_count);

                postTitle = (TextView) itemView.findViewById(R.id.post_title);
                postDemoContent = (TextView) itemView.findViewById(R.id.post_demo_content);

                replyTime = (TextView) itemView.findViewById(R.id.reply_time);
                likesCount = (TextView) itemView.findViewById(R.id.likes_count);
                likesIcon = (ImageView) itemView.findViewById(R.id.likes_icon);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.post_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            Log.e(TAG,"RecyclerView的当前项目为:" + position);

            if (postAdapter != null){
                if (position == 0){
                    if (!newsFragment.isAdded()){
                        Log.e(TAG,"添加新闻Fragment");
                        viewHolder.cardView.removeAllViews();
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.add(R.id.post_card_view,newsFragment);
                        fragmentTransaction.commit();
                    }
                }else {
                    viewHolder.authorAvatar.setImageResource(R.drawable.ic_makeblock);
                    viewHolder.authorName.setText("Makeblock");
                    viewHolder.postTime.setText("2016-06-09 22:12:22");
                    viewHolder.viewsCount.setText("877");
                    viewHolder.commentsCount.setText("22");
                    viewHolder.postTitle.setText("What kind questions can I ask in the AMA meeting?");
                    viewHolder.replyTime.setText("2016-06-10 12:32:23");
                    viewHolder.likesCount.setText("12");
                    viewHolder.postDemoContent.setText("I've heard that the AMA meeting is held once a week. So, what kind questions exactly I can ask? Does it...");
                }
            }
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public ContentValues getDataFromDb(){
            PostsDbHelper postsDbHelper = new PostsDbHelper(getContext(),null,null,1);
            SQLiteDatabase db = postsDbHelper.getReadableDatabase();
            Cursor cursor = db.query(PostsContract.Posts.TABLE_NAME,null,null,null,null,null,null);
            cursor.moveToFirst();

            return null;
        }
    }

    public void extractDataFromJson(String jsonString){

        PostsDbHelper postsDbHelper = new PostsDbHelper(getContext(),null,null,1);
        SQLiteDatabase db = postsDbHelper.getWritableDatabase();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray posts = jsonObject.getJSONArray("post");

            for (int i = 0;i < posts.length();i++){
                JSONObject currentPost = posts.getJSONObject(i);
                String title = currentPost.getString("title");
                String postTime = currentPost.getString("PostTime");
                String author = currentPost.getString("author");
                String AuthorAvatarUrl = currentPost.getString("AuthorAvatarUrl");
                String CommentsCount = currentPost.getString("CommentsCount");
                String ViewsCount = currentPost.getString("ViewsCount");
                String LikesCount = currentPost.getString("LikesCount");
                String LatestReply = currentPost.getString("LatestReply");
                String DemoContent = currentPost.getString("DemoContent");

                ContentValues contentValues = new ContentValues();

                contentValues.put(PostsContract.Posts.COLUMN_NAME_TITLE,title);
                contentValues.put(PostsContract.Posts.COLUMN_NAME_POST_TIME,postTime);
                contentValues.put(PostsContract.Posts.COLUMN_NAME_AUTHOR,author);
                contentValues.put(PostsContract.Posts.COLUMN_NAME_AUTHOR_AVATAR_URL,AuthorAvatarUrl);
                contentValues.put(PostsContract.Posts.COLUMN_NAME_COMMENTS_COUNT,CommentsCount);
                contentValues.put(PostsContract.Posts.COLUMN_NAME_VIEWS_COUNT,ViewsCount);
                contentValues.put(PostsContract.Posts.COLUMN_NAME_LIKES_COUNT,LikesCount);
                contentValues.put(PostsContract.Posts.COLUMN_NAME_LATEST_REPLY,LatestReply);
                contentValues.put(PostsContract.Posts.COLUMN_NAME_DEMO_CONTENT,DemoContent);

                db.insert(PostsContract.Posts.TABLE_NAME,null,contentValues);
            }
            db.close();

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG,"Json数据解析出错");
        }
    }

}
