package com.dlsc.workbenchfx.view.controls.module;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents the skin of the corresponding {@link Tab}.
 *
 * @author François Martin
 * @author Marco Sanfratello
 */
public class TabSkin extends SkinBase<Tab> {

  private static final Logger LOGGER = LogManager.getLogger(TabSkin.class.getName());
  private static final String STYLE_CLASS_ACTIVE_TAB = "active-tab";

  private HBox controlBox;
  private StackPane closeIconShape;
  private Button closeBtn;

  private Label nameLbl;

  private final ReadOnlyBooleanProperty activeTab;
  private final ReadOnlyStringProperty name;
  private final ReadOnlyObjectProperty<Node> icon;

  /**
   * Creates a new {@link TabSkin} object for a corresponding {@link Tab}.
   *
   * @param tab the {@link Tab} for which this Skin is created
   */
  public TabSkin(Tab tab) {
    super(tab);
    activeTab = tab.activeTabProperty();
    name = tab.nameProperty();
    icon = tab.iconProperty();

    initializeParts();
    layoutParts();
    setupBindings();
    setupEventHandlers();
    setupValueChangedListeners();

    updateIcon();

    getChildren().add(controlBox);
  }

  private void initializeParts() {
    closeIconShape = new StackPane();
    closeIconShape.getStyleClass().add("shape");
    closeBtn = new Button("", closeIconShape);
    closeBtn.getStyleClass().addAll("icon", "close-icon");

    nameLbl = new Label();
    nameLbl.getStyleClass().add("tab-name-lbl");

    controlBox = new HBox();
    controlBox.getStyleClass().addAll("tab-box", STYLE_CLASS_ACTIVE_TAB);
  }

  private void layoutParts() {
    Label iconPlaceholder = new Label(); // Will be replaced in the listener
    controlBox.getChildren().addAll(iconPlaceholder, nameLbl, closeBtn);
  }

  private void setupBindings() {
    nameLbl.textProperty().bind(name);
  }

  private void setupEventHandlers() {
    closeBtn.setOnAction(e -> getSkinnable().close());
  }

  private void setupValueChangedListeners() {
    // add or remove "active tab" style class, depending on state
    activeTab.addListener((observable, wasActive, isActive) -> {
      LOGGER.trace("Tab Factory - Was active: " + wasActive);
      LOGGER.trace("Tab Factory - Is active: " + isActive);
      if (isActive) {
        controlBox.getStyleClass().add(STYLE_CLASS_ACTIVE_TAB);
        LOGGER.trace("STYLE SET");
      } else {
        // switch from this to other tab
        controlBox.getStyleClass().remove(STYLE_CLASS_ACTIVE_TAB);
      }
    });

    // handle icon changes
    icon.addListener((observable, oldIcon, newIcon) -> {
      if (oldIcon != newIcon) {
        updateIcon();
      }
    });
  }

  /**
   * Replaces the Icon when calling setModule().
   */
  private void updateIcon() {
    Node iconNode = icon.get();
    ObservableList<Node> children = controlBox.getChildren();
    children.remove(0);
    children.add(0, iconNode);
    iconNode.getStyleClass().add("tab-icon");
  }
}
