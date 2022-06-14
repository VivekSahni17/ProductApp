//package com.vivek.productapp
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.vivek.productapp.retrofit.RetrofitApi
//import com.vivek.productapp.retrofit.RetrofitClient
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import retrofit2.Call
//import retrofit2.Retrofit
//
//class MainViewModel: ViewModel() {
//
//    var recyclerListLiveData = MutableLiveData<ProductList>()
//    init {
//        recyclerListLiveData= MutableLiveData()
//    }
//    fun getRecyclerListObserver():MutableLiveData<ProductList>{
//        return  recyclerListLiveData
//    }
//    fun makeApiCall() {
//        // Create a new coroutine to move the execution off the UI thread
//        viewModelScope.launch(Dispatchers.IO) {
//            val mainInstance=RetrofitClient.getNewUserApi().create(RetrofitApi::class.java)
//            val response= mainInstance.getProductList()
//            postValue(response)
//        }
//    }
//
//    private fun postValue(response: Call<ProductList>) {
//
//    }
//}