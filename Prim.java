import java.util.Enumeration;
import java.util.LinkedList;

public class Prim{
    public static void main(final String[] args) {
        final Graph G = new Graph();

        G.addVertex(0, 'a');
        G.addVertex(1, 'b');
        G.addVertex(2, 'c');
        G.addVertex(3, 'd');
        G.addVertex(4, 'e');
        G.addVertex(5, 'f');
        G.addVertex(6, 'g');
        G.addVertex(7, 'h');
        G.addVertex(8, 'i');

        G.addEdge('a', 'b', 4);
        G.addEdge('a', 'h', 8);
        G.addEdge('b', 'c', 8);
        G.addEdge('b', 'h', 11);
        G.addEdge('c', 'd', 7);
        G.addEdge('c', 'f', 4);
        G.addEdge('c', 'i', 2);
        G.addEdge('d', 'e', 9);
        G.addEdge('d', 'f', 14);
        G.addEdge('e', 'f', 10);
        G.addEdge('f', 'g', 2);
        G.addEdge('g', 'h', 1);
        G.addEdge('g', 'i', 6);
        G.addEdge('h', 'i', 7);

        prim(G, 'a');
    }

    public static void prim(final Graph G, final char ch) {
        for (final Node u : G.nodes()) {
            u.key = 1000000000;
            u.p = null;
        }

        G.getNode(ch).key = 0;
        while (G.V.min != null) {
            final Node u = G.V.extractMin();
            G.nodeLookUp.remove(u.ch);
            System.out.println(u);
            for (final Enumeration<Node> keys = G.adj.get(u).keys(); keys.hasMoreElements();) {
                final Node v = keys.nextElement();
                if(G.nodes().contains(v) && G.adj.get(u).get(v) < v.key){
                    //v.p = u;
                    G.V.decreaseKey(v, G.adj.get(u).get(v)); 
                }
            }
        }
    }
}