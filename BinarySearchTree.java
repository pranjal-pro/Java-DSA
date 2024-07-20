import java.util.*;

public class BinarySearchTree {
    public static class BST {
        public static class Node {
            int data;
            Node leftNode, rightNode;

            Node(int data) {
                this.data = data;
                this.leftNode = this.rightNode = null;
            }
        };

        Node insert(Node root, int data) {
            if (root == null)
                return new Node(data);
            if (root.data > data)
                root.leftNode = insert(root.leftNode, data);
            else
                root.rightNode = insert(root.rightNode, data);
            return root;
        }

        void printInOrder(Node root) {
            if (root == null)
                return;
            printInOrder(root.leftNode);
            System.out.print(root.data + " ");
            printInOrder(root.rightNode);
        }

        void printPreOrder(Node root) {
            if (root == null)
                return;
            System.out.print(root.data + " ");
            printPreOrder(root.leftNode);
            printPreOrder(root.rightNode);
        }

        void printPostOrder(Node root) {
            if (root == null)
                return;
            printPostOrder(root.leftNode);
            printPostOrder(root.rightNode);
            System.out.print(root.data + " ");
        }

        void printLevelOrder(Node root) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            queue.add(null);
            while (!queue.isEmpty()) {
                Node node = queue.remove();
                if (node == null) {
                    if (queue.isEmpty())
                        break;
                    queue.add(null);
                    System.out.print("-> ");
                    continue;
                }
                System.out.print(node.data + " ");
                if (node.leftNode != null)
                    queue.add(node.leftNode);
                if (node.rightNode != null)
                    queue.add(node.rightNode);
            }
            System.out.println();
        }

        boolean search(Node root, int key) {
            if (root == null)
                return false;
            if (root.data == key)
                return true;
            return root.data > key ? search(root.leftNode, key) : search(root.rightNode, key);
        }

        Node nextNode(Node root) {
            while (root.leftNode != null)
                root = root.leftNode;
            return root;
        }

        Node delete(Node root, int val) {
            if (root == null)
                return null;
            if (root.data == val) {
                if (root.leftNode == null && root.rightNode == null)
                    return null;
                else if (root.leftNode == null || root.rightNode == null)
                    return root.leftNode == null ? root.rightNode : root.leftNode;
                Node nextNode = nextNode(root.rightNode);
                root.data = nextNode.data;
                root.rightNode = delete(root.rightNode, nextNode.data);
            } else if (root.data > val)
                root.leftNode = delete(root.leftNode, val);
            else
                root.rightNode = delete(root.rightNode, val);
            return root;
        }

        void printRange(Node root, int start, int end) {
            if (root != null)
                if (root.data > start && root.data < end) {
                    printRange(root.leftNode, start, end);
                    System.out.print(root.data + " ");
                    printRange(root.rightNode, start, end);
                } else if (root.data <= start) {
                    if (root.data == start)
                        System.out.print(root.data + " ");
                    printRange(root.rightNode, start, end);
                } else {
                    printRange(root.leftNode, start, end);
                    if (root.data == end)
                        System.out.print(root.data + " ");
                }
        }

        ArrayList<Integer> path(Node source, Node target) {
            ArrayList<Integer> path = new ArrayList<>();
            while (source.data != target.data) {
                path.add(source.data);
                if (source.data > target.data)
                    source = source.leftNode;
                else if (source.data < target.data)
                    source = source.rightNode;
            }
            path.add(source.data);
            return path;
        }

        void path(Node source, ArrayList<Integer> path) {
            if (source == null)
                return;
            path.add(source.data);
            path(source.leftNode, path);
            path(source.rightNode, path);
            if (source.leftNode == null && source.rightNode == null)
                System.out.println(path);
            path.remove(path.size() - 1);
        }

        boolean validBST(Node root, int min, int max) {
            if (root == null)
                return true;
            if (!(root.data > min && root.data < max)) {
                System.out.println(root.data);
                return false;
            }
            return validBST(root.leftNode, min, root.data) && validBST(root.rightNode, root.data, max);
        }

        Node mirror(Node root) {
            if (root == null)
                return null;
            Node temp = root.leftNode;
            root.leftNode = mirror(root.rightNode);
            root.rightNode = mirror(temp);
            return root;
        }

        ArrayList<Integer> toArray(Node root) {
            ArrayList<Integer> array = new ArrayList<>();
            if (root == null)
                return array;
            array.addAll(toArray(root.leftNode));
            array.add(root.data);
            array.addAll(toArray(root.rightNode));
            return array;
        }

        Node toBalanceBST(ArrayList<Integer> array, int start, int end) {
            if (start > end)
                return null;
            int mid = (start + end + 1) / 2;
            Node temp = new Node(array.get(mid));
            temp.rightNode = toBalanceBST(array, mid + 1, end);
            temp.leftNode = toBalanceBST(array, start, mid - 1);
            return temp;
        }

        public class Valid {
            static int maxSize = 0;
            boolean valid;
            int min, max, size;

            Valid(boolean valid, int min, int max, int size) {
                this.valid = valid;
                this.min = min;
                this.max = max;
                this.size = size;
            }
        }

        Valid largestValidBST(Node root) {
            if (root == null)
                return new Valid(true, 1000, -1000, 0);
            Valid left = largestValidBST(root.leftNode);
            Valid right = largestValidBST(root.rightNode);

            int max = Math.max(root.data, Math.max(left.max, right.max));
            int min = Math.min(root.data, Math.min(left.min, right.min));
            int size = left.size + right.size + 1;
            if (root.data <= left.max || root.data >= right.min || !left.valid || !right.valid)
                return new Valid(false, min, max, size);
            Valid.maxSize = Math.max(Valid.maxSize, size);
            return new Valid(true, min, max, size);
        }

