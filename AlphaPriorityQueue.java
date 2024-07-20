import java.util.*;

public class AlphaPriorityQueue {
    public static void main(String[] args) {

        PriorityQueue<Integer> score = new PriorityQueue<>(Comparator.reverseOrder());
        score.add(54);
        score.add(78);
        score.add(99);
        while (!score.isEmpty())
            System.out.print(score.remove() + " ");
        System.out.println();

        PriorityQueue<Student> students = new PriorityQueue<>();
        students.add(new Student(3, "a"));
        students.add(new Student(1, "b"));
        students.add(new Student(2, "c"));
        for (Student student = students.peek(); !students.isEmpty(); students.remove(), student = students.peek())
            System.out.print(student.name + "->" + student.rank + "  ");
        System.out.println();

        PriorityQueue<Coordinate> coordinate = new PriorityQueue<>();
        coordinate.add(new Coordinate('0', 3, 3));
        coordinate.add(new Coordinate('1', 5, -1));
        coordinate.add(new Coordinate('2', -2, 4));
        for (int k = 0; k < 2; k++)
            System.out.print("C" + coordinate.remove().name + " ");
        System.out.println();

        PriorityQueue<Integer> minCost = new PriorityQueue<>();
        int ropes[] = { 2, 3, 3, 4, 6 }, cost = 0;
        for (int i : ropes)
            minCost.add(i);
        while (minCost.size() > 1) {
            int temp = minCost.remove() + minCost.remove();
            cost += temp;
            minCost.add(temp);
        }
        System.out.println("cost to cut n ropes is -> " + cost);

        PriorityQueue<Soldier> soldiers = new PriorityQueue<>();
        int army[][] = { { 1, 0, 0, 0 }, { 1, 1, 1, 1 }, { 1, 0, 0, 0 }, { 1, 0, 0, 0 } };
        for (int i = 0; i < army.length; i++) {
            int soldier = 0;
            for (int j : army[i])
                soldier += j;
            soldiers.add(new Soldier(soldier, i));
        }
        for (int i = 0; i < 2; i++)
            System.out.print("R" + soldiers.remove().index + " ");
        System.out.println();

        PriorityQueue<MaxOfSubArrayOfSizeK> max = new PriorityQueue<>();
        int max_array[] = { 1, 3, -1, -3, 5, 3, 6, 7 }, max_k = 3;
        for (int i = 0; i < max_k; i++)
            max.add(new MaxOfSubArrayOfSizeK(max_array[i], i));
        System.out.print("Max Of Sub Array Of Size K => " + max.peek().data + " ");
        for (int start = 0, end = max_k; end < max_array.length; start++, end++) {
            max.add(new MaxOfSubArrayOfSizeK(max_array[end], end));
            while (max.peek().index <= start)
                max.remove();
            System.out.print(max.peek().data + " ");
        }
        System.out.println();

        PriorityQueue<Integer> kthMax = new PriorityQueue<>();
        int kthMax_array[] = { 10, 20, 11, 70, 50, 40, 100, 5 }, kthMax_k = 3;
        for (int i = 0; i < kthMax_k; i++)
            kthMax.add(kthMax_array[i]);
        System.out.print("kth max of array => " + kthMax.peek() + " ");
        for (int i = kthMax_k; i < kthMax_array.length; i++) {
            if (kthMax.peek() < kthMax_array[i]) {
                kthMax.remove();
                kthMax.add(kthMax_array[i]);
            }
            System.out.print(kthMax.peek() + " ");
        }
        System.out.println();

        int n = 6, k = 2, filled[] = { 2, 6 };
        int maxDistance = filled[0] - 1;
        for (int i = 1; i < k; i++)
            if ((filled[i] - filled[i - 1]) / 2 > maxDistance)
                maxDistance = (filled[i] - filled[i - 1]) / 2;
        maxDistance = n - filled[k - 1] > maxDistance ? n - filled[k - 1] : maxDistance;
        System.out.println("Min time to fill " + (n - k) + " slots is " + maxDistance);

        // int paths[][] = { { 31, 100, 65, 12, 18 },
        //         { 10, 13, 47, 157, 6 },
        //         { 100, 113, 174, 11, 33 },
        //         { 88, 124, 41, 20, 140 },
        //         { 99, 32, 111, 41, 20 } };

        PriorityQueue<Integer> halveSum = new PriorityQueue<>(Collections.reverseOrder());
        int list[] = { 4, 6, 3, 9, 10, 2 }, sum = 0;
        for (int i : list) {
            halveSum.add(i);
            sum += i;
        }
        int newSum = sum, operation = 0;
        while (sum / 2 < newSum) {
            int temp = halveSum.remove();
            newSum -= Math.ceil(temp / 2.0);
            halveSum.add(temp / 2);
            operation++;
        }
        System.out.println("Min operation required are: " + operation);

        LinkedList ll = new LinkedList();
        LinkedList.Node nodes[] = new LinkedList.Node[3];
        nodes[0] = new LinkedList.Node(1);
        nodes[0].next = new LinkedList.Node(3);
        nodes[0].next.next = new LinkedList.Node(7);
        nodes[1] = new LinkedList.Node(2);
        nodes[1].next = new LinkedList.Node(4);
        nodes[1].next.next = new LinkedList.Node(8);
        nodes[2] = new LinkedList.Node(9);
        nodes[2].next = new LinkedList.Node(10);
        nodes[2].next.next = new LinkedList.Node(11);
        LinkedList.Node root = ll.mergeLL(nodes);
        ll.print(root);
    }

    public static class Student implements Comparable<Student> {
        int rank;
        String name;

        Student(int rank, String name) {
            this.rank = rank;
            this.name = name;
        }

        @Override
        public int compareTo(Student student) {
            return this.rank - student.rank;
        }
    }

    public static class Coordinate implements Comparable<Coordinate> {
        char name;
        int x, y;

        Coordinate(char name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Coordinate temp) {
            return x * x + y * y - temp.x * temp.x - temp.y * temp.y;
        }
    }

    public static class Soldier implements Comparable<Soldier> {
        int soldier;
        int index;

        Soldier(int soilder, int index) {
            this.soldier = soilder;
            this.index = index;
        }

        @Override
        public int compareTo(Soldier temp) {
            if (this.soldier != temp.soldier)
                return this.soldier - temp.soldier;
            else
                return this.index - temp.index;
        }
    }

    public static class MaxOfSubArrayOfSizeK implements Comparable<MaxOfSubArrayOfSizeK> {
        int data;
        int index;

        MaxOfSubArrayOfSizeK(int data, int index) {
            this.data = data;
            this.index = index;
        }

        @Override
        public int compareTo(MaxOfSubArrayOfSizeK max) {
            return max.data - data;
        }
    }

    public static class LinkedList {
        public static class Node implements Comparable<Node> {
            int data;
            Node next;

            Node(int data) {
                this.data = data;
                this.next = null;
            }

            @Override
            public int compareTo(Node node) {
                return data - node.data;
            }
        }

        public void print(Node root) {
            while (root != null) {
                System.out.print(root.data + " ");
                root = root.next;
            }
            System.out.println();
        }

        public Node mergeLL(Node list[]) {
            PriorityQueue<Node> pq = new PriorityQueue<>();
            for (Node node : list)
                while (node != null) {
                    pq.add(node);
                    node = node.next;
                }
            Node root = pq.remove();
            Node temp = root;
            while (!pq.isEmpty()) {
                temp.next = pq.remove();
                temp = temp.next;
            }
            return root;
        }
    }
}
