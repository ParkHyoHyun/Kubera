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
	//DB ����
	String jdbcdrive = "com.mysql.jdbc.Driver";  // ����̹� ���� Driver  D << �빮�ڷ��Ұ�
	String jdbcur1 = "jdbc:mysql://localhost:3306/kuberadb?autoReconnect=true&useSSL=false";
	// �ּ�       /�����ͺ��̽��̸�    /? autoReconnect=true&useSSL=false ���â �ȶ߰��ϱ�
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
	Stage Login_Fail = new Stage(StageStyle.UTILITY);  // Ÿ��Ʋ â�� ����, �ݱ� â�� ����
	
	
	
	// �α��� ��ư
	public void BtnLoginAction(ActionEvent event) {
		try {
			String LoginID = Login_ID.getText();			// �Է��� ID ������ ��������
			String LoginPassword = Login_Password.getText();// �Է��� ��й�ȣ ������ ��������
			
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
			} // for �� ��
			
			if(login_pass) { // �α��� ���� -> ȭ�� ��ȯ
				Parent cancel = FXMLLoader.load(getClass().getResource("Main.fxml"));
				Scene scene = new Scene(cancel);
				primaryStage.setResizable(true);
				primaryStage.setScene(scene);
			} else {
				// �α��� ���н� -> �˾� â

				Login_Fail.initModality(Modality.WINDOW_MODAL);  // �� ������ �ϸ� �� â�� �ݱ������� �θ� â�� Ȱ��ȭ�� �ȵ�
				Login_Fail.initOwner(primaryStage);
				Login_Fail.setTitle("����");
				Parent root2 = FXMLLoader.load(getClass().getResource("Login_Fail.fxml"));
				Scene scene2 = new Scene(root2);
				Login_Fail.setScene(scene2);
				Login_Fail.setResizable(false);
				Login_Fail.show();
			}
			rs.beforeFirst(); // db ���� ó�� �����ͷ� �̵�
		} catch(Exception e) {
			e.printStackTrace();
		} finally { //try �Ϻ�  (�������� ������ ����)
			try { // mysql ���� �ݱ�
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

	// ȸ������ ��ư
	public void BtnMembershipAction(ActionEvent event) {
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
