import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAncestor {
   public Girl girl;

    @Test
    public void setterIsPretty() {
        girl = new Girl();
        girl.setPretty(true);
        Assert.assertTrue(girl.isPretty());
    }
}
