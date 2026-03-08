package com.ido.life.viewholder.shortcut;

import android.util.SparseArray;
import android.util.SparseIntArray;
import com.boat.Xtend.two.R;
import com.ido.life.viewholder.shortcut.annotation.ShortcutAppBigLayout;
import com.ido.life.viewholder.shortcut.annotation.ShortcutAppSmallLayout;
import com.ido.life.viewholder.shortcut.annotation.ShortcutAppUnique;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ShortcutAppViewHolderManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00122\u00020\u0001:\u0002\u0012\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0007J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0007J\u0016\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b2\u0006\u0010\f\u001a\u00020\u000bJ\u0018\u0010\u000f\u001a\u00020\u00102\u000e\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/ido/life/viewholder/shortcut/ShortcutAppViewHolderManager;", "", "()V", "mBigLayouts", "Landroid/util/SparseIntArray;", "mSmallLayouts", "mViewHolders", "Landroid/util/SparseArray;", "Ljava/lang/Class;", "Lcom/ido/life/viewholder/shortcut/BaseShortcutAppViewHolder;", "getShortcutAppBigLayoutRes", "", "appUnique", "getShortcutAppSmallLayoutRes", "getShortcutAppViewHolder", "registerHolder", "", "clazz", "Companion", "SingleInstanceHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ShortcutAppViewHolderManager {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ShortcutAppViewHolderManager instance = SingleInstanceHolder.INSTANCE.getINSTANCE();
    private final SparseArray<Class<? extends BaseShortcutAppViewHolder>> mViewHolders = new SparseArray<>();
    private final SparseIntArray mBigLayouts = new SparseIntArray();
    private final SparseIntArray mSmallLayouts = new SparseIntArray();

    public ShortcutAppViewHolderManager() {
        registerHolder(TricyclicShortcutAppViewHolder.class);
        registerHolder(WeatherShortcutAppViewHolder.class);
        registerHolder(HeartRateShortcutAppViewHolder.class);
        registerHolder(PressureShortcutAppViewHolder.class);
        registerHolder(SleepShortcutAppViewHolder.class);
        registerHolder(RemindShortcutAppViewHolder.class);
        registerHolder(MusicShortcutAppViewHolder.class);
        registerHolder(SportsShortcutAppViewHolder.class);
        registerHolder(NormalSmallShortcutAppViewHolder.class);
    }

    /* JADX INFO: compiled from: ShortcutAppViewHolderManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/viewholder/shortcut/ShortcutAppViewHolderManager$Companion;", "", "()V", "instance", "Lcom/ido/life/viewholder/shortcut/ShortcutAppViewHolderManager;", "getInstance", "()Lcom/ido/life/viewholder/shortcut/ShortcutAppViewHolderManager;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ShortcutAppViewHolderManager getInstance() {
            return ShortcutAppViewHolderManager.instance;
        }
    }

    /* JADX INFO: compiled from: ShortcutAppViewHolderManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/viewholder/shortcut/ShortcutAppViewHolderManager$SingleInstanceHolder;", "", "()V", "INSTANCE", "Lcom/ido/life/viewholder/shortcut/ShortcutAppViewHolderManager;", "getINSTANCE", "()Lcom/ido/life/viewholder/shortcut/ShortcutAppViewHolderManager;", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final class SingleInstanceHolder {
        public static final SingleInstanceHolder INSTANCE = new SingleInstanceHolder();
        private static final ShortcutAppViewHolderManager INSTANCE = new ShortcutAppViewHolderManager();

        private SingleInstanceHolder() {
        }

        public final ShortcutAppViewHolderManager getINSTANCE() {
            return INSTANCE;
        }
    }

    private final void registerHolder(Class<? extends BaseShortcutAppViewHolder> clazz) {
        ShortcutAppUnique shortcutAppUnique = (ShortcutAppUnique) clazz.getAnnotation(ShortcutAppUnique.class);
        if (shortcutAppUnique == null) {
            throw new IllegalArgumentException("the ShortcutAppUnique annotation for ShortcutApp ViewHolder can not be null");
        }
        int[] iArrValue = shortcutAppUnique.value();
        ShortcutAppBigLayout shortcutAppBigLayout = (ShortcutAppBigLayout) clazz.getAnnotation(ShortcutAppBigLayout.class);
        int iLayoutId = shortcutAppBigLayout != null ? shortcutAppBigLayout.layoutId() : 0;
        ShortcutAppSmallLayout shortcutAppSmallLayout = (ShortcutAppSmallLayout) clazz.getAnnotation(ShortcutAppSmallLayout.class);
        int iLayoutId2 = shortcutAppSmallLayout != null ? shortcutAppSmallLayout.layoutId() : 0;
        for (int i : iArrValue) {
            if (this.mViewHolders.indexOfKey(i) >= 0) {
                return;
            }
            this.mViewHolders.put(i, clazz);
            if (iLayoutId > 0) {
                this.mBigLayouts.put(i, iLayoutId);
            }
            if (iLayoutId2 > 0) {
                this.mSmallLayouts.put(i, iLayoutId2);
            }
        }
    }

    public final int getShortcutAppBigLayoutRes(int appUnique) {
        int i = this.mBigLayouts.get(appUnique);
        return i > 0 ? i : R.layout.item_shortcut_app_unknow_big;
    }

    public final int getShortcutAppSmallLayoutRes(int appUnique) {
        int i = this.mSmallLayouts.get(appUnique);
        return i > 0 ? i : R.layout.item_shortcut_app_unknow_small;
    }

    public final Class<? extends BaseShortcutAppViewHolder> getShortcutAppViewHolder(int appUnique) {
        Class<? extends BaseShortcutAppViewHolder> cls = this.mViewHolders.get(appUnique);
        return cls != null ? cls : UnknowShortcutAppViewHolder.class;
    }
}