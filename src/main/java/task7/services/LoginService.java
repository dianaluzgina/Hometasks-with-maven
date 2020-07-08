package task7.services;

import task7.bo.User;
import task7.screens.MailRuCloudLoginPage;
import task7.screens.MailRuCloudMainPage;
import task7.screens.MailRuLoginPage;

public class LoginService {
    public static void LoginToMailRu(User user) {
        MailRuLoginPage page = new MailRuLoginPage();
        page.load()
                .typeUserName(user.getName())
                .selectDomain(user.getDomain())
                .clickPasswordButton()
                .typePassword(user.getPassword())
                .clickSubmitButton();
    }

    public static void LoginToMailRuCloud(User user) {
        MailRuLoginPage page = new MailRuLoginPage();
        page.load()
                .typeUserName(user.getName())
                .selectDomain(user.getDomain())
                .clickPasswordButton()
                .typePassword(user.getPassword())
                .clickSubmitButton()
                .clickCloudButton();
    }
}
