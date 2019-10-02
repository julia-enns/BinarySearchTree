public class Node {
    private String word;
    private int counter;
    private Node leftNode;
    private Node rightNode;

    Node(String w){
        word = w;
        leftNode = rightNode = null;
    }

    public String getWord() {
        return word;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }
}
