//
// Created by pilex on 17/8/19.
//

#ifndef DIRECT_DAYLIGHT_GAME_GAME_H
#define DIRECT_DAYLIGHT_GAME_GAME_H


#endif //DIRECT_DAYLIGHT_GAME_GAME_H

#include <SFML/Graphics.hpp>
#include "World.h"

using namespace sf;

class Game {

private:
    RenderWindow window;

    World world;

    bool movingLeft;
    bool movingRight;

public:
    Game();

    void run();

private:
    void processEvents();

    void update(Time time);

    void render();
};

