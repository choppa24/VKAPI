package enums;

public enum StatusCodes {
    ERROR_NOT_FOUND(404),
    GET_OK(200),
    POST_CRATED(201);

    private int type;

    StatusCodes(int type){
        this.type = type;
    }

    public int toInteger(){
        return type;
    }
}
