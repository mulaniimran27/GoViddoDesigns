package blockchainvideoapp.com.goviddo.goviddo.coreclass;

public class RecyclerRecent {

    private String mTextFirst;
    private String mImage;
    private String mtext;

    public RecyclerRecent(String mImage, String mText) {
        this.mTextFirst = mText;
        this.mImage = mImage;
    }

    public RecyclerRecent( String mText) {
        this.mtext = mText;
    }

    public String getmTextFirst() {
        return mTextFirst;
    }

    public String getmImage() {
        return mImage;
    }

    public String getMtext() {
        return mtext;
    }
}
