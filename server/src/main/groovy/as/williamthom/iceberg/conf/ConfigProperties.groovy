package as.williamthom.iceberg.conf

class ConfigProperties {
    List<UrlEntry> urls = []
    List<Group> groups = []

    void saveGroup(Group g) {
        removeGroup(g.name)
        this.groups.add(g)
    }

    void saveUrlEntry(UrlEntry u) {
        removeUrlEntry(u.name)
        this.urls.add(u)
    }

    void updateUrlEntriesOrder(UrlReorderRequest reorderRequest) {
        reorderRequest.orderList.each {UrlOrder orderProp ->
            UrlEntry urlEntry = this.urls.find {it.name == orderProp.name }
            urlEntry.order = orderProp.order as Integer

            saveUrlEntry(urlEntry)
        }

        orderUrlEntries()
    }

    void removeGroup(String name) {
        this.groups.removeIf { it.name == name }
    }

    void removeUrlEntry(String name) {
        this.urls.removeIf {it.name == name }
    }

    void orderUrlEntries() {
        urls.sort { it.order }
    }

}

