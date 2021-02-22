package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeListUtil {
    public static List<String> codeToList(String code) {
        int start = code.lastIndexOf("[");
        int end = code.lastIndexOf("]");
        String sites = code.substring(start + 1, end);
        return new ArrayList<>(Arrays.asList(sites.split(", ")));
    }
}
