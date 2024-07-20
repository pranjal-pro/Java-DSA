import java.util.*;

@SuppressWarnings("unchecked")

public class AlphaHashing {
    public static class HASHMAP<K, V> {
        public static class Node<K, V> {
            K key;
            V value;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        LinkedList<Node<K, V>>[] bucket;
        int size;
        boolean isEmpty;
        int N;
        double lemda;

        HASHMAP() {
            bucket = new LinkedList[4];
            for (int i = 0; i < 4; i++)
                bucket[i] = new LinkedList<>();
            size = 0;
            isEmpty = true;
            N = 4;
        }

        int hashFunction(K key) {
            return Math.abs(key.hashCode() % N);
        }

        void rehash() {
            LinkedList<Node<K, V>> temp[] = bucket;
            N *= 2;
            bucket = new LinkedList[N];
            for (int i = 0; i < N; i++)
                bucket[i] = new LinkedList<>();
            for (LinkedList<Node<K, V>> ll : temp)
                for (Node<K, V> node : ll)
                    put(node.key, node.value);
        }

        int searchInLinkedList(K key, int bi) {
            for (int i = 0; i < bucket[bi].size(); i++) {
                Node<K, V> temp = bucket[bi].get(i);
                if (temp.key == key)
                    return i;
            }
            return -1;
        }

        void put(K key, V value) {
            int bucketIndex = hashFunction(key);
            int listIndex = searchInLinkedList(key, bucketIndex);
            if (listIndex != -1) {
                bucket[bucketIndex].get(listIndex).value = value;
                return;
            }
            bucket[bucketIndex].add(new Node<>(key, value));
            size++;
            isEmpty = false;
            lemda = (double) size / N;
            if (lemda > 2.0)
                rehash();
        }

        V remove(K key) {
            int bucketIndex = hashFunction(key);
            int listIndex = searchInLinkedList(key, bucketIndex);
            if (listIndex != -1) {
                size--;
                isEmpty = (size == 0);
                Node<K, V> remove = bucket[bucketIndex].remove(listIndex);
                return remove.value;
            } else
                return null;
        }

        V get(K key) {
            int bucketIndex = hashFunction(key);
            int listIndex = searchInLinkedList(key, bucketIndex);
            if (listIndex != -1)
                return bucket[bucketIndex].get(listIndex).value;
            return null;
        }

        boolean contains(K key) {
            int bucketIndex = hashFunction(key);
            int listIndex = searchInLinkedList(key, bucketIndex);
            if (listIndex != -1)
                return true;
            return false;
        }

        ArrayList<K> keySet() {
            ArrayList<K> keys = new ArrayList<K>();
            for (LinkedList<Node<K, V>> list : bucket)
                for (Node<K, V> node : list)
                    keys.add(node.key);
            return keys;
        }
    }

    public static class BinaryTree {
        static class Node {
            int data;
            Node left, right;

            Node(int data) {
                this.data = data;
                this.left = this.right = null;
            }
        }

        static int i = -1;

        Node builtTree(int arr[]) {
            i++;
            if (i >= arr.length || arr[i] == -1)
                return null;
            Node root = new Node(arr[i]);
            root.left = builtTree(arr);
            root.right = builtTree(arr);
            return root;
        }

        public class BottomView {
            Node node;
            int distance;

            BottomView(Node node, int distance) {
                this.node = node;
                this.distance = distance;
            }
        }

        ArrayList<Integer> bottomView(Node node) {
            BottomView root = new BottomView(node, 0);
            TreeMap<Integer, Node> map = new TreeMap<>();
            Queue<BottomView> queue = new LinkedList<>();
            queue.add(root);
            map.put(0, node);
            while (!queue.isEmpty()) {
                BottomView temp = queue.remove();
                if (temp.node.left != null)
                    queue.add(new BottomView(temp.node.left, temp.distance - 1));
                if (temp.node.right != null)
                    queue.add(new BottomView(temp.node.right, temp.distance + 1));

                map.put(temp.distance, temp.node);
            }
            ArrayList<Integer> list = new ArrayList<>();
            for (int i : map.keySet())
                list.add(map.get(i).data);
            return list;
        }
    }

    public static class LRUCache {
        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<Integer> lru = new LinkedList<>();
        final int size;

        LRUCache(int size) {
            this.size = size;
            System.out.print(null + " ");
        }

        void assign(String task, int[] data) {
            lru.remove(data[0]);
            lru.add(data[0]);
            if (task == "get")
                get(data[0]);
            if (task == "put")
                put(data[0], data[1]);
        }

