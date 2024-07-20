import java.util.*;

public class Basic {
    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        // sumOfFirst_n_NaturalNumbers();
        // palindromCheck();
        // largestOf_n_Numbers();
        // incomeTaxCalculator();
        // binomialCofficient();
        // primeBetween();
        // System.out.println(toBinary(5));
        // System.out.println(toDecimal(111));
        // pattern();
        // int arr[] = {1, 2, 3, 4, 5 };
        // System.out.print(binarySearch(arr, 1, 0, arr.length - 1));
        // reverse(arr);
        // pairMaking(arr);
        // maxSubArray(arr);
        // prefixSubArray(arr);
        // kadaneAlgorithm(arr);
        // waterTraping();
        // stock();
        // bubbleSort();
        // optimisedBubbleSort(arr);
        // selectionSort();
        // insertionSort();
        // spiralMatrix();
        // stariCaseSearch();
        // stringPalindrom();
        // shortestPath();
        // printSubString();
        // stringSort();
        // compressString();
        // caseChange();
        // bitOperation();
        // binaryOddEvenCheck();
        // System.out.println("Get ith Bit: 5, 2 => " + ((5 & 1 << 2) == 0 ? 0 : 1));
        // System.out.println("Set ith Bit: 5, 2 => " + (5 | 1 << 2));
        // System.out.println("Clear ith Bit: 5, 2 => " + (5 & ~(1 << 2)));
        // System.out.println("Is the power of 2: 16? " + ((16 & (16 - 1)) == 0 ? "True"
        // : "False"));
        // binarySwap(4, 5);
        // binaryPow(4, 5);
        // System.out.println((char) ('A' | ' '));
    }

    public static void sumOfFirst_n_NaturalNumbers() {
        int n = sc.nextInt();
        for (int i = n - 1; i > 0; i--)
            n += i;
        System.out.println(n);
    }

    public static void palindromCheck() {
        int revNum = 0, num = sc.nextInt();
        for (int i = num; i != 0; i /= 10)
            revNum = revNum * 10 + i % 10;
        if (num == revNum)
            System.out.println("Palindrom");
        else
            System.out.println("Not Palindrom");
    }

    public static void largestOf_n_Numbers() {
        int n = sc.nextInt();
        int arr[] = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if (max < arr[i])
                max = arr[i];
        }
        System.out.println(max);
    }

    public static void incomeTaxCalculator() {
        int income = sc.nextInt();
        float tax;
        if (income < 5)
            tax = 0;
        else if (income < 10)
            tax = .2f * (income - 5);
        else
            tax = .2f * (income - 5) + .3f * (income - 10);
        System.out.println(tax);
    }

    public static int factorial(int n) {
        if (n <= 1)
            return 1;
        return n * factorial(n - 1);
    }

    public static void binomialCofficient() {
        int n = sc.nextInt();
        int r = sc.nextInt();
        System.out.println(factorial(n) / (factorial(r) * factorial(n - r)));
    }

    public static void primeBetween() {
        int start = sc.nextInt();
        int end = sc.nextInt();
        for (; start <= end; start++) {
            boolean bool = true;
            for (int i = 2; i <= (int) Math.sqrt(start); i++) {
                if (start % i == 0) {
                    bool = false;
                    break;
                }
                if (i == 3)
                    i++;
            }
            if (bool == false)
                ;
            else
                System.out.println(start);
        }
    }

    public static int toBinary(int num) {
        int binary = 0, zero = 0, rev = 0;
        for (; num != 0; num /= 2) {
            if (binary == 0 && num % 2 == 0)
                zero++;
            binary = binary * 10 + num % 2;
        }
        for (; binary != 0; binary /= 10)
            rev = rev * 10 + binary % 10;
        rev *= Math.pow(10, zero);
        return rev;
    }

    public static int toDecimal(int num) {
        int decimal = 0;
        for (int i = 0; num != 0; num /= 10, i++)
            decimal += num % 10 * (int) Math.pow(2, i);
        return decimal;
    }

    public static void pattern() {
        // tgrianglePattern
        for (int i = 0; i < 5; i++) {
            for (int j = 5; j >= i; j--)
                System.out.print(" ");
            for (int j = 0; j <= i; j++)
                System.out.print("* ");
            System.out.println();
        }
        System.out.println();
        // hollowRectangle
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++)
                if (i == 0 || j == 0 || j == 5 || i == 5)
                    System.out.print("* ");
                else
                    System.out.print("  ");
            System.out.println();
        }
        System.out.println();
        // invertedHalfPyramidOfCharacters
        char ch = 'a';
        for (int i = 0; i < 5; i++) {
            for (int j = 5; j > i; j--, ch++)
                System.out.print(ch + " ");
            System.out.println();
        }
        System.out.println();
        // zeroOnePattern
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= i; j++)
                if ((i + j) % 2 == 0)
                    System.out.print(1 + " ");
                else
                    System.out.print(0 + " ");
            System.out.println();
        }
        System.out.println();
        // butterfly
        for (int i = 0; i < 12; i++) {
            if (i <= 6)
                for (int j = 0; j < 12; j++) {
                    if (i >= j)
                        System.out.print("* ");
                    else if (j < 11 - i)
                        System.out.print("  ");
                    else
                        System.out.print("* ");
                }
            else
                for (int j = 0; j < 12; j++) {
                    if (12 - i > j)
                        System.out.print("* ");
                    else if (j < i)
                        System.out.print("  ");
                    else
                        System.out.print("* ");
                }
            System.out.println();
        }
        System.out.println();
        // palindromicPattern
        for (int i = 0; i <= 5; i++) {
            for (int sp = 5; sp > i; sp--)
                System.out.print("  ");
            for (int j = -i; j <= i; j++)
                if (j < 0)
                    System.out.print(-j + " ");
                else if (j == 0)
                    j++;
                else
                    System.out.print(j + " ");
            System.out.println();
        }
    }

    public static void reverse(int arr[]) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static int binarySearch(int arr[], int key, int start, int end) {
        if (arr[start] == key)
            return start;
        if (arr[end] == key)
            return end;
        int mid = (start + end) / 2;
        if (mid == start)
            return -1;
        if (arr[mid] == key)
            return mid;
        else if (arr[mid] > key)
            return binarySearch(arr, key, start + 1, mid - 1);
        else
            return binarySearch(arr, key, mid + 1, end - 1);
    }

    public static void pairMaking(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                System.out.print(arr[i] + "," + arr[j] + "  ");
            }
        }
    }

    public static void maxSubArray(int arr[]) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                if (sum > maxSum)
                    maxSum = sum;
            }
        }
        System.out.println(maxSum);
    }

    public static void prefixSubArray(int arr[]) {
        int prefix[] = new int[arr.length], sum = 0, maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++)
            if (i == 0)
                prefix[i] = arr[i];
            else
                prefix[i] = prefix[i - 1] + arr[i];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (i == 0)
                    sum = prefix[j];
                else
                    sum = prefix[j] - prefix[i - 1];
            }
            if (maxSum < sum)
                maxSum = sum;
        }
        System.out.println(maxSum);
    }

    public static void kadaneAlgorithm(int arr[]) {
        int curr = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (curr + arr[i] < 0)
                curr = curr > arr[i] ? curr : arr[i];
            else
                curr += arr[i];
            if (curr > max)
                max = curr;
        }
        System.out.println(max);
    }

    public static void waterTraping() {
        int n = sc.nextInt();
        int bars[] = new int[n];
        int water[] = new int[n];
        for (int i = 0; i < n; i++)
            bars[i] = sc.nextInt();

        int left[] = new int[n], right[] = new int[n];
        left[0] = bars[0];
        right[n - 1] = bars[n - 1];
        for (int i = 1; i < n; i++) {
            if (bars[i] < left[i - 1])
                left[i] = left[i - 1];
            else
                left[i] = bars[i];
            if (bars[n - i - 1] < right[n - i])
                right[n - i - 1] = right[n - i];
            else
                right[n - i - 1] = bars[n - i - 1];
        }
        for (int i = 0; i < n; i++) {
            water[i] = Integer.min(left[i], right[i]) - bars[i];
            System.out.print(water[i] + " ");
        }
    }

    public static void stock() {
        int n = sc.nextInt();
        int price[] = new int[n];
        int maxProfit = 0;
        for (int i = 0; i < n; i++)
            price[i] = sc.nextInt();
        for (int i = 0; i < price.length; i++) {
            for (int j = i; j < price.length; j++) {
                if (price[j] - price[i] > 0 && price[j] - price[i] > maxProfit)
                    maxProfit = price[j] - price[i];
            }
        }
        System.out.println(maxProfit);
    }

    public static void bubbleSort() {
        int arr[] = { 1, 5, 2, 4, 3 };
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
    }

    public static void optimisedBubbleSort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            boolean bool = false;
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    bool = true;
                }
            }
            if (!bool)
                break;
        }
    }

    public static void selectionSort() {
        int arr[] = { 1, 5, 2, 4, 3 };
        for (int i = 0; i < arr.length; i++) {
            int smallest = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[smallest] > arr[j]) {
                    smallest = j;
                }
            }
            int temp = arr[smallest];
            arr[smallest] = arr[i];
            arr[i] = temp;
        }
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
    }

    public static void insertionSort() {
        int arr[] = { 1, 5, 2, 4, 3 };
        for (int i = 1; i < 5; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[i] < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        for (int i = 0; i < 5; i++)
            System.out.println(arr[i]);
    }

    public static void spiralMatrix() {
        int matrix[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        int rowStart = 0;
        int colStart = 0;
        int rowEnd = matrix.length - 1;
        int colEnd = matrix[0].length - 1;
        while (rowStart != rowEnd || colStart != colEnd) {
            for (int j = colStart; j < colEnd; j++) {
                System.out.print(matrix[rowStart][j] + " ");
            }
            if (colEnd == colStart - 1)
                break;
            for (int i = rowStart; i < rowEnd; i++) {
                System.out.print(matrix[i][colEnd] + " ");
            }
            for (int j = colEnd; j > colStart; j--) {
                System.out.print(matrix[rowEnd][j] + " ");
            }
            for (int i = rowEnd; i > rowStart; i--) {
                System.out.print(matrix[i][colStart] + " ");
            }
            if (rowStart == rowEnd - 1)
                break;
            colStart++;
            colEnd--;
            rowStart++;
            rowEnd--;
        }
    }

    public static void stariCaseSearch() {
        int matrix[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        int key = 1;
        int x = 0, y = matrix.length - 1;
        boolean bool = true;
        while (bool) {
            if (key == matrix[x][y])
                break;
            else if (key > matrix[x][y])
                x++;
            else
                y--;
            if ((x < matrix[0].length && x >= 0) && (y < matrix.length && y >= 0))
                continue;
            else
                bool = false;
        }
        System.out.println(bool);
    }

    public static void stringPalindrom() {
        String str = new String(sc.nextLine());
        boolean bool = true;
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i))
                bool = false;
        }
        if (bool)
            System.out.println("Palindrom");
        else
            System.out.println("Not Palindrom");
    }

    public static void shortestPath() {
        String str = "NEWSNEWSEWNE";
        int x = 0, y = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'E')
                x--;
            if (str.charAt(i) == 'W')
                x++;
            if (str.charAt(i) == 'N')
                y++;
            if (str.charAt(i) == 'S')
                y--;
        }
        System.out.println(Math.hypot(x, y));
    }

    public static void printSubString() {
        char ch[] = sc.next().toCharArray();
        for (int i = 0; i < ch.length; i++) {
            for (int j = i; j < ch.length; j++) {
                for (int k = i; k <= j; k++)
                    System.out.print(ch[k]);
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static void stringSort() {
        String str[] = { "Baa", "Aaa", "Bbb", "Abc" };
        for (int i = 0; i < str.length; i++) {
            for (int j = i; j < str.length; j++) {
                if (str[i].compareTo(str[j]) > 0) {
                    String temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                }
            }
        }
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
    }

    public static void compressString() {
        StringBuilder str = new StringBuilder("abcddddde");
        StringBuilder comp = new StringBuilder();
        comp.append(str.charAt(0));
        for (int i = 0; i < str.length() - 1; i++) {
            int repeat = 1;
            while (str.charAt(i + 1) == str.charAt(i)) {
                repeat++;
                i++;
            }
            if (repeat != 1)
                comp.append(Integer.toString(repeat));
            comp.append(str.charAt(i + 1));
        }
        System.out.println(comp);
    }

    public static void caseChange() {
        StringBuilder str = new StringBuilder("hi, hello world");
        for (int i = 0; i < str.length(); i++) {
            if (i == 0)
                str.setCharAt(0, Character.toUpperCase(str.charAt(0)));
            else if (str.charAt(i - 1) == ' ')
                str.setCharAt(i, Character.toUpperCase(str.charAt(i)));
        }
        System.out.println(str);
    }

    public static void bitOperation() {
        System.out.println("4 & 5 => " + (4 & 5) + ", " + binaryAnd(4, 5));
        System.out.println("4 | 5 => " + (4 | 5) + ", " + binaryOr(4, 5));
        System.out.println("4 ^ 5 => " + (4 ^ 5) + ", " + binaryXor(4, 5));
        System.out.println("~4    => " + ~4 + ", " + ((4 + 1) * (-1)));
        System.out.println("4<< 1 => " + (4 << 1) + ", " + toDecimal(toBinary(4) * (int) Math.pow(10, 1)));
        System.out.println("4>> 1 => " + (4 >> 1) + ", " + toDecimal(toBinary(4) / (int) Math.pow(10, 1)));
    }

    public static int binaryAnd(int x, int y) {
        int result = 0;
        x = toBinary(x);
        y = toBinary(y);
        for (int i = 0; y != 0 && x != 0; i++, x /= 10, y /= 10)
            if (x % 10 == 1 && y % 10 == 1)
                result += Math.pow(10, i);
        result = toDecimal(result);
        return result;
    }

    public static int binaryOr(int x, int y) {
        int result = 0;
        x = toBinary(x);
        y = toBinary(y);
        for (int i = 0; y != 0 && x != 0; i++, x /= 10, y /= 10)
            if (x % 10 == 1 || y % 10 == 1)
                result += Math.pow(10, i);
        result = toDecimal(result);
        return result;
    }

    public static int binaryXor(int x, int y) {
        int result = 0;
        x = toBinary(x);
        y = toBinary(y);
        for (int i = 0; y != 0 && x != 0; i++, x /= 10, y /= 10)
            if (x % 10 != y % 10)
                result += Math.pow(10, i);
        result = toDecimal(result);
        return result;
    }

    public static void binaryOddEvenCheck() {
        int num = sc.nextInt();
        System.out.println(((num & 1) == 0) ? "even " : "odd ");
    }

    public static void binarySwap(int num1, int num2) {
        System.out.println(num1 + " " + num2);
        num1 = num1 ^ num2;
        num2 = num1 ^ num2;
        num1 = num1 ^ num2;
        System.out.println(num1 + " " + num2);
    }

    public static void binaryPow(int a, int n) {
        int result = 1;
        for (; n != 0; n = n >> 1, a *= a)
            result *= ((n & 1) == 0) ? 1 : a;
        System.out.println(result);
    }
}