package as.williamthom.iceberg.utils

import groovy.util.logging.Slf4j

@Slf4j
class DynamicFinderMap implements Map {

    private Map _original

    DynamicFinderMap(Map original) {
        this._original = original
    }

    @Override
    int size() {
        return _original.size()
    }

    @Override
    boolean isEmpty() {
        return _original.isEmpty()
    }

    @Override
    boolean containsKey(Object key) {
        return _original.containsKey()
    }

    @Override
    boolean containsValue(Object value) {
        return _original.containsValue()
    }

    @Override
    Object get(Object key) {
        if (_original == null) {
            return null
        }

        if (!(key instanceof String)) {
            return _original.get(key)
        }

        try {
            def tokens = key.tokenize(".")
            def value = _original

            tokens.eachWithIndex { String entry, int i ->
                value = value?.get(entry)
            }

            return value
        } catch (Exception ex) {
            log.error(ex.message, ex)
            return null
        }
    }

    Object get(Object key, Object defaultVal) {
        return get(key) ?: defaultVal
    }

    @Override
    Object put(Object key, Object value) {
        return _original.put(key, value)
    }

    @Override
    Object remove(Object key) {
        return _original.remove(key)
    }

    @Override
    void putAll(Map m) {
        _original.putAll(m)
    }

    @Override
    void clear() {
        _original.clear()
    }

    @Override
    Set keySet() {
        return _original.keySet()
    }

    @Override
    Collection values() {
        return _original.values()
    }

    @Override
    Set<Entry> entrySet() {
        return _original.entrySet()
    }
}
