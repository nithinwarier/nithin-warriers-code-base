package com.androidians.getlocationupdates

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.androidians.getlocationupdates.databinding.ActivityMainBinding
import com.androidians.getlocationupdates.utils.createLocationRequest
import com.androidians.getlocationupdates.utils.findAndSetText
import com.androidians.getlocationupdates.utils.hasPermission
import com.androidians.getlocationupdates.utils.showLocation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    private val permReqLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
    { permissions ->
            val granted = permissions.entries.all {
                it.value == true
            }
            if (granted) {
                recreate()
            }
    }
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            showLocation(binding.locationUpdateTV, locationResult.lastLocation)
        }
    }
    private var listeningToUpdates = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStart() {
        super.onStart()

        if (!hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permReqLauncher.launch(permissions)
        }

        getLastKnownLocation()
        // crashing because of Looper.getMainLooper()
        //startUpdatingLocation()
    }

    private fun startUpdatingLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e(TAG, "No permission, cancelling")
            return
        }
        fusedLocationClient.requestLocationUpdates(
            createLocationRequest(),
            locationCallback,
            Looper.myLooper()!!
        ).addOnSuccessListener { listeningToUpdates = true }
        .addOnFailureListener { e ->
                findAndSetText(binding.locationUpdateTV, "Unable to get location.")
                Log.d(TAG, "Unable to get location", e)
            }
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { lastLocation ->
            showLocation(binding.locationUpdateTV, lastLocation)
        }.addOnFailureListener { e ->
            Log.d(TAG, "Unable to get location", e)
        }
    }

    override fun onStop() {
        super.onStop()
        if (listeningToUpdates) {
            stopUpdatingLocation()
        }
    }

    private fun stopUpdatingLocation() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            recreate()
        }
    }
}

const val TAG = "KTXCODELAB"