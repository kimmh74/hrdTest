package book.kmh.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import book.kmh.conn.DBConnection;

public class Ex5 {

	private Connection conn;

	public void result() {
	
		
		selectTable();
	}

	private void selectTable() {
		System.out.println("===== DB 테이블 확인=======");
		String sql = "SELECT  b.Title AS 도서명,m.Name AS 회원이름, b.Price AS 책가격\r\n"
				+ "FROM rental r\r\n"
				+ "INNER JOIN book b\r\n"
				+ "ON r.BookID = b.BookID\r\n"
				+ "INNER JOIN member m\r\n"
				+ "ON r.MemberID= m.MemberID\r\n"
				+ "ORDER BY Price DESC\r\n"
				+ "LIMIT 1";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				System.out.println("DB 테이블에 조회된 결과가 없음");
			} else {
				int rowCount = 0;
				do {
					rowCount++;
					String 도서명 = rs.getString("도서명");
					String 책가격 = rs.getString("책가격");		
//					System.out.println("책ID :" +  BookID + ",책제목:" + Title + ",출판년도:" + PubYear);
					
					System.out.println("도서명 :"+ 도서명 + "\t,   책가격 : " +  책가격 +"\n");
					System.out.println("------------("+"가장 비싼도서 :"+ 도서명 +")---------------");
				} while (rs.next());
				
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, pstmt, conn);
		}
	
	}

}