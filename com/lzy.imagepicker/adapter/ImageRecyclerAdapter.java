package com.lzy.imagepicker.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageBaseActivity;
import com.lzy.imagepicker.util.DiffCallback;
import com.lzy.imagepicker.util.Utils;
import com.lzy.imagepicker.view.SuperCheckBox;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class ImageRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE_CAMERA = 0;
    private static final int ITEM_TYPE_NORMAL = 1;
    private ImagePicker imagePicker;
    private ArrayList<ImageItem> images;
    private boolean isShowCamera;
    private OnImageItemClickListener listener;
    private Activity mActivity;
    private int mImageSize;
    private LayoutInflater mInflater;
    private ArrayList<ImageItem> mSelectedImages;

    public interface OnImageItemClickListener {
        void onImageItemClick(View view, ImageItem imageItem, int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public void setOnImageItemClickListener(OnImageItemClickListener onImageItemClickListener) {
        this.listener = onImageItemClickListener;
    }

    public void refreshData(ArrayList<ImageItem> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            this.images = new ArrayList<>();
        } else {
            this.images = arrayList;
        }
        notifyDataSetChanged();
    }

    public void refreshDiffData(ArrayList<ImageItem> arrayList) {
        DiffUtil.calculateDiff(new DiffCallback(this.images, arrayList), false).dispatchUpdatesTo(this);
        setData(arrayList);
    }

    public void setData(ArrayList<ImageItem> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            this.images = new ArrayList<>();
        } else {
            this.images = arrayList;
        }
    }

    public ArrayList<ImageItem> getImages() {
        ArrayList<ImageItem> arrayList = this.images;
        return arrayList != null ? arrayList : new ArrayList<>();
    }

    public ImageRecyclerAdapter(Activity activity, ArrayList<ImageItem> arrayList) {
        this.mActivity = activity;
        if (arrayList == null || arrayList.size() == 0) {
            this.images = new ArrayList<>();
        } else {
            this.images = arrayList;
        }
        this.mImageSize = Utils.getImageItemWidth(this.mActivity);
        this.imagePicker = ImagePicker.getInstance();
        this.isShowCamera = this.imagePicker.isShowCamera();
        this.mSelectedImages = this.imagePicker.getSelectedImages();
        this.mInflater = LayoutInflater.from(activity);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new CameraViewHolder(this.mInflater.inflate(R.layout.adapter_camera_item, viewGroup, false));
        }
        return new ImageViewHolder(this.mInflater.inflate(R.layout.adapter_image_list_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof CameraViewHolder) {
            ((CameraViewHolder) viewHolder).bindCamera();
        } else if (viewHolder instanceof ImageViewHolder) {
            ((ImageViewHolder) viewHolder).bind(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return (this.isShowCamera && i == 0) ? 0 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.isShowCamera ? this.images.size() + 1 : this.images.size();
    }

    public ImageItem getItem(int i) {
        if (!this.isShowCamera) {
            return this.images.get(i);
        }
        if (i == 0) {
            return null;
        }
        return this.images.get(i - 1);
    }

    private class ImageViewHolder extends RecyclerView.ViewHolder {
        SuperCheckBox cbCheck;
        View checkView;
        ImageView ivThumb;
        View mask;
        View rootView;

        ImageViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.ivThumb = (ImageView) view.findViewById(R.id.iv_thumb);
            this.mask = view.findViewById(R.id.mask);
            this.checkView = view.findViewById(R.id.checkView);
            this.cbCheck = (SuperCheckBox) view.findViewById(R.id.cb_check);
            view.setLayoutParams(new AbsListView.LayoutParams(ImageRecyclerAdapter.this.mImageSize, ImageRecyclerAdapter.this.mImageSize));
        }

        void bind(final int i) {
            final ImageItem item = ImageRecyclerAdapter.this.getItem(i);
            this.ivThumb.setOnClickListener(new View.OnClickListener() { // from class: com.lzy.imagepicker.adapter.ImageRecyclerAdapter.ImageViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ImageRecyclerAdapter.this.listener != null) {
                        ImageRecyclerAdapter.this.listener.onImageItemClick(ImageViewHolder.this.rootView, item, i);
                    }
                }
            });
            this.checkView.setOnClickListener(new View.OnClickListener() { // from class: com.lzy.imagepicker.adapter.ImageRecyclerAdapter.ImageViewHolder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ImageViewHolder.this.cbCheck.setChecked(!ImageViewHolder.this.cbCheck.isChecked());
                    int selectLimit = ImageRecyclerAdapter.this.imagePicker.getSelectLimit();
                    if (!ImageViewHolder.this.cbCheck.isChecked() || ImageRecyclerAdapter.this.mSelectedImages.size() < selectLimit) {
                        ImageRecyclerAdapter.this.imagePicker.addSelectedImageItem(i, item, ImageViewHolder.this.cbCheck.isChecked());
                        ImageViewHolder.this.mask.setVisibility(0);
                    } else {
                        Toast.makeText(ImageRecyclerAdapter.this.mActivity.getApplicationContext(), ImageRecyclerAdapter.this.mActivity.getString(R.string.ip_select_limit, new Object[]{Integer.valueOf(selectLimit)}), 0).show();
                        ImageViewHolder.this.cbCheck.setChecked(false);
                        ImageViewHolder.this.mask.setVisibility(8);
                    }
                }
            });
            if (ImageRecyclerAdapter.this.imagePicker.isMultiMode()) {
                this.cbCheck.setVisibility(0);
                if (ImageRecyclerAdapter.this.mSelectedImages.contains(item)) {
                    this.mask.setVisibility(0);
                    this.cbCheck.setChecked(true);
                } else {
                    this.mask.setVisibility(8);
                    this.cbCheck.setChecked(false);
                }
            } else {
                this.cbCheck.setVisibility(8);
            }
            ImageRecyclerAdapter.this.imagePicker.getImageLoader().displayImage(ImageRecyclerAdapter.this.mActivity, item.path, this.ivThumb, ImageRecyclerAdapter.this.mImageSize, ImageRecyclerAdapter.this.mImageSize);
        }
    }

    private class CameraViewHolder extends RecyclerView.ViewHolder {
        View mItemView;

        CameraViewHolder(View view) {
            super(view);
            this.mItemView = view;
            this.mItemView.setLayoutParams(new AbsListView.LayoutParams(ImageRecyclerAdapter.this.mImageSize, ImageRecyclerAdapter.this.mImageSize));
        }

        void bindCamera() {
            this.mItemView.setTag(null);
            this.mItemView.setOnClickListener(new View.OnClickListener() { // from class: com.lzy.imagepicker.adapter.ImageRecyclerAdapter.CameraViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (!((ImageBaseActivity) ImageRecyclerAdapter.this.mActivity).checkPermission("android.permission.CAMERA")) {
                        ActivityCompat.requestPermissions(ImageRecyclerAdapter.this.mActivity, new String[]{"android.permission.CAMERA"}, 2);
                    } else {
                        ImageRecyclerAdapter.this.imagePicker.takePicture(ImageRecyclerAdapter.this.mActivity, 1001);
                    }
                }
            });
        }
    }
}