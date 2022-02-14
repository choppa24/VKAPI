package enums;

public enum Paths {
    RESOURCES("src/main/resources/");

    private String type;

    Paths(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }
}
