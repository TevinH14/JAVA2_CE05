// Tevin Hamilton
// JAV2 - 2003
// Articles
package com.example.hamiltontevin_ce05.model;

public class Articles {
    private final String mTitle;
    private final String mThumbnail;
    private final String mBody;

    public Articles(String mTitle, String mThumbnail, String mBody) {
        this.mTitle = mTitle;
        this.mThumbnail = mThumbnail;
        this.mBody = mBody;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public String getBody() {
        return mBody;
    }
}
