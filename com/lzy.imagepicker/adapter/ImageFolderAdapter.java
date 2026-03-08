package com.lzy.imagepicker.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.R;
import com.lzy.imagepicker.bean.ImageFolder;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.util.Utils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ImageFolderAdapter extends BaseAdapter {
    private ImagePicker imagePicker;
    private Activity mActivity;
    private int mImageSize;
    private LayoutInflater mInflater;
    private List<ImageFolder> imageFolders = new ArrayList();
    private int lastSelected = 0;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public ImageFolderAdapter(Activity activity, List<ImageFolder> list) {
        this.mActivity = activity;
        this.imageFolders.clear();
        if (list != null && list.size() > 0) {
            this.imageFolders.addAll(list);
        }
        this.imagePicker = ImagePicker.getInstance();
        this.mImageSize = Utils.getImageItemWidth(this.mActivity);
        this.mInflater = (LayoutInflater) activity.getSystemService("layout_inflater");
    }

    public void refreshData(List<ImageFolder> list) {
        this.imageFolders.clear();
        if (list != null && list.size() > 0) {
            this.imageFolders.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.imageFolders.size();
    }

    @Override // android.widget.Adapter
    public ImageFolder getItem(int i) {
        return this.imageFolders.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.adapter_folder_list_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ImageFolder item = getItem(i);
        viewHolder.folderName.setText(item.name);
        viewHolder.imageCount.setText(this.mActivity.getString(R.string.ip_folder_image_count, new Object[]{Integer.valueOf(item.images.size())}));
        ImageLoader imageLoader = this.imagePicker.getImageLoader();
        Activity activity = this.mActivity;
        String str = item.cover.path;
        ImageView imageView = viewHolder.cover;
        int i2 = this.mImageSize;
        imageLoader.displayImage(activity, str, imageView, i2, i2);
        if (this.lastSelected == i) {
            viewHolder.folderCheck.setVisibility(0);
        } else {
            viewHolder.folderCheck.setVisibility(4);
        }
        return view;
    }

    public void setSelectIndex(int i) {
        if (this.lastSelected == i) {
            return;
        }
        this.lastSelected = i;
        notifyDataSetChanged();
    }

    public int getSelectIndex() {
        return this.lastSelected;
    }

    private class ViewHolder {
        ImageView cover;
        ImageView folderCheck;
        TextView folderName;
        TextView imageCount;

        public ViewHolder(View view) {
            this.cover = (ImageView) view.findViewById(R.id.iv_cover);
            this.folderName = (TextView) view.findViewById(R.id.tv_folder_name);
            this.imageCount = (TextView) view.findViewById(R.id.tv_image_count);
            this.folderCheck = (ImageView) view.findViewById(R.id.iv_folder_check);
            view.setTag(this);
        }
    }
}