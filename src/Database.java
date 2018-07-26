import java.sql.*;

public class Database {
	static String CUSTOMER_ID,PASSWORD,CUSTOMER_NAME,BIRTH,CUSTOMER_ADDRESS,CUSTOMER_PHONENUMBER;
	static String MANAGER_PASSWORD,MANAGER_ID,MOVIE_ID,TITLE,SCREENING_GRADE,DIRECTOR,APPEARANCE,INFORMATION,RESERVES;
	static String THEATER_NAME,THEATER_ADDRESS,THEATER_PHONENUMBER;
	static String CURRENTS;
	int POINT,PURCHASE_PERFORMANCE,INTERNET_PAY,CASH;
	static int COUNT,SEAT;
	static int MULTIPLEX_NUMBER;
	static String ID,TIME;
	static Db db = new Db();
	
	
	
	
	public String getId(){	return ID;	}
	public String getT(){	return THEATER_ADDRESS; }
	public void setId(String id){ ID = id;}
	//ȸ������
	public static void join(String id,String password,String name,String birth,String address,String phone){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		 String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		 String USER = "TERMP";
		 String PASS = "TERMP";
	Connection conn = null;
	try {
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(URL, USER, PASS);
	} catch(Exception e){
		System.out.println(e.getMessage());;
	}
	try{
		Statement stmt = conn.createStatement();
		String query = "INSERT INTO CUSTOMER VALUES('"+id+"','"+password+"','"+name+"','"+birth+"','"+address+"','"+phone+"',0,'��ȣ��',0)";
		ResultSet rs = stmt.executeQuery(query);
	}catch(Exception e){
		System.out.println(e.getMessage());	
		}
	}
	

	//ȸ�����ԵǾ��ִ� ��Ȳ
	public void customer(){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		 String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		 String USER = "TERMP";
		 String PASS = "TERMP";
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch(Exception e){
			System.out.println(e.getMessage());;
		}
		String CUSTOMER_ID,PASSWORD,CUSTOMER_NAME,BIRTH,CUSTOMER_ADDRESS,CUSTOMER_PHONENUMBER,MANAGER_ID;
		int POINT,PURCHASE_PERFORMANCE;
		try{
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM CUSTOMER";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				CUSTOMER_ID = rs.getString(1);
				PASSWORD = rs.getString(2);
				CUSTOMER_NAME = rs.getString(3);
				BIRTH=rs.getString(4);
				CUSTOMER_ADDRESS=rs.getString(5);
				CUSTOMER_PHONENUMBER=rs.getString(6);
				MANAGER_ID=rs.getString(8);
				PURCHASE_PERFORMANCE=rs.getInt(9);
				POINT=rs.getInt(7);
				System.out.println(CUSTOMER_ID+","+PASSWORD+","+CUSTOMER_NAME+","+BIRTH+","+CUSTOMER_ADDRESS+","
				+CUSTOMER_PHONENUMBER+","+POINT+","+MANAGER_ID+","+PURCHASE_PERFORMANCE);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			}
		}
	
