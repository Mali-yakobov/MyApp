package mali.myfun;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Mali on 1/17/2017.
 */

public class SignUp extends Activity {
    DataBaseHelper helper=new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

    }
    public void OnSignUpClick(View v){
        if(v.getId()==R.id.button_signup) {
            EditText name = (EditText) findViewById(R.id.name_text);
            EditText email = (EditText) findViewById(R.id.email_text);
            EditText username = (EditText) findViewById(R.id.username_text);
            EditText password = (EditText) findViewById(R.id.password_text);
            EditText confirmPassword = (EditText) findViewById(R.id.confirm_text);

            String str_name = name.getText().toString();
            String str_email = email.getText().toString();
            String str_username = username.getText().toString();
            String str_password = password.getText().toString();
            String str_confirmPassword = confirmPassword.getText().toString();

            if(!str_password.equals(str_confirmPassword)){
                Toast temp=Toast.makeText(SignUp.this,"Passwrds don't match",Toast.LENGTH_SHORT);
                temp.show();
            }
            else{
                Contact c=new Contact();
                 c.setName(str_name);
                 c.setUsername(str_username);
                 c.setEmail(str_email);
                 c.setPassword(str_password);

                helper.insertContact(c);
                Toast temp=Toast.makeText(SignUp.this,"Thank you for SignUp!",Toast.LENGTH_SHORT);
                temp.show();
            }

        }


    }

}
