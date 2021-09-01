package start;

import Dao.BoardDao;
import Dao.FileUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Start extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		
//		FileUtil.fileload();
		
		Parent parent = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
		
		Scene scene = new Scene(parent);
		
		stage.setScene(scene);
		
		
		stage.setResizable(false); // 스테이지 크기 변경 불가
		stage.setTitle("MyProtein");
		
		Image image = new Image("file:/Users/keom/Desktop/download.png");
		stage.getIcons().add(image);
		
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args); // start 메서드 호출 
	}

}
