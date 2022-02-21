package controller;


import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Collection;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Course;
import model.Delivery;
import model.Module;
import model.Name;
import model.StudentProfile;
import view.CreateProfilePane;
import view.OptionsModuleChooserRootPane;
import view.ModuleMenuBar;
import view.OverviewSelectionPane;
import view.SelectModulesPane;



public class OptionsModuleChooserController {

	//fields to be used throughout the class
	private OptionsModuleChooserRootPane view;
	private CreateProfilePane cpp;
	private ModuleMenuBar mmb;
	private OverviewSelectionPane osp;
	private StudentProfile model;
	private SelectModulesPane smp;
	

	public OptionsModuleChooserController(OptionsModuleChooserRootPane view, StudentProfile model) {
		//initialise model and view fields
		this.model = model;
		this.view = view;
		cpp = view.getProfilePane();
		mmb = view.getMenuBar();
		osp = view.getOverviewSelectionPane();
		smp = view.getSelectModulesPane();
		
		cpp.populateComboBox(setupAndRetrieveCourses());

		this.attachEventHandlers();	
		this.attachBindings();

	}

	private void attachEventHandlers() {
		
		cpp.addCreateProfileListener(new CreateProfileHandler());
		
		mmb.addLoadHandler(new LoadProfileHandler());
		mmb.addSaveHandler(new SaveProfileHandler());
		mmb.addExitHandler(e -> System.exit(0));
		mmb.addAboutHandler(e -> this.alertDialogBuilder("About Dialog", null, "Options Module Chooser app v1.0"));
		
		smp.addAddTerm1Handler(new addTerm1Handlers());
		smp.addAddTerm2Handler(new addTerm2Handlers());
		smp.addReserve1Handler(new reserveTerm1Handlers());
		smp.addReserve2Handler(new reserveTerm2Handlers());
		smp.addRemoveTerm1Handler(new removeTerm1Handlers());
		smp.addRemoveTerm2Handler(new removeTerm2Handlers());
		smp.addResetHandler(new resetHandler());
		smp.addSubmitHandler(new submitHandlers());
		
		osp.addSaveListener(new SaveOverviewHandler());
	}
	
	public void attachBindings() {
		cpp.addBtnDisableBind(cpp.isEitherFieldEmpty());
		smp.addBtnDisableBindReset(cpp.isEitherFieldEmpty());//cpp as same rule for create profile should apply
		smp.addBtnDisableBindTerm1(smp.isItemSelectedTerm1());
		smp.addBtnDisableBindTerm2(smp.isItemSelectedTerm2());
		smp.addBtnDisableBindTerm1Remove(smp.isItemSelectedTerm1Remove());
		smp.addBtnDisableBindTerm2Remove(smp.isItemSelectedTerm2Remove());
		osp.addBtnDisableBind(osp.isTextFieldEmpty());
	}
	


