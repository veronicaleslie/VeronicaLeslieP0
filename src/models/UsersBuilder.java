package models;

public class UsersBuilder {
    private Object first_name;
    private Object last_name;
    private String email;
    private String password;

    public UsersBuilder setFirst_name(Object first_name) {
        this.first_name = first_name;
        return this;
    }

    public UsersBuilder setLast_name(Object last_name) {
        this.last_name = last_name;
        return this;
    }

    public UsersBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UsersBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public Users createUsers() {
        return new Users(first_name, last_name, email, password);
    }
}