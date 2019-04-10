package blockchainvideoapp.com.goviddo.goviddo.coreclass;

public class RecyclerModel {


    private String title;
    private String description;

    public RecyclerModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


}