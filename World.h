//
// Created by pilex on 17/8/19.
//

#ifndef DIRECT_DAYLIGHT_GAME_WORLD_H
#define DIRECT_DAYLIGHT_GAME_WORLD_H

#include <SFML/Graphics.hpp>

using namespace sf;

class World {
private:
    RenderWindow& window;

    Texture backgroundTex;
    Sprite background;

public:
    World(RenderWindow& window);

    void draw();
};


#endif //DIRECT_DAYLIGHT_GAME_WORLD_H
