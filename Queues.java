import java.util.*;

public class Queues {
    public static class ArrayQueue {
        int arr[], size, rear;

        ArrayQueue(int data) {
            arr = new int[data];
            size = data;
            rear = -1;
        }

        public void add(int data) {
            if (rear == size - 1) {
                System.out.println("Queue full!");
            } else
                arr[++rear] = data;
        }

        public int remove() {
            if (rear == -1 || rear >= size)
                return -1;
            int temp = arr[rear--];
            for (int i = rear; i >= 0; i--) {
                int newTemp = arr[i];
                arr[i] = temp;
                temp = newTemp;
            }
            return temp;
        }

        public int peek() {
            if (rear == -1) {
                System.out.print("Queue Empty! ");
                return -1;
            } else
                return arr[0];
        }

        public void print() {
            for (int i = 0; i <= rear; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static class CircularQueue {
        int arr[], size, front, rear;

        CircularQueue(int data) {
            arr = new int[data];
            size = data;
            front = -1;
            rear = -1;
        }

        public void add(int data) {
            if ((rear + 1) % size == front) {
                System.out.println("Queue full, " + data + " not added!");
                return;
            }
            if (front == -1)
                front++;
            rear = (rear + 1) % size;
            arr[rear] = data;
        }

        public int remove() {
            if (front == -1) {
                System.out.println("Queue Empty, nothing to remove!");
                return -1;
            }
            int temp = arr[front];
            arr[front] = 0;
            if (front == rear)
                rear = front = -1;
            else
                front = (front + 1) % size;

            return temp;
        }

        public int peek() {
            if (front == -1) {
                System.out.println("Queue Empty ");
                return -1;
            }
            return arr[front];
        }

        public void print() {
            if (front == -1) {
                System.out.println("Queue Empty, nothing to Print!");
                return;
            }
            int i = front;
            while (i != rear) {
                System.out.print(arr[i] + " < ");
                i = (i + 1) % size;
            }
            System.out.println(arr[i]);
        }
    }

    public static class LinkedQueue {
        public static class Node {
            int data;
            Node next;

            Node(int data) {
                this.data = data;
                next = null;
            }
        }

        public Node head;
        public Node tail;

        public void add(int data) {
            Node node = new Node(data);
            if (this.head == null)
                this.head = this.tail = node;
            tail.next = node;
            tail = node;
        }

        public int remove() {
            if (head == null) {
                System.out.println("Queue Empty!");
                return -1;
            }
            int temp = head.data;
            head = head.next;
            return temp;
        }

        public int peek() {
            if (head == null) {
                System.out.println("Queue Empty!");
                return -1;
            }
            return head.data;
        }

        public void print() {
            for (Node node = head; node != null; node = node.next) {
                System.out.print(node.data + " ");
            }
            System.out.println();
        }
    }

    public static class StackQueue {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> copy = new Stack<>();

        public void add(int data) {
            while (!stack.empty())
                copy.push(stack.pop());
            stack.push(data);
            while (!copy.empty())
                stack.push(copy.pop());
        }

        public int peek() {
            if (stack.empty())
                return -1;
            return stack.peek();
        }

        public int remove() {
            return stack.pop();
        }

        public void print() {
            System.out.println(stack);
        }
    }

    public static class QueueStack {
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> copy = new ArrayDeque<>();

        public void add(int data) {
            while (!queue.isEmpty())
                copy.add(queue.remove());
            queue.add(data);
            while (!copy.isEmpty())
                queue.add(copy.remove());
        }

        public int remove() {
            if (queue.isEmpty())
                return -1;
            return queue.remove();
        }

        public int peek() {
            if (queue.isEmpty())
                return -1;
            return queue.peek();
        }

        public void print() {
            System.out.println(queue);
        }
    }

    public static class DequeStack {
        Deque<Integer> deque = new LinkedList<>();

        public void push(int data) {
            deque.addLast(data);
        }

        public int peek() {
            if (deque.isEmpty())
                return -1;
            return deque.getLast();
        }

        public int pop() {
            if (deque.isEmpty())
                return -1;
            return deque.removeLast();
        }

        public void print() {
            System.out.println(deque);
        }
    }

    public static class DequeQueue {
        Deque<Integer> deque = new LinkedList<>();

        public void add(int data) {
            deque.addLast(data);
        }

        public int peek() {
            if (deque.isEmpty())
                return -1;
            return deque.getFirst();
        }

        public int remove() {
            if (deque.isEmpty())
                return -1;
            return deque.removeFirst();
        }

        public void print() {
            System.out.println(deque);
        }
    }

    public static void main(String[] args) {
        ArrayQueue arr = new ArrayQueue(5);
        arr.add(0);
        arr.add(1);
        arr.add(2);
        while (arr.peek() != -1) {
            System.out.println(arr.remove());
        }

        CircularQueue circularQueue = new CircularQueue(5);
        circularQueue.add(0);
        circularQueue.add(1);
        circularQueue.add(2);
        while (circularQueue.peek() != -1) {
            System.out.println(circularQueue.remove());
        }

        LinkedQueue ll = new LinkedQueue();
        ll.add(0);
        ll.add(1);
        ll.add(2);
        while (ll.peek() != -1) {
            System.out.println(ll.remove());
        }

        Queue<Integer> linkedlist = new LinkedList<>();
        linkedlist.add(0);
        linkedlist.add(1);
        linkedlist.add(2);
        while (linkedlist.peek() != null) {
            System.out.println(linkedlist.remove());
        }

        Queue<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(0);
        arrayDeque.add(1);
        arrayDeque.add(2);
        while (arrayDeque.peek() != null) {
            System.out.println(arrayDeque.remove());
        }

        StackQueue stack = new StackQueue();
        stack.add(0);
        stack.add(1);
        stack.add(2);
        while (stack.peek() != -1) {
            System.out.println(stack.remove());
        }

        QueueStack queue = new QueueStack();
        queue.add(0);
        queue.add(1);
        queue.add(2);
        while (queue.peek() != -1)
            System.out.println(queue.remove());

        Deque<Integer> deque = new LinkedList<>();
        deque.add(1);
        deque.addFirst(0);
        deque.removeLast();
        deque.addLast(2);
        deque.removeFirst();
        System.out.println(deque.getFirst() + " " + deque.getLast());

        DequeStack dequeStack = new DequeStack();
        dequeStack.push(0);
        dequeStack.push(1);
        dequeStack.push(2);
        while (dequeStack.peek() != -1) {
            System.out.println(dequeStack.pop() + " ");
        }

        DequeQueue dequeQueue = new DequeQueue();
        dequeQueue.add(0);
        dequeQueue.add(1);
        dequeQueue.add(2);
        while (dequeQueue.peek() != -1) {
            System.out.println(dequeQueue.remove() + " ");
        }

        System.out.println(firstNonRepeating("abcacdb"));
        Queue<Integer> q = new LinkedList<>() {
            {
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
                add(6);
                add(7);
                add(8);
                add(9);
            }
        };
        System.out.println(zigzagHalfQueue(q));
        System.out.println(reverse(q));
        printBinary(15);
        int connectRope[] = { 5, 2, 1, 4, 3 };
        System.out.println(connectRope(connectRope));
        char jobID[] = { 'a', 'b', 'c', 'd', 'e' };
        int deadline[] = { 2, 1, 2, 1, 3 };
        int profit[] = { 100, 19, 27, 25, 15 };
        System.out.print(jobSequencing(jobID, deadline, profit));
        System.out.println(reverseFirstK(q, 4));
        int maxOfSubArray[] = { 1, 2, 3, 1, 4, 5, 2, 3, 6 };
        maxOfSubArray(maxOfSubArray, 3);
    }

    public static StringBuilder firstNonRepeating(String str) {
        int count[] = new int[26];
        Queue<Character> queue = new LinkedList<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            queue.add(temp);
            count[temp - 'a']++;

            while (!queue.isEmpty() && count[queue.peek() - 'a'] != 1)
                queue.remove();
            if (queue.isEmpty())
                result.append(-1);
            else
                result.append(queue.peek());
        }
        return result;
    }

    public static Queue<Integer> zigzagHalfQueue(Queue<Integer> queue) {
        Queue<Integer> half = new LinkedList<>();
        int i = queue.size() / 2;
        while (i > 0) {
            half.add(queue.remove());
            i--;
        }
        while (!half.isEmpty()) {
            queue.add(half.remove());
            queue.add(queue.remove());
        }
        return queue;
    }

    public static Queue<Integer> reverse(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.remove());
        }
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        return queue;
    }

