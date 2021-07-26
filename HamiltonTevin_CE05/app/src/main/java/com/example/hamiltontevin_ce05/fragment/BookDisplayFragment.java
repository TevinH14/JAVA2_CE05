// Tevin Hamilton
// JAV2 - 2003
// BookDisplayFragment
package com.example.hamiltontevin_ce05.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.ListFragment;

import com.example.hamiltontevin_ce05.R;
import com.example.hamiltontevin_ce05.adpter.BookDisplayAdapter;
import com.example.hamiltontevin_ce05.data.DataContract;

public class BookDisplayFragment extends ListFragment {
    private static final int PERMISSIONS_REQUEST_ACCESS_DATA = 4097;
    private static final String PERMISSION_ACCESS_DATA = "com.fullsail.ce05.provider.AccessData";

    private Cursor mCursor;
    public BookDisplayFragment() {
    }

    public static BookDisplayFragment newInstance() {

        Bundle args = new Bundle();

        BookDisplayFragment fragment = new BookDisplayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display_books,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = getActivity();

        if(activity!=null) {
            int result = ContextCompat.checkSelfPermission(activity, PERMISSION_ACCESS_DATA);
            if (result != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{PERMISSION_ACCESS_DATA}, PERMISSIONS_REQUEST_ACCESS_DATA);
            }else {
                getBook();
            }
        }
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Activity a = getActivity();
        if(a != null) {
            mCursor.moveToPosition(position);
            int titleColumnIndex = mCursor.getColumnIndex(DataContract.TITLE);
            String title = mCursor.getString(titleColumnIndex);
            int bodyColumnIndex = mCursor.getColumnIndex(DataContract.BOOK_DESCRIPTION);
            String body = mCursor.getString(bodyColumnIndex);
            AlertDialog.Builder builder = new AlertDialog.Builder(a);
            builder.setTitle(title);
            builder.setMessage(body)
                    .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder.show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == PERMISSIONS_REQUEST_ACCESS_DATA) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getBook();
                }
            } else {
                Toast.makeText(getActivity(), R.string.get_books, Toast.LENGTH_SHORT).show();
            }
    }

    private void getBook(){
        Activity a = getActivity();
        if(a!=null) {
            ContentResolver cr = a.getContentResolver();
            if (cr != null) {
                mCursor = cr.query(DataContract.BOOK_CONTENT_URI, null, null, null, null);
                setListAdapter(new BookDisplayAdapter(a, R.layout.book_main_display_layout,mCursor));
            }
        }
    }
}
