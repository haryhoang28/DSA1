package homework7.training.InPreSuc;

import java.util.LinkedList;
import java.util.Queue;

public class InPreSuc {
    public class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public Node buildTree(String str) {
        if (str.length() == 0 || str.charAt(0) == 'N') {
            return null;
        }
        String ip[] = str.split(" ");
        Node root = new Node(Integer.parseInt(ip[0]));

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (queue.size() > 0 && i < ip.length) {
            Node currNode = queue.peek();
            queue.remove();
            String currVal = ip[i];
            if (!currVal.equals("N")) {
                currNode.left = new Node(Integer.parseInt(currVal));
                queue.add(currNode.left);
            }
            i++;
            if (i >= ip.length) {
                break;
            }
            currVal = ip[i];
            if (!currVal.equals("N")) {
                currNode.right = new Node(Integer.parseInt(currVal));
                queue.add(currNode.right);
            }
            i++;
        }
        return root;
    }

    public static void printInorder(Node root) {
        if (root == null) {
            return;
        }
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    static Node pre, suc;

    public static void findPreSuc(Node root, int key) {
        if (root == null) {
            return;
        }
        if (root.data == key) {
            if (root.left != null) {
                Node temp = root.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                pre = temp;
            }
            if (root.right != null) {
                Node temp = root.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                pre = temp;
            }
        }
        if (root.data > key) {
            suc = root;
            findPreSuc(root.left, key);
        } else {
            pre = root;
            findPreSuc(root.right, key);
        }
    }

    private static void findPre(Node root) {
        if (root == null) {
            return;
        }
        pre = root;
        findPre(root.right);
    }

    private static void findSuc(Node root) {
        if (root == null) {
            return;
        }
        suc = root;
        findSuc(root.left);
    }
}
