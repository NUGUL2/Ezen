package middlego.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconn {
	
	//데이터베이스 연결
	private String url = "jdbc:oracle:thin:@192.168.219.107:1521:xe";
	private String user = "system";
	private String password = "1234";
		
	
	public Connection getConnection () {
		Connection conn = null;
		
		//Class.forName("oracle.jdbc.driver.OracleDriver"); 마우스 올리면 try/catch 하면 자동생성 
		try {	
			//등록한 드라이버 중에 사용 가능한 클래스 찾아서 생성
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//연결 정보를 통해서 연결객체를 참조 변수 conn에 담는다
			conn = DriverManager.getConnection(url, user, password);

			System.out.println("DB연동 성공");
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
		return conn;	

		
	}

	
	
}
