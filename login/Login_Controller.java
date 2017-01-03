package login;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Login_Controller implements Initializable {
	private Stage primaryStage;
	
	public void setprimaryStage(Stage primaryStage) {
		this.primaryStage=primaryStage;
	}
	@FXML private Button btnLogin;
	@FXML private Button btnCancel;
	
	@Override
	
	public void initialize(URL location, ResourceBundle resources) {
		btnLogin.setOnAction(event->BtnLoginAction(event));

	}
	
	// 로그인 버튼
	public void BtnLoginAction(ActionEvent event) {
		try {
			Parent cancel = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(cancel);
			primaryStage.setResizable(true);
			primaryStage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
