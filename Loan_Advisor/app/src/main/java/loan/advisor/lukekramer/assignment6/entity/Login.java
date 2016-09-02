package com.example.lukekramer.assignment6.entity;

import java.io.Serializable;

/**
 * Created by lukekramer on 08/05/16.
 */
public class Login implements Serializable {

    private long id;
    private long clientid;
    private String username;
    private String password;

    private Login(){}

    public long getId() {
        return id;
    }

    public long getUserid() {
        return clientid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Login (Builder build)
    {
        this.id = build.id;
        this.clientid = build.clientid;
        this.username = build.username;
        this.password = build.password;

    }

    public static class Builder
    {
        private long id;
        private long clientid;
        private String username;
        private String password;

        public Builder ID(long value)
        {
            this.id = value;
            return this;
        }

        public Builder Userid(long value)
        {
            this.clientid = value;
            return this;
        }

        public Builder Username(String value)
        {
            this.username = value;
            return this;

        }

        public Builder Password(String value)
        {
            this.password = value;
            return this;
        }

        public Builder copy(Login value)
        {
            this.id = value.id;
            this.clientid = value.clientid;
            this.username = value.username;
            this.password = value.password;

            return this;
        }

        public Login build(){return new Login(this);}
    }
}
