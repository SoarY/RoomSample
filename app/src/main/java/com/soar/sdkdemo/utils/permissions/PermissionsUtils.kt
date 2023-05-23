package com.soar.sdkdemo.utils.permissions

import android.annotation.SuppressLint
import com.tbruyelle.rxpermissions2.RxPermissions
import java.util.*


/**
 * NAME：YONG_
 * Created at: 2023/5/16 15
 * Describe:
 */
object PermissionsUtils {

 @SuppressLint("CheckResult")
 fun checkPermission(rxPermissions: RxPermissions?, onGranted:Consumer<List<String>>?, onDenied:Consumer<List<String>>?=null, alwaysDenied:Consumer<List<String>>?=null, vararg mPermissions:String){
  rxPermissions?.requestEachCombined(*mPermissions)
   ?.subscribe {
    if (it.granted) {
     // `permission.name` is granted !
      onGranted?.accept(Arrays.asList(*mPermissions))
    } else if (it.shouldShowRequestPermissionRationale) {
     // Denied permission without ask never again
      onDenied?.accept(Arrays.asList(*mPermissions))
    } else {
     // Denied permission with ask never again
     // Need to go to the settings
      alwaysDenied?.accept(Arrays.asList(*mPermissions))
    }
   }
 }
}