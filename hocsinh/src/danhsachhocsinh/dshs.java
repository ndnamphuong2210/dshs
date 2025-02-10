package danhsachhocsinh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



public class dshs {
    public static void main(String[] args){
    	int option;  	
    	Scanner scan = new Scanner(System.in);
    	do {
    		
        	System.out.println("1. Them hoc sinh\n2. Sua thong tin\n3. Xoa hoc sinh\n4. Xem danh sach\n5. Thoat");
        	System.out.printf("Chọn: ");
        	option = scan.nextInt();
        	
        	switch(option) {
        		case 1:
        			//Nhap nek
        			System.out.println("Them hoc sinh");
        			System.out.printf("Nhap mssv: ");
        			String mssv = scan.next();
        			System.out.printf("\nNhap ho: ");
        			scan.nextLine();
        			String ho = scan.nextLine();
        			System.out.printf("Nhap ten: ");
        			String ten = scan.next();
        			System.out.printf("Nhap email: ");
        			String email = scan.next();
        			//connect database nek
        			try{
    					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pdm", "root", "napunapu");
    					String sql = "INSERT INTO hocsinhnek (mssv, ho, ten, email) VALUES (?, ?, ?, ?)";
    
    					PreparedStatement statement = connection.prepareStatement(sql);
    					statement.setString(1, mssv);
    					statement.setString(2, ho);
    					statement.setString(3, ten);
    					statement.setString(4, email);
    					
    					int row = statement.executeUpdate();
    					if(row>0) {
    						System.out.println("Thêm học sinh thành công\n");
    					}
    					connection.close();
    				} 
    				catch (SQLException e) {
    						System.out.println("error");
    						e.printStackTrace();
        			}
        			break;
        		case 2:
        			System.out.println("Nhap mssv muốn sửa thông tin: ");
        			mssv = scan.next();
        			System.out.println("Sua thong tin:\n1.Sua Ho\n2.Sua Ten\n3.Sua email\nChọn: ");
        			int choice = scan.nextInt();
        			switch (choice) {
        				case 1:
        					System.out.println("Cập nhật họ thành: ");
        					scan.nextLine();
        					String homoi = scan.nextLine();
        					try{
            					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pdm", "root", "napunapu");
            					String sql = "UPDATE hocsinhnek SET ho=? WHERE mssv = ?";
            					PreparedStatement statement = connection.prepareStatement(sql);
            					statement.setString(1, homoi);
            					statement.setString(2, mssv);
            					int row = statement.executeUpdate();
            					if(row==1) {
            						System.out.println("Cập nhật họ thành công\n");
            					}
            					connection.close();
            				} 
            				catch (SQLException e) {
            						System.out.println("error");
            						e.printStackTrace();
                			}
        					break;
        				case 2:
        					System.out.printf("Cập nhật tên thành: ");
        					String tenmoi = scan.next();
        					try{
            					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pdm", "root", "napunapu");
            					String sql = "UPDATE hocsinhnek SET ten=? WHERE mssv = ?";
            					PreparedStatement statement = connection.prepareStatement(sql);
            					statement.setString(1, tenmoi);
            					statement.setString(2, mssv);
            					int row = statement.executeUpdate();
            					if(row==1) {
            						System.out.println("Cập nhật tên thành công\n");
            					}
            					connection.close();
            				} 
            				catch (SQLException e) {
            						System.out.println("error");
            						e.printStackTrace();
                			}
        					break;
        				case 3:
        					System.out.printf("Cập nhật email thành: ");
        					String emailmoi = scan.next();
        					try{
            					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pdm", "root", "napunapu");
            					String sql = "UPDATE hocsinhnek SET email=? WHERE mssv = ?";
            					PreparedStatement statement = connection.prepareStatement(sql);
            					statement.setString(1, emailmoi);
            					statement.setString(2, mssv);
            					int row = statement.executeUpdate();
            					if(row==1) {
            						System.out.println("Cập nhật email thành công\n");
            					}
            					connection.close();
            				} 
            				catch (SQLException e) {
            						System.out.println("error");
            						e.printStackTrace();
                			}
        					break;
        			}
        			break;
        			
        		case 3:
        			System.out.println("Nhap mssv học sinh muốn xóa thông tin: ");
        			mssv = scan.next();
        			try{
    					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pdm", "root", "napunapu");
    					String sql = "DELETE FROM hocsinhnek WHERE mssv=?";
    
    					PreparedStatement statement = connection.prepareStatement(sql);
    					statement.setString(1, mssv);
    					int row = statement.executeUpdate();
    					if(row==1) {
    						System.out.println("Xóa học sinh thành công\n");
    					}
    					connection.close();
    				} 
    				catch (SQLException e) {
    						System.out.println("error");
    						e.printStackTrace();
        			}
        			break;
        		case 4: 
        			try{
    					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pdm", "root", "napunapu");
    					String sql = "SELECT * FROM hocsinhnek";
    					Statement statem = connection.createStatement();
    					ResultSet ds = statem.executeQuery(sql);
    					int count=0;
    					while(ds.next()) {
    						mssv = ds.getString(1);
    						ho = ds.getString(2);
    						ten = ds.getString(3);
    						email = ds.getString(4);
    						count++;
    						System.out.println("Học sinh "+count+":"+ mssv + " " + ho + " " + ten + " " + email);
    					}
    					System.out.println("\n");
    					connection.close();
    				} 
    				catch (SQLException e) {
    						System.out.println("error");
    						e.printStackTrace();
    				}
        	}
        	
        	
    	}
    	while (option==1||option==2||option==3||option==4);
    	System.out.println("Đã thoát !");
    	scan.close();
    }
}

