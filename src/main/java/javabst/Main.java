package javabst;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws Exception {

        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            numbers.add(i * 2 + 5);
        }

        System.out.println("numbers: " + numbers);

        BinarySearchTree myTree = new  BinarySearchTree(numbers);

        System.out.println(myTree.nodes);

        // write some test code here
        System.out.println(myTree.search(7)); // should be true
        System.out.println(myTree.search(11)); // should be true
        System.out.println(myTree.search(34535)); // should be false




        System.out.println(myTree.nodes.size());
        myTree.add(10);
        System.out.println(myTree.nodes.size());

        System.out.println(myTree.nodes);

        System.out.println("---------REMOVING 11 ------------");

        myTree.remove(11);

        System.out.println(myTree.nodes);

        myTree.add(11);

        System.out.println("------------optimal remove ----------------");
        System.out.println(myTree.nodes);
        myTree.optimalRemove(11);
        System.out.println(myTree.nodes);


        System.out.println("done");

    }
}