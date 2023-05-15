import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.io.InputStream;

public class Main {
    // Возвращает отсортированный по возрастанию список. Входные списки должны быть отсортированы по возрастанию.
    public static List<Integer> concat(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<Integer>();

        if (a.size() == 0) {
            Collections.copy(result, b);
            return result;
        }
        if (b.size() == 0) {
            Collections.copy(result, a);
            return result;
        }

        int i = 0;
        int j = 0;
        while (i + j < a.size() + b.size()) {
            if (i == a.size()) {
                while (j != b.size()) {
                    result.add(b.get(j));
                    ++j;
                }
                break;
            }

            if (j == b.size()) {
                while (i != a.size()) {
                    result.add(a.get(i));
                    ++i;
                }
                break;
            }

            if (a.get(i) < b.get(j)) {
                result.add(a.get(i));
                ++i;
            } else {
                result.add(b.get(j));
                ++j;
            }
        }

        return result;
    }

    // возвращает true, если src полностью содержится в dest, иначе false. списки должны быть отсортированы по возрастанию
    public static boolean contains(List<Integer> src, List<Integer> dest) {
        if (src.size() > dest.size()) {
            return false;
        }

        if (src.size() == 0) {
            return true;
        }

        int i = 0;
        int j = 0;
        while (i != src.size()) {
            if (j == dest.size()) {
                break;
            }

            if (dest.get(j) < src.get(i)) {
                ++j;
                continue;
            }

            if (dest.get(j) == src.get(i)) {
                ++i;
                ++j;
                continue;
            }

            if (dest.get(j) > src.get(i)) {
                break;
            }
        }

        return i == src.size();
    }

    public static void main(String[] args) {
        InputStream inFile;
        try {
            inFile = new FileInputStream(new File(args[0]));
        }
        catch (Throwable ex) {
            System.out.println(ex.getMessage());
            return;
        }

        Scanner scanner = new Scanner(inFile).useDelimiter("\\A");
        if (!scanner.hasNext()) {
            System.out.println("file is empty");
            return;
        }

        String[] split = scanner.next().split(" ");

        if (split.length <= 2) {
            return;
        }

        List<Integer> nums = new ArrayList<Integer>();
        for (String sym : split) {
            nums.add(Integer.parseInt(sym));
        }

        Collections.sort(nums);

        while (nums.get(0) == 1) {
            nums.remove(0);
        }

        Integer[] arr = new Integer[nums.size()];
        int i = 0;
        for (Integer el : nums) {
            arr[i] = el;
            ++i;
        }

        List<Set<List<Integer>>> mainList = new ArrayList<Set<List<Integer>>>();

        for (i = 0; i < arr.length; ++i) {
            if (i == 0) {
                List<Integer> prod = new ArrayList<Integer>();
                prod.add(arr[0]);
                Set<List<Integer>> prods = new HashSet<List<Integer>>();
                prods.add(prod);
                mainList.add(prods);
                continue;
            }
            if (i == 1) {
                List<Integer> prod = new ArrayList<Integer>();
                prod.add(arr[1]);
                Set<List<Integer>> prods = new HashSet<List<Integer>>();
                prods.add(prod);
                mainList.add(prods);
                continue;
            }
            if (arr[i] == arr[i - 1]) {
                mainList.add(mainList.get(i - 1));
                continue;
            }

            List<Integer> prod = new ArrayList<Integer>();
            prod.add(arr[i]);
            Set<List<Integer>> prods = new HashSet<List<Integer>>();
            prods.add(prod);

            for (int j = 1; j < i; ++j) {
                for (int k = 0; k < j; ++k) {
                    if (arr[j] * arr[k] != arr[i])
                        continue;
                    Set<List<Integer>> jSet = mainList.get(j);
                    Set<List<Integer>> kSet = mainList.get(k);

                    for (List<Integer> jProd : jSet) {
                        for (List<Integer> kProd : kSet) {
                            List<Integer> candidate = concat(jProd, kProd);
                            if (contains(candidate, nums)) {
                                prods.add(candidate);
                            }
                        }
                    }
                }
            }

            mainList.add(prods);
        }

        for (int j = 0; j < mainList.size(); ++j) {
            if (j != 0 && arr[j - 1] == arr[j])
                continue;

            Set<List<Integer>> products = mainList.get(j);
            if (products.size() == 1)
                continue;


            for (List<Integer> product : products) {
                if (product.size() == 1)
                    continue;
                System.out.print(arr[j] + " = ");
                for (int k = 0; k < product.size(); ++k) {
                    System.out.print(product.get(k));
                    if (k != product.size() - 1) {
                        System.out.print(" * ");
                    }
                }
                System.out.print('\n');
            }
        }
    }
}
