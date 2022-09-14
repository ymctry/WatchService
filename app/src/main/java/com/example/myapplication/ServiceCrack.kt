package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.util.Log
import android.widget.Toast
import java.util.*


class ServiceCrack : Service() {


    private var pm: PowerManager? = null
    private var wakeLock: WakeLock? = null

    private var mBleAdapter: BluetoothAdapter? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("auto_xxx","Service 已经启动成功")

        getBluetoothData()
    }

    private fun getBluetoothData(): Boolean {
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            //ToastUtils.show("本机不支持蓝牙功能, 无法蓝牙打卡")
            return false
        }
        val bm = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        mBleAdapter = bm.adapter//mBleAdapter为全局变量，为BluetoothAdapter对象

        if (mBleAdapter == null) {
            //ToastUtils.show("本机不支持低功耗蓝牙功能, 无法蓝牙打卡")
            Log.d("auto_xxx","本机不支持低功耗蓝牙功能, 无法蓝牙打卡")
            return false
        }
        return true
    }

    @SuppressLint("InvalidWakeLockTag")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //创建PowerManager对象
        pm =  getSystemService(Context.POWER_SERVICE) as PowerManager
        //保持cpu一直运行，不管屏幕是否黑屏
        wakeLock = pm!!.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "CPUKeepRunning")
        wakeLock!!.acquire()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        wakeLock!!.release()
        super.onDestroy()

        Log.d("auto_xxx","Service 已销毁")
    }

}