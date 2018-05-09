package com.jacob.toolkit.toolkitforandroid.mvp.presenter.statemachine;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.github.oxo42.stateless4j.delegates.Action;
import com.jacob.toolkit.toolkitforandroid.mvp.model.statemachine.AModel;
import com.jacob.toolkit.toolkitforandroid.mvp.model.statemachine.BModel;
import com.jacob.toolkit.toolkitforandroid.mvp.model.statemachine.CModel;
import com.jacob.toolkit.toolkitforandroid.mvp.model.statemachine.DModel;
import com.jacob.toolkit.toolkitforandroid.mvp.view.IStateMachineView;
import com.jacob.toolkit.toolkitforandroid.utils.LogHelper;

/**
 * @author jacob
 * @date 5/4/18
 */

public class StateMachinePresenter {


    private static final String TAG = "StateMachinePresenter";
    private static final int FIRE_EVENT = 88;

    private IStateMachineView mIStateMachineView;

    private StateMachineManager mStateMachineManager;
    private AModel mAmodel;
    private BModel mBmodel;
    private CModel mCmodel;
    private DModel mDmodel;


    private Handler mHandler;

    private Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case FIRE_EVENT:
                    if (mStateMachineManager.canFire((String) msg.obj)) {
                        LogHelper.d(TAG, "fire trigger: " + msg.obj + " current state: " + mStateMachineManager.getState());
                        mStateMachineManager.fire((String) msg.obj);
                    } else {
                        LogHelper.d(TAG, "fire trigger failed: " + msg.obj + " current state: " + mStateMachineManager.getState());
                    }
                    break;
            }
            return false;
        }
    };


    public StateMachinePresenter(IStateMachineView mIStateMachineView) {
        this.mIStateMachineView = mIStateMachineView;
    }

    public void start() {
        mAmodel = new AModel(mIModelView);
        mBmodel = new BModel(mIModelView);
        mCmodel = new CModel(mIModelView);
        mDmodel = new DModel(mIModelView);

        HandlerThread thread = new HandlerThread("state change");
        thread.start();
        mHandler = new Handler(thread.getLooper(), callback);

        mStateMachineManager = new StateMachineManager(mStateMachineInterface);

        mStateMachineManager.fire(StateMachineManager.TRIGGER_INIT);
    }


    private IModelView mIModelView = new IModelView() {
        @Override
        public void onNextAction(String trigger) {
            Message msg = mHandler.obtainMessage(FIRE_EVENT, trigger);
            mHandler.sendMessage(msg);
        }

        @Override
        public void showMessage(String msg) {
           mIStateMachineView.showMessage(msg);
        }

    };


    private StateMachineInterface mStateMachineInterface = new StateMachineInterface() {

        @Override
        public Action enterAModel() {
            return new Action() {
                @Override
                public void doIt() {
                    mAmodel.start();
                }
            };
        }

        @Override
        public Action exitAModel() {
            return new Action() {
                @Override
                public void doIt() {
                    mAmodel.stop();
                }
            };
        }
        @Override
        public Action enterBModel() {
            return new Action() {
                @Override
                public void doIt() {
                    mBmodel.start();
                }
            };
        }

        @Override
        public Action exitBModel() {
            return new Action() {
                @Override
                public void doIt() {
                    mBmodel.stop();
                }
            };
        }

        @Override
        public Action enterCModel() {
            return new Action() {
                @Override
                public void doIt() {
                    mCmodel.start();
                }
            };
        }

        @Override
        public Action exitCModel() {
            return new Action() {
                @Override
                public void doIt() {
                    mCmodel.stop();
                }
            };
        }

        @Override
        public Action enterDModel() {
            return new Action() {
                @Override
                public void doIt() {
                    mDmodel.start();
                }
            };
        }

        @Override
        public Action exitDModel() {
            return new Action() {
                @Override
                public void doIt() {
                    mDmodel.stop();
                }
            };
        }

    };

}
