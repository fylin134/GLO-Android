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
public class PosseActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posse);
        final Context mContext = this;
        Button btnTestPosse = (Button) findViewById(R.id.next);

        btnTestPosse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, contactView.class);
                startActivity(intent);
            }
        });
    }
}
