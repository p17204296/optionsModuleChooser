package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;



public class StudentProfile implements Serializable {

	private String pNumber;
	private Name studentName;
	private String email;
	private LocalDate date;
	private Course course;
	private Set<Module> selectedModules, reserveSelectedModules;
	
	public StudentProfile() {
		pNumber = "";
		studentName = new Name();
		email = "";
		date = null;
		course = null;
		selectedModules = new TreeSet<Module>();
		reserveSelectedModules =  new TreeSet<Module>();
	}
	
	public StudentProfile(String PNumber, String Email) {
		this.pNumber = PNumber;
		this.email = Email;
	}
	
	public String getpNumber() {
		return pNumber;
	}
	
	public void setpNumber(String pNumber) {
		this.pNumber = pNumber;
	}
	
	public Name getStudentName() {
		return studentName;
	}
	
	public void setStudentName(Name studentName) {
		this.studentName = studentName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public boolean addSelectedModule(Module m) {
		return selectedModules.add(m);
	}
	
	public boolean addSelectedReserveModule(Module m) {
		return reserveSelectedModules.add(m);
	}
	
	public Set<Module> getSelectedModules() {
		return selectedModules;
	}
	
	public Set<Module> getReserveSelectedModules() {
		return reserveSelectedModules;
	}
	
	public void clearSelectedModules() {
		selectedModules.clear();
	}
	
	public String getOverview() {
		String results = "Name: " + getStudentName().getFirstName() + " " + getStudentName().getFamilyName() + "\n" ;
		results += "PNumber: " + getpNumber() + "\n";
		results += "Email: " + getEmail() + "\n";
		results += "Date: " + getDate() + "\n";
		results += "Course: " + getCourse().getCourseName()  + "\n";
		results += "\n";
		results += "Modules Selected";
		results += "\n==============\n";
		for (Module m: selectedModules ) {
			results +=   "Module Code: " + m.getModuleCode() + " Module Name: " + m.getModuleName() + "\n";
			results +=   "Credits: " + m.getCredits() + " Mandotary on your course? " + m.isMandatory() + " Delivery: " 
						+ m.getRunPlan().toString().replace("TERM_1", "term 1").replace("TERM_2", "term 2").replace("YEAR_LONG", "year long") + "\n";
			results += "\n";
		}
		
		results += "Modules Reserved";
		results += "\n==============\n";
		for (Module m: reserveSelectedModules ) {
			results +=   "Module Code: " + m.getModuleCode() + " Module Name: " + m.getModuleName() + "\n";
			results +=   "Credits: " + m.getCredits() + " Mandotary on your course? " + m.isMandatory() + " Delivery: " 
						+ m.getRunPlan().toString().replace("TERM_1", "term 1").replace("TERM_2", "term 2") + "\n";
			results += "\n";
		}
		
		return results;
	}
	
	@Override
	public String toString() {
		return "StudentProfile:[pNumber=" + pNumber + ", studentName="
				+ studentName + ", email=" + email + ", date="
				+ date + ", course=" + course.actualToString() 
				+ ", selectedModules=" + selectedModules + "]";
	}
	
}
