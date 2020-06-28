package task6.bo;

public class Mail {
    private String recipient;
    private String subject;
    private String bodyLetter;

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getBodyLetter() {
        return bodyLetter;
    }

    public Mail withRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public Mail withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Mail withBodyLetter(String bodyLetter) {
        this.bodyLetter = bodyLetter;
        return this;
    }
}
