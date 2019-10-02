import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class BinaryTree {
    private ArrayList<String> words = new ArrayList<>();
    private Node root;
    private int height;
    private int amountOfWords;
    private int uniqueWords;
    private LinkedList<Node> highestFreq;

    public void addFile(File file){
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext())
                words.add(s.next().toLowerCase());
            s.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public void makeTree(){
        for(String w: words) {
            w = w.replaceAll("[^A-Za-z]", "");
            insert(w);
        }
        height = height(root);
        countWords(root);
        countFreq(root);
    }

    public void insert(String word){
        root = insertRec(root, word);
    }

    public Node insertRec(Node root, String word){
        if(root == null) {
            root = new Node(word);
            root.setCounter(1);
            amountOfWords++;
            return root;
        }

        int set = word.compareTo(root.getWord());

        if(set == 0)
            root.setCounter(root.getCounter() + 1);
        else if(set <= -1)
            root.setLeftNode(insertRec(root.getLeftNode(), word));
        else
            root.setRightNode(insertRec(root.getRightNode(), word));

        return root;
    }

    public void countWords(Node root){
        if(root != null) {
            if (root.getCounter() == 1)
                uniqueWords++;

            countWords(root.getLeftNode());
            countWords(root.getRightNode());
        }
    }

    public void countFreq(Node root){
        if(root != null) {
            if (highestFreq == null) {
                highestFreq = new LinkedList<>();
                highestFreq.add(root);
            } else if (root.getCounter() == highestFreq.get(0).getCounter())
                highestFreq.add(root);
            else if (root.getCounter() > highestFreq.get(0).getCounter()) {
                highestFreq = new LinkedList<>();
                highestFreq.add(root);
            }

            countFreq(root.getLeftNode());
            countFreq(root.getRightNode());
        }
    }


    public int height(Node root){
        if(root == null)
            return 0;
        else{
            int left = height(root.getLeftNode());
            int right = height(root.getRightNode());

            if(left > right)
                return(left+1);
            else return(right+1);
        }
    }

    public String highestFreq(){
        String print = "";
        for(Node n: highestFreq){
            String temp = "\n    " + n.getWord() + " = " + n.getCounter() + " times";
            print = print.concat(temp);
        }
        return print;
    }

    public int search(Node root, String word){
        if(root == null)
            return 0;
        else if(root.getWord().equals(word))
            return root.getCounter();
        else{
            int set = word.compareTo(root.getWord());
            if(set <= -1)
                return search(root.getLeftNode(), word);
            return search(root.getRightNode(), word);
        }
    }

    public void inOrder(Node root){
        if (root != null) {
            inOrder(root.getLeftNode());
            System.out.print(root.getWord() + " ");
            inOrder(root.getRightNode());
        }
    }

    public void preOrder(Node root){
        if (root != null) {
            System.out.print(root.getWord() + " ");
            preOrder(root.getLeftNode());
            preOrder(root.getRightNode());
        }
    }

    public void postOrder(Node root){
        if (root != null) {
            postOrder(root.getLeftNode());
            postOrder(root.getRightNode());
            System.out.print(root.getWord() + " ");
        }
    }

    public int getAmountOfWords() {
        return amountOfWords;
    }

    public int getUniqueWords() {
        return uniqueWords;
    }

    public int getHeight() {
        return height;
    }

    public Node getRoot() {
        return root;
    }
}
