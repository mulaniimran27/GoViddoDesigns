package blockchainvideoapp.com.goviddo.goviddo.coreclass;

public class Recycler_Subscription {
    private String mDescription;
    private String imageView;
    private String mTitle;

    public Recycler_Subscription(String imageView, String mTitle) {
        this.imageView = imageView;
        this.mTitle = mTitle;
    }

    public String getImageView() {
        return imageView;
    }

    public String getmTitle() {
        return mTitle;
    }

    public Recycler_Subscription(String imageView, String mTitle, String descriptiom) {
        this.imageView = imageView;
        this.mTitle = mTitle;
        this.mDescription = descriptiom;
    }

    public String getmDescription() {
        return mDescription;
    }

}

