package com.vivek.productapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vivek.productapp.databinding.ProductViewBinding
import com.vivek.productapp.util.MyDiffUtil

class MyDataAdapter( private var mList:ArrayList<ProductList.Product>):RecyclerView.Adapter<MyDataAdapter.MyViewHolder>() {
    //private var mList =  ArrayList<ProductList.Product>()
    class MyViewHolder( val mBinding: ProductViewBinding):RecyclerView.ViewHolder(mBinding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ProductViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemsViewModel  = mList[position]
        val Phone =itemsViewModel.thumbnail
        holder.mBinding.brandName.text  = itemsViewModel.brand
        holder.mBinding.price.text = itemsViewModel.price.toString()
        Glide.with(holder.itemView.context).load(Phone).centerCrop().into(holder.mBinding.image)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    fun setData(upDateList:ArrayList<ProductList.Product>){
        val diffUtil = MyDiffUtil(mList as ArrayList<ProductList.Product>,upDateList)
        val diffResult  = DiffUtil.calculateDiff(diffUtil)
        mList=upDateList
        diffResult.dispatchUpdatesTo(this)
    }
}