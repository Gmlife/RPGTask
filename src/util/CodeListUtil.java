package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeListUtil {
    public static List<Integer> codeToList(String code) {
        if (code.equals("[]")) {
            return new ArrayList<>();
        }
        int start = code.lastIndexOf("[");
        int end = code.lastIndexOf("]");
        String site_str = code.substring(start + 1, end);
        String[] sites = site_str.split(", ");
        Integer[] arr = new Integer[sites.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(sites[i]);
        }
        return new ArrayList<>(Arrays.asList(arr));
    }
}
