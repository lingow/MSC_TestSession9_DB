package com.iteso.msc_testsession8;

/**
 * Created by oscarvargas on 01/03/15.
 */
public class User {
    private String name;
    private String password;
    private boolean logged;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
