package jdbcTest;



import java.sql.*;



public class testMain {



	public static void main(String[] args) {

		// TODO Auto-generated method stub

		

		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/javafx_2";

		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			System.out.println("after forName");

			con = DriverManager.getConnection(url, "root", "Samm0902");

			System.out.println("DBms connection success");

			System.out.println("DB load success");

		} catch (Exception e) {

			System.out.println("DB load fail " + e.toString());

		}



	}



}