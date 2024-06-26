package claims.controllers.Advisor;

import java.net.URL;
import java.util.ResourceBundle;
import claims.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class AdvisorController implements Initializable {
    public BorderPane advisor_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdvisorSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case EDIT -> advisor_parent.setCenter(Model.getInstance().getViewFactory().getAdvisorEditView());
                case HOME -> advisor_parent.setCenter(Model.getInstance().getViewFactory().getAdvisorHomeView());
                case POLICY -> advisor_parent.setCenter(Model.getInstance().getViewFactory().getAdvisorPolicyView());
                case CLAIMS -> advisor_parent.setCenter(Model.getInstance().getViewFactory().getAdvisorClaimsView());
                default -> advisor_parent.setCenter(Model.getInstance().getViewFactory().getAdvisorHomeView());
            }
        });
    }
}
