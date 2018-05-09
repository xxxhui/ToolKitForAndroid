package com.jacob.toolkit.toolkitforandroid.ui.activity;

import android.text.TextUtils;
import android.widget.TextView;

import com.jacob.toolkit.toolkitforandroid.R;
import com.jacob.toolkit.toolkitforandroid.mvp.presenter.statemachine.StateMachinePresenter;
import com.jacob.toolkit.toolkitforandroid.mvp.view.IStateMachineView;
import com.jacob.toolkit.toolkitforandroid.ui.BaseActivity;

import butterknife.BindView;

/**
 * @author: Jacob
 * @date: 2018/5/8
 */
public class StateMachineActivity extends BaseActivity {

    @BindView(R.id.msg)
    TextView mTextView;

    @Override
    protected void initView() {
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_statemachine);

    }

    @Override
    protected void initPresenter() {
        new StateMachinePresenter(mIStateMachineView).start();
    }

    private IStateMachineView mIStateMachineView = new IStateMachineView() {
        @Override
        public void showMessage(String msg) {

            String previousStr = (String) mTextView.getText();
            if (TextUtils.isEmpty(previousStr)) {
                mTextView.setText(msg);
            }else{
                mTextView.setText(previousStr+"\n"+msg);
            }
        }
    };

}
