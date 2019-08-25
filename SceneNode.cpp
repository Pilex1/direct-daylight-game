//
// Created by pilex on 23/8/19.
//

#include <algorithm>
#include <cassert>
#include "SceneNode.h"

void SceneNode::attachChild(SceneNode::NodePtr child) {
    child->parent = this;
    children.push_back(std::move(child));
}

SceneNode::NodePtr SceneNode::detachChild(const SceneNode &node) {
    auto found = find_if(children.begin(), children.end(), [&] (NodePtr& p) -> bool { return p.get() == &node; });
    assert (found != children.end());

    NodePtr result = std::move(*found);
    result->parent = nullptr;
    children.erase(found);
    return result;
}

void SceneNode::draw(sf::RenderTarget &target, sf::RenderStates states) const {
    states.transform *= getTransform();

    drawCurrent(target, states);
    drawChildren(target, states);
}

void SceneNode::drawCurrent(sf::RenderTarget &target, sf::RenderStates states) const {
    // TODO

}

void SceneNode::drawChildren(sf::RenderTarget &target, sf::RenderStates states) const {
    for (const NodePtr& child : children) {
        child->draw(target, states);
    }
}

