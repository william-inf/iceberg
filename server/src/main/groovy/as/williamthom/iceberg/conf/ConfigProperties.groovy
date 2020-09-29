package as.williamthom.iceberg.conf

class ConfigProperties {
    List<UrlEntry> urls = []
    List<Group> groups = []

    void saveGroup(Group g) {
        removeGroup(g)
        this.groups.add(g)
    }

    void saveUrlEntry(UrlEntry u) {
        removeUrlEntry(u)
        this.urls.add(u)
    }

    void removeGroup(Group g) {
        this.groups.removeIf { it.name == g.name }
    }

    void removeUrlEntry(UrlEntry u) {
        this.urls.removeIf {it.name == u.name }
    }


}

