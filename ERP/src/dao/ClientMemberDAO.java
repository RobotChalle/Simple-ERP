package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.ClientMemberDTO;

public class ClientMemberDAO {
	private Connection conn = null;
	private String url = "jbdc:oracle:thin:@localhost:1521:orcl";

	public ClientMemberDAO() {
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
	
	public ClientMemberDTO login(String id,String pw){
		ClientMemberDTO m = new ClientMemberDTO();
		if(getConnection()!=null) {
			try {
				String sql = "select * from clientMember where id = ? ";
				ResultSet rs = null;
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					ClientMemberDTO cd = new ClientMemberDTO();
					cd.setId(rs.getString("id"));
					cd.setPw(rs.getString("pw"));
					cd.setCorp(rs.getString("corp"));
					m = cd;
				}
			}catch(Exception e) {
				System.out.println("쿼리 실패");
			}
		}
		return m;
	}
}