import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Month;

public class GirlTest extends TestAncestor{
    public Boy boy;
    public Girl girl;

    @Test(dependsOnMethods = "isBoyFriendWillBuyNewShoesShouldBeTrue", priority = 2)
    public void isSlimFriendBecameFatShouldBeTrue() {
        girl = new Girl(false, true);
        Assert.assertTrue(girl.isSlimFriendBecameFat());
    }

    @Test(priority = 1)// this test is intentionally failed to be isSlimFriendBecameFatShouldBeTrue test skipped
    public void isBoyFriendWillBuyNewShoesShouldBeTrue() {
        boy = new Boy(Month.JANUARY, 500_000.0);
        girl = new Girl(true, true, boy);
        Assert.assertTrue(girl.isBoyFriendWillBuyNewShoes());
    }

    @Test(priority = 0)
    public void spendBoyFriendMoneyShouldReduceBoyFriendWealth() {
        boy = new Boy(Month.JANUARY, 1500000.0);
        girl = new Girl(true, true, boy);
        girl.spendBoyFriendMoney(500000);
        Assert.assertEquals(girl.getBoyFriend().getWealth(), 1000000.0);
    }

    @Test
    public void girlGetMoodShouldBeExcellent() {
        boy = new Boy(Month.AUGUST, 15000000.0);
        girl = new Girl(true, false, boy);
        Assert.assertEquals(girl.getMood(), Mood.EXCELLENT);
    }

    @Test
    public void girlGetMoodShouldBeGood() {
        boy = new Boy(Month.AUGUST, 15000000.0);
        girl = new Girl(false, true, boy);
        Assert.assertEquals(girl.getMood(), Mood.GOOD);
    }

    @Test
    public void girlGetMoodShouldBeNeutral() {
        girl = new Girl();
        Assert.assertEquals(girl.getMood(), Mood.NEUTRAL);
    }

    @Test
    public void girlGetMoodShouldBeIHateThemAll() {
        girl = new Girl(false);
        Assert.assertEquals(girl.getMood(), Mood.I_HATE_THEM_ALL);
    }


}
