package hackathon.a3ds.com.glo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static hackathon.a3ds.com.glo.R.id.posse_name;

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

                EditText edText1 = (EditText) findViewById(R.id.posse_name);
                edText1.setInputType(InputType.TYPE_CLASS_TEXT);
                String str = edText1.getText().toString();
                Intent intent = new Intent(mContext, ContactActivity.class);
                intent.putExtra("posse_name",str);
                startActivity(intent);

            }
        });
    }
}
