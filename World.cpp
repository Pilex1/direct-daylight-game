//
// Created by pilex on 17/8/19.
//

#include "World.h"

World::World(RenderWindow& window) : window(window) {
    backgroundTex.loadFromFile("graphics/free.png");
    background.setTexture(backgroundTex);
}

void World::draw() {
    window.draw(background);
}

