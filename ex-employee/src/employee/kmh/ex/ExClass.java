package employee.kmh.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import employee.kmh.conn.DBConnection;

public class ExClass {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void deleteAll() {
		System.out.println("===== DB 테이블 확인=======");
		String sql = "DELETE from employee";

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			int rows = pstmt.executeUpdate();
			System.out.println("-------- 모든행: " + rows + "행이 삭제됨-----");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 깔끔하게 자원 해제
			DBConnection.close(pstmt, conn);
		}

	}

	public void setAuto_Increment() {
		System.out.println("==============================");
		String sql = "Alter table Employee auto_increment=1";

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			int rows = pstmt.executeUpdate();
			System.out.println("--------"+ "Auto_incerment = 1로 세팅됨" +"-----");

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//깔끔하게 자원 해제
			DBConnection.close(pstmt, conn);
		}

	}

	public void selectAll() {
		System.out.println("==============================");
		String sql ="";
		
	}

	public void insertEmployee(String EmpName, String Dept, String HireDate, int Salary) {
		
		// (1) 사원 등록 : Employee 테이블에 다음 테이터를 삽입하시오.		
		String sql = "INSERT INTO employee(EmpName,Dept,HireDate,Salary) VALUES\r\n"
				+ "(?,?,?,?)";
		

		try {
			conn=DBConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,EmpName);
			pstmt.setString(2,Dept);
			pstmt.setString(3,HireDate);
			pstmt.setInt(4,Salary);			
			int rows = pstmt.executeUpdate();
			System.out.println("---------------------------------- "+rows + "행이 추가되었습니다.");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
	}

}
