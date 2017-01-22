package mali.myfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DisplayPlaylist extends AppCompatActivity {
    String jsonString;
    JSONObject jsonObject;
    JSONArray jsonArray;
    JSONArray internaljsonArray;
    PlaylistAdapter playlistAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_playlist);
        playlistAdapter=new PlaylistAdapter(this,R.layout.list_row);
        listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(playlistAdapter);
        jsonString=getIntent().getStringExtra("json_data");

        try {
            jsonObject=new JSONObject(jsonString);
            jsonArray=jsonObject.getJSONArray("Playlists");
            int i=0,j=0;
            String listTitle,title,link,thumb;
            ArrayList<ListItems> listItemses = null;

            while (i<jsonArray.length()){
                JSONObject jObject =jsonArray.getJSONObject(i);
                listTitle=jObject.getString("ListTitle");
                internaljsonArray=jObject.getJSONArray("ListItems");
                while (j<internaljsonArray.length()){
                    JSONObject jsonObject1=internaljsonArray.getJSONObject(j);
                    title=jsonObject1.getString("Title");
                    link=jsonObject1.getString("link");
                    thumb=jsonObject1.getString("thumb");
                    ListItems l=new ListItems(title,link,thumb);
                    listItemses.add(l);
                    j++;
                }
                Playlist p=new Playlist(listTitle,listItemses);
                playlistAdapter.add(p);
                i++;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
