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
    View view;

    World world;

public:
    Game();

    void run();

private:
    void processEvents();

    void update();

    void render();
};

