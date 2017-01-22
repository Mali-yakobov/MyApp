package mali.myfun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;

public class MenuActivity extends AppCompatActivity {
    private String urlJsonObj = " http://www.razor-tech.co.il/hiring/youtube-api.json";
    Button start;
    TextView text;
    RequestQueue requestQueue;
    String jsonResponse;
    String jsonString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        requestQueue= Volley.newRequestQueue(MenuActivity.this);
        String username=getIntent().getStringExtra("uname");
        TextView textUserName=(TextView)findViewById(R.id.userName);
        textUserName.setText(username);
        start=(Button)findViewById(R.id.button_youtube);
        text=(TextView) findViewById(R.id.textView);
        start.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                makeJsonArrayRequest();
            }
        });
    }

    private void makeJsonArrayRequest() {

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(urlJsonObj, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    jsonResponse = "";
                    for (int i = 0; i < response.length(); i++) {
                        JsonObject playList = (JsonObject) response.get(i);
                        String listTitle = String.valueOf(playList.get("ListTitle"));
                        JsonArray listItemsArray = playList.getAsJsonArray("ListItems");
                        int n = listItemsArray.size();
                        jsonResponse += "listTitle: " + listTitle + "\n\n";
                        for (int j = 0; j < n; j++) {
                            JsonObject listItems = (JsonObject) listItemsArray.get(j);
                            String title = String.valueOf(listItems.get("Title"));
                            String link = String.valueOf(listItems.get("link"));
                            String thumb = String.valueOf(listItems.get("thumb"));
                            jsonResponse += "ListItems:" ;
                            jsonResponse += "[title: " + title + "\n\n";
                            jsonResponse += "link: " + link + "\n\n";
                            jsonResponse += "thumb: " + thumb +"]"+ "\n\n";
                        }
                    }
                    text.setText(jsonResponse);
                    jsonString=jsonResponse;
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse (VolleyError error){
                VolleyLog.d(MenuActivity.class.getSimpleName(), "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

        requestQueue.add(jsonObjReq);
    }

    public void parseJson(){
        Intent i = new Intent(this, DisplayPlaylist.class);
        i.putExtra("json_data", jsonString);
        startActivity(i);

    }
}

