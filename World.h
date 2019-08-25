//
// Created by pilex on 17/8/19.
//

#ifndef DIRECT_DAYLIGHT_GAME_WORLD_H
#define DIRECT_DAYLIGHT_GAME_WORLD_H

#include <SFML/Graphics.hpp>
#include "ResourceHolder.h"

using namespace sf;

class World {
private:
    RenderWindow& window;
    View view;

    // Texture backgroundTex;
    // Sprite background;
    ResourceHolder<TextureId, Texture> resources;

public:
    World(RenderWindow& window);

    void draw();

    void moveLeft(float deltaTime);
    void moveRight(float deltaTime);

};


#endif //DIRECT_DAYLIGHT_GAME_WORLD_H
