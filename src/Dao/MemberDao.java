package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Domain.Member;

public class MemberDao {
	
	
				// Dao : 데이터베이스 접근 객체
	
	// JDBC 주요 인터페이스 
		// 1. connection :  DB연결 인터페이스
		// 2. PreparedStatement : DB연결후 SQL 관리 / 조작 인터페이스  
		// 3. resultSet : 쿼리 연결 인터페이스 
	
	// 필드 
	private Connection connection; // DB연결인터페이스 선언
	
	private ResultSet resultSet; // 검색후 결과[ SQL실행후 결과 = 쿼리 ] 연결 
	
	// dao 객체 
	private static MemberDao memberDao = new MemberDao(); // 현재 클래스의 객체  
	
	// dao 객체 반환 메소드 
	public static MemberDao getMemberDao() {
		return memberDao;
	}
	
	// 생성자 
	public MemberDao() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_2?serverTime=UTC" , "root" , "Samm0902");
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("DB load fail " + e.toString());
		}
	}

	// 1. 회원가입 메소드 [ 인수 : 회원가입정보 ] 
	public boolean signup( Member member) {
		
		//1. SQL 작성 
		String sql = "insert into member( m_id , m_password , m_name , m_email , m_phone , m_point ) values(?,?,?,?,?,?)";
		
		try {
			//2. SQL <--> DB 연결 [ PreparedStatement : SQL 관리 조작 ] 
			PreparedStatement preparedStatement = connection.prepareStatement(sql); // 예외처리 무조건 처리 
			
			//3. SQL 조작 [ ? 에 값 넣기 ]
			preparedStatement.setString(1, member.getId() ); // 첫번째 ? 에 아이디 넣기 
			preparedStatement.setString(2, member.getPassword()); // 두번째 ? 에 패스워드 넣기 
			preparedStatement.setString(3, member.getName() );// 세번째 ?
			preparedStatement.setString(4, member.getEmail() ); // 네번째 ?
			preparedStatement.setString(5, member.getPhone()); // 다섯번째 ? 
			preparedStatement.setInt(6, member.getPoint() ); // 여섯번째 ? 		
			//4. SQL 실행 
			preparedStatement.executeUpdate();
			//5. SQL 결과
			return true; // 회원가입 성공
		}
		catch (Exception e) {}

		return false; // 회원가입 실패시 false 반환  db 오류 
		
	}
	// 2. 로그인 메소드 
	public boolean login( String id , String password ) {
		
		// 1.SQL작성
		String sql = "select * from member where m_id =? and m_password =?";
		
		try {
			// 2. 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			// 3. 
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			// 4. 실행 
			resultSet =  preparedStatement.executeQuery();
			// 5. 결과 [ resultset 초기값은 null -> 결과1레코드 -> 결과2레코드 -> 결과3레코드
			if( resultSet.next() ) {
				// sql 결과가 존재하면 
				return true; // 존재하는 회원
			}
			return false; // 존재하지 않는 회원
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return false; // 로그인 실패 혹은 db 오류 
	}
	// 3. 아이디찾기 메소드 
	
	public String findid(String email, String name) {
		
		String sql = "select * from member where m_email=? and m_name=?";
		
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, email);
			preparedstatement.setString(2, name);
			
			resultSet = preparedstatement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getString(1); //0:null 1:id 2:pw 3:...
			}
			
 		} catch(Exception e) {
 			
 		}
		return null;
	
	}
	
	// 4. 비밀번호 찾기 메소드 
	
	public String findpassword(String id, String email) {
		String sql = "select * from member where m_id=? and m_email=?";
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, id);
			preparedstatement.setString(2, email);
			
			resultSet = preparedstatement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getString(2);
			}
			
		} catch(Exception e) {
			
		}
		return null;
		
	}
	
	// 8. 회원 정보 호출 메소드 
	public Member getmember(String id) {
		
		String sql = "select * from member where m_id=?";
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, id);
			resultSet = preparedstatement.executeQuery();
			if(resultSet.next()) {
				Member member = new Member(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), 
						resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
				
				return member; 
			}
		} catch(Exception e) {
			
		}
		return null;
		
	}
	
	// 6. 회원 수정 메소드 
	
	public boolean memberupdate(Member member) {
		String sql = "update member set m_password=?, m_name=?, m_email=?, m_phone=? where m_id=?";
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, member.getPassword());
			preparedstatement.setString(2, member.getName());
			preparedstatement.setString(3, member.getEmail());
			preparedstatement.setString(4, member.getPhone());
			preparedstatement.setString(5, member.getId());
			
			preparedstatement.executeUpdate();
			return true;
			
		}catch (Exception e) {}
		
		return false;
	}
	
	// 7. 회원 탈퇴 메소드 
	public boolean memberdelete(String id) {
		
		String sql = "delete from member where m_id=?";
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, id);
			preparedstatement.executeUpdate();
			return true;
			
		} catch (Exception e) {
			
		}
		
		return false;
	}
	
	
}
