package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Dao.MemberDao;
import Domain.List;
import Domain.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FindpasswordController implements Initializable {
	
	@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			lblconfirm.setText("");
			
		}
	
    @FXML
    private TextField txtid;

    @FXML
    private TextField txtemail;

    @FXML
    private Label lblconfirm;

    @FXML
    private Label btnfindpassword;

    @FXML
    void findpassword(MouseEvent event) {
//    	for(Member member : List.members) {
//    		if(member.getId().equals(txtid.getText()) && member.getEmail().equals(txtemail.getText())) {
//    			lblconfirm.setText("해당 정보의 비밀번호 찾기 성공");
//    			
//    			Alert alert = new Alert(AlertType.INFORMATION);
//    			
//    			alert.setContentText("회원님의 비밀번호 :" + member.getPassword());
//    			alert.setHeaderText("비밀번호 찾기 성공");
//    			alert.showAndWait();
//    			btnfindpassword.getScene().getWindow().hide();
//    			return;
//    		}
//    	}
    	
    	MemberDao memberdao = MemberDao.getMemberDao();
    	String result = memberdao.findpassword(txtid.getText(), txtemail.getText());
    	
    	if (result != null) {
    		lblconfirm.setText("해당 정보의 비밀번호 찾기 성공");
			
			Alert alert = new Alert(AlertType.INFORMATION);
			
			alert.setContentText("회원님의 비밀번호 :" + result);
			alert.setHeaderText("비밀번호 찾기 성공");
			alert.showAndWait();
			btnfindpassword.getScene().getWindow().hide();
			return;
    			
    			
    		
    	}
    	lblconfirm.setText("등록되지 않은 회원입니다");
    	
    	
    }

} 