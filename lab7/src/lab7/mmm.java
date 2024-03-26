package lab7;
import java.util.*;

class Problem {
    String initial;
    String goal;

    public Problem(String initial, String goal) {
        this.initial = initial;
        this.goal = goal;
    }

    List<String> actions(String state) {
        throw new UnsupportedOperationException("Subclasses must implement actions method");
    }

    String result(String state, String action) {
        throw new UnsupportedOperationException("Subclasses must implement result method");
    }

    boolean goalTest(String state) {
        return state.equals(goal);
    }

    int pathCost(int costSoFar, String state1, String action, String state2) {
        return costSoFar + 1;  // Default cost is 1 for each step
    }
}

class Node {
    String state;
    Node parent;
    String action;
    int pathCost;

    Node(String state, Node parent, String action, int pathCost) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.pathCost = pathCost;
    }

    List<Node> expand(Problem problem) {
        List<Node> nodes = new ArrayList<>();
        for (String action : problem.actions(state)) {
            nodes.add(childNode(problem, action));
        }
        return nodes;
    }

    Node childNode(Problem problem, String action) {
        String nextState = problem.result(state, action);
        int nextPathCost = problem.pathCost(pathCost, state, action, nextState);
        return new Node(nextState, this, action, nextPathCost);
    }

    List<String> solution() {
        List<String> actions = new ArrayList<>();
        for (Node node : path()) {
            if (node.action != null) {
                actions.add(node.action);
            }
        }
        return actions;
    }

    List<Node> path() {
        List<Node> pathBack = new ArrayList<>();
        Node node = this;
        while (node != null) {
            pathBack.add(node);
            node = node.parent;
        }
        Collections.reverse(pathBack);
        return pathBack;
    }
}

class Graph {
    Map<String, Map<String, Integer>> graphDict;
    boolean directed;

    Graph(Map<String, Map<String, Integer>> graphDict, boolean directed) {
        this.graphDict = graphDict != null ? graphDict : new HashMap<>();
        this.directed = directed;
        if (!directed) {
            makeUndirected();
        }
    }

    void makeUndirected() {
        for (String a : new ArrayList<>(graphDict.keySet())) {
            for (Map.Entry<String, Integer> entry : graphDict.get(a).entrySet()) {
                connect1(entry.getKey(), a, entry.getValue());
            }
        }
    }

    void connect(String A, String B, int distance) {
        connect1(A, B, distance);
        if (!directed) {
            connect1(B, A, distance);
        }
    }

    void connect1(String A, String B, int distance) {
        graphDict.computeIfAbsent(A, k -> new HashMap<>()).put(B, distance);
    }

    Map<String, Integer> get(String a) {
        return graphDict.getOrDefault(a, new HashMap<>());
    }

    List<String> nodes() {
        Set<String> nodes = new HashSet<>(graphDict.keySet());
        for (Map<String, Integer> map : graphDict.values()) {
            nodes.addAll(map.keySet());
        }
        return new ArrayList<>(nodes);
    }
}

class GraphProblem extends Problem {
    Graph graph;

    GraphProblem(String initial, String goal, Graph graph) {
        super(initial, goal);
        this.graph = graph;
    }

    List<String> actions(String A) {
        return new ArrayList<>(graph.get(A).keySet());
    }

    String result(String state, String action) {
        return action;
    }

    int pathCost(int costSoFar, String A, String action, String B) {
        return costSoFar + (graph.get(A).getOrDefault(B, Integer.MAX_VALUE));
    }

    int h(Node node) {
        // Placeholder for distance calculation
        return 0;
    }
}	
class GraphProblemStochastic extends GraphProblem {
    GraphProblemStochastic(String initial, String goal, Graph graph) {
        super(initial, goal, graph);
    }

    List<String> actions(String A) {
        return new ArrayList<>(graph.get(A).keySet());
    }

    String result(String state, String action) {
        return action;  // Assuming action is directly the resulting state
    }

    int pathCost(int costSoFar, String A, String action, String B) {
        throw new UnsupportedOperationException("pathCost method must be implemented in subclasses");
    }
}

class RomaniaMap {
    static Graph UndirectedGraph() {
        Map<String, Map<String, Integer>> graphDict = new HashMap<>();
        graphDict.put("Arad", Map.of("Zerind", 75, "Sibiu", 140, "Timisoara", 118));
        graphDict.put("Bucharest", Map.of("Urziceni", 85, "Pitesti", 101, "Giurgiu", 90, "Fagaras", 211));
        graphDict.put("Craiova", Map.of("Drobeta", 120, "Rimnicu", 146, "Pitesti", 138));
        graphDict.put("Drobeta", Map.of("Mehadia", 75));
        graphDict.put("Eforie", Map.of("Hirsova", 86));
        graphDict.put("Fagaras", Map.of("Sibiu", 99));
        graphDict.put("Hirsova", Map.of("Urziceni", 98));
        graphDict.put("Iasi", Map.of("Vaslui", 92, "Neamt", 87));
        graphDict.put("Lugoj", Map.of("Timisoara", 111, "Mehadia", 70));
        graphDict.put("Oradea", Map.of("Zerind", 71, "Sibiu", 151));
        graphDict.put("Pitesti", Map.of("Rimnicu", 97));
        graphDict.put("Rimnicu", Map.of("Sibiu", 80));
        graphDict.put("Urziceni", Map.of("Vaslui", 142));
        return new Graph(graphDict, false);
    }
}
