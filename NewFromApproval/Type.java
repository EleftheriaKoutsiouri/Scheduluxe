package NewFromApproval;

public class Type {

    private int typeId;
    private String typeName;

    public Type(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    /* getters */
    public int getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }
    
}
