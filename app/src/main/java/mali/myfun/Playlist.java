package mali.myfun;

import java.util.ArrayList;

/**
 * Created by Mali on 1/21/2017.
 */

public class Playlist {
    private String listTitle;
    private ArrayList<ListItems> listItemses;

    public Playlist(String listTitle,ArrayList<ListItems> listItemses){
        this.setListTitle(listTitle);
        this.listItemses=listItemses;

    }


    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }


}
