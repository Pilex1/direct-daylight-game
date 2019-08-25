//
// Created by pilex on 17/8/19.
//

#include "World.h"

World::World(RenderWindow& window) : window(window) {
    view.setCenter(0, 0);
    view.setViewport(FloatRect(0, 0, 1, 1));

    // backgroundTex.loadFromFile("graphics/free.png");
    // background.setTexture(backgroundTex);

    resources.load(TextureId::Background, "free.png");
    resources.load(TextureId::Road, "road.png");

    resources.get(TextureId::Road).setRepeated(true);
}



void World::draw() {
    window.setView(window.getDefaultView());
    window.draw(Sprite(resources.get(TextureId::Background)));

    window.setView(view);
    window.draw(Sprite(resources.get(TextureId::Road), IntRect(0, 320, 800, 64)));
}

void World::moveLeft(float dist) {
    view.move(-100 *dist,0);
}
void World::moveRight(float dist) {
    view.move(100*dist,0);
}

