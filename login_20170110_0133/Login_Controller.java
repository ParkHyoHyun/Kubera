package login_20170110_0133;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login_Controller implements Initializable {
	private Stage primaryStage;
	//DB 연동
	String jdbcdrive = "com.mysql.jdbc.Driver";  // 드라이버 설정 Driver  D << 대문자로할것
	String jdbcur1 = "jdbc:mysql://localhost:3306/kuberadb?autoReconnect=true&useSSL=false";
	// 주소       /데이터베이스이름    /? autoReconnect=true&useSSL=false 경고창 안뜨게하기
	public void setprimaryStage(Stage primaryStage) {
		this.primaryStage=primaryStage;
	}
	
	@FXML private Button btnLogin;
	@FXML private Button btnCancel;
	@FXML private Button btnMembership;
	@FXML private TextField Login_ID;
	@FXML private PasswordField Login_Password;
	@Override
	
	public void initialize(URL location, ResourceBundle resources) {
		btnLogin.setOnAction(event->BtnLoginAction(event));
		btnMembership.setOnAction(event->BtnMembershipAction(event));
	}
	boolean login_pass = false;
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String sql = "Select * from company_account";
	ArrayList<String> ID_List = new ArrayList<String>();
	ArrayList<String> Pass_List = new ArrayList<String>();
	//
	Stage Login_Fail = new Stage(StageStyle.UTILITY);  // 타이틀 창에 제목, 닫기 창만 존재
	
	
	
	// 로그인 버튼
	public void BtnLoginAction(ActionEvent event) {
		try {
			String LoginID = Login_ID.getText();			// 입력한 ID 데이터 가져오기
			String LoginPassword = Login_Password.getText();// 입력한 비밀번호 데이터 가져오기
			
			Class.forName(jdbcdrive);
			
			conn = (Connection) DriverManager.getConnection(jdbcur1, "root", "root");
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ID_List.add(rs.getString("ID"));
				Pass_List.add(rs.getString("Password"));
			}
			String ID_Array[] = ID_List.toArray(new String[ID_List.size()]);
			String Pass_Array[] = Pass_List.toArray(new String[Pass_List.size()]);
			for(int i = 0; i < ID_Array.length; i++) {
				if(ID_Array[i].equals(LoginID) && Pass_Array[i].equals(LoginPassword)) {
					login_pass = true;
					break;
				}
			} // for 문 끝
			
			if(login_pass) { // 로그인 성공 -> 화면 전환
				Parent cancel = FXMLLoader.load(getClass().getResource("Main.fxml"));
				Scene scene = new Scene(cancel);
				primaryStage.setResizable(true);
				primaryStage.setScene(scene);
			} else {
				// 로그인 실패시 -> 팝업 창

				Login_Fail.initModality(Modality.WINDOW_MODAL);  // 이 설정을 하면 이 창을 닫기전까지 부모 창이 활성화가 안됨
				Login_Fail.initOwner(primaryStage);
				Login_Fail.setTitle("실패");
				Parent root2 = FXMLLoader.load(getClass().getResource("Login_Fail.fxml"));
				Scene scene2 = new Scene(root2);
				Login_Fail.setScene(scene2);
				Login_Fail.setResizable(false);
				Login_Fail.show();
			}
			rs.beforeFirst(); // db 제일 처음 데이터로 이동
		} catch(Exception e) {
			e.printStackTrace();
		} finally { //try 일부  (마지막에 무조건 실행)
			try { // mysql 연동 닫기
				if (rs != null) {
					rs.close();
				}
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// 회원가입 버튼
	public void BtnMembershipAction(ActionEvent event) {
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
