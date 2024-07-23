package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.EstimateDTO;
import dto.OrderDTO;
import dto.StockDTO;
import erp.StockList;

public class StatisticDAO {
	private Connection conn = null;
	private String url = "jbdc:oracle:thin:@localhost:1521:orcl";

	public StatisticDAO() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
		} catch (Exception e) {
			System.out.println("로드 실패");
		}
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, "system", "1111");
			return conn;
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		return null;
	}

	public ArrayList<StockDTO> getList() {
		ArrayList<StockDTO> StockList = new ArrayList<>();
		if (getConnection() != null) {
			try {
				String sql = "select * from stockList";
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					StockDTO sd = new StockDTO();
					sd.setProductName(rs.getString("productName"));
					sd.setLoc(rs.getString("loc"));
					sd.setProducCorpName(rs.getString("producCorpName"));
					sd.setTotalQuantity(rs.getInt("totalQuantity"));
					StockList.add(sd);
				}

			} catch (Exception e) {
				System.out.println("getList 실패");
			}
		}
		return StockList;
	}
	
	public ArrayList<EstimateDTO> getEstList(){
		ArrayList<EstimateDTO> estList = new ArrayList<>();
		if (getConnection() != null) {
			try {
				String sql = "select * from estimate";
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					int i = 0;
					EstimateDTO ed = new EstimateDTO();
					ed.setEstDate(rs.getString("estDate"));
					ed.setQuantity(i, rs.getInt("quantity"));
					ed.setSupplyPrice(i,rs.getInt("supplyPrice"));
					ed.setUnitPrice(i,rs.getInt("unitPrice"));
					estList.add(ed);
					i++;
				}

			} catch (Exception e) {
				System.out.println("getEstList 실패");
			}
		}
		return estList;
	}
	public ArrayList<OrderDTO> getOrderList(){
		ArrayList<OrderDTO> orderList = new ArrayList<>();
		if (getConnection() != null) {
			try {
				String sql = "select * from orderlist";
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					int i = 0;
					OrderDTO od = new OrderDTO();
					od.setOrderDate(rs.getString("orderDate"));
					od.setTotalPrice(i,rs.getInt("totalPrice"));
					orderList.add(od);
					i++;
				}

			} catch (Exception e) {
				System.out.println("getOrderList실패");
			}
		}
		return orderList;
		
	}

}
