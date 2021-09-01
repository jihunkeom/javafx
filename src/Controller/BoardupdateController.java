package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Dao.BoardDao;
import Domain.BoardDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BoardupdateController implements Initializable {
	
	private BoardDto boardDto = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		boardDto = CommunitypageController.getinstance().getboard();
		txttitle.setText(boardDto.getBtitle());
		txtcontents.setText(boardDto.getBcontents());
		
	}
	
	@FXML
    private TextField txttitle;

    @FXML
    private TextArea txtcontents;

    @FXML
    private Button btnboardupdate;

    @FXML
    private Button btnboardcancel;

    @FXML
    void boardcancel(ActionEvent event) {
    	MainPageController.getinstance().loadpage("boardviewpage");

    }

    @FXML
    void boardupdate(ActionEvent event) {
    	BoardDao boardDao = BoardDao.getBoardDao();
    	boardDao.boardupdate(txttitle.getText(), txtcontents.getText(), boardDto.getBno());
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setContentText("게시글이 수정되었습니다");
    	alert.setHeaderText("글 수정");
    	alert.showAndWait();
    	
    	MainPageController.getinstance().loadpage("communitypage");

    }

}
