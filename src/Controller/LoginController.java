package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Dao.MemberDao;
import Domain.List;
import Domain.Member;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController implements Initializable {
										//fxml 초기값 설정 인터페이스
	
	//현재 클래스[컨트롤러]의 객체 
	private static LoginController instance; //현재 클래스의 객체
	
	public LoginController() { //현재클래스의 메모리 받아서 객체 생성하는 생성
		instance = this;
	}
	
	public static LoginController getinstance() { //객체를 반환해주는 메서드 
		return instance;
	}
	
	public String getloginid() {
		return txtid.getText();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// fxml파일이 열렸을 때 초기 메소드 
		lblconfirm.setText(""); //컨트롤러.setText() : 해당 컨트롤에 텍스트 설정 
		imgloading.setVisible(false); // 화면 처음 입장시 로딩이미지 끄기 
		
	}
	
	
	// 씬빌더에서 정한 fx:id 가져오기  
	
	@FXML // fx:id="txtid"
    private TextField txtid; // Value injected by FXMLLoader

    @FXML // fx:id="txtpassword"
    private PasswordField txtpassword; // Value injected by FXMLLoader

    @FXML // fx:id="btnfindpassword"
    private Label btnfindpassword; // Value injected by FXMLLoader

    @FXML // fx:id="btnfindid"
    private Label btnfindid; // Value injected by FXMLLoader

    @FXML // fx:id="btnlogin"
    private Label btnlogin; // Value injected by FXMLLoader

    @FXML // fx:id="btnsignup"
    private Label btnsignup; // Value injected by FXMLLoader

    @FXML // fx:id="lblconfirm"
    private Label lblconfirm; // Value injected by FXMLLoader

    @FXML // fx:id="imgloading"
    private ImageView imgloading; // Value injected by FXMLLoader

    @FXML
    void findid(MouseEvent event) throws Exception {
    	Stage stage = new Stage();
    	Parent parent = FXMLLoader.load(getClass().getResource("/FXML/findid.fxml"));
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void findpassword(MouseEvent event) throws Exception {
    	Stage stage = new Stage();
    	Parent parent = FXMLLoader.load(getClass().getResource("/FXML/findpassword.fxml"));
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.show();
    }

    @FXML
    void login(MouseEvent event) {	 // 로그인 클릭시 
    	
    	imgloading.setVisible(true); // 로딩 이미지 표시 
    	
    	if( txtid.getText().equals("") ) {
    		lblconfirm.setText(" - 아이디를 입력해주세요 - ");
    		return;
    	}
    	if( txtpassword.getText().equals("") ) {
    		lblconfirm.setText(" - 비밀번호를 입력해주세요 - ");
    		return;
    	}
    	
    	// 2초간 일시정지  [ PauseTransition : javafx 중지 클래스 
    	PauseTransition pauseTransition = new PauseTransition(); // 객체 생성 
    	pauseTransition.setDuration( Duration.seconds(2) );		// 일시정지 시간 설정 
    	//pauseTransition.setOnFinished( 인수 ->  {실행코드} );
    		// 익명 메소드 [ 람다식 ] 
    			// 인수 -> {실행코드} 
    	
    	pauseTransition.setOnFinished( 인수 ->  { 
    					// 일시정지가 끝나면 실행되는 코드 
    		imgloading.setVisible(false); // 로딩 이미지 숨기기 
    		
    		// db처리 
    		MemberDao memberDao = MemberDao.getMemberDao();
    		boolean result =  memberDao.login( txtid.getText() , txtpassword.getText() );
    		
    		if( result ) {
    			// 로그인 성공 
    			Stage stage = new Stage();
        		try {
        			Parent parent = FXMLLoader.load(getClass().getResource("/FXML/mainpage.fxml"));
        			Scene scene = new Scene(parent);
        			stage.setScene(scene);
        			stage.setResizable(false); // 스테이지 크기변경불가 
        			stage.setTitle("adidas sports"); // 스테이지 타이틀 
        			// 스테이지 아이콘 
        				// 1.이미지 불러오기 
        				Image image = new Image("file:C:/Users/User/Desktop/H/web0714/javafx_project/src/FXML/icon.jpg");
        				stage.getIcons().add(image);
        			stage.show();
        			
        			
        		}
        		catch (Exception e) {}
        		// 기존 스테이지 닫기 
        		btnlogin.getScene().getWindow().hide();
        		return;
        		
    		}else {
    			// 로그인 실패
    			lblconfirm.setText(" - 올바른 회원정보가 아닙니다 -");
    		}
    		
//			    		// [파일처리] 입력한 정보가 리스트[회원목록]에 존재하면 
//			    		for( Member member : List.members ) {
//				        	if( txtid.getText().equals( member.getId() ) &&
//				        			txtpassword.getText().equals( member.getPassword() ) ) {
//				        		lblconfirm.setText(" - 로그인 성공 - ");
//				        		
//				        		// mainpage 실행 
//				        		Stage stage = new Stage();
//				        		try {
//				        			Parent parent = FXMLLoader.load(getClass().getResource("/FXML/mainpage.fxml"));
//				        			Scene scene = new Scene(parent);
//				        			stage.setScene(scene);
//				        			stage.setResizable(false); // 스테이지 크기변경불가 
//				        			stage.setTitle("adidas sports"); // 스테이지 타이틀 
//				        			// 스테이지 아이콘 
//				        				// 1.이미지 불러오기 
//				        				Image image = new Image("file:C:/Users/User/Desktop/H/web0714/javafx_project/src/FXML/icon.jpg");
//				        				stage.getIcons().add(image);
//				        			stage.show();
//				        			
//				        			
//				        		}
//				        		catch (Exception e) {}
//				        		
//				        		// 기존 스테이지 닫기 
//				        		btnlogin.getScene().getWindow().hide();
//				        		
//				        		return;
//				        	}
//			    		}
//        	lblconfirm.setText(" - 올바른 회원정보가 아닙니다 -");
    	} );
    	pauseTransition.play(); // 정지 클래스 시작
    }

    @FXML
    void signup(MouseEvent event) throws IOException {
    	// 1. 스테이지 생성 
    	Stage stage = new Stage();
    	//2. fxml 불러오기 
    	Parent parent = FXMLLoader.load(getClass().getResource("/FXML/signup.fxml"));
    	//3. 씬 생성 (안에 fxml 넣기_
    	Scene scene = new Scene(parent);
    	
    	//4. 스테이지 실행 (스테이지에 씬 넣기)
    	stage.setScene(scene);
    	stage.show();
    }

}
