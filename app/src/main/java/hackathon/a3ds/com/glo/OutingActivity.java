package hackathon.a3ds.com.glo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by frank on 7/9/16.
 */
public class OutingActivity extends Activity {

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
    }
}
