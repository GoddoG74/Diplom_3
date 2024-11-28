package pojos;

public class User {
    private String email;
    private String password;
    private String name;
    private String token;

    public User(String email, String password, String name, String token) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
