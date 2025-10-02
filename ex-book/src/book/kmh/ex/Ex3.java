package book.kmh.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import book.kmh.conn.DBConnection;

public class Ex3 {

	private Connection conn;

	public void result() {
	
		
		selectTable();
	}

	private void selectTable() {
		System.out.println("===== DB 테이블 확인=======");
		String sql = "SELECT  r.RentDate AS 대출일자, r.ReturnDate AS 반납일자, b.Title AS 책제목 , b.Price AS 책가격\r\n"
				+ "FROM rental AS r\r\n"
				+ "INNER JOIN book AS b\r\n"
				+ "ON r.BookID=b.BookID\r\n"
				+ "WHERE r.ReturnDate IS NULL ";
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
					String 대출일자 = rs.getString("대출일자");
					String 반납일자 = rs.getString("반납일자");
					String 책제목 = rs.getString("책제목");
					String 책가격 = rs.getString("책가격");
//					System.out.println("책ID :" +  BookID + ",책제목:" + Title + ",출판년도:" + PubYear);
					
					System.out.println("대출일자 :"+ 대출일자 + "\t,  반납일자 :" + 반납일자 + "\t,   책제목 : " +  책제목  + "\t,책가격 :"  + 책가격+"\n");
				} while (rs.next());
				System.out.println("------------("+"미반납한 도서 목록 :"+ rowCount +"건" +")---------------");
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, pstmt, conn);
		}
	
	}

}