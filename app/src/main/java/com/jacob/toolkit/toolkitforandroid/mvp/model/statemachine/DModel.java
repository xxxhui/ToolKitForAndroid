package com.jacob.toolkit.toolkitforandroid.mvp.model.statemachine;

import com.jacob.toolkit.toolkitforandroid.mvp.presenter.BasePresenter;
import com.jacob.toolkit.toolkitforandroid.mvp.presenter.statemachine.IModelView;
import com.jacob.toolkit.toolkitforandroid.mvp.presenter.statemachine.StateMachineManager;
import com.jacob.toolkit.toolkitforandroid.utils.LogHelper;

/**
 * @author: Jacob
 * @date: 2018/5/8
 */
public class DModel extends BasePresenter implements BaseModel{

    private IModelView mIModelView;

    public DModel(IModelView mIModelView) {
        this.mIModelView = mIModelView;
    }


    @Override
    public void start() {
        LogHelper.d(TAG, "start() called");
        mIModelView.onNextAction(StateMachineManager.TRIGGER_INIT);
        mIModelView.showMessage(TAG+"start() called");
    }

    @Override
    public void stop() {
        LogHelper.d(TAG, "stop() called");
        mIModelView.showMessage(TAG+"stop() called");
    }
}
