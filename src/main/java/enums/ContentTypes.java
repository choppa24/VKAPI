package enums;

public enum ContentTypes {
    APPLICATION_JSON("application/json"),
    MULTIPART("multipart/form-data"),
    FILE("file"),
    PHOTO("photo");

    private String type;

    ContentTypes(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }
}
