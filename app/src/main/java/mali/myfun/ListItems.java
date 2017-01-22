package mali.myfun;

/**
 * Created by Mali on 1/21/2017.
 */

public class ListItems {
    private String title,link, thumb;

    public ListItems(String title,String link, String thumb){
        this.setTitle(title);
        this.setLink(link);
        this.setThumb(thumb);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
