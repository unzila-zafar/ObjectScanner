package android.example.com.objectscanner

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class PermissionUtils {

    companion object {
        fun requestPermission(activity: Activity, requestCode: Int, permissions: String): Boolean {
            var granted: Boolean = false;
            var permissionsNeeded: ArrayList<String> = ArrayList();
            for (s in permissions) {
                val permissionCheck = ContextCompat.checkSelfPermission(activity, s.toString())
                val hasPermission =
                    permissionCheck == PackageManager.PERMISSION_GRANTED
                granted = granted and hasPermission
                if (!hasPermission) {
                    permissionsNeeded.add(s.toString())
                }
            }

            return if (granted) {
                true
            } else {
                ActivityCompat.requestPermissions(
                    activity,
                    permissionsNeeded.toTypedArray(),
                    requestCode
                )
                false
            }
        }

        fun permissionGranted(
            requestCode: Int, permissionCode: Int, grantResults: IntArray
        ): Boolean {
            return requestCode == permissionCode && grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
        }

    }
}