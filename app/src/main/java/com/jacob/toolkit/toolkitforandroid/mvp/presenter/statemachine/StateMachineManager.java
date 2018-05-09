package com.jacob.toolkit.toolkitforandroid.mvp.presenter.statemachine;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;

/**
 * @author jacob
 * @date 5/4/18
 */

public class StateMachineManager {

    public static final String STATE_INIT = "STATE_INIT";
    public static final String STATE_A = "STATE_A";
    public static final String STATE_B = "STATE_B";
    public static final String STATE_C = "STATE_C";
    public static final String STATE_D = "STATE_D";


    public static final String TRIGGER_INIT = "TRIGGER_INIT";
    public static final String TRIGGER_A= "TRIGGER_A";
    public static final String TRIGGER_B = "TRIGGER_B";
    public static final String TRIGGER_C = "TRIGGER_C";
    public static final String TRIGGER_D= "TRIGGER_D";


    protected StateMachineConfig<String, String> mConfig = new StateMachineConfig<>();
    protected StateMachine<String, String> mStateMachine;
    private StateMachineInterface mStateMachineInterface;


    public StateMachineManager(StateMachineInterface stateMachineInterface) {
        mStateMachineInterface = stateMachineInterface;

        mConfig.configure(STATE_INIT)
                .permit(TRIGGER_INIT,STATE_A);

        mConfig.configure(STATE_A)
                .onEntry(mStateMachineInterface.enterAModel())
                .onExit(mStateMachineInterface.exitAModel())
                .permit(TRIGGER_B, STATE_B);

        mConfig.configure(STATE_B)
                .onEntry(mStateMachineInterface.enterBModel())
                .onExit(mStateMachineInterface.exitBModel())
                .permit(TRIGGER_C, STATE_C);

        mConfig.configure(STATE_C)
                .onEntry(mStateMachineInterface.enterCModel())
                .onExit(mStateMachineInterface.exitCModel())
                .permit(TRIGGER_D, STATE_D);

        mConfig.configure(STATE_D)
                .onEntry(mStateMachineInterface.enterDModel())
                .onExit(mStateMachineInterface.exitDModel())
                .permit(TRIGGER_A, STATE_A);

        mStateMachine = new StateMachine<>(STATE_INIT, mConfig);

    }

    public void fire(String trigger) {
        mStateMachine.fire(trigger);
    }

    public boolean canFire(String trigger) {
        return mStateMachine.canFire(trigger);
    }

    public String getState() {
        return mStateMachine.getState();
    }
}
