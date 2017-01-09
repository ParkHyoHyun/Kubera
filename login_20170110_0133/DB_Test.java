package login_20170110_0133;

import java.sql.*;

public class DB_Test {
	public static void main(String[] args) throws Exception {
		String jdbcdrive = "com.mysql.jdbc.Driver";  // 드라이버 설정 Driver  D << 대문자로할것
		String jdbcur1 = "jdbc:mysql://localhost:3306/kuberadb?autoReconnect=true&useSSL=false";
									 // 주소       /데이터베이스이름    /? autoReconnect=true&useSSL=false 경고창 안뜨게하기
		Connection conn = null;
		PreparedStatement psmt = null;
		
		ResultSet rs = null;
		String sql = "Select * from company_account";
		// 
		try {
			Class.forName(jdbcdrive);
			
			conn = (Connection) DriverManager.getConnection(jdbcur1, "root", "root");
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			System.out.println("ID\tPassword\tPermission\tUN\tCN\tBN\t\tCA\tDN\tPosition");
			System.out.println("-------------------------------------------------------------------------"+"---------------------------");
			
			while (rs.next()) {
				System.out.print(rs.getString("ID") + "\t");
				System.out.print(rs.getString("Password") + "\t\t");
				System.out.print(rs.getLong("Permission")+ "\t\t");
				System.out.print(rs.getString("UN") + "\t");
				System.out.print(rs.getString("CN") + "\t");
				System.out.print(rs.getString("BN") + "\t");
				System.out.print(rs.getString("CA") + "\t");
				System.out.print(rs.getString("DN") + "\t");
				System.out.print(rs.getString("Position") + "\n");
				
			}
		
			
		} catch (Exception e) {
			System.out.println("연결실패");
			e.printStackTrace();
			
		}
		finally { //try 일부  (마지막에 무조건 실행)
			try {
				if (rs != null) {
					rs.close();
				}
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
