package com.key.magicbook.base

/**
 * created by key  on 2020/1/5
 */
open class BaseContract {
    interface View{
        fun onView(resultCode :Int,requestCode :Int,result:Any)
    }

}
