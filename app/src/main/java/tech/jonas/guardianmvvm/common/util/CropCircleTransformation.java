package tech.jonas.guardianmvvm.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class CropCircleTransformation extends BitmapTransformation {

    public CropCircleTransformation(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) {
            return null;
        }

        final int size = Math.min(source.getWidth(), source.getHeight());
        final int x = (source.getWidth() - size) / 2;
        final int y = (source.getHeight() - size) / 2;

        final Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }

        final Canvas canvas = new Canvas(result);
        final Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        final int borderWidth = 3;
        final int borderColor = 0xffffffff;

        final Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(borderColor);
        paint2.setStrokeWidth(borderWidth);
        paint2.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(source.getWidth() / 2, source.getHeight() / 2, (float) (source.getWidth() / 2 - Math.ceil(borderWidth / 2)),
                          paint2);

        return result;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }
}