package util

import android.graphics.*
import com.parkinglille.android.parkinglille.R

class Utils {
    companion object {

         fun  tintImage(bitmap: Bitmap, color:Int): Bitmap {
            val paint = Paint()
            paint.colorFilter= PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
            val bitmapResult = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888)
            val canvas =  Canvas(bitmapResult)
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint)
            return bitmapResult
        }

        fun colorID(dispo:Float,max:Float):Int{

            val valu = ((dispo/max)*100)
            return when(valu){
                in 80..100->  R.color.colorBlue
                in 60..80->  R.color.colorGreen
                in 40..60-> R.color.colorYellow
                in 20..40-> R.color.colorOrange
                else ->  R.color.colorRed
            }
        }

    }
}