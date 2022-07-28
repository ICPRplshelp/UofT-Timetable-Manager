package org.phase2.controllerfactories;

public interface ControllerBuilder<T> {

    // the different ControllerBuilders return different Controller types
    // todo: maybe remove this interface altogether?
    T getController();
}
