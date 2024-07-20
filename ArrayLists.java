import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class ArrayLists {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        // ArrayList<Integer> arr = new ArrayList<>();
        // for (int i = 0; i < 5; i++)
        // arr.add(i);
        // basicArrayList(arr);
        // printArrayList(arr);
        // printMax(arr);
        // swap(arr, 0, arr.size() - 1);
        // ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        // multidimensionArrayList(matrix);
        // printMatrix(matrix);
        // System.out.println(matrix);

        // ArrayList<Integer> waterLevel = new ArrayList<>();
        // int n = sc.nextInt();
        // for (int i = 0; i < n; i++) {
        // waterLevel.add(sc.nextInt());
        // }
        // maxWaterContainerBruteForce(waterLevel);
        // maxWaterContainerOptimised(waterLevel);

        // ArrayList<Integer> list = new ArrayList<>();
        // list = waterLevel;
        // pairSumBruteForce(list, n);
        // pairSumOptimised(list, n);
        // pairSumRotated(list, n);

        // ArrayList<Integer> arrayList = new ArrayList<>();
        // arrayList.add(1);
        // arrayList.add(100);
        // arrayList.add(200);
        // arrayList.add(1);
        // arrayList.add(100);
        // System.out.println(monotonicArrayList(arrayList));
        // System.out.println(lonelyNumber(arrayList));
        // System.out.println(mostFrequency(arrayList, 1));
        // System.out.println(beautifulArrayListBruteForce(15));
        // arrayList.clear();
        // beautifulArrayListDivAndCon(arrayList, 9, 1, 1);
        // System.out.println(arrayList);
    }

    public static void basicArrayList(ArrayList<Integer> arr) {
        System.out.println(arr);
        System.out.println(arr.get(0));
        arr.remove(0);
        Collections.sort(arr, Collections.reverseOrder());
        arr.set(0, 0);
        System.out.println(arr.indexOf(2));
    }

    public static void printArrayList(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }

    public static void printMax(ArrayList<Integer> arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.size(); i++)
            max = Math.max(max, arr.get(i));
        System.out.println("Max value is: " + max);
    }

    public static void swap(ArrayList<Integer> arr, int first, int second) {
        int temp = arr.get(first);
        arr.set(first, arr.get(second));
        arr.set(second, temp);
        printArrayList(arr);
    }

    public static void multidimensionArrayList(ArrayList<ArrayList<Integer>> matrix) {
        ArrayList<Integer> arr0 = new ArrayList<>();
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arr0.add(i);
            arr1.add(i * 2);
            arr2.add(i * 5);
        }
        matrix.add(arr0);
        matrix.add(arr1);
        matrix.add(arr2);
    }

    public static void printMatrix(ArrayList<ArrayList<Integer>> matrix) {
        for (int i = 0; i < matrix.size(); i++) {
            ArrayList<Integer> arr = matrix.get(i);
            for (int j = 0; j < arr.size(); j++) {
                System.out.print(arr.get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void maxWaterContainerBruteForce(ArrayList<Integer> waterLevel) {
        int max = 0;
        for (int i = 0; i < waterLevel.size() - 1; i++) {
            for (int j = i + 1; j < waterLevel.size(); j++) {
                max = Math.max(max, (j - i) * Math.min(waterLevel.get(i),
                        waterLevel.get(j)));
            }
        }
        System.out.println(max);
    }

    public static void maxWaterContainerOptimised(ArrayList<Integer> waterLevel) {
        int left = 0;
        int right = waterLevel.size() - 1;
        int max = 0;
        while (left < right) {
            max = Math.max((Math.min(waterLevel.get(left), waterLevel.get(right))) * (right - left), max);
            if (waterLevel.get(left) < waterLevel.get(right))
                left++;
            else
                right--;
        }
        System.out.println(max);
    }

    public static void pairSumBruteForce(ArrayList<Integer> arr, int key) {
        for (int i = 0; i < arr.size() - 1; i++)
            for (int j = i; j < arr.size(); j++)
                if (arr.get(i) + arr.get(j) == key)
                    System.out.println(arr.get(i) + ", " + arr.get(j));
    }

    public static void pairSumOptimised(ArrayList<Integer> arr, int key) {
        int left = 0, right = arr.size() - 1;
        while (left < right) {
            if (arr.get(left) + arr.get(right) == key) {
                System.out.println(arr.get(left) + ", " + arr.get(right));
                left++;
            } else if (arr.get(left) + arr.get(right) < key)
                left++;
            else
                right--;
        }
    }

    public static void pairSumRotated(ArrayList<Integer> arr, int key) {
        int left = 0, right = arr.size() - 1;
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) > arr.get(i + 1)) {
                left = i + 1;
                right = i;
                break;
            }
        }
        while (left != right) {
            if (arr.get(left) + arr.get(right) == key) {
                System.out.println(arr.get(left) + ", " + arr.get(right));
                left = (left + 1) % arr.size();
            } else if (arr.get(left) + arr.get(right) < key)
                left++;
            else
                right = (arr.size() + right - 1) % arr.size();
        }
    }

    public static boolean monotonicArrayList(ArrayList<Integer> arr) {
        boolean incerase = true, decrease = true;
        for (int i = 0; i < arr.size() - 1; i++) {
            incerase = arr.get(i) <= arr.get(i + 1) ? incerase : false;
            decrease = arr.get(i) >= arr.get(i + 1) ? decrease : false;
        }
        return incerase || decrease;
    }

    public static ArrayList<Integer> lonelyNumber(ArrayList<Integer> arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        Collections.sort(arr);
        for (int i = 0; i < arr.size(); i++) {
            if (i != arr.size() - 1) {
                if (arr.get(i) + 1 == arr.get(i + 1))
                    continue;
                if (arr.get(i) == arr.get(i + 1))
                    continue;
            }
            if (i != 0) {
                if (arr.get(i) - 1 == arr.get(i - 1))
                    continue;
                if (arr.get(i) == arr.get(i - 1))
                    continue;
                ;
            }
            ans.add(arr.get(i));
        }
        return ans;
    }

    public static int mostFrequency(ArrayList<Integer> arr, int key) {
        ArrayList<Integer> count = new ArrayList<>();
        ArrayList<Integer> value = new ArrayList<>();
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i - 1) == key) {
                if (value.contains(arr.get(i))) {
                    count.set(value.indexOf(arr.get(i)), 1 + count.get(value.indexOf(arr.get(i))));
                } else {
                    value.add(arr.get(i));
                    count.add(1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < count.size(); i++) {
            if (count.get(max) < count.get(i))
                max = i;
        }
        return value.get(max);
    }

    public static ArrayList<Integer> beautifulArrayListBruteForce(int lenght) {
        // no k should exist for i< k< j such that => 2* arr[k]= arr[i]+ arr[j];
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(1);
        for (int i = 2; i <= lenght / 2 + 2; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (Integer integer : ans)
                if (2 * integer - 1 <= lenght)
                    temp.add(2 * integer - 1);
            for (Integer integer : ans)
                if (integer * 2 <= lenght)
                    temp.add(2 * integer);
            ans = temp;
        }
        return ans;
    }

    public static void beautifulArrayListDivAndCon(ArrayList<Integer> arr, int lenght, int start, int end) {
        if (start + end > lenght) {
            arr.add(start);
            return;
        } else {
            beautifulArrayListDivAndCon(arr, lenght, start, 2 * end);
            beautifulArrayListDivAndCon(arr, lenght, start + end, 2 * end);
        }
    }
}