package com.demo.tom.mblocker;

import android.provider.BaseColumns;

/**
 * Created by tom on 16/6/12.
 */
public final class PostsContract {
    public PostsContract(){}

    public static abstract class Posts implements BaseColumns {
        public static final String TABLE_NAME = "post";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_POST_TIME = "PostTime";
        public static final String COLUMN_NAME_AUTHOR_AVATAR_URL = "AuthorAvatarUrl";
        public static final String COLUMN_NAME_VIEWS_COUNT = "ViewsCount";
        public static final String COLUMN_NAME_COMMENTS_COUNT = "CommentsCount";
        public static final String COLUMN_NAME_LIKES_COUNT = "LikesCount";
        public static final String COLUMN_NAME_LATEST_REPLY = "LatestReply";
        public static final String COLUMN_NAME_DEMO_CONTENT = "DemoContent";
    }
}
