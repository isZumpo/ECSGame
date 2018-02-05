package se.hampuscarlsson.game.listeners;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import se.hampuscarlsson.game.WorldManager;
import se.hampuscarlsson.game.components.PhysicsComponent;

public class PhysicsListener implements EntityListener {
    @Override
    public void entityAdded(Entity entity) {

    }

    @Override
    public void entityRemoved(Entity entity) {
        PhysicsComponent physicsComponent = entity.getComponent(PhysicsComponent.class);
        if(physicsComponent == null) {
            System.out.println("Expected type PhysicsComponent in PhysicsListener");
        } else {
            WorldManager.world.destroyBody(entity.getComponent(PhysicsComponent.class).body);

        }
    }
}
