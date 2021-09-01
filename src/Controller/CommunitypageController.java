package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Dao.BoardDao;
import Domain.BoardDto;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CommunitypageController implements Initializable{
	
	private static BoardDto boarddto; //현재 클릭된 게시물의 아이템 
	
	private static CommunitypageController instance;
	
	public CommunitypageController() {
		instance = this;
	}
	
	public static CommunitypageController getinstance() {
		return instance;
	}
	
	public BoardDto getboard() {
		return boarddto;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//1. Dao 객체
		BoardDao boardDao = BoardDao.getBoardDao();
		
		//2. Dao 메소드 실행  
		ObservableList<BoardDto> boardDtos = boardDao.boardlist();
		
		//3. 결과를 테이블의 각 필드에 넣기 
		TableColumn tc = tableview.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("bno"));
		
		tc = tableview.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("btitle"));
		
		tc = tableview.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("bwriter"));
		
		tc = tableview.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("bdate"));
		
		tc = tableview.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("bcount"));
	
		tableview.setItems(boardDtos);
		
		//테이블에 행 클릭했을 때 이벤트 발생 
		tableview.setOnMouseClicked((MouseEvent event) -> {
			if(event.getButton().equals(MouseButton.PRIMARY) ) { //해당이벤트가 클릭이면 
				boarddto = tableview.getSelectionModel().getSelectedItem();
				
				boardDao.boardcount(boarddto.getBcount()+1, boarddto.getBno());
				
				MainPageController.getinstance().loadpage("boardviewpage");
			}
		} );
		
	}
	
	@FXML
    private Button btnboardwrite;

    @FXML
    private TableView<BoardDto> tableview;

    @FXML
    void boardwrite(ActionEvent event) {
    	MainPageController.getinstance().loadpage("boardwritepage");

    }

}
