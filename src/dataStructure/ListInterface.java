package dataStructure;

/**
 * created by xuye on 2017年12月7日
 * 
 * 列表通用抽象操作接口，包括插入、删除、读取、获取列表长度、判断是否为空
 */
public interface ListInterface<T> {

	/**
	 * 获取列表长度
	 * 
	 * @return 列表元素个数
	 */
	int size();

	/**
	 * 判断列表是否为空
	 * 
	 * @return true，列表为空
	 */
	boolean isEmpty();

	/**
	 * 插入元素到指定位置
	 * 
	 * @param index
	 *            元素要插入的位置
	 * @param object
	 *            待插入的元素
	 * @throws Exception
	 *             Exception
	 */
	// void insert(int index, T object) throws Exception;

	/**
	 * 向列表添加元素
	 * 
	 * @param object
	 *            待添加的元素
	 */
	void add(T object);

	/**
	 * 删除指定位置的元素
	 * 
	 * @param index
	 *            待删除元素的位置
	 * 
	 * @throws Exception
	 *             Exception
	 */
	// void delete(int index) throws Exception;

	/**
	 * 获取指定位置的元素
	 * 
	 * @param index
	 *            指定位置
	 * @return 要获取的元素
	 * @throws Exception
	 *             Exception
	 */
	T get(int index) throws Exception;

}
