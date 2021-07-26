// Tevin Hamilton
// JAV2 - 2003
// BookDisplayAdapter
package com.example.hamiltontevin_ce05.adpter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import com.example.hamiltontevin_ce05.R;
import com.example.hamiltontevin_ce05.data.DataContract;
import com.loopj.android.image.SmartImageView;

public class BookDisplayAdapter extends ResourceCursorAdapter{


    public BookDisplayAdapter(Context context, int layout, Cursor c) {
        super(context, layout, c, 0);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv = view.findViewById(R.id.tv_title_display);
        SmartImageView si = view.findViewById(R.id.iv_book_display);

        int titleColumnIndex = cursor.getColumnIndex(DataContract.TITLE);
        tv.setText(cursor.getString(titleColumnIndex));

        int thumbnailColumnIndex = cursor.getColumnIndex(DataContract.THUMBNAIL);
        String imageString = cursor.getString(thumbnailColumnIndex);
        si.setImageUrl(imageString);
    }
}
