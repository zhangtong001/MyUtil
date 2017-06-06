package myBatisTest;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import myMapper.Test_Mapper;

public class Test01 {

	public static void main(String[] args) {
		SqlSession session = MySqlSessionFactory.getSession();

		Test_Mapper mapper = session.getMapper(Test_Mapper.class);

		List<Integer> select_class_2 = mapper.select_class_2();

		System.out.println(select_class_2);

	}

}
