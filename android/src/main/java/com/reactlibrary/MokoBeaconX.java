package com.reactlibrary;

import com.moko.beaconx.entity.BeaconXInfo;
import com.moko.beaconx.entity.BeaconXURL;
import com.moko.beaconx.entity.BeaconXiBeacon;
import com.moko.beaconx.utils.BeaconXInfoParseableImpl;
import com.moko.beaconx.utils.BeaconXParser;
import com.moko.beaconxpro.entity.BeaconXUID;
import com.moko.support.entity.DeviceInfo;

import java.util.ArrayList;

public class MokoBeaconX {

    MokoBeaconX(){

    }

    public static String getBeacon(DeviceInfo deviceInfo) {
        String tmp = "";
        try {
            BeaconXInfoParseableImpl beaconXInfoParseableb = new BeaconXInfoParseableImpl();
            BeaconXInfo beaconXInfo = beaconXInfoParseableb.parseDeviceInfo(deviceInfo);

            ArrayList<BeaconXInfo.ValidData> validDatas2 = new ArrayList<>(beaconXInfo.validDataHashMap.values());
            for (BeaconXInfo.ValidData validData : validDatas2) {



                /*if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_URL) {
                    BeaconXURL beaconXURL = BeaconXParser.getURL(validData.data);
                    String urlstring = beaconXURL.url;
                    urlstring = urlstring.substring(10);
                    String[] array = urlstring.split(".", -1);
                    tmp = "2URL"+array[0]+","+array[1]+","+array[2]+","+beaconXInfo.rssi;
                }*/

                if (validData.type == BeaconXInfo.VALID_DATA_FRAME_TYPE_IBEACON) {
                    BeaconXiBeacon beaconXiBeacon = BeaconXParser.getiBeacon(validData.data);
                   if(beaconXiBeacon.major != "")
                       tmp = "2iBeacon"+beaconXiBeacon.major+","+beaconXiBeacon.minor+","+beaconXiBeacon.uuid+","+beaconXInfo.rssi;
                }
            }
        } catch (Exception exception) {
            tmp = "";
            // return "";
        }
        return tmp;
    }


}
