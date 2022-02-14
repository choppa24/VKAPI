package enums;

public enum ScrollTypes {
    SCROLL_X(0),
    SCROLL_Y(250);

    private int type;

    ScrollTypes(int type){
        this.type = type;
    }

    public int toInteger(){
        return type;
    }
}
