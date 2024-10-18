package fr.kiza.teeworld.game.event;

import fr.kiza.teeworld.game.object.GameObject;

public record CollisionEvent(GameObject entity, GameObject block) {

}
