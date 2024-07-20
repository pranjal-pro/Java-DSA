import java.util.*;

public class RedBlackTrees {
    public static class Tree {
        public static class Node {
            int data, height;
            Node left, right, parent;
            char color;

            Node(int data, Node parent) {
                this.data = data;
                this.height = 1;
                this.left = this.right = null;
                this.parent = parent;
                this.color = 'R';
                checkColor(this);
            }
        }

        static void checkColor(Node root) {
            if (root.parent == null) {
                root.color = 'B';
                return;
            }
            Node sibling = root.parent.parent == null ? null
                    : root.parent.parent.left == root.parent ? root.parent.parent.right : root.parent.parent.left;
            if (root.parent.color == 'B' || sibling == null || sibling.color == 'B')
                return;

            root.parent.color = 'B';
            sibling.color = 'B';
            if (root.parent.parent.color == 'B' && root.parent.parent.parent != null) {
                root.parent.parent.color = 'R';
                if (root.parent.parent.parent.color == 'R')
                    checkColor(root.parent.parent);
            }
        }

        Node add(Node root, int data, Node parent) {
            if (root == null)
                root = new Node(data, parent);
            else if (root.data > data)
                root.left = add(root.left, data, root);
            else
                root.right = add(root.right, data, root);

            // rotation
            int bf = height(root.left) - height(root.right);
            if (bf > 1) {
                bf = height(root.left.left) - height(root.left.right);
                if (bf < 0)
                    root.left = rotateLeft(root.left);
                root = rotateRight(root);
            } else if (bf < -1) {
                bf = height(root.right.left) - height(root.right.right);
                if (bf > 0)
                    root.right = rotateRight(root.right);
                root = rotateLeft(root);
            }

            root.height = Math.max(height(root.left), height(root.right)) + 1;
            return root;
        }

        Node remove(Node root, Node target) {
            if (root == target) {
                if (root.left == null && root.right == null)
                    return null;
                else if (root.left == null || root.right == null)
                    return root.left == null ? root.right : root.left;
                Node next = inorderSuccessor(root.right);
                root.data = next.data;
                root.right = remove(root.right, next);

            } else if (root.data > target.data) {
                root.left = remove(root.left, target);
            } else
                root.right = remove(root.right, target);

            root.height = Math.max(height(root.left), height(root.right)) + 1;
            // rotation
            int bf = height(root.left) - height(root.right);
            if (bf > 1) {
                bf = height(root.left.left) - height(root.left.right);
                if (bf < 0)
                    root.left = rotateLeft(root.left);
                root = rotateRight(root);
            } else if (bf < -1) {
                bf = height(root.right.left) - height(root.right.right);
                if (bf > 0)
                    root.right = rotateRight(root.right);
                root = rotateLeft(root);
            }
            return root;
        }

        Node inorderSuccessor(Node root) {
            return root.left == null ? root : inorderSuccessor(root.left);
        }

        int height(Node root) {
            return root == null ? 0 : root.height;
        }

        Node rotateLeft(Node root) {
            // Node rotate
            Node temp = root.right;
            root.right = temp.left;
            temp.left = root;

            // Parent set
            temp.parent = root.parent;
            if (root.right != null)
                root.right.parent = root;
            root.parent = temp;

            char color = temp.color;
            temp.color = root.color;
            root.color = color;

            // Height adjust
            root.height = Math.max(height(root.left), height(root.right)) + 1;
            temp.height = Math.max(height(temp.left), height(temp.right)) + 1;
            return temp;
        }

        Node rotateRight(Node root) {
            // Node rotate
            Node temp = root.left;
            root.left = temp.right;
            temp.right = root;

            // Parent set
            temp.parent = root.parent;
            if (root.left != null)
                root.left.parent = root;
            root.parent = temp;

            char color = temp.color;
            temp.color = root.color;
            root.color = color;

            // Height adjust
            root.height = Math.max(height(root.left), height(root.right)) + 1;
            temp.height = Math.max(height(temp.left), height(temp.right)) + 1;
            return temp;
        }

        void levelOrder(Node root) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            queue.add(null);
            while (!queue.isEmpty()) {
                Node temp = queue.remove();
                if (temp == null) {
                    if (queue.isEmpty())
                        break;
                    queue.add(null);
                    System.out.print("-> ");
                    continue;
                }
                System.out.print(
                        temp.data + "(" + temp.color + "," + (temp.parent == null ? "null" : temp.parent.data) + ") ");
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int data[] = { 1, 3, 5, 8, 7, 9, 6, 4, 2 };
        Tree tree = new Tree();
        Tree.Node root = null;
        for (int i : data) {
            root = tree.add(root, i, root);
        }
        // root = tree.remove(root, root.left.left);
        tree.levelOrder(root);
    }
}