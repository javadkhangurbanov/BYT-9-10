package main.java.entities.users;

public abstract class User {
    protected Integer _id;
    protected String email;
    protected String password;
    protected String firstName;
    protected String surname;
    protected String phone;

    protected static int userCount = 0;
    public User() {
        this._id = userCount++;
    }

    public boolean logOut() {
        return false;
    }

    public boolean logIn() {
        return false;
    }

    // Added all setters and getters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
