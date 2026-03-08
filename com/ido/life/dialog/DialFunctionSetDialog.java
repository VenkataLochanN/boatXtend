package com.ido.life.dialog;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.DialRoundRelativelayout;
import com.ido.life.customview.recyclerview.BaseLinearLayoutManager;
import com.ido.life.customview.recyclerview.DialFunctionAdapter;
import com.ido.life.data.api.entity.DialDefinedFunctionEntity;
import com.ido.life.module.device.activity.DialDefinedFunctionActivity;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialFunctionSetDialog extends BaseDialogFragment {
    private static String FUNCTION = "function";
    private static String ISSUPPORTCLOSE = "issupportclose";
    private static String POSITION = "position";
    DialDefinedFunctionEntity.Function current_function_select;

    @BindView(R.id.iv_dial_rl)
    DialRoundRelativelayout ivDialRl;
    private DailFunctionListener listener;
    private DialFunctionAdapter mAdapter;

    @BindView(R.id.function_close)
    RegularTextView mFuncionClose;
    List<DialDefinedFunctionEntity.Function> mListFunction;

    @BindView(R.id.function_recycler)
    RecyclerView mRecycleView;
    int position = 0;
    boolean isSupportClose = false;

    public interface DailFunctionListener {
        void onCancel();

        void onConfirm();
    }

    private void initItemDisplayStatus() {
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_dial_function_setting;
    }

    public static DialFunctionSetDialog getInstance(List<DialDefinedFunctionEntity.Function> list, int i, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(FUNCTION, (Serializable) list);
        bundle.putInt(POSITION, i);
        bundle.putBoolean(ISSUPPORTCLOSE, z);
        DialFunctionSetDialog dialFunctionSetDialog = new DialFunctionSetDialog();
        dialFunctionSetDialog.setArguments(bundle);
        dialFunctionSetDialog.setStyle(1, 2131886083);
        dialFunctionSetDialog.setCancelable(false);
        return dialFunctionSetDialog;
    }

    public void setListener(DailFunctionListener dailFunctionListener) {
        this.listener = dailFunctionListener;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mListFunction = (List) arguments.getSerializable(FUNCTION);
            this.position = arguments.getInt(POSITION);
            this.current_function_select = this.mListFunction.get(this.position);
            this.isSupportClose = arguments.getBoolean(ISSUPPORTCLOSE);
        }
        if (getActivity() instanceof DialDefinedFunctionActivity) {
            ((DialDefinedFunctionActivity) getActivity()).setFunctionDialogView(this.ivDialRl);
        }
        if (this.isSupportClose) {
            this.mFuncionClose.setVisibility(0);
        }
        this.mRecycleView.setLayoutManager(new BaseLinearLayoutManager(getActivity()));
        this.mAdapter = new DialFunctionAdapter(getActivity(), this.mListFunction, this.position, new DialFunctionAdapter.OnItemClickListener() { // from class: com.ido.life.dialog.DialFunctionSetDialog.1
            @Override // com.ido.life.customview.recyclerview.DialFunctionAdapter.OnItemClickListener
            public void onClick(int i) {
                if ((DialFunctionSetDialog.this.getActivity() instanceof DialDefinedFunctionActivity) && ((DialDefinedFunctionActivity) DialFunctionSetDialog.this.getActivity()).selectDefaultFunc == -1) {
                    return;
                }
                DialFunctionSetDialog dialFunctionSetDialog = DialFunctionSetDialog.this;
                dialFunctionSetDialog.current_function_select = dialFunctionSetDialog.mListFunction.get(i);
                DialFunctionSetDialog.this.mAdapter.updateSelect(i);
                ((DialDefinedFunctionActivity) DialFunctionSetDialog.this.getActivity()).modifyFunctionImage(DialFunctionSetDialog.this.mListFunction.get(i));
            }

            @Override // com.ido.life.customview.recyclerview.DialFunctionAdapter.OnItemClickListener
            public void selectView(int i) {
                DialFunctionSetDialog dialFunctionSetDialog = DialFunctionSetDialog.this;
                dialFunctionSetDialog.current_function_select = dialFunctionSetDialog.mListFunction.get(i);
            }
        });
        this.mRecycleView.setAdapter(this.mAdapter);
    }

    public void upDateSelectDefautFunction(int i) {
        this.position = i;
        this.current_function_select = this.mListFunction.get(i);
        this.mAdapter.updateSelect(i);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();
            window.getDecorView().setPadding(DipPixelUtil.dip2px(10.0f), (int) (ScreenUtil.getScreenH() * 0.05f), DipPixelUtil.dip2px(10.0f), DipPixelUtil.dip2px(15.0f));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.width = -1;
            window.setAttributes(attributes);
        }
        initItemDisplayStatus();
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initListener(View view) {
        super.initListener(view);
    }

    @Override // com.ido.common.base.BaseDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @OnClick({R.id.function_confirm, R.id.function_close, R.id.function_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.function_cancel /* 2131362231 */:
            case R.id.function_close /* 2131362232 */:
                DailFunctionListener dailFunctionListener = this.listener;
                if (dailFunctionListener != null) {
                    dailFunctionListener.onCancel();
                }
                dismissAllowingStateLoss();
                break;
            case R.id.function_confirm /* 2131362233 */:
                DailFunctionListener dailFunctionListener2 = this.listener;
                if (dailFunctionListener2 != null) {
                    if (this.current_function_select != null) {
                        dailFunctionListener2.onConfirm();
                    }
                }
                dismissAllowingStateLoss();
                break;
        }
    }
}