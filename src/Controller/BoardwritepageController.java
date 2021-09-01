package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Dao.BoardDao;
import Domain.BoardDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class BoardwritepageController implements Initializable{
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    private Button btnboardwrite;

    @FXML
    private TextField txttitle;

    @FXML
    private TextArea txtcontents;

    @FXML
    private Button btnboardcancel;

    @FXML
    void boardcancel(ActionEvent event) {
    	
    	MainPageController.getinstance().loadpage("communitypage");

    }

    @FXML
    void boardwrite(ActionEvent event) {
    	
    	String title = txttitle.getText();
    	String contents = txtcontents.getText();
    	String bwriter = LoginController.getinstance().getloginid();
    	
    	BoardDto board = new BoardDto(title, contents, bwriter);
    	
    	BoardDao boardDao = BoardDao.getBoardDao();
    	boolean result = boardDao.boardwrite(board);
    	
    	if( result ) {
    		// 1. 메시지 객체 만들기 [ Alert 클래스 ]
	    	Alert alert = new Alert( AlertType.INFORMATION );
	    	// 2. 메시지 내용 넣기 
	    	alert.setContentText(" 글이 작성되었습니다! ");
	    	alert.setHeaderText(" 글 작성 " );	    	
	    	// 3. 메시지 실행 
	    	alert.showAndWait(); // 창열고 닫을때 까지 기다리기
	    	// 4. 현재 회원가입 스테이지 끄기 
	    	MainPageController.getinstance().loadpage("communitypage");   	
	    	return;
    	}else {
    		Alert alert = new Alert( AlertType.INFORMATION );
	    	// 2. 메시지 내용 넣기 
	    	alert.setContentText(" 글이 직실패! ");
	    	alert.setHeaderText(" 글 작성 실패 " );	    	
	    	// 3. 메시지 실행 
	    	alert.showAndWait(); // 창열고 닫을때 까지 기다리기
    		
    		return;
    	}

    }
	

}
