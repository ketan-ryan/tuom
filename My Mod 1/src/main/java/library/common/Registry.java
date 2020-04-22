package library.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * This class keeps track of all blocks and items in this mod so that we
 * can do things on them later during registration and initialization
 */
public class Registry {

    private static final List<IRegistryObject> registeredObjects = new ArrayList<>();
    private static Map<String, RegistryClientInfo> clientInfoMap = new HashMap<>();

    public static void register(IRegistryObject object) {
        registeredObjects.add(object);
    }

    public static void registerClientInfo(String name, RegistryClientInfo clientInfo) {
        clientInfoMap.put(name, clientInfo);
    }

    public static Stream<IRegistryObject> getRegisteredObjects() {
        return registeredObjects.stream();
    }

    public static RegistryClientInfo getClientInfoByName(String name) {
        return clientInfoMap.get(name);
    }

    public static Stream<String> getObjectsWithClientInfo() {
        return clientInfoMap.keySet().stream();
    }
}
