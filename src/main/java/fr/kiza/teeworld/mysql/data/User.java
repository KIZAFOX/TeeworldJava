package fr.kiza.teeworld.mysql.data;

public class User {

    private String name;
    private long timer;

    public User() { }

    public User(String name, long timer) {
        this.name = name;
        this.timer = timer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }
}
