package com.ido.life.dialog;

import android.content.Context;
import com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter;
import com.ido.life.customview.recyclerview.CommonRecyclerViewHolder;
import com.ido.life.util.WallpaperDialManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WallPaperDialFunctionSetDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0014J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0002H\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0002H\u0002¨\u0006\r"}, d2 = {"com/ido/life/dialog/WallPaperDialFunctionSetDialog$initView$adapter$1", "Lcom/ido/life/customview/recyclerview/CommonRecyclerViewAdapter;", "", "convert", "", "holder", "Lcom/ido/life/customview/recyclerview/CommonRecyclerViewHolder;", "function", "position", "getItem", "isFirstInGroup", "", "isLastInGroup", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WallPaperDialFunctionSetDialog$initView$adapter$1 extends CommonRecyclerViewAdapter<Integer> {
    final /* synthetic */ WallPaperDialFunctionSetDialog this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WallPaperDialFunctionSetDialog$initView$adapter$1(WallPaperDialFunctionSetDialog wallPaperDialFunctionSetDialog, Context context, int i, List list) {
        super(context, i, list);
        this.this$0 = wallPaperDialFunctionSetDialog;
    }

    @Override // com.ido.life.customview.recyclerview.CommonRecyclerViewAdapter
    public /* bridge */ /* synthetic */ void convert(CommonRecyclerViewHolder commonRecyclerViewHolder, Integer num, int i) {
        convert(commonRecyclerViewHolder, num.intValue(), i);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void convert(com.ido.life.customview.recyclerview.CommonRecyclerViewHolder r7, int r8, int r9) {
        /*
            r6 = this;
            java.lang.String r0 = "holder"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            int r0 = com.ido.life.util.WallpaperDialManager.getFunctionGroupType(r8)
            java.lang.String r1 = com.ido.life.util.WallpaperDialManager.getFunctionName(r8)
            r2 = 2131363776(0x7f0a07c0, float:1.834737E38)
            r7.setText(r2, r1)
            r1 = 2131362521(0x7f0a02d9, float:1.8344825E38)
            android.view.View r1 = r7.getView(r1)
            java.lang.String r2 = "holder.getView<ImageView>(R.id.ivCheck)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            com.ido.life.dialog.WallPaperDialFunctionSetDialog r2 = r6.this$0
            int r2 = com.ido.life.dialog.WallPaperDialFunctionSetDialog.access$getMSelectedFunction$p(r2)
            r3 = 4
            r4 = 0
            if (r2 != r8) goto L2d
            r8 = r4
            goto L2e
        L2d:
            r8 = r3
        L2e:
            r1.setVisibility(r8)
            boolean r8 = r6.isFirstInGroup(r9)
            r1 = 1
            if (r8 == 0) goto L3e
            boolean r8 = r6.isLastInGroup(r9)
            if (r8 != 0) goto L44
        L3e:
            boolean r8 = r6.isFirstInGroup(r9)
            if (r8 == 0) goto L46
        L44:
            r8 = r1
            goto L47
        L46:
            r8 = r4
        L47:
            boolean r2 = r6.isFirstInGroup(r9)
            if (r2 == 0) goto L58
            boolean r2 = r6.isLastInGroup(r9)
            if (r2 == 0) goto L58
            r9 = 2131230837(0x7f080075, float:1.8077738E38)
        L56:
            r1 = r4
            goto L6f
        L58:
            boolean r2 = r6.isFirstInGroup(r9)
            if (r2 == 0) goto L62
            r9 = 2131230838(0x7f080076, float:1.807774E38)
            goto L6f
        L62:
            boolean r9 = r6.isLastInGroup(r9)
            if (r9 == 0) goto L6c
            r9 = 2131230836(0x7f080074, float:1.8077736E38)
            goto L56
        L6c:
            r9 = 2131230835(0x7f080073, float:1.8077734E38)
        L6f:
            r2 = 2131362085(0x7f0a0125, float:1.834394E38)
            android.view.View r2 = r7.getView(r2)
            java.lang.String r5 = "holder.getView<View>(R.id.decoration)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r5)
            if (r1 == 0) goto L7e
            r3 = r4
        L7e:
            r2.setVisibility(r3)
            r1 = 2131362801(0x7f0a03f1, float:1.8345393E38)
            r7.setBackgroundRes(r1, r9)
            r9 = 2131363743(0x7f0a079f, float:1.8347303E38)
            r7.setVisible(r9, r8)
            if (r8 == 0) goto L94
            java.lang.String r8 = com.ido.life.util.WallpaperDialManager.getFunctionGroupName(r0)
            goto L96
        L94:
            java.lang.String r8 = ""
        L96:
            r7.setText(r9, r8)
            com.ido.life.dialog.WallPaperDialFunctionSetDialog r7 = r6.this$0
            int r8 = com.ido.life.R.id.layout_pic
            android.view.View r7 = r7._$_findCachedViewById(r8)
            android.widget.FrameLayout r7 = (android.widget.FrameLayout) r7
            java.lang.String r8 = "layout_pic"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
            android.view.ViewTreeObserver r7 = r7.getViewTreeObserver()
            com.ido.life.dialog.WallPaperDialFunctionSetDialog$initView$adapter$1$convert$1 r8 = new com.ido.life.dialog.WallPaperDialFunctionSetDialog$initView$adapter$1$convert$1
            r8.<init>()
            android.view.ViewTreeObserver$OnGlobalLayoutListener r8 = (android.view.ViewTreeObserver.OnGlobalLayoutListener) r8
            r7.addOnGlobalLayoutListener(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.dialog.WallPaperDialFunctionSetDialog$initView$adapter$1.convert(com.ido.life.customview.recyclerview.CommonRecyclerViewHolder, int, int):void");
    }

    private final int getItem(int position) {
        Object obj = this.this$0.mFunctionList.get(position);
        Intrinsics.checkExpressionValueIsNotNull(obj, "mFunctionList[position]");
        return ((Number) obj).intValue();
    }

    private final boolean isLastInGroup(int position) {
        int item = getItem(position);
        if (position == this.this$0.mFunctionList.size() - 1) {
            return true;
        }
        int functionGroupType = WallpaperDialManager.getFunctionGroupType(item);
        Object obj = this.this$0.mFunctionList.get(position + 1);
        Intrinsics.checkExpressionValueIsNotNull(obj, "mFunctionList[position + 1]");
        return functionGroupType != WallpaperDialManager.getFunctionGroupType(((Number) obj).intValue());
    }

    private final boolean isFirstInGroup(int position) {
        int item = getItem(position);
        if (position == 0) {
            return true;
        }
        int functionGroupType = WallpaperDialManager.getFunctionGroupType(item);
        Object obj = this.this$0.mFunctionList.get(position - 1);
        Intrinsics.checkExpressionValueIsNotNull(obj, "mFunctionList[position - 1]");
        return WallpaperDialManager.getFunctionGroupType(((Number) obj).intValue()) != functionGroupType;
    }
}