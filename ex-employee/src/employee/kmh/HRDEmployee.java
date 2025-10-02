package employee.kmh;

import employee.kmh.ex.ExClass;

public class HRDEmployee {

	public static void main(String[] args) {
		
		ExClass exClass = new ExClass();
		
		//사원 테이블 데이터 전체 삭제
		exClass.deleteAll();
		exClass.setAuto_Increment();
		exClass.selectAll();

// (1)사원 등록 : Employee 테이블에 다음 데이터를 삽입하시오.
		exClass.insertEmployee("홍길동","영업부","2020-03-01",2500000);
		exClass.insertEmployee("이순신","인사부","2019-07-15",3200000);
		exClass.insertEmployee("강감찬","개발부","2021-01-10",2800000);
		
		
	}

}
