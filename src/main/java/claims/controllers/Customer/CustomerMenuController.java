package claims.controllers.Customer;

import java.net.URL;
import java.util.ResourceBundle;
import claims.models.Model;
import claims.views.CustomerMenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CustomerMenuController implements Initializable {

    @FXML
    public Button home_btn;
    
    @FXML
    public Button insurance_btn;

    @FXML
    public Button claims_btn;

    @FXML
    public Button logout_btn;

    @FXML
    private ImageView zapad;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
        image();
    }

    private void addListeners() {
        home_btn.setOnAction(event -> onHome());
        claims_btn.setOnAction(event -> onClaims());
        insurance_btn.setOnAction(event -> onInsurance());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void image() {
        Image image = new Image(getClass().getResourceAsStream("/claims/fxml/logoV3.png"));
        this.zapad.setImage(image);
    }

    private void onHome() {
        Model.getInstance().getViewFactory().getCustomerSelectedMenuItem().set(CustomerMenuOptions.HOME);
    }

    private void onClaims() {
        Model.getInstance().getViewFactory().getCustomerSelectedMenuItem().set(CustomerMenuOptions.CLAIMS);
    }

    private void onInsurance() {
        Model.getInstance().getViewFactory().getCustomerSelectedMenuItem().set(CustomerMenuOptions.INSURANCE);
    }

    private void onLogout() {
        Model.getInstance().setCustomerLoginSuccessFlag(false);
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
