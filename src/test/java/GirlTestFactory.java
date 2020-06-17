import com.epam.gomel.homework.Mood;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Listeners;

public class GirlTestFactory {
    @DataProvider(name = "dataProvider")
    public Object[][] girlTestFactoryData() {
        return new Object[][]{
                {true, false},
                {false, true},
                {false, false}
        };
    }

    @Factory(dataProvider = "dataProvider")
    public Object[] createTest(boolean isPretty, boolean isSlimFriendGotAFewKilos) {
        return new Object[]{new GirlTestForFactory(isPretty, isSlimFriendGotAFewKilos)};
    }
}

