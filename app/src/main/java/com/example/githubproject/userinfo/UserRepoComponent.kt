package com.example.githubproject.userinfo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.githubproject.R
import kotlinx.android.synthetic.main.layout_user_repo_list_item.view.*

class UserRepoComponent @JvmOverloads constructor(context: Context,
                                                  attrs: AttributeSet? = null,
                                                  defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
   init {
       inflate()
   }

    private fun inflate() {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_user_repo_list_item, this, true)
    }

    fun setName(name: String) {
        tv_user_repo_name.text = name
    }

    fun setDescription(description: String) {
        tv_user_repo_description.text = description
    }
}