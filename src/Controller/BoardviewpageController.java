package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Dao.BoardDao;
import Domain.BoardDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BoardviewpageController implements Initializable{
	
	BoardDto boardDto = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		BoardDto boardDto = CommunitypageController.getinstance().getboard();
		
		lblbwriter.setText("작성자 : " + boardDto.getBwriter());
		lblbcount.setText("조회수 : "+boardDto.getBcount());
		lblbdate.setText("작성일 : "+boardDto.getBdate());
		txttitle.setText(boardDto.getBtitle());
		txtcontents.setText(boardDto.getBcontents());
		
		if(!boardDto.getBwriter().equals(LoginController.getinstance().getloginid())) {
			btnboarddelete.setVisible(false);
			btnboardupdate.setVisible(false);
		}
		
	}

    @FXML
    private Button btnboardlist;

    @FXML
    private TextField txttitle;

    @FXML
    private TextArea txtcontents;

    @FXML
    private Button btnboardupdate;

    @FXML
    private Button btnboarddelete;

    @FXML
    private Label lblbwriter;

    @FXML
    private Label lblbcount;

    @FXML
    private Label lblbdate;

    @FXML
    void boarddelete(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION );
    	alert.setContentText(" 해당 게시물을 정말 삭제하시겠습니까?" );
    	alert.setTitle(" 메시지 " );
    	alert.setHeaderText(" 게시물 삭제 ");
    	// Optional : null 포함 객체 
    	Optional<ButtonType> optional = alert.showAndWait();
    	
    	if( optional.get() == ButtonType.OK ) {
    		// 1. 삭제 실행
    		BoardDao boardDao = BoardDao.getBoardDao();
    		boardDao.boarddelete( boardDto.getBno() );
    		// 2. 페이지 이동
    		MainPageController.getinstance().loadpage("communtypage");
    	}
    	
    	

    }

    @FXML
    void boardlist(ActionEvent event) {
    	MainPageController.getinstance().loadpage("communitypage");

    }

    @FXML
    void boardupdate(ActionEvent event) {
    	MainPageController.getinstance().loadpage("boardupdatepage");

    }

}
