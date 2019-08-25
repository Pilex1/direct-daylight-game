//
// Created by pilex on 18/8/19.
//

#ifndef DIRECT_DAYLIGHT_GAME_RESOURCEHOLDER_H
#define DIRECT_DAYLIGHT_GAME_RESOURCEHOLDER_H

#include <map>
#include <string>
#include <memory>
#include <stdexcept>
#include <cassert>

using namespace std;

enum class TextureId {
    Background, Road
};

template <typename Identifier, typename Resource>
class ResourceHolder {

private:
    map<Identifier, unique_ptr<Resource>> resourceMap;

public:
    void load(Identifier id, const std::string &filename) {
        const string truepath = "graphics/"+filename;
        unique_ptr<Resource> resource(new Resource());
        if (!resource->loadFromFile(truepath)) {
            throw runtime_error("ResourceHolder::load - Failed to load " + truepath);
        }
        insertResource(id, move(resource));
    }

    Resource &get(Identifier id) {
        auto found = resourceMap.find(id);
        assert(found != resourceMap.end());

        return *found->second;
    }

private:
    void insertResource(Identifier id, std::unique_ptr<Resource> resource) {
        auto inserted = resourceMap.insert(make_pair(id, move(resource)));
        assert(inserted.second);
    }
};

#endif // BOOK_RESOURCEHOLDER_HPP
