package task7.bo;

public class UserFactory {
    public static User getUserWithInvalidCredentials(){
         return new User()
                 .withName("ufrdrsews89776553")
                 .withDomain("@list.ru")
                 .withPassword("tygygftd");
    }

    public static User getUserWithInvalidPassword(){
        return new User()
                .withName("be.always.happy")
                .withDomain("@bk.ru")
                .withPassword("tygygftd");
    }

    public static User getUserWithValidCredentials(){
        return new User()
                .withName("be.always.happy")
                .withDomain("@bk.ru")
                .withPassword("P8IPS3)mptuo");
    }
}
