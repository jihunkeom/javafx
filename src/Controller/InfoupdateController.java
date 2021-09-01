package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Dao.FileUtil;
import Dao.MemberDao;
import Domain.List;
import Domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InfoupdateController implements Initializable {
	
	private String loginid;
	
	private Member member = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loginid = LoginController.getinstance().getloginid();
		
		MemberDao memberdao = MemberDao.getMemberDao();
		member = memberdao.getmember(loginid);
		
		txtname.setText(member.getName());
		txtemail.setText(member.getEmail());
		txtphone.setText(member.getPhone());
		
//		for(Member member: List.members) {
//			if(member.getId().equals(loginid)) {
//				txtname.setText(member.getName());
//				txtemail.setText(member.getEmail());
//				txtphone.setText(member.getPhone());
//			}
//		}
		
		
	}

    @FXML
    private Button btnupdate;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtphone;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private PasswordField txtnewpassword;

    @FXML
    void update(ActionEvent event) throws Exception {
    	String password = txtpassword.getText();
    	if (!member.getPassword().equals(password)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("기존 비밀번호가 일치하지 않습니다");
			alert.setHeaderText("수정 실패");
			alert.showAndWait();
			return;
		}
    	
    	
		member.setName(txtname.getText());
		member.setEmail(txtemail.getText());
		member.setPhone(txtphone.getText());
		
		if(!txtnewpassword.getText().equals("")) {
			member.setPassword(txtnewpassword.getText());
		}
		
		MemberDao memberdao = MemberDao.getMemberDao();
		boolean result = memberdao.memberupdate(member);
		
		//db update 처리
		if (result) {
		
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("회원정보가 수정되었습니다. 다시 로그인 해주세요");
			alert.setHeaderText("회원정보 수정");
			alert.showAndWait();
			
			btnupdate.getScene().getWindow().hide();
			
			Stage stage = new Stage();
			Parent parent = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
			
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			
			stage.setResizable(false);
			stage.setTitle("My protein");
			
			stage.show();
			return;
		}
		
		
		
    	
//    	for (Member member : List.members) {
//    		if (!member.getPassword().equals(password)) {
//    			Alert alert = new Alert(AlertType.ERROR);
//    			alert.setContentText("기존 비밀번호가 일치하지 않습니다");
//    			alert.setHeaderText("수정 실패");
//    			alert.showAndWait();
//    			return;
//    		}
//    	}
    	
    	
//    	for(Member member:List.members) {
//    		if(member.getId().equals(loginid)) {
//    			member.setName(txtname.getText());
//    			member.setEmail(txtemail.getText());
//    			member.setPhone(txtphone.getText());
//    			
//    			if(!txtnewpassword.getText().equals("")) {
//    				member.setPassword(txtnewpassword.getText());
//    			}
//    			
//    			FileUtil.filesave();
//    			Alert alert = new Alert(AlertType.INFORMATION);
//    			alert.setContentText("회원정보가 수정되었습니다. 다시 로그인 해주세요");
//    			alert.setHeaderText("회원정보 수정");
//    			alert.showAndWait();
//    			
//    			btnupdate.getScene().getWindow().hide();
//    			
//    			Stage stage = new Stage();
//    			Parent parent = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
//    			
//    			Scene scene = new Scene(parent);
//    			stage.setScene(scene);
//    			
//    			stage.setResizable(false);
//    			stage.setTitle("My protein");
//    			
//    			stage.show();
//    			return;
//    		}
//    	}

    }

}