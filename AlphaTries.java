import java.util.*;

public class AlphaTries {
    public static class Tries {
        Tries[] child;
        boolean isEnd;

        Tries() {
            child = new Tries[26];
            isEnd = false;
        }

        void add(String str) {
            Tries root = this;
            for (char c : str.toCharArray()) {
                int idx = c - 'a';
                if (root.child[idx] == null)
                    root.child[idx] = new Tries();
                root = root.child[idx];
                root.freq++;
            }
            root.isEnd = true;
        }

        void addAll(String arr[]) {
            for (String str : arr)
                this.add(str);
        }

        String print() {
            String str = "";
            if (isEnd)
                str += '*';
            for (int i = 0; i < 26; i++) {
                if (child[i] != null) {
                    str += (char) (i + 'a');
                    str += child[i].print();
                }
            }
            return str;
        }

        boolean contains(String key) {
            Tries temp = this;
            for (char c : key.toCharArray()) {
                int idx = c - 'a';
                if (temp.child[idx] == null)
                    return false;
                else
                    temp = temp.child[idx];
            }
            return temp.isEnd;
        }

        boolean wordBreak(String key) {
            if (key.length() == 0)
                return true;
            for (int i = 0; i < key.length() + 1; i++) {
                if (this.contains(key.substring(0, i)))
                    if (this.wordBreak(key.substring(i)))
                        return true;
            }
            return false;
        }

        int freq = 0;

        List<String> prefix(List<String> lst, String str) {
            for (int i = 0; i < 26; i++) {
                if (this.child[i] == null)
                    continue;

                if (this.child[i].isEnd && child[i].freq > 1)
                    lst.add(str + (char) (i + 'a'));
                if (child[i].freq > 1)
                    child[i].prefix(lst, str + ((char) (i + 'a')));
                else
                    lst.add(str + (char) (i + 'a'));
            }
            return lst;
        }

        boolean startsWith(String key) {
            Tries temp = this;
            for (char c : key.toCharArray()) {
                int idx = c - 'a';
                if (temp.child[idx] == null)
                    return false;
                else
                    temp = temp.child[idx];
            }
            return true;
        }
    }

    public static int uniqueSubstring(String str) {
        Tries temp = new Tries();
        for (int i = 0; i < str.length(); i++) {
            temp.add(str.substring(i));
        }
        return countNode(temp);
    }

    public static int countNode(Tries temp) {
        if (temp == null)
            return 0;
        int count = 1;
        for (int i = 0; i < 26; i++)
            if (temp.child[i] == null)
                continue;
            else
                count += countNode(temp.child[i]);
        return count;
    }

    static String ans = "";

    static String longestPrefix(Tries root, String str) {
        for (int i = 0; i < 26; i++) {
            if (root.child[i] != null && root.child[i].isEnd) {
                if (str.length() + 1 > ans.length())
                    ans = str + (char) (i + 'a');
                longestPrefix(root.child[i], str + (char) (i + 'a'));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Tries tries = new Tries();
        tries.addAll(new String[] { "the", "a", "their", "any", "thee", "there" });
        System.out.println(tries.print());
        System.out.println(tries.contains("thee"));
        System.out.println(tries.wordBreak("aanythee"));
        System.out.println(tries.prefix(new ArrayList<>(), ""));
        System.out.println(tries.startsWith("thei"));
        System.out.println(uniqueSubstring("ababc"));
        System.out.println(longestPrefix(tries, ""));
        System.out.println(groupAnagram(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" }));
    }

    public static class GroupAnagram {
        List<String> list;
        HashMap<Character, GroupAnagram> child;
        boolean isEnd;

        GroupAnagram() {
            list = new ArrayList<>();
            child = new HashMap<>();
            isEnd = false;
        }

        void add(String str) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            GroupAnagram temp = this;
            for (char ch : charArray) {
                temp.child.putIfAbsent(ch, new GroupAnagram());
                temp = temp.child.get(ch);
            }
            temp.isEnd = true;
            temp.list.add(str);
        }

        List<List<String>> groupAnagram(List<List<String>> res) {
            for (char ch : this.child.keySet()) {
                if (this.child.get(ch).list != null && !this.child.get(ch).list.isEmpty())
                    res.add(this.child.get(ch).list);
                this.child.get(ch).groupAnagram(res);
            }
            return res;
        }
    }

    public static List<List<String>> groupAnagram(String[] list) {
        List<List<String>> ans = new ArrayList<>();
        GroupAnagram root = new GroupAnagram();
        for (String str : list)
            root.add(str);
        root.groupAnagram(ans);
        return ans;
    }
}