import java.util.*;

public class Heap {
    public static class Heap {
        ArrayList<Integer> list = new ArrayList<>();
        boolean isEmpty;
        int size = list.size();

        int peek() {
            return list.get(0);
        }
    }

    public static class MaxHeap extends Heap {
        void add(int data) {
            list.add(data);
            size++;
            isEmpty = false;

            for (int index = size - 1, parent = (index - 1) / 2; list.get(index) > list
                    .get(parent); index = parent, parent = (index - 1) / 2) {
                list.set(index, list.get(parent));
                list.set(parent, data);
            }
        }

        int remove() {
            int data = list.get(0);
            list.set(0, list.get(size - 1));
            list.remove(--size);
            isEmpty = list.isEmpty();

            heapify(0);
            return data;
        }

        void heapify(int index) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int maxIndex = index;

            if (right < size && list.get(maxIndex) < list.get(right))
                maxIndex = right;
            if (left < size && list.get(maxIndex) < list.get(left))
                maxIndex = left;

            if (maxIndex != index) {
                int temp = list.get(maxIndex);
                list.set(maxIndex, list.get(index));
                list.set(index, temp);
                heapify(maxIndex);
            }
        }
    }

    public static class MinHeap extends Heap {
        void add(int data) {
            list.add(data);
            size++;
            isEmpty = false;

            for (int index = size - 1, parent = (index - 1) / 2; list.get(index) < list
                    .get(parent); index = parent, parent = (index - 1) / 2) {
                list.set(index, list.get(parent));
                list.set(parent, data);
            }
        }

        void heapify(int index) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int minIndex = index;
            if (left < size && list.get(left) < list.get(minIndex))
                minIndex = left;
            if (right < size && list.get(right) < list.get(minIndex))
                minIndex = right;
            if (minIndex != index) {
                int temp = list.get(index);
                list.set(index, list.get(minIndex));
                list.set(minIndex, temp);
                heapify(minIndex);
            }
        }

        int remove() {
            int data = list.get(0);
            list.set(0, list.get(size - 1));
            list.remove(--size);
            isEmpty = list.isEmpty();

            heapify(0);
            return data;
        }
    }

    public static void main(String[] args) {
        MinHeap min = new MinHeap();
        int minArr[] = { 3, 2, 1 };
        for (int i : minArr)
            min.add(i);
        while (!min.isEmpty)
            System.out.print(min.remove() + " ");
        System.out.println();

        MaxHeap max = new MaxHeap();
        int maxArr[] = { 1, 2, 3 };
        for (int i : maxArr)
            max.add(i);
        while (!max.isEmpty)
            System.out.print(max.remove() + " ");
        System.out.println();

        int arr[] = { 1, 3, 2 };
        heapSort(arr);
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

    public static void heapSort(int arr[]) {
        for (int i = arr.length / 2; i >= 0; i--)
            heapify(arr, i, arr.length - 1);

        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, 0, i);
        }
    }

    public static void heapify(int arr[], int start, int end) {
        int left = start * 2 + 1;
        int right = start * 2 + 2;
        int max = start;

        if (left < end && arr[max] < arr[left])
            max = left;
        if (right < end && arr[max] < arr[right])
            max = right;

        if (max != start) {
            int temp = arr[start];
            arr[start] = arr[max];
            arr[max] = temp;
            heapify(arr, max, end);
        }
    }

}
