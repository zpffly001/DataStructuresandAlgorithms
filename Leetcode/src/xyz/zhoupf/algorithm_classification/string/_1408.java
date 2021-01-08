package xyz.zhoupf.algorithm_classification.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _1408 {

    public List<String> stringMatching(String[] words) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };
        List<String> list =new ArrayList<>();
        //对字符串数组words进行了自定义排序，按照长度的从小到大进行排序
        Arrays.sort(words,comparator);
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length  ; j++) {
                //如果words[j]中包含words[i]，那么他就是符合条件的字符串，则放入list集合中。
                if(words[j].indexOf(words[i]) != -1) {
                    list.add(words[i]);
                    break;
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {

    }

}
