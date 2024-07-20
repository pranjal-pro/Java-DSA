import java.util.*;

public class Binary_Tree {
    public static class BinaryTree {
        public static class Node {
            int data;
            Node leftNode, rightNode;

            Node(int data) {
                this.data = data;
                this.leftNode = null;
                this.rightNode = null;
            }
        }

        static int index = -1;

        Node builtTree(int array[]) {
            index++;
            if (index >= array.length || array[index] == -1)
                return null;
            Node root = new Node(array[index]);
            root.leftNode = builtTree(array);
            root.rightNode = builtTree(array);
            return root;
        }

        void printPreOrder(Node root) {
            if (root == null)
                return;
            System.out.print(root.data + " ");
            printPreOrder(root.leftNode);
            printPreOrder(root.rightNode);
        }

        void printInOrder(Node root) {
            if (root == null)
                return;
            printInOrder(root.leftNode);
            System.out.print(root.data + " ");
            printInOrder(root.rightNode);
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
                    System.out.print(" -> ");
                    queue.add(null);
                    continue;
                }
                System.out.print(node.data + " ");
                if (node.leftNode != null)
                    queue.add(node.leftNode);
                if (node.rightNode != null)
                    queue.add(node.rightNode);
            }
        }

        int height(Node root) {
            if (root == null)
                return 0;
            return 1 + Math.max(height(root.rightNode), height(root.leftNode));
        }

        int nodeCount(Node root) {
            if (root == null)
                return 0;
            return 1 + nodeCount(root.leftNode) + nodeCount(root.rightNode);
        }

        int nodeSum(Node root) {
            if (root == null)
                return 0;
            return root.data + nodeSum(root.leftNode) + nodeSum(root.rightNode);
        }

        int diameter(Node root) {
            if (root == null)
                return 0;
            int left = diameter(root.leftNode);
            int right = diameter(root.rightNode);
            int height = height(root.leftNode) + height(root.rightNode) + 1;
            return right > left ? right > height ? right : height : left > height ? left : height;
        }

        class DiameterOptimised {
            int height, diameter;

            DiameterOptimised(int height, int diameter) {
                this.height = height;
                this.diameter = diameter;
            }
        }

        DiameterOptimised diameterOptimised(Node root) {
            if (root == null)
                return new DiameterOptimised(0, 0);

            DiameterOptimised tempLeft = diameterOptimised(root.leftNode);
            DiameterOptimised tempRight = diameterOptimised(root.rightNode);

            int height = Math.max(tempLeft.height, tempRight.height) + 1;
            int diameter = Math.max(tempLeft.height + tempRight.height + 1,
                    Math.max(tempLeft.diameter, tempRight.diameter));
            return new DiameterOptimised(height, diameter);
        }

        boolean isSubTree(Node root, Node subRoot) {
            if (root == null)
                return false;
            if (root.data == subRoot.data)
                if (sameSubTree(root, subRoot))
                    return true;
            return isSubTree(root.leftNode, subRoot) || isSubTree(root.rightNode, subRoot);
        }

        boolean sameSubTree(Node root, Node subRoot) {
            if (subRoot == null && root == null)
                return true;
            else if (root == null || subRoot == null)
                return false;
            if (subRoot.data != root.data)
                return false;
            return sameSubTree(root.leftNode, subRoot.leftNode) && sameSubTree(root.rightNode, subRoot.rightNode);
        }

        public class TopView {
            int distance;
            Node node;

            TopView(int distance, Node node) {
                this.distance = distance;
                this.node = node;
            }
        };

        void topView(Node root) {
            HashMap<Integer, Integer> topView = new HashMap<>();
            Queue<TopView> queue = new LinkedList<>();
            int min = 0, max = 0;

            queue.add(new TopView(0, root));
            queue.add(null);
            while (!queue.isEmpty()) {
                TopView top = queue.remove();
                if (top == null) {
                    if (queue.isEmpty())
                        break;
                    queue.add(null);
                    continue;
                }
                if (top.node.leftNode != null)
                    queue.add(new TopView(top.distance - 1, top.node.leftNode));
                if (top.node.rightNode != null)
                    queue.add(new TopView(top.distance + 1, top.node.rightNode));
                if (topView.get(top.distance) == null)
                    topView.put(top.distance, top.node.data);

                min = Math.min(min, top.distance);
                max = Math.max(max, top.distance);
            }
            while (!(min > max)) {
                System.out.print(topView.get(min++) + " ");
            }
        }

        void kthLevelIteration(Node root, int k) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            queue.add(null);
            if (k == 1) {
                System.out.println(root.data);
                return;
            }
            int count = 2;
            while (!queue.isEmpty()) {
                Node node = queue.remove();
                if (node == null) {
                    if (count == k || queue.isEmpty())
                        break;
                    queue.add(null);
                    count++;
                    continue;
                }
                if (node.leftNode != null)
                    queue.add(node.leftNode);
                if (node.rightNode != null)
                    queue.add(node.rightNode);
            }
            while (!queue.isEmpty())
                System.out.print(queue.remove().data + " ");
        }

        void kthLevelRecursion(Node node, int level, int k) {
            if (node == null)
                return;
            if (level == k) {
                System.out.print(node.data + " ");
                return;
            }
            kthLevelRecursion(node.leftNode, level + 1, k);
            kthLevelRecursion(node.rightNode, level + 1, k);
        }

        boolean getPath(Node root, Node node, ArrayList<Node> path) {
            if (root == null)
                return false;

            path.add(root);
            if (root == node)
                return true;
            if (getPath(root.leftNode, node, path))
                return true;
            if (getPath(root.rightNode, node, path))
                return true;
            path.remove(path.size() - 1);
            return false;
        }

        Node lowestCommonAncestor1(Node root, Node node1, Node node2) {
            ArrayList<Node> path1 = new ArrayList<>();
            ArrayList<Node> path2 = new ArrayList<>();
            getPath(root, node1, path1);
            getPath(root, node2, path2);

            int i = 0;
            while (i < path1.size() && i < path2.size()) {
                if (path1.get(i) != path2.get(i))
                    break;
                i++;
            }
            return path1.get(i - 1);
        }

        Node lowestCommonAncestor2(Node root, Node node1, Node node2) {
            if (root == null || root == node1 || root == node2)
                return root;
            Node left = lowestCommonAncestor2(root.leftNode, node1, node2);
            Node right = lowestCommonAncestor2(root.rightNode, node1, node2);

            if (left == null || right == null)
                return left == null ? right : left;
            return root;
        }

        int minDistanceLCA(Node root, Node node) {
            if (root == null)
                return -1;
            if (root == node)
                return 0;
            int left = minDistanceLCA(root.leftNode, node);
            int right = minDistanceLCA(root.rightNode, node);

            if (left == -1 && right == -1)
                return -1;
            else if (left == -1 || right == -1)
                return left == -1 ? right + 1 : left + 1;
            return -1;
        }

        int minDistance(Node root, Node node1, Node node2) {
            Node lca = lowestCommonAncestor2(root, node1, node2);
            return minDistanceLCA(lca, node1) + minDistanceLCA(lca, node2);
        }

        int kthAncestor(Node root, Node node, int k) {
            if (k == 0) {
                System.out.println(node.data);
                return 0;
            }
            if (root == null)
                return -1;
            if (node == root)
                return 0;
            int left = kthAncestor(root.leftNode, node, k);
            int right = kthAncestor(root.rightNode, node, k);
            if (left + 1 == k || right + 1 == k) {
                System.out.println(root.data);
            }
            if (left == -1 && right == -1)
                return -1;
            if (left == -1 || right == -1)
                return left == -1 ? right + 1 : left + 1;
            return -1;
        }

        int toSumTree(Node root) {
            if (root == null)
                return 0;

            int data = root.data;
            root.data = toSumTree(root.leftNode)
                    + toSumTree(root.rightNode)
                    + (root.leftNode == null ? 0 : root.leftNode.data)
                    + (root.rightNode == null ? 0 : root.rightNode.data);
            return data;
        }

        boolean checkUnivalued(Node root, int data) {
            if (root == null)
                return true;
            if (data != root.data)
                return false;
            return checkUnivalued(root.leftNode, data) && checkUnivalued(root.rightNode, data);
        }

        boolean isMirror(Node root1, Node root2) {
            if (root1 == null && root2 == null)
                return true;
            if (root1.data != root2.data)
                return false;
            return isMirror(root1.leftNode, root2.leftNode) && isMirror(root1.rightNode, root2.rightNode);
        }

        void toMirror(Node root) {
            if (root == null)
                return;
            Node temp = root.leftNode;
            root.leftNode = root.rightNode;
            root.rightNode = temp;
        }

        Node deleteLeafNodeX(Node root, int data) {
            if (root == null || (root.data == data && root.leftNode == null && root.rightNode == null))
                return null;
            root.leftNode = deleteLeafNodeX(root.leftNode, data);
            root.rightNode = deleteLeafNodeX(root.rightNode, data);
            return root;
        }

        String duplicate(Node root, HashMap<String, Integer> map) {
            if (root == null)
                return "";
            String data = duplicate(root.leftNode, map) + " "
                    + Integer.toString(root.data) + " "
                    + duplicate(root.rightNode, map);
            if (map.get(data) != null && map.get(data) == 1) {
                System.out.println(data);
            }
            if (map.containsKey(data)) {
                map.put(data, map.get(data) + 1);
            } else
                map.put(data, 1);
            return data;
        }

        public class MaxSum {
            int maxSum;
            int maxBranch;

            MaxSum() {
                this.maxBranch = this.maxSum = 0;
            }
        }

        MaxSum maxSum(Node root) {
            if (root == null)
                return new MaxSum();
            int data = root.data;
            MaxSum left = maxSum(root.leftNode);
            MaxSum right = maxSum(root.rightNode);
            MaxSum result = new MaxSum();
            result.maxSum = Math.max(data + left.maxBranch + right.maxBranch,
                    Math.max(data + right.maxBranch,
                            Math.max(data + left.maxBranch,
                                    Math.max(data,
                                            Math.max(left.maxSum, right.maxSum)))));
            result.maxBranch = Math.max(left.maxBranch, right.maxBranch) + data;
            return result;
        }
    }

    public static void main(String[] args) {
        int array[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
        /*
         * ....1
         * .../.\
         * ..2...3
         * ./.\...\
         * 4...5...6
         */
        BinaryTree tree = new BinaryTree();
        BinaryTree.Node root = tree.builtTree(array);
        // tree.printPreOrder(root);
        // tree.printInOrder(root);
        // tree.printPostOrder(root);
        // tree.printLevelOrder(root);
        // System.out.println(tree.height(root));
        // System.out.println(tree.nodeCount(root));
        // System.out.println(tree.nodeSum(root));

        // System.out.println(tree.diameterOptimised(root).diameter);
        // System.out.println(tree.diameterOptimised(root).height);
        // BinaryTree.Node subRoot = new BinaryTree.Node(2);
        // subRoot.leftNode = new BinaryTree.Node(4);
        // subRoot.rightNode = new BinaryTree.Node(5);
        // System.out.println(tree.isSubTree(root, subRoot));
        // tree.topView(root);

        // tree.kthLevelRecursion(root, 1, 3);
        // System.out.println(tree.lowestCommonAncestor1(root, root.leftNode.leftNode,
        // root.rightNode).data);
        // System.out.println(tree.lowestCommonAncestor2(root, root.leftNode.leftNode,
        // root.leftNode.rightNode).data);
        // System.out.println(tree.minDistance(root, root.leftNode.leftNode, root.righ
        // Node));
        // tree.kthAncestor(root, root.rightNode.rightNode, 2);
        //
        // tree.toSumTree(root);

        // System.out.println(tree.isMirror(root, root));
        // tree.toMirror(root);
        // tree.deleteLeafNodeX(root, 6);
        // HashMap<String, Integer> map = new HashMap<>();
        // tree.duplicate(root, map);
        // System.out.println(tree.maxSum(root).maxSum);
    }
}