	private Course[] setupAndRetrieveCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Delivery.TERM_1);
		Module imat3451 = new Module("IMAT3451", "Computing Project", 30, true, Delivery.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, Delivery.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, Delivery.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Delivery.TERM_1);
		Module ctec3426 = new Module("CTEC3426", "Telematics", 15, false, Delivery.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Delivery.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Delivery.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Delivery.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Delivery.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Delivery.TERM_2);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Delivery.TERM_1);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Delivery.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Delivery.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Popular Technology Ethics", 15, false, Delivery.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Delivery.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, Delivery.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Delivery.TERM_2);

		Course compSci = new Course("Computer Science");
		compSci.addModuleToCourse(imat3423);
		compSci.addModuleToCourse(imat3451);
		compSci.addModuleToCourse(ctec3902_CompSci);
		compSci.addModuleToCourse(ctec3110);
		compSci.addModuleToCourse(ctec3426);
		compSci.addModuleToCourse(ctec3605);
		compSci.addModuleToCourse(ctec3606);
		compSci.addModuleToCourse(ctec3410);
		compSci.addModuleToCourse(ctec3904);
		compSci.addModuleToCourse(ctec3905);
		compSci.addModuleToCourse(ctec3906);
		compSci.addModuleToCourse(imat3410);
		compSci.addModuleToCourse(imat3406);
		compSci.addModuleToCourse(imat3611);
		compSci.addModuleToCourse(imat3613);
		compSci.addModuleToCourse(imat3614);
		compSci.addModuleToCourse(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModuleToCourse(imat3423);
		softEng.addModuleToCourse(imat3451);
		softEng.addModuleToCourse(ctec3902_SoftEng);
		softEng.addModuleToCourse(ctec3110);
		softEng.addModuleToCourse(ctec3426);
		softEng.addModuleToCourse(ctec3605);
		softEng.addModuleToCourse(ctec3606);
		softEng.addModuleToCourse(ctec3410);
		softEng.addModuleToCourse(ctec3904);
		softEng.addModuleToCourse(ctec3905);
		softEng.addModuleToCourse(ctec3906);
		softEng.addModuleToCourse(imat3410);
		softEng.addModuleToCourse(imat3406);
		softEng.addModuleToCourse(imat3611);
		softEng.addModuleToCourse(imat3613);
		softEng.addModuleToCourse(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}

	
	private void alertDialogBuilder(String title, String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle(title);
	    alert.setHeaderText(header);
	    alert.setContentText(content);
	    alert.showAndWait();
	}
	
	private class SaveProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {          
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("moduleObj.dat"));) {

				oos.writeObject(model);
				oos.flush();
				
				view.fadeAnimation(); //invokes an animation in the view to give the user visual feedback
				alertDialogBuilder("Information Dialog", "Save success", "Student Profile Information is saved to moduleObj.dat");
				
			}
			catch (IOException ioExcep){
				System.out.println("Error saving");
				ioExcep.printStackTrace();
			}
		}
	}
	
	private class LoadProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("moduleObj.dat"));) {
				smp.loadReset();
				model = (StudentProfile) ois.readObject();	
				
				view.fadeAnimation(); //invokes an animation in the view to give the user visual feedback
				alertDialogBuilder("Information Dialog", "Load success", "Student Profile Information loaded from moduleObj.dat");
				
				Set<Module> set = model.getSelectedModules();
				
				cpp.refresh(model);
				for (Module m :set){
					 if(m.getRunPlan() == Delivery.TERM_1 ) {
						 smp.getContents4().add(m);
				    }
				    else if (m.getRunPlan() == Delivery.TERM_2) {
				    	 smp.getContents5().add(m);
				    }
				}
				
				Collection<Module> coll = model.getCourse().getModulesOnCourse();
				
				for (Module m : coll) {
					if (coll != set) {
					smp.addModules(m);
					}
					else if(coll == set) {

					}
				}
				
				osp.refresh(model);
	        }
	        catch (IOException ioExcep){
	            System.out.println("Error loading");
	            ioExcep.printStackTrace();
	        }
			catch (ClassNotFoundException c) {
	            System.out.println("Class Not found");
	        }
			
		}
	}
	
	public class CreateProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			String symbol = "@";			
			Name name = cpp.getNameInput();
			if(cpp.getPnumberInput().equals("") || name.getFirstName().equals("") || name.getFamilyName().equals("") || cpp.getEmaiInput().equals(""))  {
				alertDialogBuilder(AlertType.ERROR, "Error Alert!", null, "All fields are required to be filled!");				
			} 
			else if  (!cpp.getEmaiInput().contains(symbol)) {
				alertDialogBuilder(AlertType.ERROR, "Error Alert!", null, "Email format inputted is invalid!");
			}	
			else {
				model.setCourse(cpp.getCourseInput());
				
				model.setStudentName(cpp.getNameInput());
				
				model.setpNumber(cpp.getPnumberInput());
				
				model.setEmail(cpp.getEmaiInput());
				
				model.setDate(cpp.getDateInput());
				
				Collection<Module> coll = model.getCourse().getModulesOnCourse();
				smp.reset();
				osp.clearResults();
				for (Module m : coll) {
					view.getSelectModulesPane().addModules(m);
				}
				view.fadeAnimation(); //invokes an animation in the view to give the user visual feedback
				view.changeTab(1);
			}
		}
	}
	
	public class resetHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			view.fadeAnimation(); //invokes an animation in the view to give the user visual feedback
			smp.reset();
			
			Collection<Module> coll = model.getCourse().getModulesOnCourse();
			for (Module m : coll) {
				view.getSelectModulesPane().addModules(m);
				
			}
		}	
	}
	
	public class addTerm1Handlers implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {		
			Module term1 =  smp.getSelectedItemTerm1();
			
			ObservableList<Module>  v = smp.getContents4();
			int validate = 0;
			
			for (int i = 0; i < v.size(); i++) {
				validate++;
	    	}
			
			if( validate == 3) {
				alertDialogBuilder(AlertType.ERROR, "Error Alert!", null, "Max module credit reached for Term 1!");
				return;
			}
			//------------------------------------------------//
			
			if(term1.getRunPlan() == Delivery.TERM_1) {
		    	int index = smp.modulesTerm1SelectedIndex();
		    	
		    	ObservableList<Module> m = smp.getContents4();
		    	int counter = 0;
		    	
		    	for (int i = 0; i < m.size(); i++) {
		    		counter++;
		    	}
		    	if (index != -1) {
		    		if (counter < 3) {
		    			smp.getContents().removeAll(smp.getSelectedItemTerm1());
		    			smp.getContents4().addAll(term1);
		    			smp.getCreditTerm1().setText(Integer.toString(smp.setCredit(smp.getCredit() + 15)));		    		}
		    	}
		    } 
		}
	}
	
	public class addTerm2Handlers implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {		
			Module term2 =  smp.getSelectedItemTerm2();
			ObservableList<Module>  v = smp.getContents5();
			int validate = 0;
			
			for (int i = 0; i < v.size(); i++) {
				validate++;
	    	}
			
			if( validate == 3) {
				alertDialogBuilder(AlertType.ERROR, "Error Alert!", null, "Max module credit reached for Term 2!");
				return;
			}
			//------------------------------------------------//
			
			if (term2.getRunPlan() == Delivery.TERM_2) {
		 	    int index2 = smp.modulesTerm2SelectedIndex();
		 	    	
		 	    ObservableList<Module> m = smp.getContents5();
		 	    int counter = 0;
		 	    	
		 	    for (int i = 0; i < m.size(); i++) {
		 	    	counter++;
		 	    }
		 	    if (index2 != -1) {
		 	    	if (counter < 3) {
		 	    	smp.getContents2().removeAll(smp.getSelectedItemTerm2());
		 	    	smp.getContents5().addAll(term2);
		 	    	smp.getCreditTerm2().setText(Integer.toString(smp.setCredit2(smp.getCredit2() + 15)));
		 	    	}
		 	    }  
		    }
		}
	}
	

	public class reserveTerm1Handlers implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {		
			Module term1 =  smp.getSelectedItemTerm1();
			
			if(smp.getSelectedItemTerm1() == null) {
				alertDialogBuilder(AlertType.ERROR, "Error Alert!", null, "Please select a Module from Term 2!");
				return;
			}
			
			if(term1.getRunPlan() == Delivery.TERM_1) {
		    	int index = smp.modulesTerm1SelectedIndex();
		    	
		    	ObservableList<Module> m = smp.getContents6();
		    	int counter = 0;
		    	
		    	for (int i = 0; i < m.size(); i++) {
		    		counter++;
		    	}
		    	if (index != -1) {
		    		if (counter < 1) {
		    			smp.getContents().removeAll(smp.getSelectedItemsTerm1());
		    			smp.getContents6().addAll(term1);
		    		}
		    	}
		    } 
		}
	}
	
	public class reserveTerm2Handlers implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {		
			Module term2 =  smp.getSelectedItemTerm2();
			
			if(smp.getSelectedItemTerm2() == null) {
				alertDialogBuilder(AlertType.ERROR, "Error Alert!", null, "Please select a Module from Term 2!");
				return;
			}
			
			
			if (term2.getRunPlan() == Delivery.TERM_2) {
		 	    int index2 = smp.modulesTerm2SelectedIndex();
		 	    	
		 	    ObservableList<Module> m = smp.getContents7();
		 	    int counter = 0;
		 	    	
		 	    for (int i = 0; i < m.size(); i++) {
		 	    	counter++;
		 	    }
		 	    if (index2 != -1) {
		 	    	if (counter < 1) {
		 	    	smp.getContents2().removeAll(smp.getSelectedItemTerm2());
		 	    	smp.getContents7().addAll(term2);
		 	    	}
		 	    }  
		    }
		}
	}
	
	public class submitHandlers implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
	
			ObservableList<Module> term1 = smp.getContents4();
			ObservableList<Module> term2 = smp.getContents5();
			ObservableList<Module> reserveterm1 = smp.getContents6();
			ObservableList<Module> reserveterm2 = smp.getContents7();
			
		
			
			for (Module m : term1 ) {
				model.addSelectedModule(m);
			}
			for (Module m : term2 ) {
				model.addSelectedModule(m);
			}
			for (Module m : reserveterm1) {
				model.addSelectedReserveModule(m);
			}
			for (Module m : reserveterm2) {
				model.addSelectedReserveModule(m);
			}
			view.fadeAnimation(); //invokes an animation in the view to give the user visual feedback

			if (smp.getCredit() != 60 || smp.getCredit2() != 60) {
				alertDialogBuilder(AlertType.ERROR, "Error Alert!", null, "You are requried to pick 120 Credits!");
			}
			else {
				String overview = model.getOverview();
				osp.setResults(overview);
			
				view.changeTab(2);
			}
		}
	}
	
	public class removeTerm1Handlers implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module term1 =  smp.getAllSelectedItemTerm1();
			
			if(smp.getAllSelectedItemTerm1() == null) {
				alertDialogBuilder(AlertType.ERROR, "Error Alert!", null, "Select a Module from Term 1 to remove!");
				return;
			}
			
			if(smp.getAllSelectedItemTerm1().isMandatory()) {
				alertDialogBuilder(AlertType.ERROR, "Access Denied!", null, "You cannot remove a mandatory module!");
				return;
			}
			
			if(term1.getRunPlan() == Delivery.TERM_1) {
		    	int index = smp.selectedT1SelectedIndex();
		    	
		    	if (index != -1) {
		    		if (term1.getRunPlan() == Delivery.TERM_1 && term1.isMandatory()) {
			    		return;
		    		}
		    	smp.getContents4().removeAll(smp.getAllSelectedItemTerm1());
		    	smp.getContents().addAll(term1);
		    	smp.getCreditTerm1().setText(Integer.toString(smp.setCredit(smp.getCredit() - 15)));	
		    	}
		    } 
		}
	}
	
	public class removeTerm2Handlers implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module term1 =  smp.getAllSelectedItemTerm2();
			
			if(smp.getAllSelectedItemTerm2() == null) {
				alertDialogBuilder(AlertType.ERROR, "Error Alert!", null, "Select a Module from Term 2 to remove!");
				return;
			}
			
			if(smp.getAllSelectedItemTerm2().isMandatory()) {
				alertDialogBuilder(AlertType.ERROR, "Error Alert!", null, "This Module is mandatory and can not be removed!");
				return;
			}
			
			if(term1.getRunPlan() == Delivery.TERM_2) {
		    	int index = smp.selectedT2SelectedIndex();
		    	
		    	if (index != -1) {
		    		if (term1.getRunPlan() == Delivery.TERM_2 && term1.isMandatory()) {
			    		return;
			    }
		    	smp.getContents5().removeAll(smp.getAllSelectedItemTerm2());
		    	smp.getContents2().addAll(term1);
		    	smp.getCreditTerm2().setText(Integer.toString(smp.setCredit2(smp.getCredit2() - 15)));
		    	}
		    } 
		}
	}
	
	
	
	public class SaveOverviewHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			view.fadeAnimation(); //invokes an animation in the view to give the user visual feedback
			String oss = osp.getResults();
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		              new FileOutputStream("ModuleChooser.txt"), "utf-8"))) {
				writer.write(oss);
				alertDialogBuilder("Information Dialog", "Save success", "Overview saved to ModuleChooser.txt");
				view.fadeAnimation(); //invokes an animation in the view to give the user visual feedback
			} catch (UnsupportedEncodingException e1) {
				alertDialogBuilder("Information Dialog", "Error", "Error saving to ModuleChooser.txt");
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				alertDialogBuilder("Information Dialog", "Error", "Error saving to ModuleChooser.txt");
				e1.printStackTrace();
			} catch (IOException e1) {
				alertDialogBuilder("Information Dialog", "Error", "Error saving to ModuleChooser.txt");
				e1.printStackTrace();
			}
					
		}
	}

	private void alertDialogBuilder(AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
