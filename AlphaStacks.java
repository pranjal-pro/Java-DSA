import java.util.*;

public class AlphaStacks {
    public static class ArrayStack {
        int arr[] = new int[10];
        int size = 0;

        public int arrPush(int data) {
            if (size == 10) {
                System.out.println("Stack Full !");
                return -1;
            }
            arr[size] = data;
            size++;
            return data;
        }

        public int arrPeek() {
            return arr[size - 1];
        }

        public int arrPop() {
            int temp = arr[size - 1];
            arr[size - 1] = 0;
            size--;
            return temp;
        }

        public void arrPrint() {
            for (int i = 0; i < size; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static class ArrayListStack {
        ArrayList<Integer> list = new ArrayList<>();
        int size = 0;

        public int listPush(int data) {
            list.add(data);
            size++;
            return data;
        }

        public int listPeek() {
            return list.get(size - 1);
        }

        public int listPop() {
            return list.remove(--size);
        }

        public void listPrint() {
            System.out.println(list);
        }
    }

    public static class LinkedListStack {
        LinkedList<Integer> ll = new LinkedList<>();

        public int llPush(int data) {
            ll.push(data);
            return data;
        }

        public int llPeek() {
            return ll.peek();
        }

        public int llPop() {
            return ll.pop();
        }

        public void llPrint() {
            System.out.println(ll);
        }
    }

    public static void main(String args[]) {
        ArrayStack arr = new ArrayStack();
        arr.arrPush(0);
        arr.arrPush(1);
        arr.arrPush(2);
        arr.arrPrint();

        ArrayListStack list = new ArrayListStack();
        list.listPush(0);
        list.listPush(1);
        list.listPush(2);
        list.listPrint();

        LinkedListStack ll = new LinkedListStack();
        ll.llPush(0);
        ll.llPush(1);
        ll.llPush(2);
        ll.llPrint();

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        addLast(stack, 4);
        reverseInt(stack);
        System.out.println(stack);

        StringBuilder str = new StringBuilder("abc");
        System.out.println(reverseStr(str));
        StringBuilder parentheses = new StringBuilder("{([()]){}}}{}");
        System.out.println(validParentheses(parentheses));
        StringBuilder data = new StringBuilder("(a+ (b+ ((c)))+ (a+ b))");
        System.out.println(validData(data));

        int stockSpan[] = { 100, 80, 60, 70, 60, 85, 100 };
        stockSpan(stockSpan);
        int nextGreater[] = { 6, 8, 0, 1, 3 };
        nextGreater(nextGreater);
        int maxArea[] = { 2, 1, 5, 6, 2, 3 };
        System.out.println(maxArea(maxArea));

        // Assignment Question

        Node palindromLL = new Node('a');
        palindromLL.next = new Node('b');
        palindromLL.next.next = new Node('c');
        palindromLL.next.next.next = new Node('c');
        palindromLL.next.next.next.next = new Node('a');
        System.out.println(isPalindrom(palindromLL));
        StringBuilder simplifiedPath = new StringBuilder("/Users/Pranjal/Coding/.././../");
        System.out.println(simplifiedPath(simplifiedPath));
        String decode = "2[b3[v]]";
        System.out.println(decode(decode));
        int waterLevel[] = { 7, 1, 5, 6, 0, 5 };
        System.out.println(waterLevel(waterLevel));
    }

    public static void addLast(Stack<Integer> stack, int data) {
        if (stack.isEmpty()) {
            stack.push(data);
            return;
        }
        int temp = stack.pop();
        addLast(stack, data);
        stack.push(temp);
    }

    public static void reverseInt(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int temp = stack.pop();
        reverseInt(stack);
        addLast(stack, temp);
    }

    public static StringBuilder reverseStr(StringBuilder str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++)
            stack.push(str.charAt(i));
        StringBuilder result = new StringBuilder();
        while (!stack.empty())
            result.append(stack.pop());
        return result;
    }

    public static boolean validParentheses(StringBuilder str) {
        Stack<Character> validator = new Stack<>();
        for (int i = 0; i < str.length(); i++)
            if (validator.isEmpty())
                validator.push(str.charAt(i));
            else if (validator.peek() == '(' && str.charAt(i) == ')')
                validator.pop();
            else if (validator.peek() == '[' && str.charAt(i) == ']')
                validator.pop();
            else if (validator.peek() == '{' && str.charAt(i) == '}')
                validator.pop();
            else
                validator.push(str.charAt(i));

        return validator.isEmpty();
    }

    public static Boolean validData(StringBuilder str) {
        Stack<Boolean> result = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                result.push(false);
            } else if (str.charAt(i) == ')') {
                if (result.peek())
                    result.pop();
                else
                    return false;
            } else if (!result.peek()) {
                result.pop();
                result.push(true);
            }
        }
        return true;
    }

