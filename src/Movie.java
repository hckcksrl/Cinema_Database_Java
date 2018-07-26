
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
		System.out.println("1.회원가입 2.회원로그인 3.관리자로그인 4.프로그램종료");
		System.out.print("원하는 기능의 번호를 입력하시오 : ");
		log = scan.nextInt();
		 
		switch(log){
		 case 1 :
			 Scanner input = new Scanner(System.in);
			 System.out.print("아이디 : ");
			 id = input.nextLine();
			 System.out.print("비밀번호 : ");
			 password = input.nextLine();
			 System.out.print("이름 : ");
			 name = input.nextLine();
			 System.out.print("생년월일 : ");
			 birth = input.nextLine();
			 System.out.print("주소 : ");
			 address = input.nextLine();
			 System.out.print("전화번호 : ");
			 phone = input.nextLine();
			 Database.join(id, password, name, birth, address, phone);
			 break;
		 case 2 :
			 Scanner inputs = new Scanner(System.in);
			 System.out.print("아이디 입력 :");
			 String ids = inputs.nextLine();
			 System.out.print("비밀번호 입력 : ");
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
							System.out.println("로그인 성공");
							while(true){
							System.out.print("1.회원수정 2.회원탈퇴 3.영화차트 4.영화예약 5.예약취소 6.예약현황 7.로그아웃");
							int a = inputs.nextInt();
							if(a==1){
								while(true){
									Scanner inputss = new Scanner(System.in);
								System.out.print("수정할 부분 : 1.비밀번호 2.전화번호 3.주소 4.수정완료");
								int k = inputs.nextInt();
								if(k==1){
									System.out.print("바꿀 비밀번호 :");
									password = inputss.nextLine();
									Database.change(ID,PASS,password,CUSTOMER_ADDRESS,CUSTOMER_PHONENUMBER);
								}else if(k==2){
									System.out.print("바꿀 전화번호 :");
									String phonenumber = inputss.nextLine();
									Database.change(ID,PASS,PASS,CUSTOMER_ADDRESS,phonenumber);
								}else if(k==3){
									System.out.print("바꿀 주소 :");
									String addresss = inputss.nextLine();
									Database.change(ID,PASS,PASS,addresss,CUSTOMER_PHONENUMBER);
								}else if(k==4){
									System.out.println("수정 끝");
									break;
								}else{
									System.out.println("잘못입력하셨습니다.");
								}
								}
								continue;
							}else if(a==2){
								Database.delete(ids);
								break;	
							}else if(a==3){
								Database.movies();
							}else if(a==4){
								System.out.println("예약할 영화를 선택해 주세요:");
								Database.moviename();
								Scanner h = new Scanner(System.in);
								String movie = h.nextLine();
								Database.multiplex(movie);
								System.out.print("상영관 번호를 입력하세요 :");
								int t = h.nextInt();
								System.out.print("앉을 자리를 선택해 주세요");
								int g = h.nextInt();
								Database.Mnumber(t,movie,ID,g);
							}else if(a==5){
								Database.moviename1(ID);
								System.out.println("취소할 영화의 영화관 :");
								Scanner h = new Scanner(System.in);
								String mname = h.nextLine();
								System.out.println("취소할 영화의 타이틀 :");
								String title1 = h.nextLine();
								System.out.println("취소할 영화의 영화시간 :");
								String time1 = h.nextLine();
								System.out.println("취소할 영화의 상영관 :");
								int number = h.nextInt();
								System.out.println("취소할 영화의 앉는자리 :");
								int seat = h.nextInt();
								Database.Rdelete(ID,mname, title1, time1, seat,number);
								
							}else if(a==6){
								Database.moviename1(ID);
								
							}else if(a==7){
								break;
							}
							
							else{
								System.out.println("잘못 입력하셨습니다.");
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
			 System.out.print("아이디 : ");
			 String idd = inputt.nextLine();
			 System.out.print("비밀번호 : ");
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
						System.out.println("관리자 로그인 성공");
						while(true){
							System.out.println("1.영화관 정보 관리 2.영화 정보 관리 3.상영 영화 정보 관리 4.VIP고객 관리 5.영화 티켓 발행 6.관리자 종료");
							Scanner p = new Scanner(System.in);
							int o = p.nextInt();
							if(o==1){
								System.out.println("1.영화관 등록 2.영화관 정보 수정 3.영화관 삭제");
								Scanner l = new Scanner(System.in);
								int e = l.nextInt();
								if(e==1){
									Scanner u = new Scanner(System.in);
									System.out.print("영화관 이름 :\t");
									String mname = u.nextLine();
									System.out.print("영화관 위치 :\t");
									String maddress = u.nextLine();
									System.out.print("영화관 전화번호 :\t");
									String mphone = u.nextLine();
									Database.update(mname,maddress,mphone);
								}else if(e==2){
									Scanner q = new Scanner(System.in);
									System.out.println("수정할 영화관 이름 : \t");
									String mnames = q.nextLine();
									System.out.print("1.영화관 위치 수정 2.영화관 전화번호 수정");
									int m = q.nextInt();
									if(m==1){
										Scanner qq = new Scanner(System.in);
										System.out.print("바꿀 영화관 위치 :\t");
										String maddress = qq.nextLine();
										Database.mname(mnames,maddress);
									}else if(m==2){
										Scanner qq = new Scanner(System.in);
										System.out.print("바꿀 영화관 전화번호 :\t");
										String mphone = qq.nextLine();
										Database.mname2(mnames,mphone);
									}else{
										System.out.println("잘못 입력 하셨습니다.");
									}
								}else if(e==3){
									System.out.print("삭제할 영화관 이름 : ");
									Scanner qq = new Scanner(System.in);
									String names = qq.nextLine();
									Database.Mdelete(names);
								}
							}else if(o==2){
								while(true){
								Scanner qq = new Scanner(System.in);
								System.out.print("1.영화등록  2.영화수정 3.영화삭제 4.수정 종료");
								int k = qq.nextInt();
								if(k==1){
									Scanner qq1 = new Scanner(System.in);
									System.out.print("영화 ID :");
									String mid = qq1.nextLine();
									System.out.print("영화 이름 :");
									String mname = qq1.nextLine();
									System.out.print("영화 감독 :");
									String mdirector = qq1.nextLine();
									System.out.print("주연배우 :");
									String mactor = qq1.nextLine();
									System.out.print("장르 :");
									String mgenre = qq1.nextLine();
									System.out.print("영화 등급 :");
									int mgrade = qq1.nextInt();
									Database.mupdate(mid,mname,mgrade,mdirector,mactor,mgenre);
								}else if(k==2){
									while(true){
									Scanner qq1 = new Scanner(System.in);
									System.out.print("수정할 영화 : ");
									String mname = qq1.nextLine();
									System.out.print("1.영화 등급 수정 2.영화 주연 수정 3.영화 감독 수정 4.영화 장르 수정 5.영화수정 종료");
									int m = qq1.nextInt();
									if(m==1){
										Scanner qq2 = new Scanner(System.in);
										System.out.print("수정할 영화 등급 :");
										int g = qq2.nextInt();
										Database.Mchange(mname,g);
									}else if(m==2){
										Scanner qq2 = new Scanner(System.in);
										System.out.print("수정할 영화 주연 :");
										String g = qq2.nextLine();
										Database.Mchange1(mname,g);
									}else if(m==3){
										Scanner qq2 = new Scanner(System.in);
										System.out.print("수정할 영화 감독 :");
										String g = qq2.nextLine();
										Database.Mchange2(mname,g);
									}else if(m==4){
										Scanner qq2 = new Scanner(System.in);
										System.out.print("수정할 영화 장르 :");
										String g = qq2.nextLine();
										Database.Mchange3(mname,g);
									}else if(m==5){
										break;
									}
									else{
										System.out.println("잘못입력했습니다.");
									}
								 }
							}else if(k==3){
								Scanner qq2 = new Scanner(System.in);
								System.out.print("삭제할 영화 이름 :");
								String mname = qq2.nextLine();
								Database.Mdelete1(mname);
							}else if(k==4){
								break;
							}else{
								System.out.println("잘못 입력하셨습니다.");
							}
						}
					}else if(o==3){
						while(true){
						System.out.println("1.상영 영화정보 등록 2.상영 영화정보 삭제 3.수정 끝");
						Scanner qq2 = new Scanner(System.in);
						int g = qq2.nextInt();
						if(g==1){
							Scanner qq3 = new Scanner(System.in);
							System.out.print("영화 이름 : ");
							String mname1 = qq3.nextLine();
							System.out.print("영화 상영 시간");
							String mtime = qq3.nextLine();
							System.out.print("영화관 이름  : ");
							String tname = qq3.nextLine();
							System.out.print("좌석수 : ");
							int  count = qq3.nextInt();
							System.out.print("상영관 번호 :");
							int number = qq3.nextInt();
							Database.Movieplus(number,mname1,mtime,count,tname);
						}else if(g==2){
							System.out.println("상영중인 영화 :");
							Database.movie();
							Scanner qq3 = new Scanner(System.in);
							System.out.print("상영 삭제할 영화 : ");
							String mname = qq3.nextLine();
							System.out.print("삭제할 영화의 상영관 번호 :");
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

