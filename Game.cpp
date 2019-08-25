//
// Created by pilex on 17/8/19.
//

#include "Game.h"
#include <iostream>

Game::Game() : world(window) {
    window.create(VideoMode(680, 480), "Direct Daylight");
}

void Game::run() {
    Clock clock;
    while (window.isOpen()) {
        Time deltaTime = clock.restart();
        processEvents();
        update(deltaTime);
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

    movingLeft = false;
    movingRight = false;
    if (Keyboard::isKeyPressed(Keyboard::A)) {
        movingLeft = true;
    }
    if (Keyboard::isKeyPressed(Keyboard::D)) {
        movingRight = true;
    }

    // std::cout << view.getCenter().x << ", " << view.getCenter().y << std::endl;
}

void Game::update(Time deltaTime) {
    // TODO: fixed distance steps

    if (movingLeft) {
        world.moveLeft(deltaTime.asSeconds());
    }
    if (movingRight) {
        world.moveRight(deltaTime.asSeconds());
    }
}

void Game::render() {
    window.clear();

//    window.setView(view);
    world.draw();

//    window.setView(window.getDefaultView());
    // stuff that doesn't change with the view

    window.display();

}