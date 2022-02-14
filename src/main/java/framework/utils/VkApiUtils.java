package framework.utils;

import enums.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;

public class VkApiUtils {
    private static Response response;
    private static RequestSpecification request;
    private static  String res;
    private static  Object server;
    private static  Object photo;
    private static  Object hash;

    public static int wallPost(String message){
        RestAssured.baseURI = PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"urlAPI");
        request = RestAssured.given();
        response = request.when()
                .basePath(Methods.WALL_POST.toString())
                .formParam(VkFields.OWNER_ID.toString(), PropertiesUtility.getIntValue(Resources.TEST.toString(),"ownerId"))
                .formParam(VkFields.ACCESS_TOKEN.toString(), PropertiesUtility.getStringValue(Resources.TEST.toString(),"token"))
                .formParam(VkFields.VERSION.toString(),PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"version"))
                .formParam(VkFields.MESSAGE.toString(),message)
                .post()
                .then()
                .statusCode(StatusCodes.GET_OK.toInteger())
                .extract().response();
        return response.getBody().jsonPath().get("response.post_id");
    }

    public static void getWallUploadServer(){
        RestAssured.baseURI = PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"urlAPI");
        request = RestAssured.given();
        response = request.when()
                .basePath(Methods.PHOTOS_GET_WALL_UPLOAD_SERVER.toString())
                .formParam(VkFields.OWNER_ID.toString(), PropertiesUtility.getIntValue(Resources.TEST.toString(),"ownerId"))
                .formParam(VkFields.ACCESS_TOKEN.toString(), PropertiesUtility.getStringValue(Resources.TEST.toString(),"token"))
                .formParam(VkFields.VERSION.toString(),PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"version"))
                .post()
                .then()
                .statusCode(StatusCodes.GET_OK.toInteger())
                .extract().response();
        res = response.getBody().jsonPath().get("response.upload_url");
    }

    public static void sendPhotoToURL(){
        VkApiUtils.getWallUploadServer();
        RestAssured.baseURI = res;
        response = request.when()
                .contentType(ContentTypes.MULTIPART.toString())
                .multiPart(VkFields.PHOTO.toString(), new File(PropertiesUtility.getStringValue(Resources.TEST.toString(),"file_name")))
                .post(res)
                .then()
                .statusCode(StatusCodes.GET_OK.toInteger())
                .extract().response();
        server = response.getBody().jsonPath().get(VkFields.SERVER.toString());
        photo = response.getBody().jsonPath().get(VkFields.PHOTO.toString());
        hash = response.getBody().jsonPath().get(VkFields.HASH.toString());
    }

    public static int savePhotoToWall(){
        VkApiUtils.sendPhotoToURL();
        RestAssured.baseURI = PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"urlAPI");
        Response response1 = request
                .when()
                .basePath(Methods.SAVE_WALL_PHOTO.toString())
                .formParam(VkFields.SERVER.toString(), server)
                .formParam(VkFields.PHOTO.toString(), photo)
                .formParam(VkFields.HASH.toString(), hash)
                .formParam(VkFields.ACCESS_TOKEN.toString(), PropertiesUtility.getStringValue(Resources.TEST.toString(),"token"))
                .formParam(VkFields.VERSION.toString(),PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"version"))
                .post()
                .then()
                .statusCode(StatusCodes.GET_OK.toInteger())
                .extract().response();
        int photoId = response1.getBody().jsonPath().get("response[0].id");
        System.out.println(photoId);
        return  photoId;
    }


    public static void wallEdit(int post_id, String message, int photo_id ){
        RestAssured.baseURI = PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"urlAPI");
        request = RestAssured.given();
        response = request.when()
                .basePath(Methods.WALL_EDIT.toString())
                .formParam(VkFields.OWNER_ID.toString(), PropertiesUtility.getIntValue(Resources.TEST.toString(),"ownerId"))
                .formParam(VkFields.ACCESS_TOKEN.toString(), PropertiesUtility.getStringValue(Resources.TEST.toString(),"token"))
                .formParam(VkFields.VERSION.toString(),PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"version"))
                .formParam(VkFields.POST_ID.toString(),post_id)
                .formParam(VkFields.MESSAGE.toString(),message)
                .formParam(VkFields.ATTACHMENTS.toString(),VkFields.PHOTO.toString() +
                        PropertiesUtility.getStringValue(Resources.TEST.toString(),"photoOwnerId") + photo_id)
                .post()
                .then()
                .statusCode(StatusCodes.GET_OK.toInteger())
                .extract().response();
    }

    public static void wallCreateComment(int post_id, String message){
        RestAssured.baseURI = PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"urlAPI");
        request = RestAssured.given();
        response = request.when()
                .basePath(Methods.WALL_CREATE_COMMENT.toString())
                .formParam(VkFields.OWNER_ID.toString(), PropertiesUtility.getIntValue(Resources.TEST.toString(),"ownerId"))
                .formParam(VkFields.ACCESS_TOKEN.toString(), PropertiesUtility.getStringValue(Resources.TEST.toString(),"token"))
                .formParam(VkFields.VERSION.toString(),PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"version"))
                .formParam(VkFields.POST_ID.toString(),post_id)
                .formParam(VkFields.MESSAGE.toString(),message)
                .post()
                .then()
                .statusCode(StatusCodes.GET_OK.toInteger())
                .extract().response();
    }

    public static int likesIsLiked(int post_id){
        RestAssured.baseURI = PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"urlAPI");
        request = RestAssured.given();
        response = request.when()
                .basePath(Methods.LIKES_IS_LIKED.toString())
                .formParam(VkFields.OWNER_ID.toString(), PropertiesUtility.getIntValue(Resources.TEST.toString(),"ownerId"))
                .formParam(VkFields.ACCESS_TOKEN.toString(), PropertiesUtility.getStringValue(Resources.TEST.toString(),"token"))
                .formParam(VkFields.VERSION.toString(),PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"version"))
                .formParam(VkFields.TYPE.toString(),PropertiesUtility.getStringValue(Resources.CONFIG.toString(), "typePost"))
                .formParam(VkFields.ITEM_ID.toString(),post_id)
                .post()
                .then()
                .statusCode(StatusCodes.GET_OK.toInteger())
                .extract().response();
        return  response.getBody().jsonPath().get("response.liked");
    }

    public static void wallDelete(int post_id){
        RestAssured.baseURI = PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"urlAPI");
        request = RestAssured.given();
        response = request.when()
                .basePath(Methods.WALL_DELETE.toString())
                .formParam(VkFields.OWNER_ID.toString(), PropertiesUtility.getIntValue(Resources.TEST.toString(),"ownerId"))
                .formParam(VkFields.ACCESS_TOKEN.toString(), PropertiesUtility.getStringValue(Resources.TEST.toString(),"token"))
                .formParam(VkFields.VERSION.toString(),PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"version"))
                .formParam(VkFields.POST_ID.toString(),post_id)
                .post()
                .then()
                .statusCode(StatusCodes.GET_OK.toInteger())
                .extract().response();
    }
}
