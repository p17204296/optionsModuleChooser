package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;

public class ModuleMenuBar extends MenuBar{
	
	//declared for access throughout class, as handlers are now attached via methods - see towards end of class
	private MenuItem loadItem, saveItem, exitItem, aboutItem;

	public ModuleMenuBar() {
		
		//temp vars for menus and menu items within this MenuBar
		Menu menu;
		
		menu = new Menu("_File");
		
		loadItem = new MenuItem("_Load");
		loadItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+L"));
		menu.getItems().add(loadItem);
		
		saveItem = new MenuItem("_Save");
		saveItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+S"));
		menu.getItems().add(saveItem);
	
		menu.getItems().add(new SeparatorMenuItem());
	
		exitItem = new MenuItem("E_xit");
		exitItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+X"));
		menu.getItems().add(exitItem);
		
		this.getMenus().add(menu);
		
		menu = new Menu("_Help");
		
		aboutItem = new MenuItem("_About");
		aboutItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+A"));
		menu.getItems().add(aboutItem);
		
		this.getMenus().add(menu);
	}
	
	public void addLoadHandler(EventHandler<ActionEvent> handler) {
		loadItem.setOnAction(handler);
	}
    
    public void addSaveHandler(EventHandler<ActionEvent> handler) {
    	saveItem.setOnAction(handler);
  	}
    
    public void addExitHandler(EventHandler<ActionEvent> handler) {
  		exitItem.setOnAction(handler);
  	}
    
    public void addAboutHandler(EventHandler<ActionEvent> handler) {
  		aboutItem.setOnAction(handler);
  	}

}
