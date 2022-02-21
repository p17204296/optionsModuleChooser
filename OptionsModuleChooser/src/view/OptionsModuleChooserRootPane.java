package view;


import javafx.animation.FadeTransition;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

//You may change this class to extend another type if you wish
public class OptionsModuleChooserRootPane extends BorderPane {
	
	private ModuleMenuBar mmb;
	private CreateProfilePane cpp;
	private SelectModulesPane smp;
	private OverviewSelectionPane osp;

	private TabPane tp;
	
	public OptionsModuleChooserRootPane() {
		this.setStyle("-fx-background-color: #EBF6FF;");

		mmb = new ModuleMenuBar();
		BorderPane borderPane  = new BorderPane();
		
		cpp = new CreateProfilePane();
		osp = new OverviewSelectionPane();
		smp = new SelectModulesPane();

		tp = new TabPane();
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); 
		
		Tab t1 = new Tab("Create Profile", cpp);
		Tab t2 = new Tab("Select Modules", smp);
		Tab t3 = new Tab("Overview Selection", osp);
		tp.getTabs().addAll(t1, t2, t3);

		
		this.setTop(mmb);
		this.setCenter(tp);
		
		this.getChildren().addAll(borderPane);	
	}
	
	public ModuleMenuBar getMenuBar() {
		return mmb;
	}
	
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}
	public CreateProfilePane getProfilePane() {
		return cpp;
	}
	
	public SelectModulesPane getSelectModulesPane() {
		return smp;
	}
	
	public OverviewSelectionPane getOverviewSelectionPane() {
		return osp;
	}

	//animation fade effects used by the controller to give visual feedback of certain operations
	public void fadeAnimation() {
		// TODO Auto-generated method stub
		FadeTransition ft = new FadeTransition(Duration.millis(1000), this);
		ft.setFromValue(1.0);
		ft.setToValue(0.1);
		ft.setCycleCount(2);
		ft.setAutoReverse(true);
		ft.play();
	}

	
}
