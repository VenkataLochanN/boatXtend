package com.lzy.imagepicker.adapter;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.util.Utils;
import java.util.ArrayList;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/* JADX INFO: loaded from: classes3.dex */
public class ImagePageAdapter extends PagerAdapter {
    private ImagePicker imagePicker;
    private ArrayList<ImageItem> images;
    public PhotoViewClickListener listener;
    private Activity mActivity;
    private int screenHeight;
    private int screenWidth;

    public interface PhotoViewClickListener {
        void OnPhotoTapListener(View view, float f2, float f3);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ImagePageAdapter(Activity activity, ArrayList<ImageItem> arrayList) {
        this.images = new ArrayList<>();
        this.mActivity = activity;
        this.images = arrayList;
        DisplayMetrics screenPix = Utils.getScreenPix(activity);
        this.screenWidth = screenPix.widthPixels;
        this.screenHeight = screenPix.heightPixels;
        this.imagePicker = ImagePicker.getInstance();
    }

    public void setData(ArrayList<ImageItem> arrayList) {
        this.images = arrayList;
    }

    public void setPhotoViewClickListener(PhotoViewClickListener photoViewClickListener) {
        this.listener = photoViewClickListener;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        PhotoView photoView = new PhotoView(this.mActivity);
        this.imagePicker.getImageLoader().displayImagePreview(this.mActivity, this.images.get(i).path, photoView, this.screenWidth, this.screenHeight);
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() { // from class: com.lzy.imagepicker.adapter.ImagePageAdapter.1
            @Override // uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener
            public void onPhotoTap(View view, float f2, float f3) {
                if (ImagePageAdapter.this.listener != null) {
                    ImagePageAdapter.this.listener.OnPhotoTapListener(view, f2, f3);
                }
            }
        });
        viewGroup.addView(photoView);
        return photoView;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.images.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}