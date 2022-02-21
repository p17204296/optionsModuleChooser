package view;

import java.util.Set;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.Module;
import model.Delivery;


public class SelectModulesPane extends GridPane {
	
	private ListView<Module> unselectedTerm1, unselectedTerm2, selectedYearLong, selectedTerm1, selectedTerm2, reserveTerm1, reserveTerm2;
	private ObservableList<Module> modulesTerm1, modulesTerm2, olYearLong, sTerm1, sTerm2, rTerm1, rTerm2;
	private Button btnTerm1Add, t1btnRemove, btnTerm2Add, btnTerm2Remove, btnReset, btnSubmit, btnReserve1, btnReserve2;
	private TextField tfCreditsTerm1, tfCreditsTerm2;
	private int creditT1, creditT2;

	
	public SelectModulesPane() {
		//styling
		this.setPadding(new Insets(20, 22, 20, 22));
		this.setVgap(6);
		this.setHgap(18);
		this.setAlignment(Pos.CENTER);
		

		RowConstraints r0 = new RowConstraints();
		this.setStyle("-fx-background-color: #EBF6FF;");//sets colour to blue
		r0.setVgrow(Priority.ALWAYS);		
		ColumnConstraints col0 = new ColumnConstraints();
		col0.setHgrow(Priority.ALWAYS);
		
		
		modulesTerm1 = FXCollections.observableArrayList();
		modulesTerm2 = FXCollections.observableArrayList();
		
		unselectedTerm1 = new ListView<>(modulesTerm1);
		unselectedTerm1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		unselectedTerm2 = new ListView<>(modulesTerm2);	
		unselectedTerm2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		
		Label lblUnselectedTerm1Modules = new Label("Unselected Term 1 modules");
		lblUnselectedTerm1Modules.setStyle("-fx-font-weight: bold;");
		Label lblUnselectedTerm2Modules = new Label("Unselected Term 2 modules");
		lblUnselectedTerm2Modules.setStyle("-fx-font-weight: bold;");
		Label lblTerm1 = new Label("Term 1");
		lblTerm1.setStyle("-fx-font-weight: bold;");
		Label lblTerm2 = new Label("Term 2");
		lblTerm2.setStyle("-fx-font-weight: bold;");
		Label lblCreditsTerm1 = new Label("Term 1 Credits:");
		lblCreditsTerm1.setStyle("-fx-font-weight: bold;");
		
		
		btnTerm1Add = new Button("Add");
		btnTerm1Add.setPrefSize(70, 30);
		t1btnRemove = new Button("Remove");
		t1btnRemove.setPrefSize(70, 30);
		btnReserve1 = new Button ("Reserve");
		btnReserve1.setPrefSize(70, 30);

		btnTerm2Add = new Button("Add");
		btnTerm2Add.setPrefSize(70, 30);
		btnTerm2Remove = new Button("Remove");
		btnTerm2Remove.setPrefSize(70, 30);
		btnReserve2 = new Button ("Reserve");
		btnReserve2.setPrefSize(70, 30);
		
		btnReset = new Button("Reset");
		btnReset.setPrefSize(70, 30);
		btnReset.setAlignment(Pos.CENTER);
		
		tfCreditsTerm1 = new TextField();
		tfCreditsTerm1.setEditable(false);
		tfCreditsTerm1.setText(Integer.toString(creditT1));
		tfCreditsTerm1.setPrefSize(60, 30);
		
		if(creditT1 <=30) {
			tfCreditsTerm1.setStyle("-fx-background-color: #ff8c97;"); //sets colour to red
//			tfCreditsTerm1.setStyle("fx-text-fill:white;");
		}
		
		HBox box1 = new HBox();
		box1.setAlignment(Pos.CENTER);
		box1.setSpacing(15);
		box1.setPadding(new Insets(20, 22, 20, 22));
		box1.getChildren().addAll(lblTerm1, btnTerm1Add, t1btnRemove, btnReserve1);
		
		HBox box2 = new HBox();
		box2.setAlignment(Pos.CENTER);
		box2.setSpacing(15);
		box2.setPadding(new Insets(20, 22, 20, 22));
		box2.getChildren().addAll(lblTerm2, btnTerm2Add, btnTerm2Remove, btnReserve2);
		
		HBox box3 = new HBox();
		box3.setAlignment(Pos.CENTER);
		box3.setSpacing(15);
		box3.setPadding(new Insets(20, 22, 20, 22));
		box3.getChildren().addAll(lblCreditsTerm1, tfCreditsTerm1);
	
		HBox box4 = new HBox();
		box4.getChildren().add(btnReset);
		box4.setAlignment(Pos.BASELINE_RIGHT);
		
		VBox containerLeft = new VBox();
		containerLeft.getChildren().addAll(lblUnselectedTerm1Modules, unselectedTerm1,box1,lblUnselectedTerm2Modules, unselectedTerm2, box2, box3, box4);
		containerLeft.setPrefHeight(500);
		containerLeft.setPrefWidth(250);
		
		this.add(containerLeft, 0, 0);
		this.getColumnConstraints().addAll(col0);
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHgrow(Priority.ALWAYS);
		Label lblSelectedYearLongModules = new Label("Selected Year Long modules");
		lblSelectedYearLongModules.setStyle("-fx-font-weight: bold;");
		Label lblSelectedTerm1Modules = new Label("Selected Term 1 modules");
		lblSelectedTerm1Modules.setStyle("-fx-font-weight: bold;");
		lblSelectedTerm1Modules.setPadding(new Insets(20, 0, 0, 0));
		Label lblSelectedTerm2Modules = new Label("Selected Term 2 modules");
		lblSelectedTerm2Modules.setStyle("-fx-font-weight: bold;");
		lblSelectedTerm2Modules.setPadding(new Insets(20, 0, 0, 0));
		Label lblReserveTerm1Modules = new Label("Reserve Term 1 module");
		lblReserveTerm1Modules.setStyle("-fx-font-weight: bold;");
		lblReserveTerm1Modules.setPadding(new Insets(20, 0, 0, 0));
		Label lblReserveTerm2Modules = new Label("Reserve Term 2 module");
		lblReserveTerm2Modules.setStyle("-fx-font-weight: bold;");
		lblReserveTerm2Modules.setPadding(new Insets(20, 0, 0, 0));
		Label lblCreditsTerm2 = new Label("Term 2 Credits:");
		lblCreditsTerm2.setStyle("-fx-font-weight: bold;");

		
		olYearLong = FXCollections.observableArrayList();
		sTerm1 = FXCollections.observableArrayList();		
		sTerm2 = FXCollections.observableArrayList();
		rTerm1 = FXCollections.observableArrayList();
		rTerm2 = FXCollections.observableArrayList();
		
		selectedYearLong = new ListView<>(olYearLong);
		selectedTerm1 = new ListView<>(sTerm1);
		selectedTerm2 = new ListView<>(sTerm2);
		reserveTerm1 = new ListView<>(rTerm1);
		reserveTerm2 = new ListView<>(rTerm2);

		btnSubmit = new Button("Submit");
		btnSubmit.setPrefSize(70, 30);
		
		tfCreditsTerm2 = new TextField();
		tfCreditsTerm2.setEditable(false);
		tfCreditsTerm2.setText(Integer.toString(getCredit2()));
		tfCreditsTerm2.setPrefSize(60, 30);
		
		if(creditT2 <= 30) {
			tfCreditsTerm2.setStyle("-fx-background-color: #ff8c97;");//sets colour to red
//			tfCreditsTerm2.setStyle("fx-text-fill:white;");
		}
		
	
		VBox box5 = new VBox();
		box5.getChildren().addAll(lblSelectedYearLongModules, selectedYearLong);
		box5.setPrefHeight(70);
		box5.setMinHeight(70);
		
		VBox box6 = new VBox();
		box6.getChildren().addAll(lblReserveTerm1Modules, reserveTerm1);
		box6.setPrefHeight(70);
		box6.setMinHeight(70);
		
		VBox box7 = new VBox();
		box7.getChildren().addAll(lblReserveTerm2Modules, reserveTerm2);
		box7.setPrefHeight(70);
		box7.setMinHeight(70);
		
		HBox box8 = new HBox();
		box8.setAlignment(Pos.CENTER);
		box8.setSpacing(15);
		box8.setPadding(new Insets(20, 22, 20, 22));
		box8.getChildren().addAll(lblCreditsTerm2, tfCreditsTerm2);
		
		VBox vb1 = new VBox();
		vb1.getChildren().addAll(box5 ,lblSelectedTerm1Modules,selectedTerm1, box6, lblSelectedTerm2Modules, selectedTerm2, box7, box8, btnSubmit);
		vb1.setPrefHeight(500);
	
		this.add(vb1, 1, 0);
		
		this.getColumnConstraints().addAll(col1);
		this.getRowConstraints().addAll(r0);		
	}
	
