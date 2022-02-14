package enums;

public enum Methods {
    WALL_POST("/wall.post"),
    PHOTOS_GET_WALL_UPLOAD_SERVER("/photos.getWallUploadServer"),
    WALL_CREATE_COMMENT ("/wall.createComment"),
    LIKES_IS_LIKED("/likes.isLiked"),
    WALL_DELETE ("/wall.delete"),
    WALL_EDIT("/wall.edit"),
    SAVE_WALL_PHOTO("/photos.saveWallPhoto");

    private String method;

    Methods(String method){
        this.method = method;
    }
    @Override
    public String toString(){
        return method;
    }
}
