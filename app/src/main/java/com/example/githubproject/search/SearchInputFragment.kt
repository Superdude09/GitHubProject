package com.example.githubproject.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubproject.R
import com.example.githubproject.common.Util
import kotlinx.android.synthetic.main.layout_search_input.*

class SearchInputFragment : Fragment() {
    internal var callback: OnSearchSubmittedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.layout_search_input, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButton()
    }

    private fun initButton() {
        searchButton.setOnClickListener {
            val searchInput = search_input_edit_text.text
            callback?.onSearchSubmitted(searchInput.toString())

            Util.hideSoftKeyboard(requireActivity(), search_input_edit_text)
        }
    }

    fun setOnHeadlineSelectedListener(callback: OnSearchSubmittedListener) {
        this.callback = callback
    }

    @FunctionalInterface
    interface OnSearchSubmittedListener {
        fun onSearchSubmitted(searchValue: String)
    }
}