        int rangeSum(Node root, int start, int end) {
            if (root == null)
                return 0;
            if (root.data >= start && root.data <= end)
                return root.data + rangeSum(root.leftNode, start, end) + rangeSum(root.rightNode, start, end);
            else if (root.data < start)
                return rangeSum(root.rightNode, start, end);
            else
                return rangeSum(root.leftNode, start, end);
        }

        Node closest(Node root, int target) {
            if (root == null)
                return new Node(Integer.MAX_VALUE);
            Node left = closest(root.leftNode, target);
            Node right = closest(root.rightNode, target);
            return Math.abs(right.data - target) < Math.abs(left.data - target)
                    ? Math.abs(right.data - target) < Math.abs(root.data - target) ? right : root
                    : Math.abs(left.data - target) < Math.abs(root.data - target) ? left : root;
        }

        int count = 0;

        Node kthSmallestNode(Node root, int k) {
            if (root == null)
                return null;
            Node left = kthSmallestNode(root.leftNode, k);
            if (left != null)
                return left;
            count++;
            if (count == k)
                return root;
            return kthSmallestNode(root.rightNode, k);
        }

        int twoSumBST(Node node1, Node node2, int key) {
            ArrayList<Integer> list1 = toArray(node1);
            ArrayList<Integer> list2 = toArray(node2);
            int count = 0;
            int pointer1 = 0, pointer2 = list2.size() - 1;
            while (pointer1 < list1.size() && pointer2 >= 0) {
                if (list1.get(pointer1) + list2.get(pointer2) == key) {
                    System.out.print(list1.get(pointer1) + "," + list2.get(pointer2) + "  ");
                    count++;
                    pointer1++;
                } else if (list1.get(pointer1) + list2.get(pointer2) > key)
                    pointer2--;
                else
                    pointer1++;
            }
            return count;
        }

        public static class MaxSum {
            int maxSum = 0;
            int maxBranch = 0;
            boolean isBST = true;
        };

        MaxSum maxSumBST(Node root) {
            if (root == null)
                return new MaxSum();
            MaxSum left = maxSumBST(root.leftNode);
            MaxSum right = maxSumBST(root.rightNode);
            MaxSum result = new MaxSum();
            result.isBST = left.isBST && right.isBST;
            if (result.isBST && root.leftNode != null)
                result.isBST = root.data > root.leftNode.data;
            if (result.isBST && root.rightNode != null)
                result.isBST = root.data < root.rightNode.data;
            if (result.isBST) {
                result.maxSum = Math.max(Math.max(Math.max(left.maxSum, right.maxSum), root.data), Math.max(
                        Math.max(left.maxSum, right.maxSum) + root.data, left.maxBranch + right.maxBranch + root.data));
                result.maxBranch = Math.max(left.maxBranch, right.maxBranch) + root.data;
            } else {
                result.maxSum = Math.max(left.maxSum, right.maxSum);
                result.isBST = false;
            }
            return result;
        }

    };

    public static void main(String[] args) {
        int arr[] = { 5, 1, 3, 4, 2, 7 };
        BST tree = new BST();
        BST.Node root = new BST.Node(arr[0]);

        // BinarySearchTree
        // . 5
        // ./ \
        // 1 . 7
        // .\
        // . 3
        // ./ \
        // 2 . 4
        for (int i = 1; i < arr.length; i++)
            tree.insert(root, arr[i]);
        // tree.printInOrder(root);
        // tree.printPreOrder(root);
        // tree.printPostOrder(root);
        // tree.printLevelOrder(root);
        // System.out.println(tree.search(root, 6));
        // tree.delete(root, 3);
        // tree.printRange(root, 2, 6);
        // System.out.println(tree.path(root, root.leftNode.rightNode.rightNode));
        // tree.path(root, new ArrayList<Integer>());
        // System.out.println(tree.validBST(root, -100, 100));
        // tree.mirror(root);

        // Balance BinarySearchTree
        // . . .4
        // . ./ . \
        // . 2 . . 7
        // ./ \ . /
        // 1 . 3 5
        ArrayList<Integer> list = tree.toArray(root);
        root = tree.toBalanceBST(list, 0, list.size() - 1);
        // System.out.println(tree.largestValidBST(root).maxSize);
        // BST.Node node = new BST.Node(6);
        // node.leftNode = new BST.Node(-2);
        // node.rightNode = new BST.Node(8);
        // node.leftNode.rightNode = new BST.Node(0);

        // . . .4 . . . . . // . . 6 . . . . . // . . .4
        // . ./ . \ . . . . // . / . \ . . . . // . ./ . \
        // . 2 . . 7 . . . .// -2 . . 8. . . . // . 1 . . 7
        // ./ \ . / . . . . // . \ . . . . . . // ./ \. ./ \
        // 1 . 3 5 . . . . .// . .0. . . . . . // 0 . 3 6 . 8
        // . . . . . . . . . . . . . . . . . . . / \ . \
        // . . . . . . . . . . . . . . . . . . -2 . 2 . 5
        // list.addAll(tree.toArray(node));
        // Collections.sort(list);
        // root = tree.toBalanceBST(list, 0, list.size() - 1);
        // tree.printLevelOrder(root);

        // System.out.println(tree.rangeSum(root, 5, 6));
        // System.out.println(tree.closest(root, -100).data);
        System.out.println(tree.kthSmallestNode(root, 3).data);
        System.out.println("=> " + tree.twoSumBST(root, root, 8));
        // System.out.println(tree.maxSumBST(root).maxSum);
    }
}
