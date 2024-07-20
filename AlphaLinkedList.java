import java.util.LinkedList;

public class AlphaLinkedList {

    public static void JavaCollectionFramework() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addLast(3);
        linkedList.add(1, 2);
        System.out.println(linkedList);
    }

    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class DoublyLL {
        int data;
        DoublyLL next, prev;

        DoublyLL(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public static class Circular {
        int data;
        Circular next;

        Circular(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    public static void main(String[] args) {
        addFirst(3);
        addLast(4);
        addFirst(2);
        addFirst(1);
        print(head);
        // LinkedList-1
        System.out.println("Head= " + head.data + ", Tail= " +
                tail.data);
        print(head);
        add(3, 2);
        add(6, 7);
        print(head);
        System.out.println(size);
        removeFirst();
        removeLast();
        System.out.println(searchIterative(2));
        System.out.println(searchRecursive(head, 0, 2));
        print(head);
        reverse();
        removeFromEnd(4);
        addLast(3);
        addLast(4);
        print(head);
        System.out.println(palindromCheck());
        print(head);

        // // LinkedList-2
        JavaCollectionFramework();
        tail.next = head;
        System.out.println(floydsLoopFindAlgorithm());
        removeLoop();
        head = mergeSort(head);
        toZigZag();
        print(head);

        // Practice Question
        head = null;
        addFirst(1);
        addLast(2);
        addLast(3);
        Node node = new Node(4);
        Node node2 = new Node(5);
        Node node3 = new Node(6);
        node.next = node2;
        node2.next = node3;
        tail.next = node3;
        tail = tail.next;
        addLast(7);
        findIntersection(node, head);
        deleteMN(2, 1);
        swap(0, 1);
        oddEven();
        Node listNode[] = new Node[3];
        listNode[0] = new Node(1);
        listNode[0].next = new Node(2);
        listNode[0].next.next = new Node(5);
        listNode[0].next.next.next = new Node(8);
        listNode[0].next.next.next.next = new Node(10);
        listNode[1] = new Node(3);
        listNode[1].next = new Node(4);
        listNode[1].next.next = new Node(5);
        listNode[1].next.next.next = new Node(6);
        listNode[1].next.next.next.next = new Node(7);
        listNode[2] = new Node(1);
        listNode[2].next = new Node(2);
        listNode[2].next.next = new Node(3);
        listNode[2].next.next.next = new Node(4);
        listNode[2].next.next.next.next = new Node(7);
        print(mergeSortedLL(listNode));

        // Doubly Linked List
        doublyAddFirst(1);
        doublyAddLast(2);
        doublyAddFirst(0);
        doublyAddLast(3);
        doublyRemoveFirst();
        doublyRemoveLast();
        doublyPrint(start);
        doublyReverse();

        // Circular LL
        addStartLoop(1);
        addEndLoop(2);
        addStartLoop(0);
        addEndLoop(3);
        removeEndLoop();
        removeStartLoop();
        circularPrint(open);
    }

    public static int size() {
        Node node = head;
        int i = 0;
        while (node != null) {
            i++;
            node = node.next;
        }
        return i;
    }

    public static void print(Node node) {
        // Node node = head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void addFirst(int data) {
        size++;
        Node node = new Node(data);
        if (head == null) {
            head = tail = node;
            return;
        }
        node.next = head;
        head = node;
    }

    public static void addLast(int data) {
        size++;
        Node node = new Node(data);
        if (head == null) {
            head = tail = node;
            return;
        }
        tail.next = node;
        tail = node;
    }

    public static void add(int data, int index) {
        Node node = new Node(data);
        Node temp = head;
        if (index == 0) {
            addFirst(data);
            return;
        }
        for (int i = 1; i < index; i++) {
            if (temp == null) {
                System.out.println("Index dosen't exist");
                return;
            }
            temp = temp.next;
        }
        size++;
        node.next = temp.next;
        temp.next = node;
    }

    public static void removeFirst() {
        size--;
        Node node = head;
        if (head == null) {
            System.out.println("Empty List");
            return;
        }
        node = node.next;
        head = node;
    }

    public static void removeLast() {
        size--;
        Node node = head;
        if (head == null) {
            System.out.println("Empty List");
            return;
        }
        while (node.next.next != null) {
            node = node.next;
        }
        node.next = null;
        tail = node;
    }

    public static int searchIterative(int key) {
        Node node = head;
        int i = 0;
        while (node != null) {
            if (node.data == key)
                return i;
            node = node.next;
            i++;
        }
        return -1;
    }

    public static int searchRecursive(Node node, int i, int key) {
        if (node.data == key) {
            return i;
        }
        if (node.next == null) {
            return -1;
        }
        return searchRecursive(node.next, i + 1, key);
    }

    public static void reverse() {
        Node current = tail = head, next, previous = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    public static void removeFromEnd(int index) {
        Node node = head;
        if (size < index) {
            System.out.println("Index dosen't exist");
            return;
        }
        if (size == index) {
            head = head.next;
            return;
        }
        for (int i = 1; i < size - index; i++) {
            node = node.next;
        }
        node.next = node.next.next;
    }

    public static boolean palindromCheck() {
        if (head == null) {
            System.out.println("LinkedList dosen't exist");
            return false;
        }
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        tail = slow;
        // slow at first...

        Node next, current = slow, previous = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        // reverse half arr

        Node first = head, second = tail;
        while (first != null && second != null) {
            if (first.data != second.data)
                return false;
            first = first.next;
            second = second.next;
        }
        return true;
    }

    public static boolean floydsLoopFindAlgorithm() {
        // finds loop in linked list
        Node slow = head, fast = head;
        while (fast != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }
        return false;
    }

    public static void removeLoop() {
        if (!floydsLoopFindAlgorithm())
            return;
        Node slow = head, fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = head;
        if (fast != head) {
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
            fast.next = null;
            return;
        }

        if (fast == head) {
            while (fast.next != slow) {
                fast = fast.next;
            }
            fast.next = null;
        }
    }

    public static Node mergeSort(Node node) {
        // base- case
        if (node.next == null) {
            return node;
        }

        // find mid
        Node slow = node, fast = node;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow.next;
        slow.next = null;

        // sort both half
        Node first = mergeSort(node);
        Node second = mergeSort(mid);

        // sort sorted halves
        Node temp = new Node(0);
        Node tempHead = temp;
        while (first != null && second != null) {
            if (first.data > second.data) {
                temp.next = second;
                second = second.next;
                temp = temp.next;
            } else {
                temp.next = first;
                first = first.next;
                temp = temp.next;
            }
            // System.out.println(first.data + " " + second.data);
        }
        if (first != null) {
            temp.next = first;
        }
        if (second != null) {
            temp.next = second;
        }
        return tempHead.next;
    }

    public static void toZigZag() {
        // mid
        Node slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // reverse
        Node current = slow.next, next, previous = null;
        tail = slow.next;
        slow.next = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        // zigzag
        Node first = head, second = previous;
        Node node = new Node(0);
        head = node;
        while (first != null && second != null) {
            node.next = first;
            first = first.next;
            node = node.next;
            node.next = second;
            second = second.next;
            node = node.next;
        }
        if (first != null)
            node.next = first;
        if (second != null)
            node.next = second;
        head = head.next;
    }

    public static void findIntersection(Node node1, Node node2) {
        while (node1 != null) {
            Node temp = node2;
            while (temp != null) {
                if (node1 == temp) {
                    System.out.println(node1.data);
                    return;
                }
                temp = temp.next;
            }
            node1 = node1.next;
        }
    }

    public static void deleteMN(int m, int n) {
        Node node = head;
        while (true) {
            for (int i = 1; i < m; i++) {
                if (node.next == null)
                    return;
                node = node.next;
            }
            Node temp = node;
            for (int i = 0; i < n; i++) {
                if (node.next == null) {
                    temp.next = null;
                    return;
                }
                node = node.next;
            }
            if (node.next == null) {
                temp.next = null;
                return;
            }
            temp.next = node.next;
            node = node.next;
        }
    }

    public static void swap(int first, int second) {
        if (first == second || first > size || second > size)
            return;
        Node firstPrevNode = null, secondPrevNode = null;
        Node firstNode = head, secondNode = head;
        for (int i = 0; i < first; i++) {
            firstPrevNode = firstNode;
            firstNode = firstNode.next;
        }
        for (int i = 0; i < second; i++) {
            secondPrevNode = secondNode;
            secondNode = secondNode.next;
        }
        if (firstPrevNode == secondNode) {
            Node temp = firstNode.next;
            if (secondPrevNode == null)
                head = firstNode;
            else
                secondPrevNode.next = firstNode;
            firstNode.next = secondNode;
            secondNode.next = temp;
            return;
        } else if (firstNode == secondPrevNode) {
            Node temp = secondNode.next;
            if (firstPrevNode == null)
                head = secondNode;
            else
                firstPrevNode.next = secondNode;
            secondNode.next = firstNode;
            firstNode.next = temp;
            return;
        }
        Node temp = secondNode.next;
        if (firstPrevNode == null)
            head = secondNode;
        else
            firstPrevNode.next = secondNode;
        secondNode.next = firstNode.next;
        firstNode.next = temp;
        if (secondPrevNode == null)
            head = firstNode;
        else
            secondPrevNode.next = firstNode;
        return;
    }

    public static void oddEven() {
        Node even = head, odd = head, prev = null;
        while (odd.next != null) {
            odd = odd.next;
        }
        int i = 0;
        while (i < size) {
            if (even.data % 2 == 1) {
                if (prev == null) {
                    head = even.next;
                    odd.next = even;
                    even = even.next;
                    odd = odd.next;
                    odd.next = null;
                    continue;
                }
                odd.next = even;
                even = even.next;
                prev.next = even;
                odd = odd.next;
                odd.next = null;
            } else {
                prev = even;
                even = even.next;
            }
            i++;
        }
    }

    public static Node mergeSortedLL(Node nodeList[]) {
        for (int i = 0; i < nodeList.length; i++) {
            if (nodeList[i] != null)
                break;
            else if (nodeList[i] == null && i + 1 < nodeList.length)
                continue;
            else
                return nodeList[0];
        }
        Node node = new Node(Integer.MAX_VALUE);
        int index = 0;
        for (int i = 0; i < nodeList.length; i++) {
            if (nodeList[i] == null)
                continue;
            if (node.data > nodeList[i].data) {
                index = i;
                node = nodeList[i];
            }
        }
        node = nodeList[index];
        nodeList[index] = nodeList[index].next;
        node.next = mergeSortedLL(nodeList);
        return node;
    }

    public static DoublyLL start;
    public static DoublyLL end;
    public static int len;

    public static void doublyAddFirst(int data) {
        len++;
        DoublyLL node = new DoublyLL(data);
        if (start != null) {
            start.prev = node;
            node.next = start;
            start = node;
        } else
            start = end = node;
    }

    public static void doublyAddLast(int data) {
        len++;
        DoublyLL node = new DoublyLL(data);
        if (end != null) {
            node.prev = end;
            end.next = node;
            end = node;
        } else
            start = end = node;
    }

    public static void doublyRemoveFirst() {
        if (start == null)
            return;
        len--;
        if (start.next != null) {
            start = start.next;
            start.prev = null;
        } else
            start = end = null;
    }

    public static void doublyRemoveLast() {
        if (start == null)
            return;
        len--;
        if (end.prev != null) {
            end = end.prev;
            end.next = null;
        } else
            end = start = null;
    }

    public static void doublyPrint(DoublyLL node) {
        System.out.print(len + ": ");
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void doublyReverse() {
        DoublyLL node = end = start, prevNode = null;
        while (node != null) {
            System.out.println(node.data);
            node.prev = node.next;
            node.next = prevNode;
            prevNode = node;
            node = node.prev;
        }
        start = prevNode;
    }

    public static Circular open;
    public static Circular close;

    public static void addStartLoop(int data) {
        Circular node = new Circular(data);
        if (open != null) {
            node.next = open;
            close.next = node;
            open = node;
        } else {
            open = close = node;
            open.next = close;
        }
    }

    public static void addEndLoop(int data) {
        Circular node = new Circular(data);
        if (open != null) {
            node.next = open;
            close.next = node;
            close = node;
        } else {
            open = close = node;
            open.next = close;
        }
    }

    public static void removeStartLoop() {
        if (open == null)
            return;
        if (open.next != open) {
            open = open.next;
            close.next = open;
        } else {
            open = close = null;
        }
    }

    public static void removeEndLoop() {
        if (open == null)
            return;
        Circular node = open;
        while (node.next != close)
            node = node.next;
        if (close.next != close) {
            node.next = open;
            close = node;
        } else {
            open = close = null;
        }
    }

    public static void circularPrint(Circular start) {
        Circular node = start;
        do {
            System.out.print(node.data + " ");
            node = node.next;
        } while (node != start);
    }
}