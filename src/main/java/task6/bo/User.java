package task6.bo;

public class User {
    private String name;
    private String domain;
    private String password;


    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public String getPassword() {
        return password;
    }
    public User withName(String name){
        this.name=name;
        return this;
    }

    public User withDomain(String domain){
        this.domain=domain;
        return this;
    }

    public User withPassword(String password){
        this.password=password;
        return this;
    }
}