    public static void printBinary(int data) {
        Queue<Integer> binary = new LinkedList<>();
        binary.add(1);
        for (int i = 0; i < data; i++) {
            int number = binary.remove();
            System.out.print(number + " ");
            binary.add(number * 10);
            binary.add(number * 10 + 1);
        }
        System.out.println();
    }

    public static int connectRope(int arr[]) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        int answer = 0;
        while (queue.size() != 1) {
            int temp = queue.remove() + queue.remove();
            queue.add(temp);
            answer += temp;
        }
        return answer;
    }

    public static Stack<Character> jobSequencing(char jobID[], int deadline[], int profit[]) {
        int sort[][] = new int[deadline.length][3];
        for (int i = 0; i < deadline.length; i++) {
            sort[i][0] = jobID[i];
            sort[i][1] = deadline[i];
            sort[i][2] = profit[i];
        }
        Arrays.sort(sort, Comparator.comparingDouble(o -> o[1]));
        Stack<Character> task = new Stack<>();
        task.push((char) sort[0][0]);
        int currDeadline = sort[0][1];
        int currProfit = sort[0][2];
        for (int i = 1; i < deadline.length; i++) {
            if (currDeadline == sort[i][1]) {
                if (currProfit < sort[i][2]) {
                    task.pop();
                    task.push((char) sort[i][0]);
                }
            } else {
                currDeadline = sort[i][1];
                currProfit = sort[i][2];
                task.push((char) sort[i][0]);
            }
        }
        return task;
    }

    public static Queue<Integer> reverseFirstK(Queue<Integer> queue, int k) {
        if (k < 0 || k > queue.size()) {
            System.out.println("Error 404!");
            return queue;
        }
        Stack<Integer> stack = new Stack<>();
        int size = queue.size();
        for (int i = 1; i < size + 1; i++) {
            if (i < k)
                stack.push(queue.remove());
            else if (i == k) {
                queue.add(queue.remove());
                while (!stack.isEmpty())
                    queue.add(stack.pop());
            } else
                queue.add(queue.remove());
        }
        return queue;
    }

    public static void maxOfSubArray(int array[], int k) {
        Deque<Integer> max = new LinkedList<>();
        int result[] = new int[array.length - k];
        max.add(0);
        for (int i = 1; i < k; i++) {
            if (array[i] > array[max.getFirst()]) {
                max.removeFirst();
                max.add(i);
            }
        }
        result[0] = array[max.getFirst()];
        for (int i = k; i < array.length; i++) {
            if (i - k > max.getLast())
                max.removeLast();
            if (array[max.getFirst()] < array[i]) {
                max.clear();
                max.push(i);
                result[i - k] = array[max.getFirst()];
            } else {
                max.addLast(i);
                result[i - k] = array[max.getFirst()];
            }
        }
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }
}
