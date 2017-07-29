package com.example.vicky.newsapp.data;

import android.provider.BaseColumns;

//Class created to define the table and column names

public class Contract {

    public static class TABLE_ARTICLES implements BaseColumns {
        public static final String TABLE_NAME = "articles";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_URLTOIMAGE = "urlToImage";
        public static final String COLUMN_NAME_PUBLISHED_DATE = "published_date";

    }
}
