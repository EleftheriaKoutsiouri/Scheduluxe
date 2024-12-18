package Scheduluxe;

public class Destination {
    private int destinationId;
    private String destinationName;
    private String destinationPhotoPath;
    private String destinationDetails;



    public Destination(int destId, String destName, String destPhotoPath, String destDetails) {
        this.destinationId = destId;
        this.destinationName = destName;
        this.destinationPhotoPath = destPhotoPath;
        this.destinationDetails = destDetails;
    }

    //getters
    public int getDestId() {
        return destinationId;
    }

    public String getDestName() {
        return destinationName;
    }

    public String getDestPhotoPath() {
        return destinationPhotoPath;
    }

    public String getDestDetails() {
        return destinationDetails;
    }
}
