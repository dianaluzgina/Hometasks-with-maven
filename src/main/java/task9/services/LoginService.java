package task9.services;

import task9.bo.User;
import task9.logger.Log;
import task9.screens.MailRuLoginPage;

public class LoginService {
    public static void LoginToMailRu(User user) {
        Log.logInfo("Signing into mailbox with user:" + user.toString());
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
