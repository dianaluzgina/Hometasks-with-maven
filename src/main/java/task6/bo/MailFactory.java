package task6.bo;

import task6.Utilities;

public class MailFactory {
    public static Mail getMailWhereRecipientEqualsSender(){
        return new Mail()
                .withRecipient("be.always.happy@bk.ru")
                .withSubject(Utilities.getUniqueSubject("Hello"))
                .withBodyLetter("Dear Classmate!");
    }
    public static Mail getMailWithInvalidAddress(){
        return new Mail()
                .withRecipient("ufrdrsews89776553")
                .withSubject(Utilities.getUniqueSubject("Hello"))
                .withBodyLetter("Dear friend!");
    }
    public static Mail getMailWithoutSubjectAndBody(){
        return new Mail()
                .withRecipient("be.always.happy@bk.ru");
    }
}
