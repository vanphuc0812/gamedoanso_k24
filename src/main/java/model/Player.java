package model;

public class Player {
    private String username;
    private String password;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        //tien xu li du lieu
        return password;
    }

    public void setPassword(String password) {
        //validate data
        //ma hoa password
        this.password = password;
    }
}
