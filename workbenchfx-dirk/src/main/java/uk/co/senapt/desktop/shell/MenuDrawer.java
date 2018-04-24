package uk.co.senapt.desktop.shell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Skin;
import uk.co.senapt.desktop.shell.skins.MenuDrawerSkin;

import java.util.Objects;


/**
 * Created by lemmi on 22.08.17.
 */
public class MenuDrawer extends Control {

    private Shell shell;

    public MenuDrawer(Shell shell) {
        this.shell = Objects.requireNonNull(shell);

        getStyleClass().add("menu-drawer");

        Menu menu1 = new Menu("Customer");
        Menu menu2 = new Menu("Tariff Management");
        Menu menu3 = new Menu("Complaints");

        MenuItem item11 = new MenuItem("Item 1.1");
        MenuItem item12 = new MenuItem("Item 1.2");
        MenuItem item13 = new MenuItem("Item 1.3");
        MenuItem item14 = new MenuItem("Item 1.4");

        Menu item21 = new Menu("Item 2.1");
        MenuItem item22 = new MenuItem("Item 2.2");

        MenuItem item211 = new MenuItem("Item 2.1.1");
        MenuItem item212 = new MenuItem("Item 2.1.2");
        MenuItem item213 = new MenuItem("Item 2.1.3");
        MenuItem item214 = new MenuItem("Item 2.1.4");
        MenuItem item215 = new MenuItem("Item 2.1.5");

        MenuItem item31 = new MenuItem("Item 3.1");
        MenuItem item32 = new MenuItem("Item 3.2");
        MenuItem item33 = new MenuItem("Item 3.3");

        MenuItem itemA = new MenuItem("Complaints");
        MenuItem itemB = new MenuItem("Printing");
        MenuItem itemC = new MenuItem("Settings");

        menu1.getStyleClass().add("customer-management-icon-menu");
        menu2.getStyleClass().add("tariff-management-icon-menu");
        menu3.getStyleClass().add("complaints-icon-menu");

        itemA.getStyleClass().add("blank-icon-small");
        itemB.getStyleClass().add("blank-icon-small");
        itemC.getStyleClass().add("blank-icon-small");

        item21.getItems().addAll(item211,item212, item213, item214, item215);

        menu1.getItems().addAll(item11, item12, item13, item14);
        menu2.getItems().addAll(item21, item22);
        menu3.getItems().addAll(item31, item32, item33);

        getItems().addAll(menu1, menu2, menu3, itemA, itemB, itemC);

        MenuItem showRecentActivities = new MenuItem("Recent Activities");
        showRecentActivities.getStyleClass().add("menu-drawer-icon-recent-activities");
        showRecentActivities.setOnAction(evt -> shell.setShowLeftTray(true));
        getItems().add(showRecentActivities);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new MenuDrawerSkin(this);
    }

    public Shell getShell() {
        return shell;
    }

    // menu items support

    private final ObservableList<MenuItem> items = FXCollections.observableArrayList();

    public final ObservableList<MenuItem> getItems() {
        return items;
    }
}