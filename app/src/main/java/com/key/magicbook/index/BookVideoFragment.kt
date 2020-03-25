package com.key.magicbook.index

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.key.keylibrary.base.BaseFragment
import com.key.keylibrary.bean.BusMessage
import com.key.magicbook.R
import kotlinx.android.synthetic.main.fragment_index_book_video.*

/**
 * created by key  on 2020/3/2
 */
class BookVideoFragment :BaseFragment(){

    override fun setLayoutId(): Int {
        return R.layout.fragment_index_book_video
    }

    override fun initView() {
        setTitle(toolbar)
    }
}