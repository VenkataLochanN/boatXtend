package com.ido.life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.bean.ShortcutAppData;
import com.ido.life.viewholder.shortcut.BaseShortcutAppViewHolder;
import com.ido.life.viewholder.shortcut.ShortcutAppViewHolderManager;
import com.ido.life.viewholder.shortcut.UnknowShortcutAppViewHolder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShortcutAppEditAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 62\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003678B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u001a\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\b¢\u0006\u0002\u0010\tJ\u000e\u0010(\u001a\u00020\u00152\u0006\u0010)\u001a\u00020\u0015J\u0010\u0010*\u001a\u0004\u0018\u00010\u00072\u0006\u0010+\u001a\u00020\u0015J\b\u0010,\u001a\u00020\u0015H\u0016J\u0006\u0010-\u001a\u00020\u0015J\u0010\u0010.\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u0015H\u0016J\u000e\u0010/\u001a\u00020\u00152\u0006\u0010)\u001a\u00020\u0015J\u0018\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00022\u0006\u0010+\u001a\u00020\u0015H\u0016J\u0018\u00103\u001a\u00020\u00022\u0006\u00104\u001a\u0002052\u0006\u0010)\u001a\u00020\u0015H\u0016R.\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u00069"}, d2 = {"Lcom/ido/life/adapter/ShortcutAppEditAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "mContext", "Landroid/content/Context;", "appDatas", "Ljava/util/ArrayList;", "Lcom/ido/life/bean/ShortcutAppData;", "Lkotlin/collections/ArrayList;", "(Landroid/content/Context;Ljava/util/ArrayList;)V", "getAppDatas", "()Ljava/util/ArrayList;", "setAppDatas", "(Ljava/util/ArrayList;)V", "value", "", "isEdit", "()Z", "setEdit", "(Z)V", "mBottomMargin", "", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mItemHeight", "mSmallItemWidth", "onItemDeleteClickListener", "Lcom/ido/life/adapter/ShortcutAppEditAdapter$OnItemDeleteClickListener;", "getOnItemDeleteClickListener", "()Lcom/ido/life/adapter/ShortcutAppEditAdapter$OnItemDeleteClickListener;", "setOnItemDeleteClickListener", "(Lcom/ido/life/adapter/ShortcutAppEditAdapter$OnItemDeleteClickListener;)V", "onItemLongClickListener", "Lcom/ido/life/adapter/ShortcutAppEditAdapter$OnItemLongClickListener;", "getOnItemLongClickListener", "()Lcom/ido/life/adapter/ShortcutAppEditAdapter$OnItemLongClickListener;", "setOnItemLongClickListener", "(Lcom/ido/life/adapter/ShortcutAppEditAdapter$OnItemLongClickListener;)V", "getAppUnique", "viewType", "getItem", "position", "getItemCount", "getItemHeight", "getItemViewType", "getShowType", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "Companion", "OnItemDeleteClickListener", "OnItemLongClickListener", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ShortcutAppEditAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "ShortcutAppEditAdapter";
    private ArrayList<ShortcutAppData> appDatas;
    private boolean isEdit;
    private int mBottomMargin;
    private Context mContext;
    private int mItemHeight;
    private int mSmallItemWidth;
    private OnItemDeleteClickListener onItemDeleteClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    /* JADX INFO: compiled from: ShortcutAppEditAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/adapter/ShortcutAppEditAdapter$OnItemDeleteClickListener;", "", "onItemDeleteClick", "", "position", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface OnItemDeleteClickListener {
        void onItemDeleteClick(int position);
    }

    /* JADX INFO: compiled from: ShortcutAppEditAdapter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/ido/life/adapter/ShortcutAppEditAdapter$OnItemLongClickListener;", "", "onItemLongClick", "", "position", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public final int getAppUnique(int viewType) {
        if (viewType > 0) {
            return viewType & ViewCompat.MEASURED_SIZE_MASK;
        }
        return -1;
    }

    public final int getShowType(int viewType) {
        if (viewType > 0) {
            return viewType >> 24;
        }
        return -1;
    }

    public final ArrayList<ShortcutAppData> getAppDatas() {
        return this.appDatas;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setAppDatas(ArrayList<ShortcutAppData> arrayList) {
        this.appDatas = arrayList;
    }

    public final void setMContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.mContext = context;
    }

    public ShortcutAppEditAdapter(Context mContext, ArrayList<ShortcutAppData> arrayList) {
        Intrinsics.checkParameterIsNotNull(mContext, "mContext");
        this.mContext = mContext;
        this.appDatas = arrayList;
        this.mSmallItemWidth = (ScreenUtil.getScreenW() - DipPixelUtil.dip2px(75.0f)) / 2;
        this.mItemHeight = this.mSmallItemWidth;
        this.mBottomMargin = DipPixelUtil.dip2px(100.0f);
    }

    public final OnItemDeleteClickListener getOnItemDeleteClickListener() {
        return this.onItemDeleteClickListener;
    }

    public final void setOnItemDeleteClickListener(OnItemDeleteClickListener onItemDeleteClickListener) {
        this.onItemDeleteClickListener = onItemDeleteClickListener;
    }

    public final OnItemLongClickListener getOnItemLongClickListener() {
        return this.onItemLongClickListener;
    }

    public final void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    /* JADX INFO: renamed from: isEdit, reason: from getter */
    public final boolean getIsEdit() {
        return this.isEdit;
    }

    public final void setEdit(boolean z) {
        this.isEdit = z;
        notifyDataSetChanged();
    }

    /* JADX INFO: renamed from: getItemHeight, reason: from getter */
    public final int getMItemHeight() {
        return this.mItemHeight;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int shortcutAppSmallLayoutRes;
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        int showType = getShowType(viewType);
        int appUnique = getAppUnique(viewType);
        if (showType == 1) {
            shortcutAppSmallLayoutRes = ShortcutAppViewHolderManager.INSTANCE.getInstance().getShortcutAppBigLayoutRes(appUnique);
        } else {
            shortcutAppSmallLayoutRes = ShortcutAppViewHolderManager.INSTANCE.getInstance().getShortcutAppSmallLayoutRes(appUnique);
        }
        View itemView = LayoutInflater.from(this.mContext).inflate(R.layout.item_shortcut_app_container_big, parent, false);
        View viewFindViewById = itemView.findViewById(R.id.vsContainer);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "itemView.findViewById(R.id.vsContainer)");
        ViewStub viewStub = (ViewStub) viewFindViewById;
        viewStub.setLayoutResource(shortcutAppSmallLayoutRes);
        viewStub.inflate();
        try {
            Constructor<? extends BaseShortcutAppViewHolder> constructor = ShortcutAppViewHolderManager.INSTANCE.getInstance().getShortcutAppViewHolder(appUnique).getConstructor(ShortcutAppEditAdapter.class, View.class);
            Intrinsics.checkExpressionValueIsNotNull(constructor, "viewHolderClazz.getConst…s.java, View::class.java)");
            BaseShortcutAppViewHolder baseShortcutAppViewHolderNewInstance = constructor.newInstance(this, itemView);
            if (baseShortcutAppViewHolderNewInstance != null) {
                return baseShortcutAppViewHolderNewInstance;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.ido.life.viewholder.shortcut.BaseShortcutAppViewHolder");
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
            return new UnknowShortcutAppViewHolder(this, itemView);
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
            return new UnknowShortcutAppViewHolder(this, itemView);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        if (holder instanceof BaseShortcutAppViewHolder) {
            BaseShortcutAppViewHolder baseShortcutAppViewHolder = (BaseShortcutAppViewHolder) holder;
            baseShortcutAppViewHolder.onBind(getItem(position));
            ImageView ivDeleteShortcutApp = baseShortcutAppViewHolder.getIvDeleteShortcutApp();
            if (ivDeleteShortcutApp != null) {
                ivDeleteShortcutApp.setVisibility(this.isEdit ? 0 : 8);
            }
            ImageView ivDeleteShortcutApp2 = baseShortcutAppViewHolder.getIvDeleteShortcutApp();
            if (ivDeleteShortcutApp2 != null) {
                ivDeleteShortcutApp2.setEnabled(getItemCount() > 1);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        ShortcutAppData item = getItem(position);
        if (item == null) {
            return -1;
        }
        return item.getWidgets_type() | (item.getSize_type() << 24);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<ShortcutAppData> arrayList = this.appDatas;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public final ShortcutAppData getItem(int position) {
        ArrayList<ShortcutAppData> arrayList = this.appDatas;
        if (arrayList != null) {
            return arrayList.get(position);
        }
        return null;
    }
}