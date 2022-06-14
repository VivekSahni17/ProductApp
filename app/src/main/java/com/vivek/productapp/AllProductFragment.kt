
package com.vivek.productapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vivek.productapp.databinding.FragmentAllProductBinding
import com.vivek.productapp.retrofit.RetrofitClient
import com.vivek.productapp.util.ErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllProductFragment : Fragment() {
    private var _binding:FragmentAllProductBinding?=null
    private val binding get()=_binding!!
    private val myDataAdapter by lazy { mProductList?.let { MyDataAdapter(it) } }
    private  var mProductList:ArrayList<ProductList.Product>?=null





    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentAllProductBinding.inflate(inflater,container,false)
        _binding!!.recyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
        _binding!!.recyclerView.adapter = myDataAdapter


        myDataAdapter?.setData(arrayListOf())
        getProductList()
        return binding.root

    }

    private fun  getProductList(){
        RetrofitClient.getNewUserApi().getProductList().enqueue(object : Callback<ProductList> {
            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                val listData = response.body()
                if (response.code() == 200) {
                    if (listData != null) {

                        mProductList?.addAll(listData.products)
                       


                    }
                } else if (response.code() in 400..499) {
                    ErrorUtils.parseError(response.errorBody()?.toString()!!)
                }
            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                Log.d(ContentValues.TAG, t.localizedMessage)

            }

        })
    }
    }



