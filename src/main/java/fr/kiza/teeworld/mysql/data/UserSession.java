package fr.kiza.teeworld.mysql.data;

public class UserSession {

    private static UserSession instance;

    private String username;

    public static UserSession getInstance() {
        if(instance == null){
            instance = new UserSession();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}