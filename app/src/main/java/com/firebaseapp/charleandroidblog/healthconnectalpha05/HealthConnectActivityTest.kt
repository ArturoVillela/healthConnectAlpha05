package com.firebaseapp.charleandroidblog.healthconnectalpha05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.PermissionController
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.BloodGlucoseRecord
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class HealthConnectActivityTest : AppCompatActivity() {
    val TAG = "zzzzz"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_connect_test)

        initHC()
    }

    val PERMISSIONS =
        setOf(HealthPermission.createReadPermission(BloodGlucoseRecord::class),
            HealthPermission.createWritePermission(BloodGlucoseRecord::class))

    val permissionsContract =
        PermissionController.createRequestPermissionResultContract()
    val requestPermissions =
        registerForActivityResult(permissionsContract) { granted ->
            if (granted.containsAll(PERMISSIONS)) {
                Log.d(TAG, "initHC: permissions granted...")
            } else {
                Log.d(TAG, "initHC: permissions not granted... or some at least..")
            }
        }

    private fun initHC() {
        if (HealthConnectClient.isAvailable(this)) {
            Log.d(TAG, "initHC: is available and installed")
            val healthConnectClient = HealthConnectClient.getOrCreate(this)
            //val requestPermissionActivityContract = healthConnectClient.permissionController.createRequestPermissionActivityContract()
            checkPermissionsAndRun(healthConnectClient)

        } else {
            Log.d(TAG, "initHC: is not avaliable or installed... install hc firsts..")
        }
    }

    fun checkPermissionsAndRun(client: HealthConnectClient) {
        lifecycleScope.launch {
            val granted = client.permissionController.getGrantedPermissions(PERMISSIONS)
            if (granted.containsAll(PERMISSIONS)) {
                // Permissions already granted
                Log.d(TAG, "checkPermissionsAndRun: already granted..")
            } else {
                requestPermissions.launch(PERMISSIONS)
            }
        }
    }


}