        void get(int data) {
            System.out.print(map.getOrDefault(data, -1) + " ");
        }

        void put(int key, int value) {
            map.put(key, value);
            if (map.size() > size) {
                map.remove(lru.remove());
                lru.add(key);
            }
            System.out.print(null + " ");
        }
    }

    public static void main(String[] args) {
        HASHMAP<String, Integer> map = new HASHMAP<>();
        map.put("burger", 40);
        map.put("pizza", 100);
        map.put("momo", 60);
        for (String key : map.keySet())
            System.out.println(key + "=" + map.get(key));

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("burger", 40);
        hashMap.put("pizza", 100);
        hashMap.put("momo", 60);
        System.out.println(hashMap); // {pizza=100, burger=40, momo=60}-> Random Order

        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("burger", 40);
        linkedHashMap.put("pizza", 100);
        linkedHashMap.put("momo", 60);
        System.out.println(linkedHashMap); // {burger=40, pizza=100, momo=60}-> Insertion Order

        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("burger", 40);
        treeMap.put("pizza", 100);
        treeMap.put("momo", 60);
        System.out.println(treeMap); // {burger=40, momo=60, pizza=100}-> Sorted Order

        { // More about Maps
            Set<String> keys = hashMap.keySet();
            for (String key : keys)
                System.out.print(key + "=" + hashMap.get(key) + "  ");
            System.out.println();

            Iterator<String> i = hashMap.keySet().iterator();
            while (i.hasNext()) {
                Object ob = i.next();
                System.out.print(ob + "=" + hashMap.get(ob) + " ");
            }
            System.out.println();

            Set<Map.Entry<String, Integer>> entrySet = hashMap.entrySet();
            System.out.println(entrySet);
            // .keySet(), .entrySet(), .get(key), .put(key,value), .containsKey(key),
            // .size(),.remove(key), .clear(), .isEmpty()
        }

        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(2);
        hashSet.add(1);
        hashSet.add(3);
        hashSet.forEach(e -> System.out.print(e + " ")); // [1, 2, 3] -> Random Order
        System.out.println();

        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(2);
        linkedHashSet.add(1);
        linkedHashSet.add(3);
        linkedHashSet.forEach(e -> System.out.print(e + " ")); // 2 1 3 -> Insertion Order
        System.out.println();

        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(2);
        treeSet.add(1);
        treeSet.add(3);
        treeSet.forEach(e -> System.out.print(e + " ")); // 1 2 3 -> Sorted Order
        System.out.println();

        { // More about Sets
            {
                Iterator<Integer> str = hashSet.iterator();
                while (str.hasNext())
                    System.out.print(str.next());
            }
            hashSet.forEach(str -> System.out.print(str));

            hashSet.forEach(System.out::print);

            for (Iterator<Integer> str = hashSet.iterator(); str.hasNext();)
                System.out.print(str.next());

            for (Integer str : hashSet)
                System.out.print(str);
            System.out.println();
            // .isEmpty(), .clear(), .contains("pranjal"), .size(), .remove("pranjal"),
            // .toArray(), .iterator(), .forEach()
        }

        // majority elements are elements occured more than n/3 times
        int elements_majorityElements[] = { 1, 2, 1 };
        System.out.print("Majority Element=> ");
        printMajorityElements(elements_majorityElements);

        // anaragrams are word with same letters
        System.out.println("isAnagram(race, care) => " + isAnagram("race", "care"));

        // count distinct element
        int elements_distinctElements[] = { 4, 3, 2, 5, 6, 7, 3, 4, 2, 1 };
        System.out.println("No. of distinct elements are: " + distinctElements(elements_distinctElements));

        // Union & Intersection
        int arr1[] = { 7, 3, 9 }, arr2[] = { 6, 3, 9, 2, 9, 4 };
        System.out.println("Union: " + union(arr1, arr2));
        System.out.println("Intersection: " + intersection(arr1, arr2));

        // Find Itinerary form Tickets
        String tickets[][] = { { "Chennai", "Bengalauru" },
                { "Mumbai", "Delhi" },
                { "Goa", "Chennai" },
                { "Delhi", "Goa" } };
        System.out.println(route(tickets));

        // Largest SubArray with sum = 0
        int array_Sum0[] = { 15, -2, 2, -8, 1, 7, 10, 23 };
        System.out.println("lenght of largrst sub-array with sum= 0 is " + largestSubArray(array_Sum0));

        // SubArrays with sum= k
        int array_SumK[] = { 10, 2, -2, -20, 10 }, k = -10;
        System.out.println("sub-array with sum= 3 are " + sumOfSubArrayK(array_SumK, k));

        // Bottom View of Tree
        int arr[] = { 20, 8, 5, -1, -1, 3, 10, -1, -1, 14, -1, -1, 22, -1, 25, -1, -1 };
        BinaryTree tree = new BinaryTree();
        BinaryTree.Node root = tree.builtTree(arr);
        System.out.println(tree.bottomView(root));

        // Two sum
        System.out.println(twoSum(new int[] { 2, 7, 11, 15 }, 9));

        // Sort by frequency
        System.out.println(freqSort("tree"));

        // Least Recently Used (LRU) cache
        String operation[] = { "LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get" };
        int values[][] = { { 2 }, { 1, 1 }, { 2, 2 }, { 1 }, { 3, 3 }, { 2 }, { 4, 4 }, { 1 }, { 3 }, { 4 } };
        lRUCache(operation, values);
    }

