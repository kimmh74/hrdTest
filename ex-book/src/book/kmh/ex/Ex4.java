package book.kmh.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import book.kmh.conn.DBConnection;

public class Ex4 {

	private Connection conn;

	public void result() {
	
		
		selectTable();
	}

	private void selectTable() {
		System.out.println("===== DB 테이블 확인=======");
		String sql = "SELECT b.BookID as 책ID, b.Title AS 책제목, COUNT(RentDate) AS 대출횟수\r\n"
				+ "FROM rental AS r\r\n"
				+ "INNER JOIN book AS b \r\n"
				+ "ON r.BookID = b.BookID\r\n"
				+ "GROUP BY b.BookID, b.Title\r\n"
				+ "ORDER BY 책ID ";
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
					String 책ID = rs.getString("책ID");
					String 책제목 = rs.getString("책제목");
					String 대출횟수 = rs.getString("대출횟수");			
//					System.out.println("책ID :" +  BookID + ",책제목:" + Title + ",출판년도:" + PubYear);
					
					System.out.println("책ID :"+ 책ID + "\t,  책제목 :" + 책제목 + "\t,   대출횟수 : " +  대출횟수 +"\n");
				} while (rs.next());
				System.out.println("------------("+"도서별 대출 횟수 :"+ rowCount +"건" +")---------------");
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, pstmt, conn);
		}
	
	}

}