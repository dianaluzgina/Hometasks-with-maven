package task4;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Month;

public class GirlTestForFactory {
    private static final Double HUGE_WEALTH = 15000000.0;
    private boolean isPretty;
    private boolean isSlimFriendGotAFewKilos;

    public Boy boy;
    public Girl girl;

    public GirlTestForFactory(boolean isPretty, boolean isSlimFriendGotAFewKilos) {
        this.isPretty = isPretty;
        this.isSlimFriendGotAFewKilos = isSlimFriendGotAFewKilos;
    }

    @Test
    public void girlGetMoodShouldBeExcellent() {
        boy = new Boy(Month.AUGUST, HUGE_WEALTH);
        girl = new Girl(isPretty, isSlimFriendGotAFewKilos, boy);
        Assert.assertEquals(girl.getMood(), Mood.EXCELLENT);
    }

    @Test
    public void girlGetMoodShouldBeGood() {
        boy = new Boy(Month.AUGUST, HUGE_WEALTH);
        girl = new Girl(isPretty, isSlimFriendGotAFewKilos, boy);
        Assert.assertEquals(girl.getMood(), Mood.GOOD);
    }

    @Test
    public void girlGetMoodShouldBeNeutral() {
        girl = new Girl(isPretty, isSlimFriendGotAFewKilos);
        Assert.assertEquals(girl.getMood(), Mood.NEUTRAL);
    }

    @Test
    public void girlGetMoodShouldBeIHateThemAll() {
        girl = new Girl(isPretty, isSlimFriendGotAFewKilos);
        Assert.assertEquals(girl.getMood(), Mood.I_HATE_THEM_ALL);
    }
}
