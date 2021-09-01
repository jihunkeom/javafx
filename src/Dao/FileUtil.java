package Dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import Domain.List;
import Domain.Member;

public class FileUtil {
	
	static String memberpath = "/Users/keom/mega-java/javafx_project/src/Dao/memberlist.txt";
	
	public static void filesave() {
		try {
			FileOutputStream fileoutputstream = new FileOutputStream(memberpath);
			
			for(Member temp : List.members) {
				String outstring = temp.getId()+","+temp.getPassword()+","+temp.getName()+","
						+temp.getEmail()+","+temp.getPhone()+","+temp.getPoint()+"\n";
				
				fileoutputstream.write(outstring.getBytes());
			}
			
			fileoutputstream.close();
			
		}
		catch (Exception e) {}
	}
	
	
	
	public static void fileload() {
		
		try {
			FileInputStream fileinputstream = new FileInputStream(memberpath);
			
			byte[] bytes = new byte[10000];
			fileinputstream.read(bytes);
			
			String instring = new String(bytes);
			String[] Members = instring.split("\n");
			
			for (int i=0; i<Members.length-1; i++) {
				String[] field = Members[i].split(",");
				Member member = new Member( field[0], field[1],field[2], 
						field[3], field[4], Integer.parseInt(field[5]) );
				List.members.add(member);
			}
			
			fileinputstream.close();
			
		}
		catch (Exception e) {
			
		}
		
	}

}
