package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.EstimateDTO;
import dto.OrderDTO;

public class OrderDAO {
	private Connection conn = null;
	private String url = "jbdc:oracle:thin:@localhost:1521:orcl";

	public OrderDAO() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
		} catch (Exception e) {
			System.out.println("로드실패");
		}
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, "system", "1111");
			return conn;
		} catch (Exception e) {
			System.out.println("연결실패");
		}
		return null;
	}

	public ArrayList<OrderDTO> getList() {
		ArrayList<OrderDTO> orderList = new ArrayList<>();
		if (getConnection() != null) {
			try {
				String sql = "select * from orderlist";
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					OrderDTO od = new OrderDTO();
					od.setOrderNum(rs.getInt("orderNum"));
					od.setTradCorpName(rs.getString("tradCorpName"));
					od.setOrderDate(rs.getString("orderDate"));
					for (int j = 0; j < 5; j++) {
						String productName = rs.getString("productName");
						if (productName != null) {
							od.setProductName(j, productName);
							od.setQuantity(j, rs.getInt("quantity"));
							od.setPrice(j, rs.getInt("price"));
							od.setTotalPrice(j, rs.getInt("totalPrice"));
						}
					}
					orderList.add(od);
				}

			} catch (Exception e) {
				System.out.println("쿼리 실패");
			}
		}
		return orderList;
	}

	public void add(OrderDTO temp) {
		if (getConnection() != null) {
			try {
				String sql1 = "insert into orderlist values (?,?,?,?,?,?,?)";
				PreparedStatement psmt1 = conn.prepareStatement(sql1);
				psmt1.setInt(1, temp.getOrderNum());
				psmt1.setString(2, temp.getTradCorpName());
				psmt1.setString(3, temp.getOrderDate());
				for (int i = 0; i < 5; i++) {
					if (temp.getProductName(i) != null) {
						psmt1.setString(4, temp.getProductName(i));
						psmt1.setInt(5, temp.getQuantity(i));
						psmt1.setInt(6, temp.getPrice(i));
						psmt1.setInt(7, temp.getQuantity(i)*temp.getPrice(i));
						psmt1.executeUpdate();
						// 다음은 재고관리에 상품을 추가하는 코드
						if (dup(temp.getProductName(i))) {// 중복된 상품명이 존재하면 이미 있는값에 개수만 추가해준다.
							String sql2 = "update stockList set totalQuantity = totalQuantity + ? where productName like ?";
							PreparedStatement psmt2 = conn.prepareStatement(sql2);
							psmt2.setInt(1, temp.getQuantity(i));
							psmt2.setString(2, temp.getProductName(i));
							psmt2.executeUpdate();
						} else {// 중복된 상품명이 없으면 새로 추가한다.
							String sql3 = "insert into stockList values (?,null,?,?)";
							PreparedStatement psmt3 = conn.prepareStatement(sql3);
							psmt3.setString(1, temp.getProductName(i));
							psmt3.setString(2, temp.getTradCorpName());
							psmt3.setInt(3, temp.getQuantity(i));
							psmt3.executeUpdate();
						}
					}
				}

			} catch (Exception e) {
				System.out.println("OrderAdd실패");
			}
		}

	}

	public boolean dup(String name) {
		int a = 0;
		if (getConnection() != null) {
			try {
				String sql = "select productName from stockList where productName like ? ";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, name);
				a = psmt.executeUpdate();
			} catch (Exception e) {
			}
		}
		if (a == 0) {
			return false;
		}
		return true;
	}
}