    public static int stockSpan(int data[]) {
        Stack<Integer> stack = new Stack<>();
        int result[] = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                result[i] = 1;
                continue;
            }
            while (true) {
                if (stack.isEmpty()) {
                    result[i] = i;
                    stack.push(i);
                    break;
                }
                if (data[stack.peek()] > data[i]) {
                    result[i] = i - stack.peek();
                    stack.push(i);
                    break;
                } else
                    stack.pop();
            }
        }
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
        return 0;
    }

    public static void nextGreater(int data[]) {
        Stack<Integer> stack = new Stack<>();
        int result[] = new int[data.length];
        for (int i = data.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                stack.push(data[i]);
                result[i] = -1;
                continue;
            }
            while (true) {
                if (stack.peek() > data[i]) {
                    result[i] = stack.peek();
                    stack.push(data[i]);
                    break;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(data[i]);
                        result[i] = -1;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < data.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }

    public static int maxArea(int data[]) {
        int left[] = new int[data.length];
        int right[] = new int[data.length];
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < data.length; i++) {
            while (!stack.isEmpty() && data[stack.peek()] >= data[i])
                stack.pop();
            if (stack.empty())
                left[i] = 0;
            else
                left[i] = stack.peek() + 1;
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            stack.pop();
        }
        for (int i = data.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && data[stack.peek()] >= data[i])
                stack.pop();
            if (stack.empty())
                right[i] = data.length;
            else
                right[i] = stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < data.length; i++) {
            max = Math.max(max, (right[i] - left[i]) * data[i]);
        }
        return max;
    }

    public static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            next = null;
        }

        public Node head;
    }

    public static boolean isPalindrom(Node data) {
        Stack<Character> str = new Stack<>();
        Node node = data;
        while (node != null) {
            str.push(node.data);
            node = node.next;
        }
        while (data != null) {
            if (str.peek() == data.data)
                str.pop();
            data = data.next;
        }
        return str.isEmpty();
    }

    public static StringBuilder simplifiedPath(StringBuilder str) {
        Stack<String> stack = new Stack<>();
        String list[] = str.toString().split("/");
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals("."))
                continue;
            else if (list[i].equals(".."))
                stack.pop();
            else
                stack.push(list[i]);
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append("/" + stack.pop());
        }
        return result;
    }

    public static StringBuilder decode(String string) {
        Stack<String> str = new Stack<>();
        Stack<Integer> repeat = new Stack<>();
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                int digit = 0;
                while (Character.isDigit(string.charAt(i))) {
                    digit = digit * 10 + string.charAt(i) - '0';
                    i++;
                }
                repeat.push(digit);
                i--;
            } else if (string.charAt(i) == '[') {
                i++;
                StringBuilder temp = new StringBuilder();
                while (string.charAt(i) != '[' && string.charAt(i) != ']' && !Character.isDigit(string.charAt(i))) {
                    temp.append(string.charAt(i));
                    i++;
                }
                str.push(temp.toString());
                i--;
            } else if (string.charAt(i) == ']') {
                StringBuilder temp = new StringBuilder(str.pop());
                StringBuilder repBuilder = new StringBuilder(temp);
                for (int j = 1; j < repeat.peek(); j++)
                    temp.append(repBuilder);
                repeat.pop();
                if (str.isEmpty())
                    str.push(temp.toString());
                else
                    str.push(str.pop() + temp);
            }
        }
        return new StringBuilder(str.peek());
    }

    public static int waterLevel(int data[]) {
        Stack<Integer> stack = new Stack<>();
        int water[] = new int[data.length];
        int result = 0;
        stack.push(0);
        water[0] = data[0];
        for (int index = 1; index < data.length; index++) {
            if (!(data[stack.peek()] > data[index])) {
                int temp = index;
                while (!(data[stack.peek()] > data[index])) {
                    int pop = stack.pop();
                    if (stack.empty()) {
                        temp = pop;
                        break;
                    }
                }
                for (int i = stack.empty() ? temp : stack.peek() + 1; i < index; i++) {
                    water[i] = data[temp];
                }
            }
            water[index] = data[index];
            stack.push(index);
        }
        for (int i = 0; i < data.length; i++) {
            result += water[i] - data[i];
        }
        return result;
    }
}