package com.example.versonone

import android.content.Context
import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

// 壓縮圖片為圓形
class GlideCircleTransform(context: Context) : BitmapTransformation() {
    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
    }
    override fun transform(pool: BitmapPool,
                           toTransform: Bitmap,
                           outWidth: Int,
                           outHeight: Int): Bitmap {
        return circleCrop(pool, toTransform)
    }

    private fun circleCrop(pool: BitmapPool,
                           source: Bitmap): Bitmap {
         val size = Math.min(source.width, source.height)
         val x = (source.width - size) / 2
         val y = (source.height - size) / 2
         val squared = Bitmap.createBitmap(source, x, y, size, size) // 回傳不可變的圖檔,參數(source, x, y, width, height)
         val result = pool.get(size, size, Bitmap.Config.ARGB_8888) //Config 只是Bitmap 中的一個子類別　(ARGB = Alpha,Red,Green,Blue)
         val canvas = Canvas(result)  //創造一個畫布,產物存在Canvas class的Bitmap中
         Paint().shader = BitmapShader(squared, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)  // 參數二跟三會改變的只有"CLAMP"
         Paint().isAntiAlias = true // 抗鋸齒開關
         val r = size / 2f
         canvas.drawCircle(r, r, r, Paint()) // (圓心X座標, 圓心Y座標, 圓的半徑, 畫筆)
         return result
     }
}