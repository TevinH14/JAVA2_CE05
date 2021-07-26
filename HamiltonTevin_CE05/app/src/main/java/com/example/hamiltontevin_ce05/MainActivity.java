// Tevin Hamilton
// JAV2 - 2003
// MainActivity
package com.example.hamiltontevin_ce05;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.hamiltontevin_ce05.data.DatabaseHelper;
import com.example.hamiltontevin_ce05.fragment.BookDisplayFragment;
import com.example.hamiltontevin_ce05.model.Articles;
import com.example.hamiltontevin_ce05.network.NetworkTask;
import com.example.hamiltontevin_ce05.network.NetworkUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  NetworkTask.OnFinished {
    private static final String MOVIE_REDDIT_API = "https://www.reddit.com/r/arma3/hot.json";
    private static  DatabaseHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDB = DatabaseHelper.getInstance(MainActivity.this);
        startTask();
            getSupportFragmentManager().beginTransaction().add(R.id.fl_book_fragment_container, BookDisplayFragment.newInstance()).commit();
    }

    private void startTask(){
        if(NetworkUtils.isConnected(this)){
            if (mDB.getCount() <= 0) {
                NetworkTask task = new NetworkTask(this);
                task.execute(MOVIE_REDDIT_API);
            }
        }else {
            Toast.makeText(this,"please conncet to the internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnPost(ArrayList<Articles> redditPosts) {
        for(Articles a:redditPosts){
            mDB.insertPerson(a.getTitle(), a.getThumbnail(), a.getBody());
        }
    }



}
