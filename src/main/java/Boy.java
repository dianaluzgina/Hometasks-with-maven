import java.time.Month;
import lombok.Getter;
import lombok.Setter;

public class Boy extends Human {

  @Getter
  private Month birthdayMonth;
  @Getter
  @Setter
  private Girl girlFriend;
  @Getter
  private double wealth;

  public Boy(Month birthdayMonth, double wealth, Girl girlFriend) {
//    this.birthdayMonth = birthdayMonth;
//    this.wealth = wealth;
    if (birthdayMonth!=null) {
      this.birthdayMonth = birthdayMonth;
    } else throw new NullPointerException("Please, enter month of birth (not null)");
    if (wealth>=0) {
      this.wealth = wealth;
    } else throw new NumberFormatException("Please, enter wealth >=0");
    this.girlFriend = girlFriend;
//    this.girlFriend.setBoyFriend(this);
    if (girlFriend!= null) {
      this.girlFriend.setBoyFriend(this);
    }
  }

  public Boy(Month birthdayMonth, double wealth) {
    this(birthdayMonth, wealth, null);
  }

  public Boy(Month birthdayMonth) {
    this(birthdayMonth, 0, null);
  }

  @Override
  public Mood getMood() {
    if (isRich() && isPrettyGirlFriend() && isSummerMonth()) {
      return Mood.EXCELLENT;
    } else if (isRich() && isPrettyGirlFriend()) {
      return Mood.GOOD;
    } else if (isRich() && isSummerMonth()) {
      return Mood.NEUTRAL;
    } else if (isRich() || isPrettyGirlFriend() || isSummerMonth()) {
      return Mood.BAD;
    }
    return Mood.HORRIBLE;
  }

  public void spendSomeMoney(double amountForSpending) {
    if (amountForSpending <= getWealth()) {
     // wealth += amountForSpending;
      wealth -= amountForSpending;
    } else {
      throw new RuntimeException(String
          .format("Not enough money! Requested amount is %s$ but you can't spend more then %s$",
              amountForSpending, getWealth()));
    }
  }

  public boolean isSummerMonth() {
    return Month.JUNE.equals(getBirthdayMonth())
        || Month.JULY.equals(getBirthdayMonth())
    //&& Month.AUGUST.equals(getBirthdayMonth());
            || Month.AUGUST.equals(getBirthdayMonth());
  }

  public boolean isRich() {
    return getWealth() >= 1_000_000;
  }

  public boolean isPrettyGirlFriend() {
    return getGirlFriend() != null && getGirlFriend().isPretty();
  }
}
