package NewFromApproval;

public class Destination {

    private int destinationId;
    private String destinationName;
    private String destinationPhotoPath;
    private String destinationDetails;
    private float latitude;
    private float longtitude;



    public Destination(int destId, String destName) {
        this.destinationId = destId;
        this.destinationName = destName;
    }

    /* getters */
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

    public float getLatitude() {
        return latitude;
    }

    public float getLongtitude() {
        return longtitude;
    }
}
