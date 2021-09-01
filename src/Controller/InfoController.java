package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class InfoController implements Initializable {

	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//1. 로그인된 아이디 가져오기  
		String loginid = LoginController.getinstance().getloginid();
		
//		//2. 리스트에서 아이디 찾아서 정보 가져오기 
//		
//		for(Member member: List.members) {
//			if (member.getId().equals(loginid)) {
//				txtid.setText(member.getId());
//				txtname.setText(member.getName());
//				txtemail.setText(member.getEmail());
//				txtpoint.setText(member.getPoint()+"");
//				
//				String phone = member.getPhone();
//				
//				txtphone.setText(member.getPhone().substring(0, 3)+"-"+member.getPhone().substring(3,7)+"-"+member.getPhone().substring(7));
//			}
//		}
		
		MemberDao memberdao = MemberDao.getMemberDao();
		Member member = memberdao.getmember(loginid);
		
		txtid.setText(member.getId());
		txtname.setText(member.getName());
		txtemail.setText(member.getEmail());
		txtpoint.setText(member.getPoint()+"");
		String phone = member.getPhone();
		txtphone.setText(member.getPhone().substring(0, 3)+"-"+member.getPhone().substring(3,7)+"-"+member.getPhone().substring(7));
		
		
	}
	
    @FXML
    private Label txtid;

    @FXML
    private Label txtname;

    @FXML
    private Label txtemail;

    @FXML
    private Label txtphone;

    @FXML
    private Label txtpoint;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;

    @FXML
    void delete(ActionEvent event) throws IOException {
    	
    	String loginid = LoginController.getinstance().getloginid();
    	
    	//1. 메시지 창으로 확인여부 물어보기
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setContentText("정말로 탈퇴하시겠습니까?");
    	alert.setHeaderText("회원탈퇴");
    	
    	Optional<ButtonType> optional = alert.showAndWait();
    	
    	//2. ok 눌렀을때 
    	if (optional.get() == ButtonType.OK) {
    		//3. 리스트와 파일에서 제거후 업데이트
    		
    		MemberDao memberdao = MemberDao.getMemberDao();
    		boolean result = memberdao.memberdelete(loginid);
    		
    		if (result) {
    			btndelete.getScene().getWindow().hide();
				
	    		Parent parent = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
	    		
	    		Stage stage = new Stage();
	    		Scene scene = new Scene(parent);
	    		
	    		stage.setScene(scene);
	    		
	    		
	    		stage.setResizable(false); // 스테이지 크기 변경 불가
	    		stage.setTitle("MyProtein");
	    		
	    		Image image = new Image("file:/Users/keom/Desktop/download.png");
	    		stage.getIcons().add(image);
	    		
	    		stage.show();
	    		
    		}
    		
//    		for(Member member : List.members) {
//    			if (member.getId().equals(loginid)) {
//    				List.members.remove(member);
//    				
//    				FileUtil.filesave();
//    				
//    				btndelete.getScene().getWindow().hide();
//    				
//    				//5. 로그인창 띄우기 
//    	    		Parent parent = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
//    	    		
//    	    		Stage stage = new Stage();
//    	    		Scene scene = new Scene(parent);
//    	    		
//    	    		stage.setScene(scene);
//    	    		
//    	    		
//    	    		stage.setResizable(false); // 스테이지 크기 변경 불가
//    	    		stage.setTitle("MyProtein");
//    	    		
//    	    		Image image = new Image("file:/Users/keom/Desktop/download.png");
//    	    		stage.getIcons().add(image);
//    	    		
//    	    		stage.show();
//    			}
//    			
//    		}
    	
    	}
    }

    @FXML
    void update(ActionEvent event) {
    	
    	//페이지전환 [ 메인페이지에서 메소드 불러오기 ]
    	MainPageController.getinstance().loadpage("infoupdatepage");
    	

    }

}
