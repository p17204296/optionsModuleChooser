package view;


import java.time.LocalDate;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Course;
import model.Name;
import model.StudentProfile;

public class CreateProfilePane extends GridPane {
	
	private ComboBox<Course> cboCourses;
	private TextField txtPnumber, txtFirstName, txtSurname, txtEmail;
	private DatePicker date;
	private Button btnCreateProfile;	
	
	public CreateProfilePane() {
		
		this.setPadding(new Insets(80, 80, 80, 80));
		this.setVgap(15);
		this.setHgap(20);
		this.setStyle("-fx-background-color: #EBF6FF;");
		this.setAlignment(Pos.CENTER);
			
		//create labels
		Label lblCourse = new Label("Select Course: ");
		lblCourse.setStyle("-fx-font-weight: bold;");
		lblCourse.setAlignment(Pos.CENTER_RIGHT);
		lblCourse.setMaxWidth(Double.MAX_VALUE);
		Label lblPnumber = new Label("Input P Number: ");
		lblPnumber.setStyle("-fx-font-weight: bold;");
		lblPnumber.setAlignment(Pos.CENTER_RIGHT);
		lblPnumber.setMaxWidth(Double.MAX_VALUE);
		Label lblFirstName = new Label("Input First Name: ");
		lblFirstName.setStyle("-fx-font-weight: bold;");
		lblFirstName.setAlignment(Pos.CENTER_RIGHT);
		lblFirstName.setMaxWidth(Double.MAX_VALUE);
		Label lblSurname = new Label("Input Surname: ");
		lblSurname.setStyle("-fx-font-weight: bold;");
		lblSurname.setAlignment(Pos.CENTER_RIGHT);
		lblSurname.setMaxWidth(Double.MAX_VALUE);
		Label lblEmail = new Label("Input Email: ");
		lblEmail.setStyle("-fx-font-weight: bold;");
		lblEmail.setAlignment(Pos.CENTER_RIGHT);
		lblEmail.setMaxWidth(Double.MAX_VALUE);
		Label lblDate = new Label("Input Date: ");
		lblDate.setStyle("-fx-font-weight: bold;");
		lblDate.setAlignment(Pos.CENTER_RIGHT);
		lblDate.setMaxWidth(Double.MAX_VALUE);

		
		// setup combobox
		cboCourses = new ComboBox<Course>();
		
		// setup text fields
		txtPnumber = new TextField();
		txtFirstName = new TextField();
		txtSurname = new TextField();
		txtEmail = new TextField();
		date = new DatePicker();
		
		//initialise create butto
		btnCreateProfile = new Button("Create Profile");
		
		this.add(lblCourse, 0, 0);
		this.add(cboCourses, 1, 0);
		
		this.add(lblPnumber, 0, 1);
		this.add(txtPnumber, 1, 1);
		
		this.add(lblFirstName, 0, 2);
		this.add(txtFirstName, 1, 2);
		
		this.add(lblSurname, 0, 3);
		this.add(txtSurname, 1, 3);
		
		this.add(lblEmail, 0, 4);
		this.add(txtEmail, 1, 4);
		
		this.add(lblDate, 0, 5);
		this.add(date, 1, 5);
		
		this.add(new HBox(), 0, 6);
		this.add(btnCreateProfile, 1, 6);
		System.out.println(cboCourses.getValue());
	}

	
	public void populateComboBox(Course[] c) {
		cboCourses.getItems().addAll(c);
		cboCourses.getSelectionModel().select(0);
	}
	
	
	public Course getCourseInput() {
		return cboCourses.getSelectionModel().getSelectedItem();
	}
	
	public String getPnumberInput() {
		return txtPnumber.getText();
	}
	
	public String getEmaiInput() {
		return txtEmail.getText();
	}
	
	public void addCreateProfileListener(EventHandler<ActionEvent> handler) {
		btnCreateProfile.setOnAction(handler);
	}
	
	public Name getNameInput() {
		String firstName =  txtFirstName.getText();
		String Surname = txtSurname.getText();
		return new Name(firstName, Surname);
	}
	
	
	public StudentProfile getProfileInput() {
		String Pnumber = txtPnumber.getText();
		String Email = txtEmail.getText();
		return new StudentProfile(Pnumber, Email);
	}
	
	public LocalDate getDateInput() {
		return date.getValue();
	}
	
	public void refresh(StudentProfile m){
		cboCourses.setValue(m.getCourse());
		txtPnumber.setText(m.getpNumber());
		txtFirstName.setText(m.getStudentName().getFirstName());
		txtSurname.setText(m.getStudentName().getFamilyName());
		txtEmail.setText(m.getEmail());
		date.setValue(m.getDate());
	}

	public BooleanBinding isEitherFieldEmpty() {
		return txtPnumber.textProperty().isEmpty().or(txtFirstName.textProperty().isEmpty()).or(txtSurname.textProperty().isEmpty()).or(txtEmail.textProperty().isEmpty().or(date.valueProperty().isNull()));
	}
	
	public void addBtnDisableBind(BooleanBinding property) {
		btnCreateProfile.disableProperty().bind(property);
	}
	
}
