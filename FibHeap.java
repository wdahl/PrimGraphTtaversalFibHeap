import java.util.LinkedList;

public class FibHeap{
    LinkedList<Node> rootList;
    Node min;
    int n;

    public FibHeap(){
        rootList = new LinkedList<>();
        min = null;
        n = 0;
    }

    public void insert(Node x){
        rootList.add(x);

        if(min == null || x.key < min.key){
            min = x;
        }

        n++;
    }

    public static FibHeap union(FibHeap h1, FibHeap h2){
        FibHeap h = new FibHeap();
        h.min = h1.min;
        for(Node n : h1.rootList){
            h.rootList.add(n);
        }

        for(Node n : h2.rootList){
            h.rootList.add(n);
        }

        if(h1.min == null || (h2.min != null && h2.min.key < h1.min.key)){
            h.min = h2.min;
        }

        h.n = h1.n + h2.n;
        return h;
    }

    public Node extractMin(){
        Node z = min;
        if(z != null){
            for(Node x : z.children){
                rootList.add(x);
                x.p = null;
            }

            delete(z, rootList);
            if(rootList.size() == 0){
                min = null;
            }
            else{
                consolidate();
            }

            n--;
        }
        return z;
    }

    public void consolidate(){
        Node[] a = new Node[100];
        for(int i=0; i<a.length; i++){
            a[i] = null;
        }

        Node[] rootArray = new Node[rootList.size()];
        for(int i=0; i<rootArray.length; i++){
            rootArray[i] = rootList.get(i);
        }

        for(Node w : rootArray){
            Node x = w;
            int d = x.degree;
            while(a[d] != null){
                Node y = a[d];
                if(x.key > y.key){
                    Node temp = x;
                    x = y;
                    y = temp;
                }
                 
                link(y, x);
                a[d] = null;
                d++;
            }
            a[d] = x;
        }

        min = null;
        for(int i=0; i<a.length; i++){
            if(a[i] != null){
                if(min == null){
                    rootList = new LinkedList<>();
                    rootList.add(a[i]);
                    min = a[i];
                }
                else{
                    rootList.add(a[i]);
                    if(a[i].key < min.key){
                        min = a[i];
                    }
                }
            }
        }
    }

    public void link(Node y, Node x){
        rootList.remove(y);
        x.addChild(y);
        x.degree++;
        y.mark = false;
    }

    public void decreaseKey(Node x, int k){
        if(k > x.key){
            System.out.println("Error: New key is greater than current key");
            System.exit(0);
        }

        x.key = k;
        Node y = x.p;
        if(y != null && x.key < y.key){
            cut(x, y);
            cascadingCut(y);
        }

        if(x.key < min.key){
            min = x;
        }
    }

    public void cut(Node x, Node y){
        y.children.remove(x);
        y.degree--;
        rootList.add(x);
        x.p = null;
        x.mark = false;
    }

    public void cascadingCut(Node y){
        Node z = y.p;
        if(z != null){
            if(y.mark = false){
                y.mark = true;
            }
            else{
                cut(y, z);
                cascadingCut(z);
            }
        }
    }

    public static int log2(int x)
    {
        return (int) (Math.log(x) / Math.log(2));
    }

    public boolean delete(Node x, LinkedList<Node> nodes){
        if(nodes.remove(x) == false){
            for(Node v : nodes){
                if(delete(x, v.children)){
                    return true;
                }
            }
        }

        if(x.p != null){
            x.p.degree--;
        }
        return true;

    }

    public String toString(){
        return rootList.toString();
    }
}