package com.ido.life.module.alexa;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import com.ido.life.module.alexa.SpinnerPopWindow;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SpinnerTextView extends AppCompatTextView implements SpinnerPopWindow.OnRecyclerItemClickListener, View.OnClickListener {
    private List<SpinnerData> dataList;
    private OnItemSelectListener onItemSelectListener;
    private SpinnerPopWindow popWindow;

    public interface OnItemSelectListener {
        void OnItemSelect(int i, SpinnerData spinnerData);
    }

    public SpinnerTextView(Context context) {
        super(context);
        this.dataList = new ArrayList();
    }

    public SpinnerTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.dataList = new ArrayList();
        init(context);
    }

    public SpinnerTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.dataList = new ArrayList();
        init(context);
    }

    private void init(Context context) {
        this.dataList = new ArrayList();
        this.popWindow = new SpinnerPopWindow(context, this.dataList, this);
        setOnClickListener(this);
    }

    public void setDataList(List<SpinnerData> list) {
        this.dataList = list;
        setNewData(list);
    }

    @Override // com.ido.life.module.alexa.SpinnerPopWindow.OnRecyclerItemClickListener
    public void onItemClick(int i, List<SpinnerData> list) {
        SpinnerData spinnerData = list.get(i);
        setText(spinnerData.getName());
        OnItemSelectListener onItemSelectListener = this.onItemSelectListener;
        if (onItemSelectListener != null) {
            onItemSelectListener.OnItemSelect(i, spinnerData);
        }
        this.popWindow.dismiss();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        SpinnerPopWindow spinnerPopWindow = this.popWindow;
        if (spinnerPopWindow == null) {
            return;
        }
        if (spinnerPopWindow.isShowing()) {
            this.popWindow.dismiss();
        } else {
            this.popWindow.showWindow(view);
        }
    }

    public void dismissPopWindow() {
        SpinnerPopWindow spinnerPopWindow = this.popWindow;
        if (spinnerPopWindow != null && spinnerPopWindow.isShowing()) {
            this.popWindow.dismiss();
        }
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    public void setNewData(List<SpinnerData> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.dataList = list;
        this.popWindow.setNewData(this.dataList);
    }
}