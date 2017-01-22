package mali.myfun;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mali on 1/21/2017.
 */

public class PlaylistAdapter extends ArrayAdapter {
    List list=new ArrayList();
    public PlaylistAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Playlist object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row=convertView;
        PlaylistHolder playlistHolder;
        if(row==null){
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.list_row,parent,false);
            playlistHolder=new PlaylistHolder();
            playlistHolder.tx_listTitle=(TextView)row.findViewById(R.id.title);
            row.setTag(playlistHolder);
        }
        else {
            playlistHolder=(PlaylistHolder) row.getTag();
        }
        Playlist playlist=(Playlist)this.getItem(position);
        playlistHolder.tx_listTitle.setText(playlist.getListTitle());

        return row;
    }

    static class PlaylistHolder
    {
        TextView tx_listTitle,tx_array;
    }
}