	public void addModules(Module m) {		
	    if(m.getRunPlan() == Delivery.TERM_1 && !m.isMandatory()) {
	    		modulesTerm1.add(m);
	    }
	    else if (m.getRunPlan() == Delivery.TERM_1 && m.isMandatory()) {
	    	sTerm1.add(m);
	    	tfCreditsTerm1.setText(Integer.toString(creditT1 = creditT1 + 15));
	    }
	    else if(m.getRunPlan() == Delivery.TERM_2) {
	    	if(m.isMandatory() == true) {
	    		sTerm2.add(m);
	    		tfCreditsTerm2.setText(Integer.toString(creditT2 = creditT2 + 15));
	    	}
	    	else if ( m.isMandatory() == false){
	    		modulesTerm2.add(m);
	    	}
	    }  
	    else if (m.getRunPlan() == Delivery.YEAR_LONG) {
	    	olYearLong.add(m);
	    	tfCreditsTerm1.setText(Integer.toString(creditT1 = creditT1 + 15));
	    	tfCreditsTerm2.setText(Integer.toString(creditT2 = creditT2 + 15));
	    }
	}
	
	public Module getSelectedItemTerm1() {
		return unselectedTerm1.getSelectionModel().getSelectedItem();
	}
	
	public ObservableList<Module> getSelectedItemsTerm1() {
		return unselectedTerm1.getSelectionModel().getSelectedItems();
	}
	
