
# react-native-sense-beacons

## Getting started

`$ npm install react-native-sense-beacons --save`

### Mostly automatic installation

`$ react-native link react-native-sense-beacons`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-sense-beacons` and add `RNSenseBeacons.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNSenseBeacons.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNSenseBeaconsPackage;` to the imports at the top of the file
  - Add `new RNSenseBeaconsPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-sense-beacons'
  	project(':react-native-sense-beacons').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-sense-beacons/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-sense-beacons')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNSenseBeacons.sln` in `node_modules/react-native-sense-beacons/windows/RNSenseBeacons.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Sense.Beacons.RNSenseBeacons;` to the usings at the top of the file
  - Add `new RNSenseBeaconsPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNSenseBeacons from 'react-native-sense-beacons';

// TODO: What to do with the module?
RNSenseBeacons;
```
  