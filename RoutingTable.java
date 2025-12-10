package proxy;



import java.util.concurrent.ConcurrentHashMap;

public class RoutingTable {

    private final ConcurrentHashMap<String, NodeLink> map = new ConcurrentHashMap<>();

    public void registerKey(String k, NodeLink link) {
        map.putIfAbsent(k, link);
    }

    public NodeLink find(String key) {
        return map.get(key);
    }

    public String[] allKeys() {
        return map.keySet().toArray(new String[0]);
    }
}

