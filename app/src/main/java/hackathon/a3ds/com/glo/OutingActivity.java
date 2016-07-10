package hackathon.a3ds.com.glo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by frank on 7/9/16.
 */
public class OutingActivity extends Activity {
    // URL for all Slack APIs
    private static String url = "https://pure-caverns-99011.herokuapp.com/getPosse";
    private JSONArray mUsers;
    private String mJsonString;
    String test = new String();
    ListView listView;
    ArrayList<String> values;
    // Get ListView object from xml

    public static final String NODE_MEMBERS = "allPosses";

    @TargetApi(Build.VERSION_CODES.M)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outing);
        final Context mContext = this;
        Button btnCancelOuting = (Button) findViewById(R.id.cancel);
        Button btnCreateOuting = (Button) findViewById(R.id.create);
        Button btnMembersOuting = (Button) findViewById(R.id.members);

        btnCancelOuting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnCreateOuting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, OutingViewActivity.class);
                startActivity(intent);
            }
        });
        btnCreateOuting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker timePicker1;
                timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
                int hour = timePicker1.getCurrentHour();
                int min = timePicker1.getCurrentMinute();
                EditText nameTxt = (EditText) findViewById(R.id.name);
                EditText destinationTxt = (EditText) findViewById(R.id.destination);
                String str_name = nameTxt.getText().toString();
                String str_des = destinationTxt.getText().toString();

                setContentView(R.layout.activity_posse);
            }
        });
        btnMembersOuting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new GetUsersList().execute();


            }
        });
    }
    private class GetUsersList extends AsyncTask<Void, Void, Void> {
        private  String url = "https://pure-caverns-99011.herokuapp.com/getPosse";
        private JSONArray mUsers;
        private String mJsonString;
        public static final String NODE_MEMBERS = "allPosses";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... args) {
            restHandler restHandler = new restHandler();

            // Make HTTP call with params
            List<NameValuePair> params = new LinkedList<NameValuePair>();
            mJsonString = restHandler.makeCall(url, restHandler.GET, null);

            if(mJsonString != null){
                try {
                    JSONObject jsonObject = new JSONObject(mJsonString);
                    mUsers = jsonObject.getJSONArray(NODE_MEMBERS);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                try{
                    // loop through all users
                    for(int i=0; i<mUsers.length(); i++){
                        JSONObject user = mUsers.getJSONObject(i);
                        test += user.getString("name");
                        test += "\n";
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                finally{
                    // Add user info - ensures that if a JSONException occurrs due to
                    // missing JSON response fields, that userinfo is still addded
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);

            // Defined Array values to show in ListView
            listView = (ListView) findViewById(R.id.listView);
            values = new ArrayList<String>();
            values.add(test);
            values.add("Jason Brown");
            values.add("Kelly Hauser");
            values.add("Frank Lin");
            values.add("Andrea Martinez");
            System.out.println(test);
            // Update usersList ListView with new parsed JSON data
            setContentView(R.layout.contacts_table);
           ListView listView = (ListView) findViewById(R.id.listView);
            //posList.add("test");

        }
    }
}


