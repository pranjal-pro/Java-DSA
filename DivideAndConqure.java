import java.util.*;

public class DivideAndConqure {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int arr[] = { 3, 4, 3, 4, 4, 1, 3 };
        // mergeSort(arr, 0, arr.length - 1);
        // quickSort(arr, 0, arr.length - 1);
        // System.out.println(rotateSortSearch(arr, 4, 0, arr.length - 1));
        // stringMergeSort(list, 0, list.length - 1);
        // System.out.println(majorityElement(arr, 0, arr.length - 1));
        // System.out.println(inversionCount(arr, 0, arr.length - 1));
    }

    public static int inversionCount(int arr[], int start, int end) {
        if (start >= end)
            return 0;
        int mid = (start + end) / 2;
        int inversion = inversionCount(arr, start, mid) + inversionCount(arr, mid + 1, end);

        int temp[] = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
        while (i <= mid && j <= end)
            if (arr[i] > arr[j]) {
                inversion += mid - i + 1;
                temp[k++] = arr[j++];
            } else
                temp[k++] = arr[i++];

        while (i <= mid)
            temp[k++] = arr[i++];

        while (j <= end)
            temp[k++] = arr[j++];

        for (i = 0, j = start; i < temp.length;)
            arr[j++] = temp[i++];
        return inversion;
    }

    public static int majorityElement(int arr[], int start, int end) {
        if (start == end)
            return arr[start];

        int mid = (start + end) / 2;
        int left = majorityElement(arr, start, mid);
        int right = majorityElement(arr, mid + 1, end);
        if (left == right)
            return left;
        else
            return countMajorityElement(arr, start, mid, left) > countMajorityElement(arr, mid + 1, end, right) ? left
                    : right;
    }

    public static int countMajorityElement(int arr[], int start, int end, int element) {
        int count = 0;
        for (; start <= end; start++)
            if (arr[start] == element)
                count++;

        return count;
    }

    public static void stringMergeSort(String list[], int start, int end) {
        if (start >= end)
            return;
        int mid = (start + end) / 2;
        stringMergeSort(list, start, mid);
        stringMergeSort(list, mid + 1, end);
        String tempString[] = new String[end - start + 1];
        int i = start, j = mid + 1, k = 0;
        while (k < tempString.length)
            if (list[i].compareTo(list[j]) <= 0) {
                tempString[k++] = list[i++];
                if (i > mid)
                    while (k < tempString.length)
                        tempString[k++] = list[j++];
            } else {
                tempString[k++] = list[j++];
                if (j > end)
                    while (k < tempString.length) {
                        tempString[k++] = list[i++];
                    }
            }
        i = 0;
        j = start;
        while (i < tempString.length)
            list[j++] = tempString[i++];
    }

    public static int rotateSortSearch(int arr[], int key, int start, int end) {
        int mid = (start + end) / 2;
        if (key == arr[mid])
            return mid;
        if (start >= end)
            return -1;

        if (arr[mid] > arr[start])
            if (key < arr[mid] && key >= arr[start])
                return rotateSortSearch(arr, key, start, mid - 1);
            else
                return rotateSortSearch(arr, key, mid + 1, end);
        else if (key > arr[mid] && key <= arr[end])
            return rotateSortSearch(arr, key, mid + 1, end);
        else
            return rotateSortSearch(arr, key, start, mid - 1);

    }

    public static void quickSort(int arr[], int start, int end) {
        if (start >= end)
            return;
        int swap = start;
        for (int i = start; i <= end; i++) {
            if (arr[i] <= arr[end]) {
                int temp = arr[i];
                arr[i] = arr[swap];
                arr[swap] = temp;
                swap++;
            }
        }
        quickSort(arr, start, swap - 2);
        quickSort(arr, swap, end);
    }

    public static void mergeSort(int arr[], int start, int end) {
        if (start == end)
            return;
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        int temp[] = new int[end - start + 1];
        int first = start, second = mid + 1, tempCount = 0;
        while (tempCount < temp.length)
            if (arr[first] <= arr[second]) {
                temp[tempCount++] = arr[first++];
                if (first == mid + 1)
                    while (tempCount < temp.length)
                        temp[tempCount++] = arr[second++];
            } else {
                temp[tempCount++] = arr[second++];
                if (second == end + 1)
                    while (tempCount < temp.length)
                        temp[tempCount++] = arr[first++];
            }

        for (tempCount = 0, first = start; tempCount < temp.length;) {
            arr[first++] = temp[tempCount++];
        }
    }
}