package myUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class MyPage {

	public static Map<String, String> page(String pageNo, String select,
			String counts) {

		int pageSize = 5;
		if (null == pageNo || pageNo.equals("")) {
			pageNo = "1";
		}
		int pageNoInt = Integer.parseInt(pageNo);
		int maxNo = pageSize * pageNoInt;
		int minNo = pageSize * (pageNoInt - 1) + 1;

		String sql = "select t2.*,t2.xh from "
				+ "(select t1.*,rownum as xh from " + "(" + select
				+ ") t1) t2 " + "where t2.xh between " + minNo + " and "
				+ maxNo;
		String beforePage = "前一页";
		if (pageNoInt > 1) {
			beforePage = "<a href='http://localhost:8080/a/WorkInfoServlet?pageNo="
					+ (pageNoInt - 1) + "'>前一页</a>";
		}

		int totalPage = 0;
		Connection connection = null;//DBConnectionUtil.getConnection();
		ResultSet rs = null;
		try {
			Statement createStatement = connection.createStatement();
			rs = createStatement.executeQuery(counts);
			rs.next();
			int sum = rs.getInt("counts");

			rs.close();
			createStatement.close();
			connection.close();

			totalPage = (sum % pageSize == 0) ? sum / pageSize : sum / pageSize
					+ 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String afterPage = "后一页";
		if (pageNoInt < totalPage) {
			afterPage = "<a href='http://localhost:8080/a/WorkInfoServlet?pageNo="
					+ (pageNoInt + 1) + "'>后一页</a>";
		}

		String page = beforePage + "   " + afterPage + "   " + pageNo + "/"
				+ totalPage;

		Map map = new HashMap<String, String>();

		map.put("page", page);
		map.put("sql", sql);

		return map;
	}
}
