package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPageController implements Initializable {
	
	//현재 클래스 [컨트롤러]의 객체화 
	private static MainPageController instance;
	
	public MainPageController() {
		instance = this;
	}
	public static MainPageController getinstance() {
		return instance;
	}
	
	
	
	private String loginid;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loginid = LoginController.getinstance().getloginid();
		
		lblloginid.setText(loginid);
		
	}
	

    @FXML
    private Label lblloginid;

    @FXML
    private Button btnlogout;

    @FXML
    private Label btncommunity;

    @FXML
    private Label btnproduct;

    @FXML
    private Label btnchatting;

    @FXML
    private Label btninfo;
    
    @FXML
    private BorderPane borderpane;
    
    @FXML
    private AnchorPane anchorpane;

    @FXML
    void chatting(MouseEvent event) {
    	loadpage("chattingpage");

    }

    @FXML
    void community(MouseEvent event) {
    	loadpage("communitypage");

    }

    @FXML
    void info(MouseEvent event) {
    	loadpage("infopage");

    }

    @FXML
    void logout(ActionEvent event) throws Exception { //로그아웃 클릭했을 때 
    	
    	//0. 메시지를 띄우기 [확인 ]
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setContentText("로그아웃 하시겠습니까?");
    	alert.setHeaderText("로그아웃");
    	
    	
    	//해당 메시지에서 선택한 화면 가져오기 
    	Optional<ButtonType> optional = alert.showAndWait();
    	
    	if(optional.get() == ButtonType.OK) { // 확인버튼눌렀을때 

        	//1. 현재 스테이지 닫기 [현재 컨트롤명] 
        	btnlogout.getScene().getWindow().hide();
        	//2. 로그인 스테이지 열기 
        	Stage stage = new Stage();
        	
        	Parent parent = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
    		
    		Scene scene = new Scene(parent);
    		
    		stage.setScene(scene);
    		
    		
    		stage.setResizable(false); // 스테이지 크기 변경 불가
    		stage.setTitle("MyProtein");
    		
    		Image image = new Image("file:/Users/keom/Desktop/download.png");
    		stage.getIcons().add(image);
    		
    		stage.show();
    	}
    	

    }

    @FXML
    void product(MouseEvent event) {
    	loadpage("productpage");

    }
    
    //페이지 전환 메소드 [cp에 표시할 fxml 변환 ]
    public void loadpage(String page) {
    	try {
    		Parent parent = FXMLLoader.load(getClass().getResource("/FXML/"+page+".fxml"));
    		borderpane.setCenter(parent);
    	} catch (Exception e) {}
    	
    	
    }

}