//
// Created by pilex on 17/8/19.
//

#include "Game.h"
#include <iostream>

Game::Game(): world(window) {
    window.create(VideoMode(680, 480), "Direct Daylight");

//   view.reset(FloatRect(0,0,680, 480));
    view.setCenter(0, 0);
    view.setViewport(FloatRect(0, 0, 1, 1));

    window.setView(view);
}

void Game::run() {
    while (window.isOpen()) {
        processEvents();
        update();
        render();
    }
}

void Game::processEvents() {
    Event event;
    while (window.pollEvent(event)) {
        if (event.type == Event::Closed) {
            window.close();
        }
    }

    if (Keyboard::isKeyPressed(Keyboard::Escape)) {
        window.close();
    }

    if (Keyboard::isKeyPressed(Keyboard::A)) {

        view.move(-1, 0);
    }
    if (Keyboard::isKeyPressed(Keyboard::D)) {
        view.move(1, 0);
    }

    std::cout << view.getCenter().x << ", " << view.getCenter().y << std::endl;
}

void Game::update() {

}

void Game::render() {
    window.clear();

    window.setView(view);
    world.draw();

    window.setView(window.getDefaultView());
    // stuff that doesn't change with the view

    window.display();

}