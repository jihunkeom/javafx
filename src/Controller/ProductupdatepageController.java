package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Dao.ProductDao;
import Domain.ProductDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ProductupdatepageController implements Initializable {
	
	private ProductDto productdto = null;
	private int pactivation = 0;
	private Stage filestage;
	private String pimage = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productdto = ProductpageController.getinstance().productdto;
		pactivation = productdto.getPactivation();
		pimage = productdto.getPimage();
		
		txtpname.setText(productdto.getPname());
		txtpcontents.setText(productdto.getPcontents());
		txtpprice.setText(productdto.getPprice()+"");
		txtpstock.setText(productdto.getPprice()+"");
		
		if(productdto.getPcategory().equals("상의")) {opt_1.setSelected(true);}
		if(productdto.getPcategory().equals("하의")) {opt_2.setSelected(true);}
		if(productdto.getPcategory().equals("ACC")) {opt_3.setSelected(true);}
		if(productdto.getPcategory().equals("신발")) {opt_4.setSelected(true);}
		if(productdto.getPcategory().equals("가방")) {opt_5.setSelected(true);}
		
		lblpath.setText(pimage);
		Image image = new Image(productdto.getPimage());
		uploadimg.setImage(image);
		
		if(productdto.getPactivation()==0) btnactivation.setText("Preparing");
		if(productdto.getPactivation()==1) btnactivation.setText("On Sale");
		if(productdto.getPactivation()==2) btnactivation.setText("Sold Out");
	}
	
	@FXML
    private Button btnproductupdate;

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
    private Button btnactivation;

    @FXML
    void activation(ActionEvent event) {
    	if(pactivation==0) {
    		btnactivation.setText("On Sale"); 
    		pactivation = 1;}
    	else if(pactivation==1) {
    		btnactivation.setText("Preparing"); 
    		pactivation = 0;
    	}

    }

    @FXML
    void productcancel(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {
    	productdto.setPname(txtpname.getText());
    	productdto.setPcontents(txtpcontents.getText());
    	productdto.setPprice(Integer.parseInt(txtpprice.getText()));
    	productdto.setPstock(Integer.parseInt(txtpstock.getText()));
    	
    	
    	if(opt_1.isSelected()) { productdto.setPcategory("상의");}
    	if(opt_2.isSelected()) { productdto.setPcategory("하의");}
    	if(opt_3.isSelected()) { productdto.setPcategory("ACC");}
    	if(opt_4.isSelected()) { productdto.setPcategory("가방");}
    	if(opt_5.isSelected()) { productdto.setPcategory("신발");}
    	
    	productdto.setPimage(pimage);
    	productdto.setPactivation(pactivation);
    	
    	ProductDao productDao = ProductDao.getproductDao();
    	boolean result = productDao.productupdate(productdto);
    	
    	if(result) {
    		MainPageController.getinstance().loadpage("productpage");
    	}else {
    		
    	}

    }

    @FXML
    void upload(ActionEvent event) {

    }

}
