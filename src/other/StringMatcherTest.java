package other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatcherTest {
	public static void main(String[] args) {
		String url = "https://biztest.chunyu.me/api/v4/feedback/detail/?app=0&platform=android&systemVer=7.0&version=8.5.4&app_ver=8.5.4&imei=02%3A00%3A00%3A00%3A00%3A00&device_id=02%3A00%3A00%3A00%3A00%3A00&mac=02%3A00%3A00%3A00%3A00%3A00&secureId=eaf3727c2c43fb2c&installId=1516678230430&phoneType=HUAWEI+NXT-AL10_by_HUAWEI&vendor=chunyu&screen_height=1812&screen_width=1080";
		String regular = "device_id=[a-zA-Z0-9%]+&";
		Pattern p = Pattern.compile(regular);
		Matcher m = p.matcher(url);
		boolean b = m.find();
		System.out.println("url中是否找到了匹配的字符串：" + b);
		if (b) {
			System.out.println("匹配值：" + m.group());
		}
		System.out.println("替换之前的url:" + url);
		url = url.replaceAll(regular, "device_id=123&");
		System.out.println("替换之后的url:" + url);

	}
}
