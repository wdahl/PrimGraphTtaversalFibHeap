import java.util.Collection;
import java.util.Hashtable;

public class Graph {
    FibHeap V;
    Hashtable<Node, Hashtable<Node, Integer>> adj;
    Hashtable<Character, Node> nodeLookUp;

    public Graph(){
        V = new FibHeap();
        adj = new Hashtable<>();
        nodeLookUp = new Hashtable<>();
    }

    public void addVertex(int v, char c){
        Node v_node = new Node(v, c);
        nodeLookUp.put(c, v_node);
        V.insert(v_node);
        adj.put(v_node, new Hashtable<>());
    }

    public void addEdge(char u, char v, int w){
        Node u_node = nodeLookUp.get(u);
        Node v_node = nodeLookUp.get(v);
        adj.get(u_node).put(v_node, w);
        adj.get(v_node).put(u_node, w);
    }

    public Collection<Node> nodes(){
        return nodeLookUp.values();
    }

    public Node getNode(char n){
        return nodeLookUp.get(n);
    }
}