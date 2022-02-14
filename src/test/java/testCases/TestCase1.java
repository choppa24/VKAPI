package testCases;

import enums.Resources;
import enums.ScrollTypes;
import framework.base.BaseTest;
import framework.utils.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MyPage;
import pages.NewsPage;
import pages.SignInPage;

public class TestCase1 extends BaseTest {
    protected final int STR_LEN = 50;
    protected final int TRUE_VALUE = 1;

    @Test
    public void test() {
          LoggerUtility.info("TESTCASE1");
        LoggerUtility.info("Step1 go to site");
        DriverUtility.get((PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"url")));
        SignInPage signInPage = new SignInPage();
        Assert.assertTrue(signInPage.isUniqueElementDisplayed(), "No unique element found for SignInPage.");

        LoggerUtility.info("Step2 authorization");
        signInPage.writeLogin(PropertiesUtility.getStringValue(Resources.TEST.toString(),"login"));
        signInPage.writePassword(PropertiesUtility.getStringValue(Resources.TEST.toString(),"password"));
        signInPage.clickLoginButton();
        NewsPage newsPage = new NewsPage();
        Assert.assertTrue(newsPage.isUniqueElementDisplayed(), "No unique element found for NewsPage.");

        LoggerUtility.info("Step3 go to 'My page'");
        newsPage.clickMyPageButton();
        MyPage myPage = new MyPage();
        Assert.assertTrue(myPage.isUniqueElementDisplayed(), "No unique element found for MyPage.");

        LoggerUtility.info("Step4 creating a record");
        String message = RandomUtility.getRandomString(STR_LEN);
        int post_id = VkApiUtils.wallPost(message);

        LoggerUtility.info("Step5 post check");
        Assert.assertTrue( myPage.isMyLabel(post_id),"the desired post did not appear on the wall");

        LoggerUtility.info("Step6 post edit");
        String newMessage = RandomUtility.getRandomString(STR_LEN);
        int photo_Id = VkApiUtils.savePhotoToWall();
        VkApiUtils.wallEdit(post_id, newMessage, photo_Id);

        LoggerUtility.info("Step7 check for post changes");
        Assert.assertTrue(myPage.isChangeLabel(newMessage),"modified entry not found");

        LoggerUtility.info("Step8 adding a comment");
        String comment = RandomUtility.getRandomString(STR_LEN);
        VkApiUtils.wallCreateComment(post_id, comment);

        LoggerUtility.info("Step9 checking if a comment has been added");
        DriverUtility.scrolling(ScrollTypes.SCROLL_X.toInteger(), ScrollTypes.SCROLL_Y.toInteger());
        Assert.assertTrue(myPage.isMyComment(post_id),"The comment didn't appear on the wall.");

        LoggerUtility.info("Step10 post like");
        myPage.likeWriting(post_id);

        LoggerUtility.info("Step11 like check");
        Assert.assertEquals(VkApiUtils.likesIsLiked(post_id), TRUE_VALUE,
                " the post did not get a like from the correct user");

        LoggerUtility.info("Step12 post delete");
        VkApiUtils.wallDelete(post_id);

        LoggerUtility.info("Step13 post deletion check");
        Assert.assertFalse(myPage.isDeleteMyLabel(post_id),"the post on the wall remains");
    }
}
