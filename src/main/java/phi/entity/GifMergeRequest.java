package phi.entity;

import java.util.*;

/**
 * Created by changmin on 2018. 5. 17..
 */
public class GifMergeRequest {
    private List<String> paths;

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    private String delay;
}
