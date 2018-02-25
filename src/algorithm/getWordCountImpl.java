package algorithm;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xuye on 2018年2月25日
 * <p>
 * 统计一段英文每个单词出现的次数，参考：http://blog.csdn.net/qq_14979657/article/details/61423179
 */
public class getWordCountImpl {

	public static void main(String[] args) {
		String word = "aB,e c...1d%$^~E!@";
		getWordCount(word);
	}

	private static void getWordCount(String word) {
		// 使用正则表达式匹配所有的单词
		String reg = "[a-zA-Z]+";
		word = word.toLowerCase();
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(word);

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int wordCount = 0;// 单词的总个数
		while (m.find()) {
			// 不断检查匹配结果
			String result = m.group();
			// System.out.println("找到单词：" + result);
			wordCount++;
			if (!map.containsKey(result)) {
				// 没遇到过该单词，记录一下
				map.put(result, 1);
			} else {
				// 之前已经遇到过该单词了，次数+1
				int resultCount = map.get(result);
				map.put(result, resultCount + 1);
			}
		}

		System.out.println("单词总数：" + wordCount);
		System.out.println("单词出现频率：" + map);
	}
}