    static void printMajorityElements(int[] elements) {
        HashMap<Integer, Integer> majorityCheck = new HashMap<>();
        for (int element : elements)
            majorityCheck.put(element, majorityCheck.getOrDefault(element, 0) + 1);
        Set<Integer> keys_MajorityElement = majorityCheck.keySet();
        for (int key : keys_MajorityElement)
            if (majorityCheck.get(key) > elements.length / 3)
                System.out.print(key + " ");
        System.out.println();
    }

    static boolean isAnagram(String str1, String str2) {
        HashMap<Character, Integer> anagram = new HashMap<>();
        for (Character ch : str1.toCharArray())
            anagram.put(ch, anagram.getOrDefault(ch, 0) + 1);
        for (char ch : str2.toCharArray())
            if (anagram.get(ch) != null) {
                anagram.put(ch, anagram.get(ch) - 1);
                if (anagram.get(ch) == 0)
                    anagram.remove(ch);
            } else
                return false;
        return anagram.isEmpty();
    }

    static int distinctElements(int[] elements) {
        HashSet<Integer> distinctNumber = new HashSet<>();
        for (int element : elements)
            distinctNumber.add(element);
        return distinctNumber.size();
    }

    static HashSet<Integer> union(int arr1[], int arr2[]) {
        HashSet<Integer> union = new HashSet<>();
        for (int i : arr1)
            union.add(i);
        for (int i : arr2)
            union.add(i);
        return union;
    }

    static HashSet<Integer> intersection(int arr1[], int arr2[]) {
        HashSet<Integer> temp = new HashSet<>();
        for (int i : arr1)
            temp.add(i);
        HashSet<Integer> intersection = new HashSet<>();
        for (int i : arr2)
            if (temp.contains(i))
                intersection.add(i);
        return intersection;
    }

    static StringBuilder route(String[][] ticket) {
        HashSet<String> to = new HashSet<>();
        for (String[] pair : ticket)
            to.add(pair[1]);
        String start = "";
        for (String[] pair : ticket)
            if (!to.contains(pair[0]))
                start = pair[0];

        HashMap<String, String> fromTo = new HashMap<>();
        for (String[] pair : ticket)
            fromTo.put(pair[0], pair[1]);

        StringBuilder route = new StringBuilder(start);
        while (start != null) {
            start = fromTo.get(start);
            route.append("-> " + start);
        }
        return route;
    }

    static int largestSubArray(int array[]) {
        int sum = 0;
        int length = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if (map.get(sum) == null)
                map.put(sum, i);
            else
                length = Math.max(length, i - map.get(sum));
        }
        return length;
    }

    static int sumOfSubArrayK(int array[], int k) {
        int count = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i : array) {
            sum += i;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
        }
        return count;
    }

    public static ArrayList<Integer> twoSum(int arr[], int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++)
            if (map.containsKey(target - arr[i]))
                return new ArrayList<>(Arrays.asList(map.get(target - arr[i]), i));
            else
                map.put(arr[i], i);
        return null;
    }

    public static StringBuilder freqSort(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? b.getKey() - a.getKey() : b.getValue() - a.getValue());
        for (Map.Entry<Character, Integer> entry : map.entrySet())
            queue.add(entry);
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : queue)
            for (int i = 0; i < entry.getValue(); i++)
                result.append(entry.getKey());
        return result;
    }

    public static void lRUCache(String operation[], int value[][]) {
        LRUCache lru = new LRUCache(value[0][0]);
        for (int i = 0; i < operation.length; i++) {
            lru.assign(operation[i], value[i]);
        }
        System.out.println();
    }
}