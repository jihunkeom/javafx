package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Domain.BoardDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BoardDao {
	
	Connection connection;
	
	ResultSet resultSet;
	
	private static BoardDao boardDao = new BoardDao();
	
	public static BoardDao getBoardDao() {
		return boardDao;
	}
	
	public BoardDao() {
		// 1. mysql 드라이버 가져오기 
		try {
		
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_2?serverTime=UTC", "root", "Samm0902");
			System.out.println("Board DB success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//1. 글쓰기 메소드
	public boolean boardwrite(BoardDto board) {
		//1. SQL 작성 
		String sql = "insert into board(btitle, bcontents, bwriter) values(?,?,?)";
		
		try {
			//2. SQL <--> DB 연결 [ PreparedStatement : SQL 관리 조작 ] 
			PreparedStatement preparedStatement = connection.prepareStatement(sql); // 예외처리 무조건 처리 
			
			//3. SQL 조작 [ ? 에 값 넣기 ]
			preparedStatement.setString(1, board.getBtitle()); // 두번째 ? 
			preparedStatement.setString(2, board.getBcontents() );// 세번째 ?
			preparedStatement.setString(3, board.getBwriter() ); // 네번째 ?
			 
			
			//4. SQL 실행 
			preparedStatement.executeUpdate(); //insert, update, delete
			//5. SQL 결과
			return true; // 회원가입 성공
		}
		catch (Exception e) {}

		return false; // 회원가입 실패시 false 반환  db 오류 
	}
	//2. 글목록 (모든 글 호출) 메소드 
	public ObservableList<BoardDto> boardlist() {
		ObservableList<BoardDto> boardDtos = FXCollections.observableArrayList();
		
		String sql = "select * from board";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				//1.dto 만들어서 객체 선언 
				BoardDto boardDto = new BoardDto(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
				
				//2.dto를 리스트에 저장
				boardDtos.add(boardDto);
				
			}
			return boardDtos;
			
		} catch (Exception e) {
			
		}
		return null;
	}
	//3. 글 상세 메소드
	//4. 글수정 메소드 
	public boolean boardupdate(String title, String contents, int bno) {
		String sql = "update board set btitle=?, bcontents=? where bno=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, contents);
			preparedStatement.setInt(3, bno);
			
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {}
		return false;
	}
	//5. 글삭제 메소드 
	public boolean boarddelete( int bno ) {
		String sql = "delete from board where bno =? ";	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, bno);
			preparedStatement.executeUpdate();
			return true;
		}catch (Exception e) {}
		return false;
	}
	//6. 조회수 증가 메소드 
	
	public boolean boardcount(int bcount, int bno) {
		String sql = "update board set bcount=? where bno=?";
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setInt(1, bcount);
			preparedstatement.setInt(2, bno);
			
			preparedstatement.executeUpdate();
			return true;
		} catch (Exception e) {}
		return false;
	}

}
