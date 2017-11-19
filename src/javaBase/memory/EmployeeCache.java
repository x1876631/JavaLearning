package javaBase.memory;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

/**
 * Created by xuye on 2017年11月19日
 * <p>
 * 软引用实现高速缓存的例子
 * 
 * 【思路】
 * 
 * 获取对象前，先去缓存找一下，如果找到了对象直接返回使用；找不到再创建新对象，并缓存起来方便下次使用。
 * 从打印结果来看，确实是直接从缓存获取了数据，耗时为0，极大的加快了操作速度，提高性能
 * 
 * 【实现方法】
 * 
 * 缓存由hash集合+软引用实现，Hash集合以缓存对象的唯一标识为key，存储缓存对象(软引用)。每次获取缓存对象，就是从软引用里获取对象
 */
public class EmployeeCache {
	private static EmployeeCache cache;
	private Hashtable<String, EmployeeRef> employeeRefs;// 缓存存储器，保存缓存对象唯一标识和对应的软引用
	private ReferenceQueue<Employee> q;// 引用队列，与其绑定的软引用所持有的对象被回收时，jvm会将那个软引用加入到该引用队列里

	/**
	 * Created by xuye on 2017年11月19日
	 * <p>
	 * 一个引用类型是要的缓存对象的软引用，创建该软引用时，保存了一个缓存对象的唯一标识
	 */
	private class EmployeeRef extends SoftReference<Employee> {
		private String _key = "";

		public EmployeeRef(Employee referent, ReferenceQueue<? super Employee> q) {
			super(referent, q);
			_key = referent.getID();
		}
	}

	private EmployeeCache() {
		employeeRefs = new Hashtable<String, EmployeeCache.EmployeeRef>();
		q = new ReferenceQueue<Employee>();
	}

	public static EmployeeCache getInstance() {
		if (cache == null) {
			cache = new EmployeeCache();
		}
		return cache;
	}

	/**
	 * 缓存雇员信息
	 * 
	 * @param em
	 *            要缓存的雇员信息
	 */
	private void cacheEmployee(Employee em) {
		// 缓存前先清掉无用的雇员信息
		cleanCache();
		// 将要缓存的雇员信息，添加上软引用
		EmployeeRef ref = new EmployeeRef(em, q);
		// 把制作好的软引用，添加到缓存集合里
		employeeRefs.put(em.getID(), ref);
	}

	/**
	 * 清除无用的雇员信息
	 */
	private void cleanCache() {
		EmployeeRef ref = null;
		while ((ref = (EmployeeRef) q.poll()) != null) {
			// 当在垃圾队列里发现有雇员对象已经被回收时，将其从缓存集合里清除
			employeeRefs.remove(ref._key);
		}
	}

	public Employee getEmployee(String ID) {
		Employee em = null;
		// 每次获取雇员信息前，先从缓存里找一下
		if (employeeRefs.containsKey(ID)) {
			EmployeeRef ref = employeeRefs.get(ID);
			em = ref.get();
		}

		// 如果从缓存里找不到要获取的对象，则重新创建一下，并保存到缓存里
		if (em == null) {
			em = new Employee(ID);
			System.out.println("获取雇员" + ID + "信息，缓存里找不到，创建一个新的实例");
			this.cacheEmployee(em);
		}
		return em;
	}

	/**
	 * 清除所有缓存
	 */
	public void clearCache() {
		cleanCache();
		employeeRefs.clear();
		System.gc();
		System.runFinalization();
	}

}
