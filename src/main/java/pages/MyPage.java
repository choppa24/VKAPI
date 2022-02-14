package pages;

import elements.Button;
import elements.Label;
import enums.Resources;
import framework.base.BaseForm;
import framework.utils.DriverUtility;
import framework.utils.PropertiesUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class MyPage extends BaseForm {
    private final int AMENDMENT = 2;
    private final String ID_POSITION ="ID";
    private final int AMENDMENT_POST = 1;
    private final String myDeletePostXpath = "//div[contains(@id,'post382063484_ID')]";
    private final String myWallPostXpath = "//div[contains(@id,'post382063484_ID')]";
    private final String myWallChangePostXpath = "//div[contains(@id,'382063484')]//div[text()='ID']";
    private final String myXpath = "//div[@id='post382063484_ID']";
    private final String nextCommentXpath = "//span[@class='js-replies_next_label']";
    private final String myCommentXpath = "//div[@class='reply_author']/a[@data-from-id='382063484']";
    private String likeButtonXpath = "//div[contains(@class,'like_wall') and contains(@class,'ID')]//div[@data-reaction-set-id='reactions']";
    private final static By pageLocator = By.id("profile_edit_act");

    public MyPage(){
        super(new Label(pageLocator, "uniqueElement from MyPage"),"MyPage");
    }

    public boolean isMyLabel(int id){
        int position = myWallPostXpath.indexOf(ID_POSITION);
        String newWallPostXpath = myWallPostXpath.substring(0,position) + id+ myWallPostXpath.substring(position+AMENDMENT, myWallPostXpath.length());
        Label myWallPost = new Label(By.xpath(newWallPostXpath), "myWallPost");
        return myWallPost.isDisplayed();
    }

    public boolean isChangeLabel(String message){
        int position = myWallChangePostXpath.indexOf(ID_POSITION);
        String newWallChangePostXpath = myWallChangePostXpath.substring(0,position) + message+ myWallChangePostXpath.substring(position+AMENDMENT, myWallChangePostXpath.length());
        Label myWallChangePost = new Label(By.xpath(newWallChangePostXpath), "myWallChangePost");
        return myWallChangePost.isDisplayed();
    }

    public boolean isDeleteMyLabel(int id){
        Actions actions =new Actions(DriverUtility.getDriver());
        actions.pause(PropertiesUtility.getIntValue(Resources.CONFIG.toString(),"pauseTime"));
        actions.perform();
        int position = myDeletePostXpath.indexOf(ID_POSITION);
        String newDeletePostXpath = myDeletePostXpath.substring(0,position) + id+myDeletePostXpath.substring(position+AMENDMENT, myDeletePostXpath.length());
        Label myWallPost = new Label(By.xpath(newDeletePostXpath), "myWallDeletePost");
        return myWallPost.isDisplayed();
    }

    public boolean isMyComment(int id){
        int position = myXpath.indexOf(ID_POSITION);
        String newMyXpath = myXpath.substring(0,position) + id + myXpath.substring(position+AMENDMENT, myXpath.length());
        Button myNextCommentButton = new Button(By.xpath(newMyXpath + nextCommentXpath),"myNextCommentButton");
        myNextCommentButton.click();
        Label myComment = new Label(By.xpath(newMyXpath + myCommentXpath), "myComment");
        return myComment.isDisplayed();
    }

    public void likeWriting(int id){
        int position = likeButtonXpath.indexOf(ID_POSITION);
        String newLikeButtonXpath = likeButtonXpath.substring(0,position) + id + likeButtonXpath.substring(position + AMENDMENT, likeButtonXpath.length());
        Button likeButton = new Button(By.xpath(newLikeButtonXpath),"likeButton");
        likeButton.click();
    }
}
