package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Domain.ProductDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductDao {
	//DAO: DB 접근 객체 (접근만 하고 리턴 값은 DTO가 받아서 컨트롤러 혹은 뷰로 전달) 
	
	
	Connection connection; //Connection은 인터페이스  그러므로 new로 객체할당하면 안되고 클래스로부터 메모리 주소 받아야 한다!
	ResultSet resultSet;
	
	private static ProductDao productDao = new ProductDao(); //생성자 실행시킴으로써 바로 DB연결된 객체임!! 
	//얘를 다른데서 불러오니깐 다론곳에서 매번 새로 객체할당 안해도됨 -> 메모리 효율적!! 
	
	public static ProductDao getproductDao() {
		return productDao;
	}
	
	public ProductDao() { //생성자로 객체할당함으로써 바로 데이터베이스에 연결시키기!! 
		//DB접근 
		
		
		try {
			//1. 드라이버 호출 
			Class.forName("com.mysql.jdbc.Driver");
		
			//2. DB의 URL 연결
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_2?serverTime=UTC" , "root" , "Samm0902");
			//인터페이스니깐 클래스로부터 메모리 받음!  
			
			
			
		} catch(Exception e) {
			System.out.println("해당 DB 드라이버가 없습니다.");
		}
		
	}
	
	//CRUD
	//제품 등록 메소드 
	public boolean productadd(ProductDto productDto) {
		String sql = "insert into javafx_2.product (pname, pimage, pcontents, pcategory, pprice, pstock, pactivation) values(?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productDto.getPname());
			preparedStatement.setString(2, productDto.getPimage());
			preparedStatement.setString(3, productDto.getPcontents());
			preparedStatement.setString(4, productDto.getPcategory());
			preparedStatement.setInt(5, productDto.getPprice());
			preparedStatement.setInt(6, productDto.getPstock());
			preparedStatement.setInt(7, productDto.getPactivation());
			
			preparedStatement.executeUpdate();
			
			return true;
		} catch (Exception e) {System.out.println(e);}
		
		return false;
	}
	
	//제품 목록 호출 메소드
	public ObservableList<ProductDto> productlist(){
		ObservableList< ProductDto > productDtos = FXCollections.observableArrayList();
		String sql = "select * from product";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				ProductDto productDto = new ProductDto(
						resultSet.getInt(1), 
						resultSet.getString(2),
						resultSet.getString(3), 
						resultSet.getString(4),
						resultSet.getString(5), 
						resultSet.getInt(6), 
						resultSet.getInt(7), 
						resultSet.getInt(8),
						resultSet.getInt(9));
				productDtos.add(productDto);
			}
			return productDtos;
		}catch (Exception e) {}
		return null;
	}
	
	//제품 수정 메소드 
	public boolean productupdate(ProductDto productDto) {
		String sql = "update product set pname=?, pimage=?, pcontents=?, pcateogry=?, pprice=?, pstock=?, pactivation=? where pno=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,  productDto.getPname());
			preparedStatement.setString(2,  productDto.getPimage());
			preparedStatement.setString(3, productDto.getPcontents());
			preparedStatement.setString(4, productDto.getPcategory());
			preparedStatement.setInt(5,  productDto.getPprice());
			preparedStatement.setInt(5,  productDto.getPstock());
			preparedStatement.setInt(7,  productDto.getPactivation());
			preparedStatement.setInt(8,  productDto.getPno());
			
			preparedStatement.executeUpdate();
			return true;
		}catch (Exception e) {}
		return false;
	}
	
	//제품 삭제 메소드 
	
	
	

}
