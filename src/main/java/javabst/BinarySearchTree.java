package javabst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

// Skeleton for the Binary search tree. Feel free to modify this class.
public class BinarySearchTree {
    Node root;
    List<Node> nodes;
    private boolean isRoot = true;

    public BinarySearchTree(List<Integer> elements) {
        elements.sort(null);
        nodes = new ArrayList<>();
        buildBSTFromList(elements, 0, elements.size() - 1);
    }

    private Node buildBSTFromList(List<Integer> list, int start, int end) {
        if (start > end) return null;

        int middle = (start + end) / 2;
        Node currentRoot = new Node(list.get(middle));
        if (isRoot) {
            root = currentRoot;
            isRoot = false;
        }

        currentRoot.setLeft(buildBSTFromList(list, start, middle - 1));

        currentRoot.setRight(buildBSTFromList(list, middle + 1, end));

        nodes.add(currentRoot);

        return currentRoot;

    }

    public boolean search(Integer toFind) {
        Queue<Node> Q = new LinkedList<>();
        List<Node> discovered = new LinkedList<>();
        discovered.add(root);
        Q.add(root);
        while (!Q.isEmpty()) {
            Node v = Q.remove();
            if (v.getValue().equals(toFind)) {
                return true;
            }
            for (Node node : v.getChildren()) {
                if (!discovered.contains(node) && node != null) {
                    Q.add(node);
                    discovered.add(node);
                }
            }
        }
        return false;
    }

    public void add(Integer toAdd) throws Exception {
        addRecursive(root, toAdd);
    }

    private Node addRecursive(Node current, int toAdd) throws Exception {
        if (toAdd < current.getValue()) {
            if (current.getLeft() == null) {
                Node node = new Node(toAdd);
                current.setLeft(node);
                nodes.add(node);
                return null;
            }
            current.setLeft(addRecursive(current.getLeft(), toAdd));
        } else if (toAdd > current.getValue()) {
            if (current.getRight() == null) {
                Node node = new Node(toAdd);
                current.setRight(node);
                nodes.add(node);
                return null;
            }
            current.setRight(addRecursive(current.getRight(), toAdd));
        } else {
            throw new Exception();
        }
        return current;
    }

    public void remove(Integer toRemove) {
        List<Integer> elements = nodes.stream()
                .map(node -> node.getValue())
                .filter(nodeValue -> !nodeValue.equals(toRemove))
                .collect(Collectors.toList());
        elements.sort(null);
        nodes = new ArrayList<>();
        buildBSTFromList(elements, 0, elements.size() - 1);
    }

    public void optimalRemove(Integer toRemove) {
        if (root == null)
            throw new IllegalArgumentException();
        else {
            if (root.getValue() == toRemove) {
                Node auxRoot = new Node(0);
                auxRoot.setLeft(root);
                root.remove(toRemove, auxRoot);
                root = auxRoot.getLeft();
            } else {
                root.remove(toRemove, null);
            }
        }
    }
}
