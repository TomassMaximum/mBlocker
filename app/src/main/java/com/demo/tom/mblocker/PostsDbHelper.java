package com.demo.tom.mblocker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tom on 16/6/12.
 */
public class PostsDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "posts.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_POSTS =
            "CREATE TABLE " + PostsContract.Posts.TABLE_NAME + " (" +
                    PostsContract.Posts._ID + " INTEGER PRIMARY KEY," +
                    PostsContract.Posts.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    PostsContract.Posts.COLUMN_NAME_AUTHOR + TEXT_TYPE + COMMA_SEP +
                    PostsContract.Posts.COLUMN_NAME_AUTHOR_AVATAR_URL + TEXT_TYPE + COMMA_SEP +
                    PostsContract.Posts.COLUMN_NAME_VIEWS_COUNT + TEXT_TYPE + COMMA_SEP +
                    PostsContract.Posts.COLUMN_NAME_COMMENTS_COUNT + TEXT_TYPE + COMMA_SEP +
                    PostsContract.Posts.COLUMN_NAME_LIKES_COUNT + TEXT_TYPE + COMMA_SEP +
                    PostsContract.Posts.COLUMN_NAME_POST_TIME + TEXT_TYPE + COMMA_SEP +
                    PostsContract.Posts.COLUMN_NAME_LATEST_REPLY + TEXT_TYPE + COMMA_SEP +
                    PostsContract.Posts.COLUMN_NAME_DEMO_CONTENT + TEXT_TYPE + COMMA_SEP +
                    " )";

    private static final String SQL_DELETE_POSTS =
            "DROP TABLE IF EXISTS " + PostsContract.Posts.TABLE_NAME;

    public PostsDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_POSTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_POSTS);
        onCreate(db);
    }
}
