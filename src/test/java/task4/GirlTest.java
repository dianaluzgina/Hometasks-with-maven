package task4;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Month;

public class GirlTest extends TestAncestor{
    private static final Double HUGE_WEALTH = 15000000.0;
    private static final Double AVERAGE_WEALTH = 1500000.0;
    private static final Double POOR_WEALTH = 500000.0;
    private static final Integer AMOUNT_FOR_SPENDING = 500000;
    private static final Double EXPECTED_WEALTH_OF_BOY = 1000000.0;

    public Boy boy;
    public Girl girl;

    @Test(dependsOnMethods = "isBoyFriendWillBuyNewShoesShouldBeTrue", priority = 2)
    public void isSlimFriendBecameFatShouldBeTrue() {
        girl = new Girl(false, true);
        Assert.assertTrue(girl.isSlimFriendBecameFat());
    }

    @Test(priority = 1)// this test is intentionally failed to be isSlimFriendBecameFatShouldBeTrue test skipped
    public void isBoyFriendWillBuyNewShoesShouldBeTrue() {
        boy = new Boy(Month.JANUARY, POOR_WEALTH);
        girl = new Girl(true, true, boy);
        Assert.assertTrue(girl.isBoyFriendWillBuyNewShoes());
    }

    @Test(priority = 0)
    public void spendBoyFriendMoneyShouldReduceBoyFriendWealth() {
        boy = new Boy(Month.JANUARY, AVERAGE_WEALTH);
        girl = new Girl(true, true, boy);
        girl.spendBoyFriendMoney(AMOUNT_FOR_SPENDING);
        Assert.assertEquals(java.util.Optional.of(girl.getBoyFriend().getWealth()), EXPECTED_WEALTH_OF_BOY);
    }

    @Test
    public void girlGetMoodShouldBeExcellent() {
        boy = new Boy(Month.AUGUST, HUGE_WEALTH);
        girl = new Girl(true, false, boy);
        Assert.assertEquals(girl.getMood(), Mood.EXCELLENT);
    }

    @Test
    public void girlGetMoodShouldBeGood() {
        boy = new Boy(Month.AUGUST, HUGE_WEALTH);
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
