package thinkingInJava.chapter20;

import java.util.List;

public class PasswordUtils {

	/**
	 * 验证密码
	 */
	@UseCase(id = 47, description = "Passwords must contain at least one numeric")
	public boolean validatePassword(String password) {
		return password.matches("\\w*\\d\\w*");
	}

	/**
	 * 加密，其实就是取反了下密码
	 */
	@UseCase(id = 48)
	public String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}

	/**
	 * 检查新密码
	 */
	@UseCase(id = 49, description = "New passwords can't equal previously used ones")
	public boolean chekForNewPassword(List<String> prevPasswords,
			String password) {
		return !prevPasswords.contains(password);
	}

}
