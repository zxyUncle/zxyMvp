package com.cloudpick.yunnasdk.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cloudpick.yunnasdk.NetConfigUtils;
import com.cloudpick.yunnasdk.R;


/**
 * Created by admin on 2018/3/26.
 */

public class ImageLoaderWrapper {
    public static String getImgWithSuffix() {
        return NetConfigUtils.Companion.getYN_IMGURL() + "/%s.jpg";
    }

    public static void displayImageByGoodsId(Context context, ImageView iv, String goodsId) {
        if (!isValidContextForGlide(context)) {
            return;
        }
        String url = String.format(getImgWithSuffix(), goodsId);
        RequestOptions options = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.yn_image_placehold)
                .error(R.drawable.yn_image_placehold);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(iv);

    }


    public static boolean isValidContextForGlide(final Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return false;
            }
        }
        return true;
    }

//    /**
//     * 获取imageloader对象
//     */
//    public static ImageLoader getImageLoader() {
//        ImageLoader imageLoader = ImageLoader.getInstance();
//        if (!imageLoader.isInited()) {
//            initImageLoader();
//        }
//        return imageLoader;
//    }
//
//    /**
//     * imageloader 初始化
//     */
//    public static void initImageLoader(){
//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageForEmptyUri(R.drawable.image_placehold)
//                .showImageOnLoading(R.drawable.image_placehold)
//                .showImageOnFail(R.drawable.image_placehold)
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .build();
//
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration
//                .Builder(App.getContext())
//                .defaultDisplayImageOptions(options)
//                .memoryCacheSize(10 * 1024 * 1024)
//                .diskCacheSize(100 * 1024 * 1024)
//                .imageDownloader(new BaseImageDownloader(App.getContext(), 15 * 1000, 30 * 1000))
//                .build();
//        ImageLoader.getInstance().init(config);
//    }
}
