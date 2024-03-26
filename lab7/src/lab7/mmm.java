import numpy as np

from turtle import distance  

class Problem:
    def __init__(self, initial, goal):
        self.initial = initial
        self.goal = goal

    def actions(self, state):
        """Return the possible actions from the given state."""
        raise NotImplementedError("Subclasses must implement actions method")

    def result(self, state, action):
        """Return the state that results from taking the given action in the given state."""
        raise NotImplementedError("Subclasses must implement result method")

    def goal_test(self, state):
        """Return True if the state is a goal state."""
        return state == self.goal

    def path_cost(self, cost_so_far, state1, action, state2):
        """Return the cost of the path from the initial state to state2 via action."""
        return cost_so_far + 1  # Default cost is 1 for each step
    

class Node:
    def __init__(self, state, parent=None, action=None, path_cost=0):
        self.state = state  # The state in the state space to which the node corresponds
        self.parent = parent  # The node in the search tree that generated this node
        self.action = action  # The action that was applied to the parent to generate this node
        self.path_cost = path_cost  # The cost of the path from the initial state to this node

    def expand(self, problem):
        """Return a list of nodes reachable from this node."""
        return [self.child_node(problem, action) for action in problem.actions(self.state)]

    def child_node(self, problem, action):
        """Return a child node from this node given an action."""
        next_state = problem.result(self.state, action)
        next_path_cost = problem.path_cost(self.path_cost, self.state, action, next_state)
        return Node(next_state, self, action, next_path_cost)

    def solution(self):
        """Return the sequence of actions to go from the root to this node."""
        return [node.action for node in self.path()[1:]]

    def path(self):
        """Return a list of nodes forming the path from the root to this node."""
        node, path_back = self, []
        while node:
            path_back.append(node)
            node = node.parent
        return list(reversed(path_back))


from collections import deque

class Graph:
    def __init__(self, graph_dict=None, directed=True):
        self.graph_dict = graph_dict or {}
        self.directed = directed
        if not directed:
            self.make_undirected()

    def make_undirected(self):
        for a in list(self.graph_dict.keys()):
            for (b, dist) in self.graph_dict[a].items():
                self.connect1(b, a, dist)

    def connect(self, A, B, distance=1):
        self.connect1(A, B, distance)
        if not self.directed:
            self.connect1(B, A, distance)

    def connect1(self, A, B, distance):
        self.graph_dict.setdefault(A, {})[B] = distance

    def get(self, a, b=None):
        if b is None:
            return self.graph_dict.get(a, {})
        else:
            return self.graph_dict.get(a, {})
            


    def nodes(self):
        s1 = set([k for k in self.graph_dict.keys()])
        s2 = set([k2 for v in self.graph_dict.values() for k2, v2 in v.items()])
        nodes = s1.union(s2)
        return list(nodes)

def breadth_first_graph_search(problem):
    # Breadth-first graph search
    node = Node(problem.initial)
    if problem.goal_test(node.state):
        return node
    frontier = deque([node])
    explored = set()
    while frontier:
        node = frontier.popleft()
        explored.add(node.state)
        for child in node.expand(problem):
            if child.state not in explored and child not in frontier:
                if problem.goal_test(child.state):
                    return child
                frontier.append(child)
    return None

def depth_first_graph_search(problem):
    # Depth-first graph search
    frontier = [(Node(problem.initial))] # Stack
    explored = set()
    while frontier:
        node = frontier.pop()
        if problem.goal_test(node.state):
            return node
        explored.add(node.state)
        frontier.extend(child for child in node.expand(problem) if child.state not in explored and child not in frontier)
    return None

def UndirectedGraph(graph_dict=None):
    return Graph(graph_dict=graph_dict, directed=False)

romania_map = UndirectedGraph({
    'Arad': {'Zerind': 75, 'Sibiu': 140, 'Timisoara': 118},
    'Bucharest': {'Urziceni': 85, 'Pitesti': 101, 'Giurgiu': 90, 'Fagaras': 211},
    'Craiova': {'Drobeta': 120, 'Rimnicu': 146, 'Pitesti': 138},
    'Drobeta': {'Mehadia': 75},
    'Eforie': {'Hirsova': 86},
    'Fagaras': {'Sibiu': 99},
    'Hirsova': {'Urziceni': 98},
    'Iasi': {'Vaslui': 92, 'Neamt': 87},
    'Lugoj': {'Timisoara': 111, 'Mehadia': 70},
    'Oradea': {'Zerind': 71, 'Sibiu': 151},
    'Pitesti': {'Rimnicu': 97},
    'Rimnicu': {'Sibiu': 80},
    'Urziceni': {'Vaslui': 142}
})

class GraphProblem(Problem):
    def __init__(self, initial, goal, graph):
        super().__init__(initial, goal)
        self.graph = graph
    
    def actions(self, A):
        return list(self.graph.get(A).keys())
    
    def result(self, state, action):
        return action
    
    def path_cost(self, cost_so_far, A, action, B):
        return cost_so_far + (self.graph.get(A, {}).get(B) or np.inf)
    
    def find_min_edge(self):
        m = np.inf
        for d in self.graph.graph_dict.values():
            local_min = min(d.values())
            m = min(m, local_min)
        return m
    
    def h(self, node):
        locs = getattr(self.graph, 'locations', None)
        if locs:
            if type(node) is str:
                return int(distance(locs[node], locs[self.goal]))
            return int(distance(locs[node.state], locs[self.goal]))
        else:
            return np.inf
        
class GraphProblemStochastic(GraphProblem):
    def __init__(self, initial, goal, graph):
        super().__init__(initial, goal, graph)  # Passing graph to the superclass constructor
       
    def actions(self, A):
        return list(self.graph.get(A).keys())
    
    def result(self, state, action):
        return action  # Assuming action is directly the resulting state
    
    def path_cost(self, cost_so_far, A, action, B):
        raise NotImplementedError("path_cost method must be implemented in subclasses")


def GraphSearch():
    problem = GraphProblem('Arad', 'Bucharest', romania_map)
    searcher = breadth_first_graph_search

    def do(searcher, problem):
        goal_node = searcher(problem)
        print('Search algorithm:', searcher.__name__)
        print('List of nodes visited:', goal_node.solution())
        print('Path cost:', goal_node.path_cost)  # Access path cost attribute directly


    do(searcher, problem)

GraphSearch()