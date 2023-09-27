package forkJoin_pool.pattern;

import java.util.Collection;

public interface Node {

    Collection<Node> getChildren();

    int getValue();
}