	public Module getSelectedItemTerm2() {
		return unselectedTerm2.getSelectionModel().getSelectedItem();
	}
	
	public ObservableList<Module> getAllSelectedItemsTerm1() {
		return unselectedTerm1.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<Module> getAllSelectedItemsTerm2() {
		return unselectedTerm2.getSelectionModel().getSelectedItems();
	}
	
	public Module getAllSelectedItemTerm1() {
		return selectedTerm1.getSelectionModel().getSelectedItem();
	}
	
	public Module getAllSelectedItemTerm2() {
		return selectedTerm2.getSelectionModel().getSelectedItem();
	}
	
	public ObservableList<Module> getContents() {
		return modulesTerm1;
	}
	
	public ObservableList<Module> getContents2() {
		return modulesTerm2;
	}
	
	public ObservableList<Module> getContents3() {
		return olYearLong;
	}
	
	public ObservableList<Module> getContents4() {
		return sTerm1;
	}
	
	public ObservableList<Module> getContents5() {
		return sTerm2;
	}
	
	public ObservableList<Module> getContents6() {
		return rTerm1;
	}
	
	public ObservableList<Module> getContents7() {
		return rTerm2;
	}
	
	public int modulesTerm1SelectedIndex() {
		return unselectedTerm1.getSelectionModel().getSelectedIndex();
	}
	
	public int modulesTerm2SelectedIndex() {
		return unselectedTerm2.getSelectionModel().getSelectedIndex();
	}
	
	public int selectedT1SelectedIndex() {
		return selectedTerm1.getSelectionModel().getSelectedIndex();
	}
	
	public int selectedT2SelectedIndex() {
		return selectedTerm2.getSelectionModel().getSelectedIndex();
	}
	
	public int getCredit2() {
		return creditT2;
	}
	
	public int getCredit() {
		return creditT1;
	}
	
	public TextField getCreditTerm1() {
		return tfCreditsTerm1;
	}
	
	public TextField getCreditTerm2() {
		return tfCreditsTerm2;
	}
	
	public int setCredit(int creditT1) {
		this.creditT1 = creditT1;
		
		if(creditT1 == 60) {
			tfCreditsTerm1.setStyle("-fx-background-color: #70ff66;"); //sets colour to green
//			tfCreditsTerm1.setStyle("fx-text-fill:white;");
		}else {
			tfCreditsTerm1.setStyle("-fx-background-color: yellow;");
//			tfCreditsTerm1.setStyle("fx-text-;fill:white");
		}
		
		return creditT1;
	}
	
	public int setCredit2(int creditT2) {
		this.creditT2 = creditT2;
		
		if(creditT2 == 60) {
			tfCreditsTerm2.setStyle("-fx-background-color: #70ff66;"); //sets colour to green
//			tfCreditsTerm2.setStyle("fx-text-fill:white;");
		}else {
			tfCreditsTerm2.setStyle("-fx-background-color: yellow;");
//			tfCreditsTerm2.setStyle("fx-text-;fill:white");
		}
		
		return creditT2;
	}
	
	public void setModule(Set<Module> set) {
		for (Module m: set) {
			selectedTerm1.getSelectionModel().select(m);
		}
	}
	
	public void reset() {
		creditT1 = 0; 
		creditT2 = 0;
		sTerm1.clear();
		sTerm2.clear();
		olYearLong.clear();
		modulesTerm2.clear();
		modulesTerm1.clear();
		rTerm1.clear();
		rTerm2.clear();
		
		if(creditT2 < 60 && creditT1 < 60) {
			tfCreditsTerm1.setStyle("-fx-background-color: #ff8c97;"); //sets colour to red
			tfCreditsTerm2.setStyle("-fx-background-color: #ff8c97;"); //sets colour to red
		}
	}
	
	public void loadReset() {
		creditT1 = 15; 
		creditT2 = 15;
		sTerm1.clear();
		sTerm2.clear();
		olYearLong.clear();
		modulesTerm2.clear();
		modulesTerm1.clear();
		rTerm1.clear();
		rTerm2.clear();
		
		
		if(creditT2 < 60 && creditT1 < 60) {
			tfCreditsTerm1.setStyle("-fx-background-color: #ff8c97;"); //sets colour to red
			tfCreditsTerm2.setStyle("-fx-background-color: #ff8c97;"); //sets colour to red
		}
		
	}
	
	public void addAddTerm1Handler(EventHandler<ActionEvent> handler) {
		btnTerm1Add.setOnAction(handler);
	}
	
	public void addRemoveTerm1Handler(EventHandler<ActionEvent> handler) {
		t1btnRemove.setOnAction(handler);
	}
	
	public void addAddTerm2Handler(EventHandler<ActionEvent> handler) {
		btnTerm2Add.setOnAction(handler);
	}
	
	public void addRemoveTerm2Handler(EventHandler<ActionEvent> handler) {
		btnTerm2Remove.setOnAction(handler);
	}
	
	public void addResetHandler(EventHandler<ActionEvent> handler) {
		btnReset.setOnAction(handler);
	}
	
	public void addSubmitHandler(EventHandler<ActionEvent> handler) {
		btnSubmit.setOnAction(handler);
	}
	
	public void addReserve1Handler(EventHandler<ActionEvent> handler) {
		btnReserve1.setOnAction(handler);
	}
	
	public void addReserve2Handler(EventHandler<ActionEvent> handler) {
		btnReserve2.setOnAction(handler);
	}
	
	public BooleanBinding isItemSelectedTerm1() {
		return unselectedTerm1.getSelectionModel().selectedItemProperty().isNull();
	}
	
	public void addBtnDisableBindTerm1(BooleanBinding property) {
		btnTerm1Add.disableProperty().bind(property);
	}
	
	public BooleanBinding isItemSelectedTerm2() {
		return unselectedTerm2.getSelectionModel().selectedItemProperty().isNull();
	}
	
	public void addBtnDisableBindTerm2(BooleanBinding property) {
		btnTerm2Add.disableProperty().bind(property);
	}
	
	public BooleanBinding isItemSelectedTerm1Remove() {
		return selectedTerm1.getSelectionModel().selectedItemProperty().isNull();
	}
	
	public void addBtnDisableBindTerm1Remove(BooleanBinding property) {
		t1btnRemove.disableProperty().bind(property);
	}
	
	public BooleanBinding isItemSelectedTerm2Remove() {
		return selectedTerm2.getSelectionModel().selectedItemProperty().isNull();
	}
	
	public void addBtnDisableBindTerm2Remove(BooleanBinding property) {
		btnTerm2Remove.disableProperty().bind(property);
	}
	
	public void addBtnDisableBindReset(BooleanBinding property) {
		btnReset.disableProperty().bind(property);
	}
	
}
