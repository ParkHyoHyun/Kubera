package login_20170110_0133;

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
		
		// cancel Ŭ���� ������ ��� �˾�â
			Stage cancel = new Stage(StageStyle.UTILITY);  // Ÿ��Ʋ â�� ����, �ݱ� â�� ����
			cancel.initModality(Modality.WINDOW_MODAL);  // �� ������ �ϸ� �� â�� �ݱ������� �θ� â�� Ȱ��ȭ�� �ȵ�
			cancel.initOwner(primaryStage);
			cancel.setTitle("����");
			Parent root2 = FXMLLoader.load(getClass().getResource("Cancel.fxml"));
			Scene scene2 = new Scene(root2);
			cancel.setScene(scene2);
			cancel.setResizable(false);
				
		// �α���ȭ�� ��� Ŭ�� �̺�Ʈ  -> �˾�â ȣ��
			Button btnCancel = (Button) root.lookup("#btnCancel");
			btnCancel.setOnAction(event->cancel.show());		
		// �α��� ��� ��ư -> �˾�â ��� ��ư �̺�Ʈ -> �˾�â�� ����
			Button btnCancel2 = (Button) root2.lookup("#btnCancel2");
			btnCancel2.setOnAction(event->cancel.close());		
		// �α��� ��� ��ư -> �˾�â ���� ��ư �̺�Ʈ -> ���α׷� ����
			Button btnExit = (Button) root2.lookup("#btnExit");
			btnExit.setOnAction(event->Platform.exit());
			
		  // ȸ������â �˾�â ����
			Stage membership = new Stage(StageStyle.UTILITY);
			membership.initModality(Modality.WINDOW_MODAL);  // �� ������ �ϸ� �� â�� �ݱ������� �θ� â�� Ȱ��ȭ�� �ȵ�
			membership.initOwner(primaryStage);
			membership.setTitle("Kubera ERP Solution ȸ������");
			Parent root3 = FXMLLoader.load(getClass().getResource("Membership.fxml"));
			Scene scene3 = new Scene(root3);
			membership.setScene(scene3);
			membership.setResizable(false);
			
		  // ȸ������ �˾�â ȣ��
			Button btnMembership = (Button) root.lookup("#btnMembership");
			btnMembership.setOnAction(event->membership.show());
		  // ȸ������â �ݱ�
			Button btnMembershipExit = (Button) root3.lookup("#btnMembershipExit");
			btnMembershipExit.setOnAction(event->membership.close());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
