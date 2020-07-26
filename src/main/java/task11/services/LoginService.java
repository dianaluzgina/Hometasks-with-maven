package task11.services;

import task11.bo.User;
import task11.logger.Log;
import task11.screens.MailRuLoginPage;

public class LoginService {
    public static void loginToMailRu(User user) {
        Log.logInfo("Signing into mailbox with user:" + user.toString());
        MailRuLoginPage page = new MailRuLoginPage();
        page.load()
                .typeUserName(user.getName())
                .selectDomain(user.getDomain())
                .clickPasswordButton()
                .typePassword(user.getPassword())
                .clickSubmitButton();
    }

    public static void loginToMailRuCloud(User user) {
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
