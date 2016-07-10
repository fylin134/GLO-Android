package hackathon.a3ds.com.glo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

/**
 * Created by frank on 7/9/16.
 */
public class OutingActivity extends Activity {

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
    }
}
