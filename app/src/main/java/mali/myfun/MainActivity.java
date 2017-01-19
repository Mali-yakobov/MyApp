package mali.myfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper helper=new DataBaseHelper(this);
    // UI references.
    private EditText username;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onButtonClick(View v){

        if(v.getId()==R.id.buttonLogin) {
            EditText username=(EditText)findViewById(R.id.username);
            String strUsername=username.getText().toString();
            EditText pass=(EditText)findViewById(R.id.password);
            String str_password=pass.getText().toString();

            //String password=helper.searchPass(str_password);

            //if(str_password.equals(password)) {
                Intent i = new Intent(MainActivity.this, MenuActivity.class);
                i.putExtra("uname", strUsername);
                startActivity(i);
           // }
           // else{
              //  Toast temp=Toast.makeText(MainActivity.this,"Please SignUp",Toast.LENGTH_SHORT);
              //  temp.show();
            //}
        }

        if(v.getId()==R.id.signUp) {
            Intent i = new Intent(MainActivity.this, SignUp.class);
            startActivity(i);

        }
    }


}
