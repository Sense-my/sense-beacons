// main index.js

import { NativeModules } from 'react-native';

const { SenseBeacons } = NativeModules;

export function initilize() {
    SenseBeacons.initilize(callback => {
        console.log(callback);
    });
}
var isScan = false;
var beacons = {};
export function scan() {
    if (!isScan) {
        isScan = true;
        SenseBeacons.startScan(callback => {
            
            setInterval(function () {
                setBeacons();
            }, 1000);
        });

    }

}

function setBeacons() {
    SenseBeacons.getBeacons(callback => {
        
        var tmp = "";
        if(callback.length > 3){
            tmp = callback.substring(0, callback.length - 2)
            tmp = tmp.toString().split("||");
        }else{
            tmp = [];
        }
        
        var newArray = {};
        for (var i = 0; i < tmp.length; i++) {
            var tmp2 = tmp[i].split(",");
            try {
                var curtime = new Date().getTime();
                if (curtime - tmp2[4] <= 1500) {
                    if (typeof beacons[tmp2[1]] != "undefined") {
                        newArray[tmp2[1]] = beacons[tmp2[1]];
                        newArray[tmp2[1]].rssi = tmp2[3];
                    } else if (typeof tmp2[0] != "undefined") {
                        newArray[tmp2[1]] = {};
                        newArray[tmp2[1]].major = tmp2[0]
                        newArray[tmp2[1]].minor = tmp2[1]
                        newArray[tmp2[1]].uuid = tmp2[2]
                        newArray[tmp2[1]].rssi = tmp2[3]
                    }
                }
            } catch (e) {
                console.log("issue", e)
            }


        }

        beacons = newArray;
    });
}

export function getBeacons() {
    return beacons;
}