	//�α���
	public static void login(String id,String password){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		 String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		 String USER = "TERMP";
		 String PASS = "TERMP";
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch(Exception e){
			System.out.println(e.getMessage());;
		}
		try{
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM CUSTOMER";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				CUSTOMER_ID = rs.getString(1);
				PASSWORD = rs.getString(2);
				CUSTOMER_ADDRESS=rs.getString(5);
				CUSTOMER_PHONENUMBER=rs.getString(6);
				CUSTOMER_ID = CUSTOMER_ID.replaceAll(" ","");
				PASSWORD = PASSWORD.replaceAll(" ","");
				if((id.equals(CUSTOMER_ID))&&(password.equals(PASSWORD))){
					db.setAddress(CUSTOMER_ADDRESS);
					db.setAddress(CUSTOMER_PHONENUMBER);
					db.setId(id);
					System.out.println("�α��� ����"+db.getId());
					break;
				}else{
					System.out.println("�α��ν���");
					break;
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			}
	}
	//ȸ����������
	public static void change(String id,String password,String passwords,String address,String phone){	
		 String DRIVER = "oracle.jdbc.driver.OracleDriver";
		 String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		 String USER = "TERMP";
		 String PASS = "TERMP";
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch(Exception e){
			System.out.println(e.getMessage());;
		}
		String CUSTOMER_ID,PASSWORD;
		try{
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM CUSTOMER";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				CUSTOMER_ID = rs.getString(1);
				PASSWORD = rs.getString(2);
				ID = CUSTOMER_ID;

				if((id.equals(CUSTOMER_ID))&&(password.equals(PASSWORD))){
					PreparedStatement pstmt = conn.prepareStatement("UPDATE CUSTOMER SET PASSWORD = ? WHERE CUSTOMER_ID =?");
					PreparedStatement pstmt1 = conn.prepareStatement("UPDATE CUSTOMER SET CUSTOMER_ADDRESS = ? WHERE CUSTOMER_ID =?");
					PreparedStatement pstmt2 = conn.prepareStatement("UPDATE CUSTOMER SET CUSTOMER_PHONENUMBER = ? WHERE CUSTOMER_ID =?");
					pstmt.setString(1, passwords);
					pstmt.setString(2, ID);
					pstmt1.setString(1, address);
					pstmt1.setString(2, ID);
					pstmt2.setString(1, phone);
					pstmt2.setString(2, ID);
					int rowCount = pstmt.executeUpdate();
					int rowCount1 = pstmt1.executeUpdate();
					int rowCount2 = pstmt2.executeUpdate();
					if((rowCount == 0)||(rowCount1 == 0)||(rowCount2 == 0)) {
						System.out.println("���� ����");
						break;
					}else{
						System.out.println("���� �Ϸ�");
						break;
					}	
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			}
		}
	//ȸ������
	public static void delete(String id){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		 String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		 String USER = "TERMP";
		 String PASS = "TERMP";
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		try{
			Statement stmts = conn.createStatement();
			String que = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = '"+id+"'";
			stmts.executeUpdate(que);
		}catch(Exception e){
			System.out.println(e.getMessage());		
		}
	}
	//��ȭ��Ʈ
	public static void movies(){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		Connection conn = null; 
		try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM MOVIE ORDER BY RESERVES DESC";
				ResultSet rs = stmt.executeQuery(query);
				System.out.println("��ȭID\t��ȭ�̸�\t���\t����\t�ֿ�\t�帣\t������");
				while(rs.next()){
					MOVIE_ID = rs.getString(1).replaceAll(" ","");
					TITLE = rs.getString(2).replaceAll(" ","");
					SCREENING_GRADE = rs.getString(3).replaceAll(" ","");
					DIRECTOR = rs.getString(4).replaceAll(" ","");
					APPEARANCE = rs.getString(5).replaceAll(" ","");
					INFORMATION = rs.getString(6).replaceAll(" ","");
					RESERVES = rs.getString(7).replaceAll(" ","");
					
					System.out.println(MOVIE_ID+"\t"+TITLE+"\t"+SCREENING_GRADE+"\t"+DIRECTOR+"\t"+APPEARANCE+"\t"
							+INFORMATION+"\t"+RESERVES);
					
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	}
	//�����ο�ȭ
	public static void moviename(){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		Connection conn = null; 
		try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM MULTIPLEX";
				ResultSet rs = stmt.executeQuery(query);

				while(rs.next()){
					TITLE = rs.getString(6).replaceAll(" ","");
					System.out.print(TITLE+"\t");
					
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	}
	//���� ��ȭ�� �󿵰�����
	public static void multiplex(String movie){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		Connection conn = null; 
		String TITLE;
		try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM MULTIPLEX WHERE TITLE ='"+movie+"'";
				ResultSet rs = stmt.executeQuery(query);
				System.out.println("�󿵰���ȣ\t�¼���\t��ȭ���̸�\t��ȭ�ð�\t��ȭ_ID\t��ȭ�̸�");
				while(rs.next()){
					MULTIPLEX_NUMBER = rs.getInt(1);
					COUNT = rs.getInt(2);
					THEATER_NAME = rs.getString(3).replaceAll(" ","");
					TIME = rs.getString(4).replaceAll(" ","");
					MOVIE_ID = rs.getString(5).replaceAll(" ","");
					TITLE = rs.getString(6).replaceAll(" ","");
					System.out.println(MULTIPLEX_NUMBER+"\t"+COUNT+"\t"+THEATER_NAME+"\t"+TIME+"\t"+MOVIE_ID+"\t"+TITLE);
					
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		
	}
	//��ȭ�� ���
	public static void update(String name,String address, String phone){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		Connection conn = null; 
		String TITLE;
		try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt = conn.createStatement();
				String query = "INSERT INTO THEATER VALUES('"+name+"','"+address+"','"+phone+"')";
				ResultSet rs = stmt.executeQuery(query);
		
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	}
	//�ٲ� ��ȭ�� ��ġ
	public static void mname(String names,String address){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		Connection conn = null; 
		String TITLE;
		try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM THEATER";
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
					THEATER_NAME = rs.getString(1);
					THEATER_ADDRESS = rs.getString(2);
					String g = THEATER_NAME.replaceAll(" ", "");
					if((names.equals(g))){
						PreparedStatement pstmt1 = conn.prepareStatement("UPDATE THEATER SET THEATER_ADDRESS = ? WHERE THEATER_NAME =?");
						pstmt1.setString(1, address);
						pstmt1.setString(2, THEATER_NAME);
						
						int rowCount1 = pstmt1.executeUpdate();
						if((rowCount1 == 0)) {
							System.out.println("���� ����");
							break;
						}else{
							System.out.println("���� �Ϸ�");
							break;
						}	
				}
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
				
	}
	//�ٲ� ��ȭ�� ��ȭ��ȣ
	public static void mname2(String names,String phone){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		Connection conn = null; 
		String TITLE;
		try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM THEATER";
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
					THEATER_NAME = rs.getString(1);
					THEATER_PHONENUMBER = rs.getString(3);
					String g = THEATER_NAME.replaceAll(" ", "");
					if((names.equals(g))){
						PreparedStatement pstmt2 = conn.prepareStatement("UPDATE THEATER SET THEATER_PHONENUMBER = ? WHERE THEATER_NAME =?");
						pstmt2.setString(1, phone);
						pstmt2.setString(2, THEATER_NAME);
						int rowCount2 = pstmt2.executeUpdate();
						if((rowCount2 == 0)) {
							System.out.println("���� ����");
							break;
						}else{
							System.out.println("���� �Ϸ�");
							break;
						}	
					}
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
				
	}
	//��ȭ�� ����
	public static void Mdelete(String name){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		 String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		 String USER = "TERMP";
		 String PASS = "TERMP";
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		try{
			Statement stmts = conn.createStatement();
			String que = "DELETE FROM THEATER WHERE THEATER_NAME = '"+name+"'";
			stmts.executeUpdate(que);
		}catch(Exception e){
			System.out.println(e.getMessage());		
		}
	}
	//��ȭ ����
	public static void mupdate(String id,String name,int grade,String director,String actor,String genre){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		 String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		 String USER = "TERMP";
		 String PASS = "TERMP";
	Connection conn = null;
	try {
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(URL, USER, PASS);
	} catch(Exception e){
		System.out.println(e.getMessage());;
	}
	try{
		Statement stmt = conn.createStatement();
		String query = "INSERT INTO MOVIE VALUES('"+id+"','"+name+"','"+name+"',"+grade+",'"+director+"','"+actor+"',0)";
		ResultSet rs = stmt.executeQuery(query);
	}catch(Exception e){
		System.out.println(e.getMessage());	
		}
	}
	//������ ��ȭ ���
	public static void Mchange(String name,int grade){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		Connection conn = null; 
		String TITLE;
		try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM MOVIE";
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
					TITLE = rs.getString(2);
					String g = TITLE.replaceAll(" ", "");
					if((name.equals(g))){
						PreparedStatement pstmt2 = conn.prepareStatement("UPDATE MOVIE SET SCREENING_GRADE = ? WHERE TITLE =?");
						pstmt2.setLong(1, grade);
						pstmt2.setString(2, TITLE);
						int rowCount2 = pstmt2.executeUpdate();
						if((rowCount2 == 0)) {
							System.out.println("���� ����");
							break;
						}else{
							System.out.println("���� �Ϸ�");
							break;
						}	
					}
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
				
	}
	//������ ��ȭ �ֿ����
	public static void Mchange1(String name,String actor){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		Connection conn = null; 
		String TITLE;
		try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM MOVIE";
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
					TITLE = rs.getString(2);
					String g = TITLE.replaceAll(" ", "");
					if((name.equals(g))){
						PreparedStatement pstmt2 = conn.prepareStatement("UPDATE MOVIE SET APPEARANCE = ? WHERE TITLE =?");
						pstmt2.setString(1, actor);
						pstmt2.setString(2, TITLE);
						int rowCount2 = pstmt2.executeUpdate();
						if((rowCount2 == 0)) {
							System.out.println("���� ����");
							break;
						}else{
							System.out.println("���� �Ϸ�");
							break;
						}	
					}
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
				
	}
	//������ ��ȭ ����
	public static void Mchange2(String name,String director){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		Connection conn = null; 
		String TITLE;
		try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM MOVIE";
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
					TITLE = rs.getString(2);
					String g = TITLE.replaceAll(" ", "");
					if((name.equals(g))){
						PreparedStatement pstmt2 = conn.prepareStatement("UPDATE MOVIE SET DIRECTOR = ? WHERE TITLE =?");
						pstmt2.setString(1, director);
						pstmt2.setString(2, TITLE);
						int rowCount2 = pstmt2.executeUpdate();
						if((rowCount2 == 0)) {
							System.out.println("���� ����");
							break;
						}else{
							System.out.println("���� �Ϸ�");
							break;
						}	
					}
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
				
	}
	//������ ��ȭ �帣
	public static void Mchange3(String name,String genre ){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		Connection conn = null; 
		String TITLE;
		try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM MOVIE";
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
					TITLE = rs.getString(2);
					String g = TITLE.replaceAll(" ", "");
					if((name.equals(g))){
						PreparedStatement pstmt2 = conn.prepareStatement("UPDATE MOVIE SET INFORMATION = ? WHERE TITLE =?");
						pstmt2.setString(1, genre);
						pstmt2.setString(2, TITLE);
						int rowCount2 = pstmt2.executeUpdate();
						if((rowCount2 == 0)) {
							System.out.println("���� ����");
							break;
						}else{
							System.out.println("���� �Ϸ�");
							break;
						}	
					}
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
				
	}
	//��ȭ ����
	public static void Mdelete1(String name){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		 String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		 String USER = "TERMP";
		 String PASS = "TERMP";
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		try{
			Statement stmts = conn.createStatement();
			String que = "DELETE FROM MOVIE WHERE TITLE = '"+name+"'";
			stmts.executeUpdate(que);
		}catch(Exception e){
			System.out.println(e.getMessage());		
		}
	}
	//�� ��ȭ ���
	public static void Movieplus(int number,String title,String time,int count,String mname){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		 String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		 String USER = "TERMP";
		 String PASS = "TERMP";
	Connection conn = null;
	try {
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(URL, USER, PASS);
	} catch(Exception e){
		System.out.println(e.getMessage());;
	}
	try{
		Statement stmt1 = conn.createStatement();
		String query1 = "SELECT * FROM MOVIE WHERE TITLE = '"+title+"'";
		ResultSet rs1 = stmt1.executeQuery(query1);
		while(rs1.next()){
			MOVIE_ID = rs1.getString(1);
		}
		Statement stmt2 = conn.createStatement();
		String query2 = "INSERT INTO MULTIPLEX VALUES("+number+","+count+",'"+mname+"'"
				+ ",'"+time+"','"+MOVIE_ID+"','"+title+"')";
		ResultSet rs2 = stmt2.executeQuery(query2);
	}catch(Exception e){
		System.out.println(e.getMessage());	
		}
	}
	//��ȭ����
	public static void Mdelete2(String name,int count){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		 String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		 String USER = "TERMP";
		 String PASS = "TERMP";
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		try{
			Statement stmt = conn.createStatement();
			String que = "DELETE FROM MULTIPLEX WHERE TITLE = '"+name+"' AND MULTIPLEX_NUMBER = "+count+"";
			stmt.executeUpdate(que);
		}catch(Exception e){
			System.out.println(e.getMessage());		
		}
	}
	//�� ������ ������
	public static void movie(){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		Connection conn = null; 
		try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM MULTIPLEX";
				ResultSet rs = stmt.executeQuery(query);
				System.out.println("�󿵰� ��ȣ\t�¼���\t��ȭ���̸�\t�󿵽ð�\t��ȭ_ID\t��ȭ�̸�");
				while(rs.next()){
					MULTIPLEX_NUMBER = rs.getInt(1);
					COUNT = rs.getInt(2);
					THEATER_NAME = rs.getString(3).replaceAll(" ","");
					TIME = rs.getString(4).replaceAll(" ","");
					MOVIE_ID = rs.getString(5).replaceAll(" ","");
					TITLE = rs.getString(6).replaceAll(" ","");
					
					System.out.println(MULTIPLEX_NUMBER+"\t"+COUNT+"\t"+THEATER_NAME+"\t"+TIME+"\t"+MOVIE_ID+"\t"
							+TITLE);
					
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	}
	//��ȭ ����
	public static void Mnumber(int number,String name,String id,int seat){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		Connection conn = null; 
		String TITLE=null;
		String Mid=null;
		String tname =null;
		String time = null;
		
		try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM MULTIPLEX WHERE MULTIPLEX_NUMBER ="+number+" AND TITLE= '"+name+"' ";
				ResultSet rs = stmt.executeQuery(query);
				Statement stmt1 = conn.createStatement();
				String que = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = '"+id+"'";
				ResultSet rs1 = stmt1.executeQuery(que);
				while(rs.next()){
						
					int numbers = rs.getInt(2);
						tname = rs.getString(3);
						time = rs.getString(4);
						TITLE = rs.getString(6);
						Mid = rs.getString(5);
						numbers = numbers-1;
						PreparedStatement pstmt = conn.prepareStatement("UPDATE MULTIPLEX SET COUNT = ? WHERE TITLE = ? AND MULTIPLEX_NUMBER = ? ");
						pstmt.setInt(1,numbers);
						pstmt.setString(2,TITLE);
						pstmt.setInt(3,number);
						int rowCount = pstmt.executeUpdate();
					}
				while(rs1.next()){
					int purchase = rs1.getInt(9);
					int point = rs1.getInt(7);
					point = point + 100;
					purchase++ ;
					PreparedStatement pstmt1 = conn.prepareStatement("UPDATE CUSTOMER SET PURCHASE_PERFORMANCE = ? WHERE CUSTOMER_ID =?");
					PreparedStatement pstmt2 = conn.prepareStatement("UPDATE CUSTOMER SET POINT = ? WHERE CUSTOMER_ID =?");
					pstmt1.setInt(1,purchase);
					pstmt1.setString(2,id);
					pstmt2.setInt(1,point);
					pstmt2.setString(2,id);
					int rowCount1 = pstmt1.executeUpdate();
					int rowCount2 = pstmt2.executeUpdate();
					break;
				}
				
				Statement stmt2 = conn.createStatement();
				String query1 = "INSERT INTO RESERVE VALUES('"+id+"','"+tname+"','"+Mid+"','"+TITLE+"','"+time+"',"+number+","+seat+",'����Ϸ�')";
				ResultSet rs2 = stmt2.executeQuery(query1);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	}
	//���� ��Ȳ ������
	public static void moviename1(String id){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		Connection conn = null; 
		String mid = null;
		try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM RESERVE WHERE CUSTOMER_ID = '"+id+"'";
				ResultSet rs = stmt.executeQuery(query);
				System.out.println("���̵�\t��ȭ���̸�\t��ȭ_ID\t��ȭ�̸�\t��ȭ�ð�\t�󿵰���ȣ\t�ɴ��ڸ�\t������Ȳ");
				while(rs.next()){
					CUSTOMER_ID = rs.getString(1).replaceAll(" ","");
					THEATER_NAME = rs.getString(2).replaceAll(" ", "");
					MOVIE_ID = rs.getString(3).replaceAll(" ", "");
					TITLE = rs.getString(4).replaceAll(" ", "");
					TIME = rs.getString(5).replaceAll(" ", "");
					MULTIPLEX_NUMBER = rs.getInt(6);
					SEAT = rs.getInt(7);
					CURRENTS =	rs.getString(8).replaceAll(" ", "");
					System.out.println(CUSTOMER_ID+"\t"+THEATER_NAME+"\t"+MOVIE_ID+"\t"+TITLE+"\t"+TIME+"\t"+MULTIPLEX_NUMBER+"\t"+SEAT+"\t"+CURRENTS);
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	}
	//���� ���
	public static void Rdelete(String id,String tname,String name,String time,int seat,int number){
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		Connection conn = null; 
		String mid = null;
		
		try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt3 = conn.createStatement();
				String query3 = "DELETE FROM RESERVE WHERE CUSTOMER_ID = '"+id+"' AND TITLE ='"+name+"' AND TIME ='"+time+"' AND SEAT="+seat+""
						+ "AND THEATER_NAME='"+tname+"'";
				ResultSet rs3 = stmt3.executeQuery(query3);
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM MULTIPLEX WHERE MULTIPLEX_NUMBER ="+number+" AND TITLE= '"+name+"' ";
				ResultSet rs = stmt.executeQuery(query);
				Statement stmt1 = conn.createStatement();
				String que = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = '"+id+"'";
				ResultSet rs1 = stmt1.executeQuery(que);
				while(rs.next()){
						
					int numbers = rs.getInt(2);
						tname = rs.getString(3);
						time = rs.getString(4);
						TITLE = rs.getString(6);
						numbers = numbers+1;
						PreparedStatement pstmt = conn.prepareStatement("UPDATE MULTIPLEX SET COUNT = ? WHERE TITLE = ? AND MULTIPLEX_NUMBER = ? ");
						pstmt.setInt(1,numbers);
						pstmt.setString(2,TITLE);
						pstmt.setInt(3,number);
						int rowCount = pstmt.executeUpdate();
					}
				while(rs1.next()){
					int purchase = rs1.getInt(9);
					int point = rs1.getInt(7);
					point = point - 100;
					purchase-- ;
					PreparedStatement pstmt1 = conn.prepareStatement("UPDATE CUSTOMER SET PURCHASE_PERFORMANCE = ? WHERE CUSTOMER_ID =?");
					PreparedStatement pstmt2 = conn.prepareStatement("UPDATE CUSTOMER SET POINT = ? WHERE CUSTOMER_ID =?");
					pstmt1.setInt(1,purchase);
					pstmt1.setString(2,id);
					pstmt2.setInt(1,point);
					pstmt2.setString(2,id);
					int rowCount1 = pstmt1.executeUpdate();
					int rowCount2 = pstmt2.executeUpdate();
					break;
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	}
}