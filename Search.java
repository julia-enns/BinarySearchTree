import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Search {
    private BinaryTree binTree;
    private String fileName;
    static private File myFile;

    Search(){
        binTree = new BinaryTree();
        fileName = null;
        myFile = null;
    }

    private void inputFile(){
        Scanner scan = new Scanner(System.in);
        System.out.print("> Enter the input file name: ");
        fileName = scan.nextLine();
        myFile = new File(fileName + ".txt");
        while(!myFile.exists()){
            System.out.print("> The file you entered does not exist.\n  Enter a different input file name: ");
            fileName = scan.nextLine();
            myFile = new File(fileName + ".txt");
        }
    }

    private void print(){
        System.out.println("\n> Total number of words in " + fileName + " = " + binTree.getAmountOfWords());
        System.out.println("> Number of unique words in " + fileName + " = " + binTree.getUniqueWords());
        System.out.println("> The word(s) which occur(s) most often and the number of times that it/they occur(s) =" + binTree.highestFreq());
        System.out.println("> The maximum height of the tree = " + binTree.getHeight());
    }

    private void makeChoice(){
        Scanner s = new Scanner(System.in);
        System.out.println("\n>Please select one of the following options:\n  1. Search word\n" +
                "  2. Select BST traversal method\n  3. Quit");
        String value = s.nextLine();
        while(!value.equals("1") && !value.equals("2") && !value.equals("3")){
            System.out.println("Invalid input.\n>Please reselect one of the following options:\n  1. Search word\n" +
                    "  2. Select BST traversal method\n  3. Quit");
            value = s.nextLine();
        }
        int input = Integer.parseInt(value);

        while(input != 3) {

            if(input == 1)
                searchWord();
            if(input == 2)
                searchMethod();

            System.out.println(">Please select one of the following options:\n  1. Search word\n" +
                    "  2. Select BST traversal method\n  3. Quit");
            value = s.nextLine();
            while(!value.equals("1") && !value.equals("2") && !value.equals("3")){
                System.out.println("Invalid input.\n>Please reselect one of the following options:\n  1. Search word\n" +
                        "  2. Select BST traversal method\n  3. Quit");
                value = s.nextLine();
            }
            input = Integer.parseInt(value);
        }
        System.out.println("Exiting program...");
    }

    private void searchWord(){
        Scanner scan = new Scanner(System.in);
        System.out.print(">Enter the word you are looking for in " + fileName + "?");
        String word = scan.nextLine().toLowerCase();
        int num = binTree.search(binTree.getRoot(), word);
        if(num == 0)
            System.out.println(">Word not found!\n");
        else
            System.out.println(">Found! It appears " + num + " times in the input text file\n");

    }

    private void searchMethod(){
        Scanner scan= new Scanner(System.in);
        System.out.print(">Enter the BST traversal method (1 = IN-ORDER, 2 = PRE-ORDER, 3 = POST-ORDER) for " + fileName + "?");
        String value = scan.nextLine();
        while(!value.equals("1") && !value.equals("2") && !value.equals("3")){
            System.out.println("Invalid input.\n>Enter the BST traversal method (1= IN-ORDER, 2 = PRE-ORDER, 3 = POST-ORDER) for " + fileName + "?");
            value = scan.nextLine();
        }
        int input = Integer.parseInt(value);
        if(input == 1) {
            System.out.print(">IN-ORDER output: ");
            binTree.inOrder(binTree.getRoot());
            System.out.println("\n");
        }
        if(input == 2) {
            System.out.print(">PRE-ORDER output: ");
            binTree.preOrder(binTree.getRoot());
            System.out.println("\n");
        }
        if(input == 3) {
            System.out.print(">POST-ORDER output: ");
            binTree.postOrder(binTree.getRoot());
            System.out.println("\n");
        }
    }

    public static void main(String[] args){
        Search tree = new Search();
        tree.inputFile();

        tree.binTree.addFile(myFile);
        tree.binTree.makeTree();

        tree.print();
        tree.makeChoice();
    }
}
