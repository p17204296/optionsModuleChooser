package view;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import model.StudentProfile;

public class OverviewSelectionPane extends StackPane {

	private TextArea results;
	private Button save;
	
	public OverviewSelectionPane() {
		
		results = new TextArea("Results will appear here...");
		results.setEditable(false);
		
		ScrollPane scrollPane = new ScrollPane();
	    scrollPane.setContent(results);
	    scrollPane.setFitToWidth(true);
	    scrollPane.setFitToHeight(true);
	    scrollPane.setPadding(new Insets(40,40,60,40));	
		this.setStyle("-fx-background-color: #EBF6FF;");

		
	    save = new Button("Save Overview");
	    
		StackPane.setAlignment(save, Pos.BOTTOM_CENTER);
		save.setTranslateY(-15);
		
		this.getChildren().addAll(scrollPane, save);
	}
	
	public void setColor(String colour) {
		this.setStyle("-fx-background-color: " + colour + ";");
	}
	
	public void setResults(String overview) {
		results.setText(overview);
	}
	
	public String getResults() {
		return results.getText();
	}
	
	public void addSaveListener(EventHandler<ActionEvent> handler) {
		save.setOnAction(handler);
	}
	
	public void clearResults() {
		results.clear();
	}
	
	public void refresh(StudentProfile sp){
		results.setText(sp.getOverview());
	}
	
	public BooleanBinding isTextFieldEmpty() {
		return results.textProperty().isEmpty();
	}
	
	public void addBtnDisableBind(BooleanBinding value) {
		save.disableProperty().bind(value);
	}
}
