package task4;

import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Month;

public class BoyTest {
    private static final Double HUGE_WEALTH = 15000000.0;
    private static final Double AVERAGE_WEALTH = 1500000.0;
    private static final Double AMOUNT_FOR_SPENDING = 2000000.0;
    private static final Integer WEALTH_TO_CHECK_EXCEPTION = -5;

    public Boy boy;
    public Girl girl;

    @Test(groups = "CanExclude")
    public void isSummerMonthShouldBeTrue() {

        boy = new Boy(Month.AUGUST, HUGE_WEALTH, girl);
        girl = new Girl(true, true, boy);
        Assert.assertTrue(boy.isSummerMonth());
    }

    @Test(groups = "CanExclude")
    public void isPrettyGirlfriendShouldBeTrue() {
        boy = new Boy(Month.JANUARY, AVERAGE_WEALTH, girl);
        girl = new Girl(true, true, boy);
        Assert.assertTrue(boy.isPrettyGirlFriend());
    }

    @Test
    @Parameters({"wealth"})
    public void isRichShouldBeTrue( double wealth) {
        boy = new Boy(Month.JANUARY, wealth);
        Assert.assertTrue(boy.isRich());
    }

    @DataProvider(name="dataProvider")
    public Object[][] spendSomeMoneyData(){
        return new Object[][]{
                {1500000,500000,1000000.0},
                {600,200,400.0},
                {0,0,0.0}
        };
      }

    @Test(dataProvider = "dataProvider")
    public void spendSomeMoneyShouldReduceWealth(double wealth, double amountForSpending, double result) {
        boy = new Boy(Month.JANUARY, wealth);
        boy.spendSomeMoney(amountForSpending);
        Assert.assertEquals(boy.getWealth(), result);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void spendSomeMoneyShouldTrowException() {
        boy = new Boy(Month.JANUARY, AVERAGE_WEALTH);
        boy.spendSomeMoney(AMOUNT_FOR_SPENDING);
    }

    @Test
    public void getMoodShouldBeExcellent() {
        girl = new Girl(true, true);
        boy = new Boy(Month.AUGUST, HUGE_WEALTH, girl);
        Assert.assertEquals(boy.getMood(), Mood.EXCELLENT);
    }

    @Test
    public void getMoodShouldBeGood() {
        girl = new Girl(true, true);
        boy = new Boy(Month.JANUARY, HUGE_WEALTH, girl);
        Assert.assertEquals(boy.getMood(), Mood.GOOD);
    }

    @Test
    public void getMoodShouldBeNeutral() {
        boy = new Boy(Month.AUGUST, HUGE_WEALTH);
        Assert.assertEquals(boy.getMood(), Mood.NEUTRAL);
    }

    @Test
    public void getMoodShouldBeBad() {
        boy = new Boy(Month.AUGUST);
        Assert.assertEquals(boy.getMood(), Mood.BAD);
    }

    @Test
    public void getMoodShouldBeHorrible() {
        boy = new Boy(Month.JANUARY);
        Assert.assertEquals(boy.getMood(), Mood.HORRIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void constructorBoyShouldTrowNullPointerException() {
        boy = new Boy(null);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void constructorBoyShouldTrowNumberFormatException() {
        boy = new Boy(Month.JANUARY, WEALTH_TO_CHECK_EXCEPTION);
    }
}
