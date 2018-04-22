package com.dlsc.workbenchfx.model.module;

import com.dlsc.workbenchfx.model.WorkbenchFxModel;
import javafx.scene.Node;

/**
 * Represents the base for a module, to be displayed in WorkbenchFX.
 *
 * @author François Martin
 * @author Marco Sanfratello
 */
public interface Module {

    /**
     * Returns the node to be displayed in the toolbar for the tab of this module.
     */
    Node getTab();

    /**
     * Returns the node of the tile to be displayed for this module in the home screen.
     */
    Node getTile();

    // Lifecycle
    /**
     * Gets called when the module is being opened from the overview for the first time.
     * @param workbenchModel the calling workbench object
     */
    void init(WorkbenchFxModel workbenchModel);

    /**
     * Gets called whenever the currently displayed content is being switched to this module.
     * @implNote if a module is being opened from the overview for the first time, it will
     * get initialized first by calling init(), afterwards activate() will be called.
     * @return content to be displayed in this module
     */
    Node activate();

    /**
     * Gets called whenever this module is the currently displayed content and the content is being
     * switched to another module.
     * @implNote Assuming Module 1 and Module 2, with both being already initialized and Module 1
     * being the currently displayed content.
     * When switching the content to Module 2, deactivate() gets called on Module 1,
     * followed by a call of activate() on Module 2.
     */
    void deactivate();

    /**
     * Gets called when this module is explicitly being closed by the user in the toolbar.
     * @implNote Assuming Module 1 and Module 2, with both being already initialized and Module 2
     * being the currently active and displayed module.
     * When calling destroy() on Module 1:
     * If true is returned, Module 2 will be removed Module 1 will be set as the active module.
     * If false is returned, Module 2 will not be removed and kept as the active module.
     * When implementing a closing dialog, make sure to switch to this module first, so the user
     * can see it, even if this module is being closed in its deactivated state, by calling:
     * {@code workbenchModel.openModule(this)} before opening the dialog.
     *
     * @return true if successful
     */
    boolean destroy();



}
