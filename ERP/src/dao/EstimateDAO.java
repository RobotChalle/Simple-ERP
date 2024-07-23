package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.ClientMemberDTO;
import dto.EstimateDTO;

public class EstimateDAO {// ���� �����ؾ��Ѵ�.
	private Connection conn = null;
	private String url = "jbdc:oracle:thin:@localhost:1521:orcl";

	public EstimateDAO() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
		} catch (Exception e) {
			System.out.println("�ε����");
		}
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, "system", "1111");
			return conn;
		} catch (Exception e) {
			System.out.println("�������");
		}
		return null;
	}

	public void add(EstimateDTO temp) {
		if (getConnection() != null) {
			try {
				String sql = "insert into estimate values (?,?,?,?,?,?,?)";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, temp.getEstNum());
				psmt.setString(2, temp.getEstCorp());
				psmt.setString(3, temp.getEstDate());
				for (int i = 0; i < 5; i++) {
					if (temp.getProductName(i) != null) {
						psmt.setString(4, temp.getProductName(i));
						psmt.setInt(5, temp.getQuantity(i));
						psmt.setInt(6, temp.getUnitPrice(i));
						psmt.setInt(7, temp.getSupplyPrice(i));
						psmt.executeUpdate();
						// ������ �������� ��ǰ ���� ���� , ����� �ֹ��� ������ŭ -
						String sql2 = "update stockList set totalQuantity = totalQuantity - ? where productName like ?";
						PreparedStatement psmt1 = conn.prepareStatement(sql2);
						psmt1.setInt(1, temp.getQuantity(i));
						psmt1.setString(2, temp.getProductName(i));
						psmt1.executeUpdate();
					}
				}
			} catch (Exception e) {
				System.out.println("Estimate add ����");
			}
		}
	}

	public ArrayList<EstimateDTO> getList() {
		ArrayList<EstimateDTO> estList = new ArrayList<>();
		if (getConnection() != null) {
			try {
				String sql = "select * from estimate ";
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();

				while (rs.next()) {
					EstimateDTO ed = new EstimateDTO();
					ed.setEstNum(rs.getInt("estNum"));
					ed.setEstCorp(rs.getString("estCorp"));
					ed.setEstDate(rs.getString("estDate"));
					for (int j = 0; j < 5; j++) {
						String productName = rs.getString("productName");
						if (productName != null) {
							ed.setProductName(j, productName);
							ed.setQuantity(j, rs.getInt("quantity"));
							ed.setUnitPrice(j, rs.getInt("unitPrice"));
							ed.setSupplyPrice(j, rs.getInt("supplyPrice"));
						}
					}
					// ������ DTO�� ����Ʈ�� �߰�
					estList.add(ed);
				}
			} catch (Exception e) {
				System.out.println("Estimate getList ����");
			}
		}
		return estList;
	}
}
