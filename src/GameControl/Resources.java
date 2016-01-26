package GameControl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omelchuk.Roman on 25.01.2016.
 */
public class Resources {

    private Map<String, Integer> resources = new HashMap<String, Integer>();

    public Map<String, Integer> getResources() {
        return resources;
    }

    public void setResources(Map<String, Integer> resources) {
        this.resources = resources;
    }

    public void addResource(String resource, Integer amount) {
        this.resources.put(resource,amount);
    }
}
