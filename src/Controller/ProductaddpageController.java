package Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import Dao.BoardDao;
import Dao.ProductDao;
import Domain.BoardDto;
import Domain.ProductDto;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ProductaddpageController implements Initializable {
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

	@FXML
    private Button btnproductadd;

    @FXML
    private Button btnproductcancel;

    @FXML
    private ImageView uploadimg;

    @FXML
    private TextField txtpname;

    @FXML
    private TextField txtpprice;

    @FXML
    private TextArea txtpcontents;

    @FXML
    private TextField txtpstock;

    @FXML
    private RadioButton opt_1;

    @FXML
    private ToggleGroup category;

    @FXML
    private RadioButton opt_2;

    @FXML
    private RadioButton opt_3;

    @FXML
    private RadioButton opt_5;

    @FXML
    private RadioButton opt_4;

    @FXML
    private Button btnupload;

    @FXML
    private Label lblpath;

    @FXML
    void productadd(ActionEvent event) {
    	String pname = txtpname.getText();
    	String pcontents = txtpname.getText();
    	int pprice = Integer.parseInt(txtpprice.getText());
    	int pstock = Integer.parseInt(txtpstock.getText());
    	
    	String pcategory = "";
    	
    	if(opt_1.isSelected()) { pcategory="상의";}
    	if(opt_2.isSelected()) { pcategory="하의";}
    	if(opt_3.isSelected()) { pcategory="ACC";}
    	if(opt_4.isSelected()) { pcategory="가방";}
    	if(opt_5.isSelected()) { pcategory="신발";}
    	
    	ProductDto productDto = new ProductDto(pname, pimage, pcontents, pcategory, pprice, pstock, 0);
    	
    	ProductDao productDao = ProductDao.getproductDao();
    	boolean result = productDao.productadd(productDto);
    	
    	if (result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("제품 등록 성공");
    		alert.setHeaderText("제품등록");
    		alert.showAndWait();
    		MainPageController.getinstance().loadpage("productpage");
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("제품 등록 실패");
    		alert.setHeaderText("제품등록 실패 ");
    		alert.showAndWait();
    		
    	}

    }

    @FXML
    void productcancel(ActionEvent event) {

    }
    
    //첨부파일 화면 스테이지 
    private Stage filestage;
    private String pimage;

    @FXML
    void upload(ActionEvent event) { //파일경로 가져와서 DB에 저장하기  
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
    			new ExtensionFilter("그림파일: Image File", "*.png", "*.jpg","*.gif"));
    	
    	File file = fileChooser.showOpenDialog(filestage);
    	
    	if(file!=null) {
    		lblpath.setText("파일경로 : "+file.getPath());
    		pimage = file.toURI().toString();
    		Image image = new Image(pimage);
    		uploadimg.setImage(image);
    	}

    }

}