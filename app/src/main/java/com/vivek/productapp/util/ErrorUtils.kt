package com.vivek.productapp.util

import android.text.TextUtils
import android.util.Log
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object ErrorUtils {
       private val ERRORS = "errors"
        private val ERROR = "error"

        fun parseError(`object`: Any) {
            when (`object`) {
                is JSONObject -> {
                    parseJson(`object` as JsonObject)
                }
                is JsonObject -> {
                    parseJSON(`object` as JSONObject)
                }
                is String -> {
                    try {
                        val json = JSONObject(`object`.toString())
                        getError(json)
                    } catch (e: JSONException) {
                        // Toaster.INSTANCE.somethingWentWrong();
                    }
                }
                else -> {
                    Log.e("TAG", "ON ERROR")
                    //            Toaster.INSTANCE.somethingWentWrong();
                }
            }
        }

        private fun parseJSON(`object`: JSONObject) {
            try {
                getError(`object`)
            } catch (e: JSONException) {
//            Toaster.INSTANCE.somethingWentWrong();
            }
        }

        @Throws(JSONException::class)
        private fun getError(`object`: JSONObject) {
            if (`object`.has(ERRORS)) {
                if (`object`.optJSONObject(ERRORS) is JSONObject) {
                    getDynamicError(`object`.optJSONObject(ERRORS))
                    return
                }
            }
            if (`object`.has("message")) {
                //Toaster.INSTANCE.shortToast(`object`.getString("message"))
                return
            }
            val obj = `object`[ERROR]
            if (obj is JSONObject) {
                getDynamicError(obj)
            } else if (obj is String) {
//            Toaster.INSTANCE.shortToast(obj.toString());
            } else if (obj is JSONArray) {
            }
        }

        private fun getDynamicError(obj: JSONObject) {
            val it = obj.keys()
            while (it.hasNext()) {
                val ob = obj.opt(it.next())
                if (ob is Array<*>) {
                    val err = ob
                    //                Toaster.INSTANCE.shortToast(err[0]);
                    break
                } else if (ob is JSONArray) {
                    if (parseJSONArray(ob)) break
                }
            }
        }

        private fun parseJSONArray(ob: JSONArray): Boolean {
            try {
                val error = ob[0] as String
                //            Toaster.INSTANCE.shortToast(error);
                return true
            } catch (e: JSONException) {
//            Toaster.INSTANCE.somethingWentWrong();
            }
            return false
        }

        private fun parseJson(`object`: JsonObject) {
            if (!`object`.has(ERROR)) {
//            Toaster.INSTANCE.somethingWentWrong();
                return
            }
            if (`object`["error"].isJsonObject) {
                val jsonString = `object`[ERROR].asJsonObject.asString
                try {
                    val js = JSONObject(jsonString)
                    getError(js)
                } catch (e: JSONException) {
//                Toaster.INSTANCE.somethingWentWrong();
                }
            }
        }

        fun onFailure(t: Throwable) {
            if (TextUtils.isEmpty(t.message)) {
//            Toaster.INSTANCE.somethingWentWrong();
            } else {
                // Toaster.INSTANCE.shortToast(t.getMessage());
            }
        }


    }