import java.util.*;

public class BST {

    static class Node{
        int data;
        Node left, right;

        public Node(int item){    // constructor
            data  = item;
            left= right = null;
        }
    }

    Node root;
    BST(){
        root = null;
    }

    // insert function
    Node insert(Node root, int data){
        if(root == null){
            root = new Node(data);
            return root;
        }
        if(data > root.data){
            root.right = insert(root.right, data);
        }
        else if(data < root.data){
            root.left = insert(root.left, data);
        }
        return root;
    }

    // delete function
    Node delete(Node root, int data){

        if (root == null) return root;

        if(data > root.data){
            root.right = delete(root.right, data);
        }
        else if ( data < root.data){
            root.left = delete(root.left, data);
        }

        // data found
        else {
            if(root.left == null){
                return root.right;
            }
            else if ( root.right == null){
                return root.left;
            }
            else {
                // find successor
                int successor = minValue(root.right);

                // change data
                root.data = successor;

                // delete the successor node
                root.right = delete(root.right, successor);
            }

        }

        return root;
    }


    int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    public static void main(String[] args) {
        BST tree = new BST();

        // Insert elements
        tree.root = tree.insert(tree.root, 50);
        tree.insert(tree.root, 30);
        tree.insert(tree.root, 70);
        tree.insert(tree.root, 20);
        tree.insert(tree.root, 40);
        tree.insert(tree.root, 60);
        tree.insert(tree.root, 80);

        // Inorder before deletion
        System.out.print("Inorder before deletion: ");
        inorder(tree.root);
        System.out.println();

        // Delete node
        tree.root = tree.delete(tree.root, 50);

        // Inorder after deletion
        System.out.print("Inorder after deletion: ");
        inorder(tree.root);
        System.out.println();
    }

    static void inorder( Node root){
        if(root != null){
            inorder(root.left);
            System.out.println(root.data + " ");
            inorder(root.right);
        }
    }


}
