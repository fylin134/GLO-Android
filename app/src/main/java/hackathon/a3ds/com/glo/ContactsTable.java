package hackathon.a3ds.com.glo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by matcurtis on 7/9/16.
 */
public class ContactsTable extends Activity {

    ListView listView;
    TextView name;
    ArrayList<String> values;
    ArrayList<String> finValues = new ArrayList<String>();
    Button btnSAN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setContentView(R.layout.contacts_table);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listView);

        // Defined Array values to show in ListView
        values = new ArrayList<String>();
        values.add("Android List View");
        values.add("Adapter implementation");
        values.add("Simple List View In Android");
        values.add("Create List View Android");
        values.add("Android Example");
        values.add("List View Source Code");
        values.add("List View Array Adapter");

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                addName(itemValue);


                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_SHORT)
                        .show();

            }

        });

        final String posse_name = intent.getStringExtra("posse_name");

        name = (TextView) findViewById(R.id.nameTextView);
        name.setText(posse_name);

        Button btnDone = (Button) findViewById(R.id.done);
        btnSAN = (Button) findViewById(R.id.selectAllNone);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Write(posse_name);
                Intent intent = new Intent(ContactsTable.this, HomeActivity.class);
                intent.putExtra("posse_members", finValues);
                startActivity(intent);
            }
        });

        btnSAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (values.size() == finValues.size()) {
                    finValues.clear();
                } else {
                    finValues = values;
                }

            }
        });
    }

    private void addName(String name) {
        if (values.size() == finValues.size()) {
        }
        if (finValues.contains(name))
            return;
        finValues.add(name);
    }
    public void Write(String name) {


        SharedPreferences sharedPref = ContactsTable.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < finValues.size(); i++) {
            sb.append(finValues.get(i) + ",");
        }
        editor.putString(name, sb.toString());
        editor.commit();
        System.out.println("sb "+sb.toString());

        SharedPreferences sharedPre = ContactsTable.this.getPreferences(Context.MODE_PRIVATE);
        //int defaultValue = getResources().getInteger(R.string.saved_high_score_default);
        String temp = sharedPre.getString(name,"");
        System.out.println("temp : "+ temp);

        List elephantList = Arrays.asList(temp.split(","));
        for (int i = 0; i < elephantList.size(); i++) {
            System.out.println("elephantList.get("+i+") "+ elephantList.get(i));
        }
    }
}
