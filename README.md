connectivity
============

[Graph connectivity](http://en.wikipedia.org/wiki/Connectivity_(graph_theory) problem solver implementation.

# Usage

Given a generic `org.nnsoft.trudeau.api.UndirectedGraph<V, E>`, users can find a set of solutions by applying the [Minimum spanning tree](http://en.wikipedia.org/wiki/Minimum_spanning_tree) algorithm:

```
import static org.nnsoft.trudeau.connectivity.ConnectivitySolver.findConnectedComponent;

import java.util.Collection;
import java.util.List;
import org.nnsoft.trudeau.api.UndirectedGraph;

...

UndirectedGraph<V, E> graph;

Collection<List<V>> minimumSpanningTrees =
    findConnectedComponent( graph ).includingAllVertices().applyingMinimumSpanningTreeAlgorithm();
```

Sometimes users need to include some vertices in the solutions, so they can include them by the following:

```
V a;
V b;
V c;

Collection<List<V>> minimumSpanningTrees =
    findConnectedComponent( graph ).includingVertices( a, b, c ).applyingMinimumSpanningTreeAlgorithm();
```
