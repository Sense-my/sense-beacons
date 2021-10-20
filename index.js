// main index.js

import { NativeModules } from 'react-native';

const { SenseBeacons } = NativeModules;

export function deb(){
    console.log("dd",SenseBeacons);
}

