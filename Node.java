import java.util.LinkedList;

public class Node{
    int key;
    int degree;
    Node p;
    boolean mark;
    LinkedList<Node> children;
    char ch;

    public Node(int k, char c){
        key = k;
        ch = c;
        degree = 0;
        p = null;
        mark = false;
        children = new LinkedList<>();
    }

    public void addChild(Node child){
        child.p = this;
        children.add(child);
    }

    public String toString(){
        return ch + "";
    }
}