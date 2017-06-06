package myUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ObjectMapping {

	public static <T> ArrayList<T> setCollection(Class<T> c, ResultSet rs) {
		ArrayList<T> arrayList = new ArrayList<T>();

		// 对象数据的封装
		Field[] fs = c.getDeclaredFields();
		Method[] ms = c.getDeclaredMethods();

		// 循环rs从其中取出对应的数据，然后把数据通过ms的invoke封装给T的集合
		try {
			while (rs.next()) {
				T newInstance = c.newInstance();
				for (int i = 0; i < fs.length; i++) {
					String name = fs[i].getName();
					Object object_column = rs.getObject(name);
					for (int j = 0; j < ms.length; j++) {
						if (ms[j].getName().toLowerCase().equals("set" + name)) {
							ms[j].invoke(newInstance, object_column);
						}
					}
				}
				arrayList.add(newInstance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
}
