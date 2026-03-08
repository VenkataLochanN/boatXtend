package com.ido.life.module.alexa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SpinnerPopWindow extends PopupWindow {
    private LayoutInflater inflater;
    private List<SpinnerData> list;
    private RecyclerView mListView;
    private OnRecyclerItemClickListener onItemClickListener;
    private PopUpAdapter popAdapter;

    public interface OnRecyclerItemClickListener {
        void onItemClick(int i, List<SpinnerData> list);
    }

    public SpinnerPopWindow(Context context, List<SpinnerData> list, OnRecyclerItemClickListener onRecyclerItemClickListener) {
        super(LayoutInflater.from(context).inflate(R.layout.spinner_window_layout, (ViewGroup) null), -2, (int) (((double) context.getResources().getDimensionPixelOffset(R.dimen.sw_dp_50)) * 7.8d), true);
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.onItemClickListener = onRecyclerItemClickListener;
        init();
    }

    private void init() {
        setFocusable(false);
        setAnimationStyle(R.style.alexa_pop_style);
        this.mListView = (RecyclerView) getContentView().findViewById(R.id.popup_listview);
        if (this.list == null) {
            this.list = new ArrayList();
        }
        RecyclerView recyclerView = this.mListView;
        PopUpAdapter popUpAdapter = new PopUpAdapter(this.list);
        this.popAdapter = popUpAdapter;
        recyclerView.setAdapter(popUpAdapter);
    }

    public void setNewData(List<SpinnerData> list) {
        this.list = list;
        this.popAdapter.setNewData(list);
    }

    public void showWindow(View view) {
        if (isShowing()) {
            return;
        }
        int[] iArr = new int[2];
        int dimensionPixelOffset = view.getResources().getDimensionPixelOffset(R.dimen.sw_dp_7);
        view.getLocationOnScreen(iArr);
        showAtLocation(view, 0, iArr[0] - dimensionPixelOffset, iArr[1] - dimensionPixelOffset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class PopUpAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<SpinnerData> list;

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int i) {
            return i;
        }

        public PopUpAdapter(List<SpinnerData> list) {
            this.list = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(SpinnerPopWindow.this.inflater.inflate(R.layout.adapter_alexa_language_list_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.languageImg.setImageResource(this.list.get(i).getImageRes());
            viewHolder.languageTv.setText(this.list.get(i).getName());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.list.size();
        }

        public void setNewData(List<SpinnerData> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        /* JADX INFO: Access modifiers changed from: private */
        class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView languageImg;
            public TextView languageTv;

            public ViewHolder(View view) {
                super(view);
                this.languageImg = (ImageView) view.findViewById(R.id.iv_language);
                this.languageTv = (TextView) view.findViewById(R.id.tv_language);
                view.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.alexa.-$$Lambda$SpinnerPopWindow$PopUpAdapter$ViewHolder$3TgGv4yLh4cRr34TOQdhSVIIJIA
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f$0.lambda$new$0$SpinnerPopWindow$PopUpAdapter$ViewHolder(view2);
                    }
                });
            }

            public /* synthetic */ void lambda$new$0$SpinnerPopWindow$PopUpAdapter$ViewHolder(View view) {
                if (SpinnerPopWindow.this.onItemClickListener != null) {
                    SpinnerPopWindow.this.onItemClickListener.onItemClick(getAdapterPosition(), PopUpAdapter.this.list);
                }
            }
        }
    }
}