package blockchainvideoapp.com.goviddo.goviddo.coreclass;

import android.widget.ImageView;

public class RecyclerCardViewModel {

    String name, heading, url, short_text, vdo_cipherid;
    int count, videoid;


   public RecyclerCardViewModel(String heading, int count) {
        this.heading = heading;
        this.count = count;
    }

    public RecyclerCardViewModel(String url, String short_text, String vdo_cipherid, int videoid) {
        this.url = url;
        this.short_text = short_text;
        this.vdo_cipherid = vdo_cipherid;
        this.videoid = videoid;

    }


    public String getHeading() {
        return heading;
    }

    public int getCount() {
        return count;
    }

    public String getUrl() {
        return url;
    }

    public String getShort_text() {
        return short_text;
    }

    public String getVdo_cipherid() {
        return vdo_cipherid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setShort_text(String short_text) {
        this.short_text = short_text;
    }

    public void setVdo_cipherid(String vdo_cipherid) {
        this.vdo_cipherid = vdo_cipherid;
    }

    public void setVideoid(int videoid) {
        this.videoid = videoid;
    }

    public int getVideoid() {
        return videoid;

    }
}
