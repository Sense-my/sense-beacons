// SenseBeaconsModule.java

package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.moko.beaconxpro.entity.BeaconXAxis;
import com.moko.beaconxpro.entity.BeaconXInfo;
import com.moko.beaconxpro.entity.BeaconXTH;
import com.moko.beaconxpro.entity.BeaconXUID;
import com.moko.beaconxpro.entity.BeaconXURL;
import com.moko.beaconxpro.entity.BeaconXiBeacon;
import com.moko.beaconxpro.utils.BeaconXInfoParseableImpl;
import com.moko.beaconxpro.utils.BeaconXParser;
import com.moko.support.MokoBleScanner;
import com.moko.support.MokoSupport;
import com.moko.support.callback.MokoScanDeviceCallback;
import com.moko.support.entity.DeviceInfo;

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
    ArrayList<String> iBeacons = new ArrayList<String>();

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
                    iBeacons.add("");
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
        String ytr = "";
        if(iBeacons.get(index) != ""){
            ytr = iBeacons.get(index);
        }else{


        BeaconXInfoParseableImpl beaconXInfoParseable = new BeaconXInfoParseableImpl();
        try{
            BeaconXInfo beaconXInfo = beaconXInfoParseable.parseDeviceInfo(deviceInfo);
            ArrayList<BeaconXInfo.ValidData> validDatas = new ArrayList<>(beaconXInfo.validDataHashMap.values());


            for (BeaconXInfo.ValidData validData : validDatas) {


                if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_UID) {
                    BeaconXUID beaconXUID = BeaconXParser.getUID(validData.data);
                    //ytr = "UID"+beaconXUID.instanceId;
                }
                if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_URL) {
                    BeaconXURL beaconXURL = BeaconXParser.getURL(validData.data);
                    String urlstring = "http://www.r11111111111r.5.7,1635228930134";
                    urlstring = urlstring.substring(10);
                    String[] array = urlstring.split(".", -1);
                    //ytr = "URL"+array[0]+","+array[1]+","+array[2]+","+beaconXInfo.rssi;
                }
                if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_TLM) {
                    //BeaconXTLM beaconXTLM = BeaconXParser.getTLM(validData.data);
                    //ytr = "TLM"+beaconXTLM.toString()+"||";
                }
                if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_IBEACON) {
                    BeaconXiBeacon beaconXiBeacon = BeaconXParser.getiBeacon(beaconXInfo.rssi, validData.data);
                    ytr = "iBeacon"+beaconXiBeacon.major+","+beaconXiBeacon.minor+","+beaconXiBeacon.uuid+","+beaconXInfo.rssi;
                    iBeacons.set(index,ytr);
                }
                if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_TH) {
                    BeaconXTH beaconXTH = BeaconXParser.getTH(validData.data);
                    //ytr = "TH"+beaconXTH.humidity;
                }
                if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_AXIS) {
                    BeaconXAxis beaconXAxis = BeaconXParser.getAxis(validData.data);
                    //ytr = "Axis"+beaconXAxis.scale;
                }


            }

            //return major+","+minor+","+uuid;
        }catch(Exception exception){
            ytr = "";
           // return "";
        }

        if(ytr == ""){

            ytr = MokoBeaconX.getBeacon(deviceInfo);
            if(ytr != ""){
                iBeacons.set(index,ytr);
            }
         /*   try{
                BeaconXInfoParseableImplB beaconXInfoParseableb = new BeaconXInfoParseableImplB();
                BeaconXInfo beaconXInfo = beaconXInfoParseableb.parseDeviceInfo(deviceInfo);
                ArrayList<BeaconXInfo.ValidData> validDatas2 = new ArrayList<>(beaconXInfo.validDataHashMap.values());
                for (BeaconXInfo.ValidData validData : validDatas2) {


                    BeaconXiBeacon beaconXiBeacon = BeaconXParser.getiBeacon(beaconXInfo.rssi, validData.data);
                    ytr = "iBeacon"+beaconXiBeacon.major+","+beaconXiBeacon.minor+","+beaconXiBeacon.uuid+","+beaconXInfo.rssi;
                }
                ytr = "PURSY"+beaconXInfo.mac+validDatas2.toString();
            }catch(Exception exception){
                ytr =  "";
                // return "";
            }

          */

        }
        }
        return ytr != ""? ytr+","+timestamps.get(index)+"||":"";
//84:CC:A8:65:C9:6A

    }

    /*public static BeaconXiBeacon getiBeacon(String data) {
        BeaconXiBeacon iBeacon = new BeaconXiBeacon();
        iBeacon.uuid = data.substring(0, 32);
        iBeacon.major = Integer.parseInt(data.substring(32, 36), 16) + "";
        iBeacon.minor = Integer.parseInt(data.substring(36, 40), 16) + "";
        int txPower = Integer.parseInt(data.substring(40, 42), 16);
        iBeacon.rangingData = (byte) txPower + "";
        return iBeacon;
    }*/


}
