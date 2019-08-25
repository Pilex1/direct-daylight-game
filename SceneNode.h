//
// Created by pilex on 23/8/19.
//

#ifndef DIRECT_DAYLIGHT_GAME_SCENENODE_H
#define DIRECT_DAYLIGHT_GAME_SCENENODE_H

#include <memory>
#include <vector>
#include <SFML/Graphics/Transformable.hpp>
#include <SFML/Graphics/Drawable.hpp>
#include <SFML/System/NonCopyable.hpp>

using namespace std;
class SceneNode: public sf::Transformable, public sf::Drawable, private sf::NonCopyable {
public:
    typedef unique_ptr<SceneNode> NodePtr;

public:
    SceneNode();

    void attachChild(NodePtr child);
    NodePtr detachChild(const SceneNode& node);

private:
    vector<NodePtr> children;
    SceneNode* parent;

private:
//    renders the whole scene node
    virtual void draw(sf::RenderTarget& target, sf::RenderStates states) const;

// renders just this node, not including its children
    virtual void drawCurrent(sf::RenderTarget& target, sf::RenderStates states) const;

    void drawChildren(sf::RenderTarget& target, sf::RenderStates states) const;
};



#endif //DIRECT_DAYLIGHT_GAME_SCENENODE_H
