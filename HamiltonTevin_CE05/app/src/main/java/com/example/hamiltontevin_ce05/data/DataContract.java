
package com.example.hamiltontevin_ce05.data;

import android.net.Uri;

public class DataContract {

    /*
     *  Table name
     */
    public static final String DATA_TABLE = "articles";

    private static final String BOOK_DATA_TABLE = "books";


    /**
     * Table columns
     */
    public static final String ID = "_id";
    public static final String TITLE = "title";
    public static final String THUMBNAIL = "thumbnail";
    public static final String BODY = "body";

    public static final String BOOK_DESCRIPTION = "description";
    public static final String BOOK_ID = "book_id";

    /**
     * URI
     */
    public static final String URI_AUTHORITY = "com.fullsail.ce05.student.provider";
    private static final String CONTENT_URI_STRING = "content://" + URI_AUTHORITY + "/";
    public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING + DATA_TABLE);

    private static final String BOOK_URI_AUTHORITY = "com.fullsail.ce05.provider";
    private static final String BOOK_CONTENT_URI_STRING = "content://" + BOOK_URI_AUTHORITY + "/";

    public static final Uri BOOK_CONTENT_URI = Uri.parse(BOOK_CONTENT_URI_STRING + BOOK_DATA_TABLE);

}
