package fr.kiza.teeworld.game.object;

import fr.kiza.teeworld.game.client.window.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Handler {

    protected final Game game;

    public final List<GameObject> objects = new ArrayList<>();

    public Handler(final Game game) {
        this.game = game;
    }

    public void render(final Graphics2D graphics){
       this.objects.forEach(gameObject -> gameObject.render(graphics));
    }

    public void update(){
        this.objects.forEach(GameObject::update);
    }

    public void add(final GameObject object){
        this.objects.add(object);
    }

    public void remove(final GameObject object){
        this.objects.remove(object);
    }

    public GameObject getObject(final GameObject object){
        return this.objects.get(this.objects.indexOf(object));
    }
}
