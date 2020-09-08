package com.leo.view;

public class Database {
    private String driver;
    private String url;
    private String user;
    private String passwd;

    public Database() {
    }

    public Database(String driver, String url, String user, String passwd) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.passwd = passwd;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "Database{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
