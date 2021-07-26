// Tevin Hamilton
// JAV2 - 2003
// NetworkTask
package com.example.hamiltontevin_ce05.network;

import android.os.AsyncTask;

import com.example.hamiltontevin_ce05.model.Articles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NetworkTask extends AsyncTask<String,String, ArrayList<Articles>> {

    final private OnFinished mOnFinishedInterface;

    public NetworkTask(OnFinished mOnFinishedInterface) {
        this.mOnFinishedInterface = mOnFinishedInterface;
    }

    //create a interface to pass and retrieve data to Network Task to download JSON obj
    public interface OnFinished{
        void OnPost(ArrayList<Articles> redditArticles);
    }

    @Override
    protected ArrayList<Articles> doInBackground(String... strings) {

        if(strings == null || strings.length <= 0 || strings[0].trim().isEmpty()){
            return null;
        }
        String data = null;
        try {
            data = NetworkUtils.getNetworkData(strings[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(data != null) {
            try {

                JSONObject response = new JSONObject(data);

                JSONObject postJson = response.getJSONObject("data");
                JSONArray postJSONArray = postJson.getJSONArray("children");

                ArrayList<Articles> postArrayList = new ArrayList<>();

                for (int i = 0; i < postJSONArray.length(); i++) {
                    JSONObject obj = postJSONArray.getJSONObject(i);
                    JSONObject postObj = obj.getJSONObject("data");

                    String title = postObj.getString("title");
                    String thumbnail = postObj.getString("thumbnail");
                    String body = postObj.getString("selftext");

                    postArrayList.add(new Articles(title, thumbnail, body));
                }
                // Update the UI
                return postArrayList;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    @Override
    protected void onPostExecute(ArrayList<Articles> redditPosts) {
        mOnFinishedInterface.OnPost(redditPosts);
    }
}
