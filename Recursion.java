import java.util.*;

public class Recursion {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        printReverseNumber(10);
        printNumber(10);
        System.out.println(printYear(2003, new StringBuilder()));
        System.out.println(printFactorial(5));
        System.out.println(printSumNumber(10));
        System.out.println(printNthFibonacci(5));
        System.out.println(stringLength("abcdefghijklmnopqrstuvwxyz", 0));
        int arr[] = { 1, 2, 3, 4, 5, 5, 6 };
        System.out.println(sortCheck(arr, 0));
        System.out.println(firstOccurance(arr, 5, 0));
        System.out.println(lastOccurance(arr, 5, 0));
        allOccurance(arr, 5, 0);
        System.out.println(printPow(2, 4));
        System.out.println(tilingLength(3));
        System.out.println(removeDuplicateString(new StringBuilder("appnacollege"),
                new StringBuilder(), 0));
        System.out.println(friendPair(4));
        printBinaryString(6, "", 0);
        System.out.println();
        System.out.println(contiguousSubstring("abacbac", 7));
        towerOfHanoi(5, "a", "b", "c");
    }

    public static void printReverseNumber(int n) {
        System.out.print(n + " ");
        if (n == 1) {
            System.out.println();
            return;
        }
        printReverseNumber(n - 1);
    }

    public static void printNumber(int n) {
        if (n == 0)
            return;
        printNumber(n - 1);
        System.out.print(n + "" + (n == 10 ? '\n' : ' '));
    }

    public static StringBuilder printYear(int year, StringBuilder str) {
        if (year > 9)
            printYear(year / 10, str);
        switch (year % 10) {
            case 0:
                str.append("zero ");
                break;
            case 1:
                str.append("one ");
                break;
            case 2:
                str.append("two ");
                break;
            case 3:
                str.append("three ");
                break;
            case 4:
                str.append("four ");
                break;
            case 5:
                str.append("five ");
                break;
            case 6:
                str.append("six ");
                break;
            case 7:
                str.append("seven ");
                break;
            case 8:
                str.append("eight ");
                break;
            case 9:
                str.append("nine ");
                break;
        }
        return str;
    }

    public static int printFactorial(int n) {
        if (n >= 2)
            return n * printFactorial(n - 1);
        else
            return 1;
    }

    public static int printSumNumber(int n) {
        if (n > 0)
            return n + printSumNumber(n - 1);
        else
            return 0;
    }

    public static int printNthFibonacci(int n) {
        if (n == 1)
            return 1;
        else if (n == 0)
            return 0;
        else
            return printNthFibonacci(n - 1) + printNthFibonacci(n - 2);
    }

    public static boolean sortCheck(int arr[], int n) {
        if (n == arr.length - 1)
            return true;
        else if (arr[n] > arr[n + 1])
            return false;
        return sortCheck(arr, n + 1);
    }

    public static int firstOccurance(int arr[], int key, int i) {
        if (i == arr.length)
            return -1;
        if (arr[i] == key)
            return i;
        else
            return firstOccurance(arr, key, i + 1);
    }

    public static int lastOccurance(int arr[], int key, int i) {
        if (arr.length == i) {
            return -1;
        }
        int lastIndex = lastOccurance(arr, key, i + 1);
        if (lastIndex == -1)
            if (arr[i] == key)
                return i;
        return lastIndex;
    }

    public static void allOccurance(int arr[], int key, int i) {
        if (i == arr.length) {
            System.out.println();
            return;
        }
        if (arr[i] == key)
            System.out.print(i + " ");
        allOccurance(arr, key, i + 1);
    }

    public static int printPow(int num, int exp) {
        // return exp == 0 ? 1 : num * printPow(num, exp - 1);
        if (exp == 0)
            return 1;
        int halfExp = printPow(num, exp / 2);
        if (exp % 2 == 0)
            return halfExp * halfExp;
        else
            return num * halfExp * halfExp;
    }

    public static int tilingLength(int lenght) {
        if (lenght == 1)
            return 1;
        if (lenght == 0)
            return 1;
        return tilingLength(lenght - 2) + tilingLength(lenght - 1);
    }

    public static StringBuilder removeDuplicateString(StringBuilder str, StringBuilder newStr, int n) {
        if (n == str.length())
            return newStr;
        else if (n == 0) {
            newStr.append(str.charAt(n));
        }
        for (int i = 0; i < newStr.length(); i++) {
            if (str.charAt(n) == newStr.charAt(i))
                break;
            if (i == newStr.length() - 1)
                newStr.append(str.charAt(n));
        }
        return removeDuplicateString(str, newStr, n + 1);
    }

    public static int friendPair(int friend) {
        if (friend == 1)
            return 1;
        else if (friend == 2)
            return 2;
        else
            return friendPair(friend - 1) + (friend - 1) * friendPair(friend - 2);

    }

    public static void printBinaryString(int length, String str, int previous) {
        if (length == 0) {
            System.out.print(str + "  ");
            return;
        }
        printBinaryString(length - 1, str + "0", 0);
        if (previous == 0)
            printBinaryString(length - 1, str + "1", 1);
    }

    public static int stringLength(String str, int i) {
        if (str.isEmpty() == true)
            return i;
        return stringLength(str.substring(1), ++i);
    }

    public static int contiguousSubstring(String str, int lenght) {
        if (lenght == 1)
            return 1;
        if (lenght < 1)
            return 0;
        int count = contiguousSubstring(str.substring(0, str.length() - 1), lenght - 1)
                + contiguousSubstring(str.substring(1, str.length()), lenght - 1)
                - contiguousSubstring(str.substring(1, str.length() - 1), lenght - 2);
        if (str.charAt(0) == str.charAt(str.length() - 1))
            count++;
        return count;
    }

    public static void towerOfHanoi(int n, String start, String mid, String end) {
        if (n == 1) {
            System.out.print(n + "->" + start + " " + end + "  ");
            return;
        }
        towerOfHanoi(n - 1, start, end, mid);
        System.out.print(n + "->" + start + " " + end + "  ");
        towerOfHanoi(n - 1, mid, start, end);
    }
}