package com.adityabugalia.playingwithlights.bindingadapter


import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["setTextViewText"])
fun setTextViewText(textView: TextView, text: String) {
    Log.d("Text", "" + text)
    textView.text = text
}
@BindingAdapter(value = ["setTextViewText"])
fun setTextViewText(textView: TextView, text: Int) {
    Log.d("Text", "" + text)
    textView.text = text.toString()
}
@BindingAdapter(value = ["setBackgroundColorView"])
fun setBackgroundColorView(view: View, color: String) {
    view.setBackgroundColor(Color.parseColor(color))
}

@BindingAdapter(value = ["setBackgroundColorLL"])
fun setBackgroundColorLL(view: LinearLayout, color: String) {
    view.setBackgroundColor(Color.parseColor(color))
}

@BindingAdapter(value = ["setBackgroundColorSB"])
fun setBackgroundColorSB(view: SeekBar, color: String) {
    view.setBackgroundColor(Color.parseColor(color))
}
