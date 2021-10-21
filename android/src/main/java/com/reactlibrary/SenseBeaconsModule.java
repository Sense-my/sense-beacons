// SenseBeaconsModule.java

package com.reactlibrary;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.moko.beaconxpro.entity.BeaconXAxis;
import com.moko.beaconxpro.entity.BeaconXInfo;
import com.moko.beaconxpro.entity.BeaconXTH;
import com.moko.beaconxpro.entity.BeaconXTLM;
import com.moko.beaconxpro.entity.BeaconXUID;
import com.moko.beaconxpro.entity.BeaconXURL;
import com.moko.beaconxpro.entity.BeaconXiBeacon;
import com.moko.beaconxpro.utils.BeaconXInfoParseableImpl;
import com.moko.beaconxpro.utils.BeaconXParser;
import com.moko.support.MokoBleScanner;
import com.moko.support.MokoSupport;
import com.moko.support.callback.MokoScanDeviceCallback;
import com.moko.support.entity.DeviceInfo;

import org.json.JSONArray;

import java.security.Timestamp;
import java.util.ArrayList;


public class SenseBeaconsModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public SenseBeaconsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "SenseBeacons";
    }

    ArrayList<DeviceInfo> beacons = new ArrayList<DeviceInfo>();
    ArrayList<String> macs = new ArrayList<String>();
    ArrayList<String> timestamps = new ArrayList<String>();

    @ReactMethod
    public void initilize(Callback callback) {
        MokoSupport.getInstance().init(reactContext);
        callback.invoke("Beacons Initilized");
    }

    @ReactMethod
    public void getBeacons(Callback callback) {
        String t = "";
        for(int i = 0; i < beacons.size(); i++){
            t += parseData(i,beacons.get(i));
        }
        callback.invoke(t == ""? beacons.size():t);
    }

    @ReactMethod
    public void startScan(Callback callback) {
        MokoBleScanner mokoBleScanner = new MokoBleScanner(reactContext);
        mokoBleScanner.startScanDevice(new MokoScanDeviceCallback() {
            @Override
            public void onStartScan() {
            }

            @Override
            public void onScanDevice(DeviceInfo device) {
                Long tsLong = System.currentTimeMillis();
                String ts = tsLong.toString();

                if(!macs.contains(device.mac)){

                    macs.add(device.mac);
                    timestamps.add(ts);
                    beacons.add(device);
                }else{
                    int index = macs.indexOf(device.mac);
                    beacons.set(index, device);
                    timestamps.set(index, ts);
                }

            }

            @Override
            public void onStopScan() {
            }

        });

        callback.invoke("Beacons scan started");
    }



    String parseData(int index, DeviceInfo deviceInfo){
        BeaconXInfoParseableImpl beaconXInfoParseable = new BeaconXInfoParseableImpl();
        try{
            BeaconXInfo beaconXInfo = beaconXInfoParseable.parseDeviceInfo(deviceInfo);
            ArrayList<BeaconXInfo.ValidData> validDatas = new ArrayList<>(beaconXInfo.validDataHashMap.values());

            String ytr = "";
            for (BeaconXInfo.ValidData validData : validDatas) {
                /*if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_IBEACON) {
                    BeaconXiBeacon beaconXiBeacon = BeaconXParser.getiBeacon(beaconXInfo.rssi, validData.data);
                    major = beaconXiBeacon.major;
                    minor = beaconXiBeacon.minor;
                    uuid = beaconXiBeacon.uuid;
                }*/

                /*if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_UID) {
                    BeaconXUID beaconXUID = BeaconXParser.getUID(validData.data);
                    //ytr = beaconXUID.instanceId;
                }
                if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_URL) {
                    BeaconXURL beaconXURL = BeaconXParser.getURL(validData.data);
                    //ytr = beaconXURL.url;
                }
                if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_TLM) {
                    BeaconXTLM beaconXTLM = BeaconXParser.getTLM(validData.data);
                    //ytr = beaconXTLM.adv_cnt;
                }*/
                if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_IBEACON) {
                    BeaconXiBeacon beaconXiBeacon = BeaconXParser.getiBeacon(beaconXInfo.rssi, validData.data);
                    ytr = beaconXiBeacon.major+","+beaconXiBeacon.minor+","+beaconXiBeacon.uuid+","+beaconXInfo.rssi+","+timestamps.get(index)+"||";
                }
                /*if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_TH) {
                    BeaconXTH beaconXTH = BeaconXParser.getTH(validData.data);
                    //ytr = beaconXTH.humidity;
                }
                if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_AXIS) {
                    BeaconXAxis beaconXAxis = BeaconXParser.getAxis(validData.data);
                    //ytr = beaconXAxis.scale;
                }*/
            }
            return ytr;
            //return major+","+minor+","+uuid;
        }catch(Exception exception){
            return "";
        }


    }
}
