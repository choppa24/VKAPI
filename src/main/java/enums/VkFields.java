package enums;

public enum VkFields {
    OWNER_ID("owner_id"),
    GROUP_ID("group_id"),
    POST_ID("post_id"),
    TYPE("type"),
    ITEM_ID("item_id"),
    MESSAGE("message"),
    VERSION("v"),
    PHOTO("photo"),
    HASH("hash"),
    SERVER("server"),
    ACCESS_TOKEN("access_token"),
    ATTACHMENTS("attachments");

    private String type;

    VkFields(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }
}
