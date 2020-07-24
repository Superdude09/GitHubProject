package com.example.githubproject.userinfo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.example.githubproject.R
import com.example.githubproject.common.Util
import kotlinx.android.synthetic.main.layout_user_repo_list_item.view.*

class UserRepoComponent @JvmOverloads constructor(context: Context,
                                                  attrs: AttributeSet? = null,
                                                  defStyleAttr: Int = 0) : CardView(context, attrs, defStyleAttr) {
    companion object{
        const val CONTENT_PADDING_IN_DP = 8f
        const val MARGIN_IN_DP = 8f
        const val ELEVATION_IN_DP = 4f
        const val CORNER_RADIUS_IN_DP = 2f
    }

   init {
       inflate()
       initialize()
   }

    private fun inflate() {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_user_repo_list_item, this, true)
    }

    private fun initialize() {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        val marginInDP = Util.convertDpToPixel(MARGIN_IN_DP, context).toInt()
        (layoutParams as MarginLayoutParams).setMargins(0, 0, 0, marginInDP)

        val paddingInDP = Util.convertDpToPixel(CONTENT_PADDING_IN_DP, context).toInt()
        setContentPadding(paddingInDP, paddingInDP, paddingInDP, paddingInDP)

        elevation = Util.convertDpToPixel(ELEVATION_IN_DP, context)

        radius = Util.convertDpToPixel(CORNER_RADIUS_IN_DP, context)

        useCompatPadding = true
    }

    fun setName(name: String) {
        tv_user_repo_name.text = name
    }

    fun setDescription(description: String) {
        tv_user_repo_description.text = description
    }
}