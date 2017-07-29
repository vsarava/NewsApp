package com.example.vicky.newsapp.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.example.vicky.newsapp.data.Contract.TABLE_ARTICLES.*;


public class DatabaseUtils {

    public static Cursor getAll(SQLiteDatabase db) {
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                COLUMN_NAME_PUBLISHED_DATE + " DESC"
        );
        return cursor;
    }

    public static void bulkInsert(SQLiteDatabase db, ArrayList<Article> articles) {

        db.beginTransaction();
        try {
            for (Article a : articles) {
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_NAME_AUTHOR,a.getAuthor());
                cv.put(COLUMN_NAME_TITLE, a.getTitle());
                cv.put(COLUMN_NAME_DESCRIPTION, a.getDescription());
                cv.put(COLUMN_NAME_URL,a.getUrl());
                cv.put(COLUMN_NAME_URLTOIMAGE,a.getUrlToImage());
                cv.put(COLUMN_NAME_PUBLISHED_DATE, a.getPublishedAt());
                db.insert(TABLE_NAME, null, cv);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public static void deleteAll(SQLiteDatabase db) {
        db.delete(TABLE_NAME, null, null);
    }

}
