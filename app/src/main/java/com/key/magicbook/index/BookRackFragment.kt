package com.key.magicbook.index

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.key.keylibrary.base.BaseFragment
import com.key.keylibrary.base.ConstantValues
import com.key.keylibrary.utils.FileUtils
import com.key.magicbook.R
import com.key.magicbook.bean.BookList
import com.key.magicbook.bookpage.Config
import com.key.magicbook.bookpage.PageFactory
import com.key.magicbook.bookpage.PageWidget
import com.key.magicbook.read.ReadActivity
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission
import kotlinx.android.synthetic.main.fragment_index_mine_book.*


/**
 * created by key  on 2020/3/2
 */
class BookRackFragment :BaseFragment(){
    override fun setLayoutId(): Int {
        return  R.layout.fragment_index_mine_book
    }

    override fun initView() {
        setTitle(refresh)
        refresh.setEnableRefresh(false)
        refresh.setEnableLoadMore(false)


        val layoutManager = GridLayoutManager(activity, 3)
        layoutManager.orientation = GridLayoutManager.VERTICAL
        list.layoutManager = layoutManager


        val mineBookAdapter = MineBookAdapter(R.layout.item_book_mine)
        list.adapter = mineBookAdapter

        val arrayList = ArrayList<String>()
        arrayList.add("圣墟")



        mineBookAdapter.setNewData(arrayList)



        mineBookAdapter.setOnItemClickListener { _,
                                                 _,
                                                 _ ->
            startActivity(Intent(activity,ReadActivity::class.java))
            activity!!.overridePendingTransition(0,0)
        }
    }


    inner class MineBookAdapter(res :Int) :BaseQuickAdapter<String,BaseViewHolder>(res){
        override fun convert(helper: BaseViewHolder, item: String) {
            helper.setText(R.id.book_name,item)
        }
    }


}




