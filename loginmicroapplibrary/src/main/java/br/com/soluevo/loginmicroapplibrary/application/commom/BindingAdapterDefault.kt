package br.com.soluevo.loginmicroapplibrary.application.commom

import android.graphics.BitmapFactory
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter( "imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Picasso.get()
        .load(imageUrl)
        .into(view)
}

@BindingAdapter( "loadImageFromPath")
fun loadImageFromPath(view: ImageView, path: String) {
    view.setImageBitmap(BitmapFactory.decodeFile(path))
}

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(show: Boolean) {
    visibility = if (show) VISIBLE else GONE
}
