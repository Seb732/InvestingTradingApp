package model;

public class User {
    long id;
    String first_name;
    String last_name;
    String teleNumb;
    String email;

    public User() {
    }

    public User(long id, String first_name, String last_name, String teleNumb, String email) {
        this();
        setId(id);
        setFirst_name(first_name);
        setLast_name(last_name);
        setTeleNumb(teleNumb);
        setEmail(email);

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTeleNumb() {
        return teleNumb;
    }

    public void setTeleNumb(String teleNumb) {
        this.teleNumb = teleNumb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
