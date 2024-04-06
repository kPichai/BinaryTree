import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 * @author: Kieran Pichai
 * @version: 04/04/24
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(4);
        this.root.setLeft(new BSTNode(2));
        this.root.getLeft().setLeft(new BSTNode(1));
        this.root.getLeft().setRight(new BSTNode(3));
        this.root.setRight(new BSTNode((7)));
    }
    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {

        // Calls recursive algorithm below, returns boolean
        return searchRecursively(val, root);
    }

    // This method is a recursive method that searches the BST for a value
    public static boolean searchRecursively(int val, BSTNode root) {

        // Base case that checks if you are at a leaf and the leaf is not the correct value
        if (root.getLeft() == null && root.getRight() == null && root.getVal() != val) {
            // Means there is nowhere else to search so returns false
            return false;
        }

        // Checks if the current root has the value searching for
        else if (root.getVal() == val) {
            return true;
        }

        // Recursive steps if you aren't at a leaf
        if (val > root.getVal() && root.getRight() != null) {
            // Calls algorithm on right side of root if the val is greater
            return searchRecursively(val, root.getRight());
        }

        // Calls algorithm on left side of root if the val is less
        return searchRecursively(val, root.getLeft());

    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder() {

        // Creates empty arraylist to store path, same one that is returned later
        ArrayList<BSTNode> inorder = new ArrayList<BSTNode>();

        inorder = findInorderPath(inorder, root);

        return inorder;
    }

    // This recursive method goes through the tree and adds the inorder path to the array
    public ArrayList<BSTNode> findInorderPath(ArrayList<BSTNode> path, BSTNode cur) {

        // Base case when you reach an empty node below leaf
        if (cur == null) {
            // Returns null to exit and terminate this branch
            return null;
        }

        // Recursive call to left side of tree (because inorder is left, root, right)
        findInorderPath(path, cur.getLeft());

        path.add(cur);

        // Recursive call to right side of tree
        findInorderPath(path, cur.getRight());

        return path;
    }
    /**
     * @return ArrayList of BSTNodes in preorder
     */
    public ArrayList<BSTNode> getPreorder() {

        // Creates empty arraylist to store path that is returned later
        ArrayList<BSTNode> preorder = new ArrayList<BSTNode>();

        preorder = findPreorderPath(preorder, root);

        return preorder;
    }

    // This recursive method goes through the tree and adds the preorder path to the array
    public ArrayList<BSTNode> findPreorderPath(ArrayList<BSTNode> path, BSTNode cur) {

        // Base case when you reach an empty node below leaf
        if (cur == null) {
            // Returns null to exit and terminate this branch
            return null;
        }

        // Add current Node to path (because preorder is root, left, right)
        path.add(cur);

        // Recursive call to left side of tree
        findPreorderPath(path, cur.getLeft());

        // Recursive call to right side of tree
        findPreorderPath(path, cur.getRight());

        return path;
    }

    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder() {

        // Creates empty arraylist to store path to return later
        ArrayList<BSTNode> postorder = new ArrayList<BSTNode>();

        postorder = findPostorderPath(postorder, root);

        return postorder;
    }

    // This recursive method goes through the tree and adds the postorder path to the array
    public ArrayList<BSTNode> findPostorderPath(ArrayList<BSTNode> path, BSTNode cur) {

        // Base case when you reach an empty node below leaf
        if (cur == null) {
            // Returns null to exit and terminate this branch
            return null;
        }

        // Recursive call to left side of tree (because preorder is left, right, root)
        findPostorderPath(path, cur.getLeft());

        // Recursive call to right side of tree
        findPostorderPath(path, cur.getRight());

        path.add(cur);

        return path;
    }

    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val) {

        // Sets the original root the new insert in case it is null (nothing at the root currently)
        root = insertRecursively(val, root);
    }

    // This recursive method  goes down through the binary tree until it finds where to insert a node
    public BSTNode insertRecursively(int value, BSTNode node) {

        // Checks base case where the node you are at is null (below a leaf
        if (node == null) {
            // Sets the node you are at to the desired node with a value
            node = new BSTNode(value);
            // Returns the node, so it can be passed to the parent to set as a child
            return node;
        }

        // Checks if the value is to the left of the current node
        else if (value < node.getVal()) {
            // If so, recursive step that goes down and sets left side of node
            node.setLeft(insertRecursively(value, node.getLeft()));
        }

        // Checks if the value is to the right of the current node
        else if (value > node.getVal()) {
            // If so, recursive step that goes down and sets right side of node
            node.setRight(insertRecursively(value, node.getRight()));
        }

        return node;
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {

        // Returns a boolean that determines if the BST is valid
        return checkBST(getInorder());
    }

    // Recursive method that checks a valid inorder call
    public boolean checkBST(ArrayList<BSTNode> inorder) {

        // Checks if you are near end of inorder ArrayList
        if (inorder.size() <= 1) {
            // No more comparisons to make so returns true
            return true;
        }

        // Makes sure the inorder is actually in order
        if (inorder.get(0).getVal() > inorder.get(1).getVal()) {
            // If it isn't then return false
            return false;
        }

        // Removes first index to shift Arraylist down
        inorder.remove(0);

        // Returns a call on the shifted array
        return checkBST(inorder);
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 14 in the tree");
        System.out.println(tree.search(1));

        System.out.println("\nSearching for 6 in the tree");
        System.out.println(tree.search(4));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);
        tree.insert(5);
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);
        System.out.println(tree.isValidBST());
    }
}