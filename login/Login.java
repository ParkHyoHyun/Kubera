package login;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
		Parent root = loader.load();
		Login_Controller controller = loader.getController();
		controller.setprimaryStage(primaryStage);
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Kubera ERP Solution");
		primaryStage.setResizable(false);
		primaryStage.setWidth(600);
		primaryStage.setHeight(375);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// cancel 클릭시 나오는 경고 팝업창
			Stage cancel = new Stage(StageStyle.UTILITY);  // 타이틀 창에 제목, 닫기 창만 존재
			cancel.initModality(Modality.WINDOW_MODAL);  // 이 설정을 하면 이 창을 닫기전까지 부모 창이 활성화가 안됨
			cancel.initOwner(primaryStage);
			cancel.setTitle("종료");
			Parent root2 = FXMLLoader.load(getClass().getResource("Cancel.fxml"));
			Scene scene2 = new Scene(root2);
			cancel.setScene(scene2);
			cancel.setResizable(false);
				
		// 로그인화면 취소 클릭 이벤트  -> 팝업창 호출
			Button btnCancel = (Button) root.lookup("#btnCancel");
			btnCancel.setOnAction(event->cancel.show());
				
		// 로그인 취소 버튼 -> 팝업창 취소 버튼 이벤트 -> 팝업창만 종료
			Button btnCancel2 = (Button) root2.lookup("#btnCancel2");
			btnCancel2.setOnAction(event->cancel.close());
				
		// 로그인 취소 버튼 -> 팝업창 종료 버튼 이벤트 -> 프로그램 종료
			Button btnExit = (Button) root2.lookup("#btnExit");
			btnExit.setOnAction(event->Platform.exit());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
