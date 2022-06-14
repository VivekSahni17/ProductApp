package com.vivek.productapp.util

import androidx.recyclerview.widget.DiffUtil
import com.vivek.productapp.ProductList

class MyDiffUtil(private val mList:ArrayList<ProductList.Product>, private val tempList:ArrayList<ProductList.Product>):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mList.size
    }

    override fun getNewListSize(): Int {
       return tempList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mList[oldItemPosition].id == tempList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  when{
            mList[oldItemPosition].id!=tempList[newItemPosition].id->{
                false
            }
            mList[oldItemPosition].brand!=tempList[newItemPosition].brand->{
                false
            }
            mList[oldItemPosition].images!=tempList[newItemPosition].images->{
                false
            }
            mList[oldItemPosition].price!=tempList[newItemPosition].price->{
                false
            }

            else->true
        }
    }
}