package javaBase.memory;

public class MyShareObject {
	public static final MyShareObject sharedInstance = new MyShareObject();

	public Integer object2 = new Integer(22);
	public Integer object4 = new Integer(44);

	public long member1 = 12345;
	public long member2 = 67890;
}
