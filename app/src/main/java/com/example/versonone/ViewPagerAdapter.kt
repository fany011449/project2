import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresFeature
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.versonone.R
import com.example.versonone.Actor
import com.example.versonone.GlideCircleTransform
import com.example.versonone.JsonFileRes
import kotlinx.android.synthetic.main.activity_adapter.view.*
import java.io.File
import java.net.URL

class ViewPagerAdapter(val context: Context) : PagerAdapter(){  // container => 放View的容器 ; position => view頁面數字
    val jsonFileRes = JsonFileRes().readJsonFile(context)
    override fun instantiateItem(container: ViewGroup, position: Int): Any {  // instantiateItem 實例化頁面 => 主要將xml資源加載成View，把要顯示的圖片arrayList載入View裡頭的imageView，將View裝到container並且回傳
        val view = LayoutInflater.from(container.context)  // 從cotext中抓一個xml的資訊
             .inflate(R.layout.activity_adapter, container, false)  // 動態將圖檔放入view中
        Glide
            .with(context)
            .load(
                if (jsonFileRes.act[position].actimage == null || jsonFileRes.act[position].actimage == "") {
                    (jsonFileRes.act[position].actimageurl)
                } else {
                    (jsonFileRes.act[position].actimage)
                }
            )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view.ivAdapter)
        container.addView(view)  // 將 view 加進 container 裡面
        view.setOnClickListener {            // (匿名class)轉換畫面: MainActivity -> act1
            val intent = Intent(context , Actor::class.java)
            intent.putExtra("index", position)  // 傳當下arrayList的index
            ContextCompat.startActivity(context, intent, null)
        }
        return view // 回傳照片
    }
    override fun isViewFromObject(view: View, any: Any): Boolean {  // isViewFromObject 判斷頁面 View 和 instantiateItem 回傳物件是否一致
        return view == any
    }
    override fun getCount(): Int {  // getCount() 回傳 ViewPager 的項目總數
        return jsonFileRes.act.size
    }
    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {  // destroyItem(ViewGroup, int, Object) 用來移除不在檢視範圍的View，將View從container中移除
        container.removeView(view as View)
    }
}