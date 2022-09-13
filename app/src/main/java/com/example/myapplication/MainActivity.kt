package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.*

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)*/
        //2.把Activity移除,资源并没有回收.
        finish()

        //3.启动Service
        val service = Intent(this, ServiceCrack::class.java)
        startService(service)
        Log.d("auto_xxx", "启动ServiceCrack服务....")


        /*Timer().schedule(object : TimerTask() {
            override fun run() {
                //需要执行的任务
                Log.d("auto_xxx","MainActivity 2秒执行一次")
            }
        }, 0,2000)*/
    }
}