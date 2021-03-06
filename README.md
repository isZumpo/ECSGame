# Backstory
A while ago a group of friends and I tried to create a game using java and libgdx. However following the object oriented aproach for structuring our code eventually caught up on us. It ended up with a sort of messy code.
# Solution
It seems like for more modern game some of the object oriented thinking is thrown away and replaced with a entity-component-system(ECS). Instead of creating multiple objects depending on eachother using inheritance a component system is used which allows easy construction and changing of game functionality. A simple container called a entity holds a list of components and then utilizing a sort of database systems can query for entities with certain components and do logical things with them.
* Entity - Container
* Component - Data
* System - Logic

# Libraries
* [Libgdx](https://libgdx.badlogicgames.com/) will be used for everything game related like rendering and helpfull classes like Vectors.
* [Ashley](https://github.com/libgdx/ashley) will be used for the entity-controller-system(ECS) as it has good documentation for libgdx. However after futher research artemis might be a better choice as it way faster.
* [Box2D](http://box2d.org/) will be used for physics.
