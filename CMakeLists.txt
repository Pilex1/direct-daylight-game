cmake_minimum_required(VERSION 3.14)
project(direct_daylight_game)

set(CMAKE_CXX_STANDARD 14)

add_executable(direct_daylight_game main.cpp Game.cpp Game.h World.cpp World.h ResourceHolder.h)
target_link_libraries (direct_daylight_game sfml-graphics sfml-window sfml-system)

file(COPY graphics DESTINATION ${CMAKE_BINARY_DIR})
