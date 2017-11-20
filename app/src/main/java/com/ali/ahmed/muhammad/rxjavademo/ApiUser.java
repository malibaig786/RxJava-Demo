package com.ali.ahmed.muhammad.rxjavademo;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class ApiUser {
    public long id;
    public String firstname;
    public String lastname;

    public ApiUser(long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "ApiUser{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
