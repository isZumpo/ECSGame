package se.hampuscarlsson.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import se.hampuscarlsson.game.WorldManager;
import se.hampuscarlsson.game.components.DespawnComponent;

public class DespawnSystem extends IteratingSystem {

    private ComponentMapper<DespawnComponent> despawnMapper = ComponentMapper.getFor(DespawnComponent.class);


    public DespawnSystem() {
        super(Family.all(DespawnComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DespawnComponent despawnComponent = despawnMapper.get(entity);
        despawnComponent.timeRemaining -= deltaTime;
        if(despawnComponent.timeRemaining < 0) {
            WorldManager.engine.removeEntity(entity);
        }

    }
}
