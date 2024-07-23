package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.OrderDTO;
import dto.StockDTO;

public class StockDAO {
	private Connection conn = null;
	private String url = "jbdc:oracle:thin:@localhost:1521:orcl";

	public StockDAO() {
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
				System.out.println("재고저장실패");
			}
		}
		return StockList;
	}
	
}
