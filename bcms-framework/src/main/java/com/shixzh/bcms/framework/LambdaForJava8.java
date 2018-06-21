package com.shixzh.bcms.framework;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.shixzh.bcms.po.Customer;

/**
 * Java8 lambda expression used to transfer list to map.
 *
 */
public class LambdaForJava8 {

	public static void main(String[] args) {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1l, "zs"));
		customers.add(new Customer(2l, "zq"));
		customers.add(new Customer(3l, "zq"));
		Map<Long, String> map = getIdNameMap(customers);
		printMap(map);

		Map<Long, Customer> map2 = getIdCustomerMap(customers);
		printMap(map2);

		Map<Long, Customer> map3 = getIdCustomerMapByFunction(customers);
		printMap(map3);

		Map<String, Customer> map4 = getNameCustomerMapByFunction(customers);
		printMap(map4);

		Map<String, Customer> map5 = getNameCustomerMapByFunction(customers);
		printMap(map5);

		Map<String, Customer> map6 = getNameCustomerMap(customers);
		printMap(map6);
	}

	public static <K, V> void printMap(Map<K, V> map) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			System.out.println("key=" + entry.getKey() + ", value=" + entry.getValue());
		}
		System.out.println("---------------------------------------------");
	}

	public static Map<Long, String> getIdNameMap(List<Customer> list) {
		return list.stream().collect(Collectors.toMap(Customer::getId, Customer::getName));
	}

	// 收集成实体本身map
	public static Map<Long, Customer> getIdCustomerMap(List<Customer> list) {
		return list.stream().collect(Collectors.toMap(Customer::getId, Customer -> Customer));
	}

	// Function接口中的一个静态方法代替Customer -> Customer，使整个方法更简洁优雅
	public static Map<Long, Customer> getIdCustomerMapByFunction(List<Customer> list) {
		return list.stream().collect(Collectors.toMap(Customer::getId, Function.identity()));
	}

	// name是有可能重复的。toMap有个重载方法，可以传入一个合并的函数来解决key冲突问题
	public static Map<String, Customer> getNameCustomerMapByFunction(List<Customer> list) {
		// 这里只是简单的使用后者覆盖前者来解决key重复问题。
		return list.stream().collect(Collectors.toMap(Customer::getName, Function.identity(), (key1, key2) -> key2));
	}

	// 指定一个Map的具体实现，来收集数据
	public static Map<String, Customer> getNameCustomerMap(List<Customer> list) {
		// LinkedHashMap可以按照插入顺序输出
		return list.stream().collect(
				Collectors.toMap(Customer::getName, Function.identity(), (key1, key2) -> key2, LinkedHashMap::new));
	}

}
