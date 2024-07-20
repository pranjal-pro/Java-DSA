import java.util.*;

public class AVLTrees {
    public static class AVLTree {
        public static class Node {
            int data, height = 0;
            Node left = null, right = null;

            Node(int data) {
                this.data = data;
                height = 1;
            }
        };

        public Node root;

        void levelOrder() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            queue.add(null);
            while (!queue.isEmpty()) {
                Node node = queue.remove();
                if (node == null) {
                    if (queue.isEmpty())
                        break;
                    System.out.print("-> ");
                    queue.add(null);
                    continue;
                }
                System.out.print(node.data + " ");
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            System.out.println();
        }

        Node add(Node root, int data) {
            if (root == null)
                return new Node(data);
            else if (data > root.data)
                root.right = add(root.right, data);
            else
                root.left = add(root.left, data);
            root.height = 1 + Math.max(height(root.left), height(root.right));
            int balanceFactor = height(root.left) - height(root.right);

            if (balanceFactor > 1) {
                if (data > root.left.data)
                    root.left = antiClockwiseRotate(root.left);
                root = clockwiseRotate(root);
            } else if (balanceFactor < -1) {
                if (data < root.right.data)
                    root.right = clockwiseRotate(root.right);
                root = antiClockwiseRotate(root);
            }
            return root;
        }

        Node remove(Node root, Node node) {
            if (root == null)
                return null;
            if (node.data > root.data)
                root.right = remove(root.right, node);
            else if (node.data < root.data)
                root.left = remove(root.left, node);
            else {
                if (root.left == null && root.right == null)
                    return null;
                else if (root.left == null || root.right == null)
                    return root.left == null ? root.right : root.left;
                Node inOrderSucessor = inOrderSuccessor(root.right);
                node.data = inOrderSucessor.data;
                root.right = remove(root.right, inOrderSucessor);
            }

            root.height = 1 + Math.max(height(root.left), height(root.right));
            int bf = height(root.left) - height(root.right);

            if (bf > 1) {
                int leftBf = height(root.left.left) - height(root.left.right);
                if (leftBf < 0)
                    root.left = antiClockwiseRotate(root.left);
                root = clockwiseRotate(root);
            } else if (bf < -1) {
                int rightBf = height(root.right.left) - height(root.right.right);
                if (rightBf > 0)
                    root.right = clockwiseRotate(root.right);
                root = antiClockwiseRotate(root);
            }
            return root;
        }

        Node inOrderSuccessor(Node root) {
            if (root.left == null)
                return root;
            return inOrderSuccessor(root.left);
        }

        Node clockwiseRotate(Node root) {
            Node temp = root.left;
            root.left = temp.right;
            temp.right = root;

            root.height = Math.max(height(root.left), height(root.right)) + 1;
            temp.height = Math.max(height(temp.left), height(temp.right)) + 1;
            return temp;
        }

        Node antiClockwiseRotate(Node root) {
            Node temp = root.right;
            root.right = temp.left;
            temp.left = root;

            root.height = Math.max(height(root.left), height(root.right)) + 1;
            temp.height = Math.max(height(temp.left), height(temp.right)) + 1;
            return temp;
        }

        int height(Node root) {
            return root == null ? 0 : root.height;
        }
    };

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] data = { 1, 2, 3, 4 };
        for (int value : data)
            tree.root = tree.add(tree.root, value);
        tree.root = tree.remove(tree.root, tree.root.left);
        tree.levelOrder();
    }
}