package com.example.decryption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {
    WifiManager wifiManager;
    WifiInfo wifiInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();

    }

     void sendBroadCast(){
        int ip = wifiInfo.getIpAddress();

        int broadCastIp = ip | 0xFF000000;

         DatagramSocket Ds = null;
         try{
             InetAddress server = InetAddress.getByName(Formatter.formatIpAddress(broadCastIp));
             Ds = new DatagramSocket();
             String data = "Hallo";
             DatagramPacket Dp = new DatagramPacket(data.getBytes(), data.length(), server, 5500);
             Ds.send(Dp);
         }catch (IOException e){
             e.printStackTrace();
         }finally {
             if(Ds != null){
                 Ds.close();
             }
         }
    }
}