package com.jacob.toolkit.toolkitforandroid.mvp.presenter.statemachine;

/**
 * @author jacob
 * @date 5/4/18
 */

public interface IModelView {

    void onNextAction(String trigger);

    void showMessage(String msg);
}
