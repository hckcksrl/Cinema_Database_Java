
import java.sql.*;

import java.util.*;
public class Movie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String CUSTOMER_ID,PASSWORD,CUSTOMER_NAME,BIRTH,CUSTOMER_ADDRESS,CUSTOMER_PHONENUMBER;
		String MANAGER_PASSWORD,MANAGER_ID,MOVIE_ID,TITLE,SCREENING_GRADE,DIRECTOR,APPEARANCE,INFORMATION;
		String THEATER_NAME,THEATER_ADDRESS,THEATER_PHONENUMBER;
		String CURRENTS;
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:DBSERVER";
		String USER = "TERMP";
		String PASS = "TERMP";
		String ID;
		String id,password,name,address,phone,birth;
		Connection conn = null;
		int POINT,PURCHASE_PERFORMANCE,INTERNET_PAY,CASH,COUNT,MULTIPLEX_NUMBER;
		int log;		
		Scanner scan = new Scanner(System.in);
		while(true){
		System.out.println("1.ȸ������ 2.ȸ���α��� 3.�����ڷα��� 4.���α׷�����");
		System.out.print("���ϴ� ����� ��ȣ�� �Է��Ͻÿ� : ");
		log = scan.nextInt();
		 
		switch(log){
		 case 1 :
			 Scanner input = new Scanner(System.in);
			 System.out.print("���̵� : ");
			 id = input.nextLine();
			 System.out.print("��й�ȣ : ");
			 password = input.nextLine();
			 System.out.print("�̸� : ");
			 name = input.nextLine();
			 System.out.print("������� : ");
			 birth = input.nextLine();
			 System.out.print("�ּ� : ");
			 address = input.nextLine();
			 System.out.print("��ȭ��ȣ : ");
			 phone = input.nextLine();
			 Database.join(id, password, name, birth, address, phone);
			 break;
		 case 2 :
			 Scanner inputs = new Scanner(System.in);
			 System.out.print("���̵� �Է� :");
			 String ids = inputs.nextLine();
			 System.out.print("��й�ȣ �Է� : ");
			 String passwords = inputs.nextLine();
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
						ID = CUSTOMER_ID;
						PASS = PASSWORD;
						CUSTOMER_ADDRESS=rs.getString(5);
						CUSTOMER_PHONENUMBER=rs.getString(6);
						CUSTOMER_ID = CUSTOMER_ID.replaceAll(" ","");
						PASSWORD = PASSWORD.replaceAll(" ","");
						if((ids.equals(CUSTOMER_ID))&&(passwords.equals(PASSWORD))){
							System.out.println("�α��� ����");
							while(true){
							System.out.print("1.ȸ������ 2.ȸ��Ż�� 3.��ȭ��Ʈ 4.��ȭ���� 5.������� 6.������Ȳ 7.�α׾ƿ�");
							int a = inputs.nextInt();
							if(a==1){
								while(true){
									Scanner inputss = new Scanner(System.in);
								System.out.print("������ �κ� : 1.��й�ȣ 2.��ȭ��ȣ 3.�ּ� 4.�����Ϸ�");
								int k = inputs.nextInt();
								if(k==1){
									System.out.print("�ٲ� ��й�ȣ :");
									password = inputss.nextLine();
									Database.change(ID,PASS,password,CUSTOMER_ADDRESS,CUSTOMER_PHONENUMBER);
								}else if(k==2){
									System.out.print("�ٲ� ��ȭ��ȣ :");
									String phonenumber = inputss.nextLine();
									Database.change(ID,PASS,PASS,CUSTOMER_ADDRESS,phonenumber);
								}else if(k==3){
									System.out.print("�ٲ� �ּ� :");
									String addresss = inputss.nextLine();
									Database.change(ID,PASS,PASS,addresss,CUSTOMER_PHONENUMBER);
								}else if(k==4){
									System.out.println("���� ��");
									break;
								}else{
									System.out.println("�߸��Է��ϼ̽��ϴ�.");
								}
								}
								continue;
							}else if(a==2){
								Database.delete(ids);
								break;	
							}else if(a==3){
								Database.movies();
							}else if(a==4){
								System.out.println("������ ��ȭ�� ������ �ּ���:");
								Database.moviename();
								Scanner h = new Scanner(System.in);
								String movie = h.nextLine();
								Database.multiplex(movie);
								System.out.print("�󿵰� ��ȣ�� �Է��ϼ��� :");
								int t = h.nextInt();
								System.out.print("���� �ڸ��� ������ �ּ���");
								int g = h.nextInt();
								Database.Mnumber(t,movie,ID,g);
							}else if(a==5){
								Database.moviename1(ID);
								System.out.println("����� ��ȭ�� ��ȭ�� :");
								Scanner h = new Scanner(System.in);
								String mname = h.nextLine();
								System.out.println("����� ��ȭ�� Ÿ��Ʋ :");
								String title1 = h.nextLine();
								System.out.println("����� ��ȭ�� ��ȭ�ð� :");
								String time1 = h.nextLine();
								System.out.println("����� ��ȭ�� �󿵰� :");
								int number = h.nextInt();
								System.out.println("����� ��ȭ�� �ɴ��ڸ� :");
								int seat = h.nextInt();
								Database.Rdelete(ID,mname, title1, time1, seat,number);
								
							}else if(a==6){
								Database.moviename1(ID);
								
							}else if(a==7){
								break;
							}
							
							else{
								System.out.println("�߸� �Է��ϼ̽��ϴ�.");
							}
					}
							}else{
								continue;
							}
						
						}
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
				break;
		 case 3 :
			 Scanner inputt = new Scanner(System.in);
			 System.out.print("���̵� : ");
			 String idd = inputt.nextLine();
			 System.out.print("��й�ȣ : ");
			 String passwordd = inputt.nextLine();
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch(Exception e){
				System.out.println(e.getMessage());;
			}
			try{
				Statement stmt = conn.createStatement();
				String query ="SELECT * FROM MANAGERS";
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
					MANAGER_ID = rs.getString(1).replaceAll(" ", "");
					MANAGER_PASSWORD = rs.getString(2).replaceAll(" ", "");
					if((idd.equals(MANAGER_ID))&&(passwordd.equals(MANAGER_PASSWORD))){
						System.out.println("������ �α��� ����");
						while(true){
							System.out.println("1.��ȭ�� ���� ���� 2.��ȭ ���� ���� 3.�� ��ȭ ���� ���� 4.VIP�� ���� 5.��ȭ Ƽ�� ���� 6.������ ����");
							Scanner p = new Scanner(System.in);
							int o = p.nextInt();
							if(o==1){
								System.out.println("1.��ȭ�� ��� 2.��ȭ�� ���� ���� 3.��ȭ�� ����");
								Scanner l = new Scanner(System.in);
								int e = l.nextInt();
								if(e==1){
									Scanner u = new Scanner(System.in);
									System.out.print("��ȭ�� �̸� :\t");
									String mname = u.nextLine();
									System.out.print("��ȭ�� ��ġ :\t");
									String maddress = u.nextLine();
									System.out.print("��ȭ�� ��ȭ��ȣ :\t");
									String mphone = u.nextLine();
									Database.update(mname,maddress,mphone);
								}else if(e==2){
									Scanner q = new Scanner(System.in);
									System.out.println("������ ��ȭ�� �̸� : \t");
									String mnames = q.nextLine();
									System.out.print("1.��ȭ�� ��ġ ���� 2.��ȭ�� ��ȭ��ȣ ����");
									int m = q.nextInt();
									if(m==1){
										Scanner qq = new Scanner(System.in);
										System.out.print("�ٲ� ��ȭ�� ��ġ :\t");
										String maddress = qq.nextLine();
										Database.mname(mnames,maddress);
									}else if(m==2){
										Scanner qq = new Scanner(System.in);
										System.out.print("�ٲ� ��ȭ�� ��ȭ��ȣ :\t");
										String mphone = qq.nextLine();
										Database.mname2(mnames,mphone);
									}else{
										System.out.println("�߸� �Է� �ϼ̽��ϴ�.");
									}
								}else if(e==3){
									System.out.print("������ ��ȭ�� �̸� : ");
									Scanner qq = new Scanner(System.in);
									String names = qq.nextLine();
									Database.Mdelete(names);
								}
							}else if(o==2){
								while(true){
								Scanner qq = new Scanner(System.in);
								System.out.print("1.��ȭ���  2.��ȭ���� 3.��ȭ���� 4.���� ����");
								int k = qq.nextInt();
								if(k==1){
									Scanner qq1 = new Scanner(System.in);
									System.out.print("��ȭ ID :");
									String mid = qq1.nextLine();
									System.out.print("��ȭ �̸� :");
									String mname = qq1.nextLine();
									System.out.print("��ȭ ���� :");
									String mdirector = qq1.nextLine();
									System.out.print("�ֿ���� :");
									String mactor = qq1.nextLine();
									System.out.print("�帣 :");
									String mgenre = qq1.nextLine();
									System.out.print("��ȭ ��� :");
									int mgrade = qq1.nextInt();
									Database.mupdate(mid,mname,mgrade,mdirector,mactor,mgenre);
								}else if(k==2){
									while(true){
									Scanner qq1 = new Scanner(System.in);
									System.out.print("������ ��ȭ : ");
									String mname = qq1.nextLine();
									System.out.print("1.��ȭ ��� ���� 2.��ȭ �ֿ� ���� 3.��ȭ ���� ���� 4.��ȭ �帣 ���� 5.��ȭ���� ����");
									int m = qq1.nextInt();
									if(m==1){
										Scanner qq2 = new Scanner(System.in);
										System.out.print("������ ��ȭ ��� :");
										int g = qq2.nextInt();
										Database.Mchange(mname,g);
									}else if(m==2){
										Scanner qq2 = new Scanner(System.in);
										System.out.print("������ ��ȭ �ֿ� :");
										String g = qq2.nextLine();
										Database.Mchange1(mname,g);
									}else if(m==3){
										Scanner qq2 = new Scanner(System.in);
										System.out.print("������ ��ȭ ���� :");
										String g = qq2.nextLine();
										Database.Mchange2(mname,g);
									}else if(m==4){
										Scanner qq2 = new Scanner(System.in);
										System.out.print("������ ��ȭ �帣 :");
										String g = qq2.nextLine();
										Database.Mchange3(mname,g);
									}else if(m==5){
										break;
									}
									else{
										System.out.println("�߸��Է��߽��ϴ�.");
									}
								 }
							}else if(k==3){
								Scanner qq2 = new Scanner(System.in);
								System.out.print("������ ��ȭ �̸� :");
								String mname = qq2.nextLine();
								Database.Mdelete1(mname);
							}else if(k==4){
								break;
							}else{
								System.out.println("�߸� �Է��ϼ̽��ϴ�.");
							}
						}
					}else if(o==3){
						while(true){
						System.out.println("1.�� ��ȭ���� ��� 2.�� ��ȭ���� ���� 3.���� ��");
						Scanner qq2 = new Scanner(System.in);
						int g = qq2.nextInt();
						if(g==1){
							Scanner qq3 = new Scanner(System.in);
							System.out.print("��ȭ �̸� : ");
							String mname1 = qq3.nextLine();
							System.out.print("��ȭ �� �ð�");
							String mtime = qq3.nextLine();
							System.out.print("��ȭ�� �̸�  : ");
							String tname = qq3.nextLine();
							System.out.print("�¼��� : ");
							int  count = qq3.nextInt();
							System.out.print("�󿵰� ��ȣ :");
							int number = qq3.nextInt();
							Database.Movieplus(number,mname1,mtime,count,tname);
						}else if(g==2){
							System.out.println("������ ��ȭ :");
							Database.movie();
							Scanner qq3 = new Scanner(System.in);
							System.out.print("�� ������ ��ȭ : ");
							String mname = qq3.nextLine();
							System.out.print("������ ��ȭ�� �󿵰� ��ȣ :");
							int number = qq3.nextInt();
							Database.Mdelete2(mname,number);
						}else{
							break;
						}
					}
					}else if(o==4){
						break;
					}else if(o==5){
						break;
					}else if(o==6){
						break;
					}
				}
			}
				}
		}catch(Exception e){
			System.out.println(e.getMessage());	
		} 
		 break;
		 case 4:
			 break;
			}
		break;
		}		
	}
}

