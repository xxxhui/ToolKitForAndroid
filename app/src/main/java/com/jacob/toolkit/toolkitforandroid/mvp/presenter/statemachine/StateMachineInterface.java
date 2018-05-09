package com.jacob.toolkit.toolkitforandroid.mvp.presenter.statemachine;

import com.github.oxo42.stateless4j.delegates.Action;

/**
 * @author jacob
 * @date 3/26/18
 */

public interface StateMachineInterface {

    Action enterAModel();

    Action exitAModel();

    Action enterBModel();

    Action exitBModel();

    Action enterCModel();

    Action exitCModel();

    Action enterDModel();

    Action exitDModel();



}
