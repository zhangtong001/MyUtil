package myBatisTest;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionFactory {

	static SqlSessionFactory sqlSessionFactory = null;
	static String resource = "mybatis-config.xml";
	static InputStream inputStream = null;

	public static SqlSessionFactory getFactory() {

		try {
			inputStream = MySqlSessionFactory.class.getClassLoader().getResourceAsStream(resource);
			// inputStream = Resources.getResourceAsStream(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (sqlSessionFactory == null) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}
		return sqlSessionFactory;
	}

	public static SqlSession getSession() {
		return getFactory().openSession();
	}

}
