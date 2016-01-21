# react-native-crosswalk-android
代码骨架借鉴自 https://github.com/lucasferreira/react-native-webview-android
webview 替换成 crosswalk https://crosswalk-project.org/

因为 crosswalk 包中包含的 javax* 会导致重复引用，需要在包中去掉 javax，下面的脚本可以得到一个不含 javax 包的 crosswalk aar 文件，需要放到 libs 目录下，脚本在 libs/getCrosswalk.sh 
```bash
#!/bin/sh
ver="14.43.343.25"
wget https://download.01.org/crosswalk/releases/crosswalk/android/maven2/org/xwalk/xwalk_core_library/${ver}/xwalk_core_library-${ver}.aar
unzip -j xwalk_core_library-${ver}.aar classes.jar
zip -d classes.jar javax\*
zip -r xwalk_core_library-${ver}.aar classes.jar
rm -f classes.jar
```

[![npm version](http://img.shields.io/npm/v/react-native-crosswalk-android.svg?style=flat-square)](https://npmjs.org/package/react-native-crosswalk-android "View this project on npm")
[![npm downloads](http://img.shields.io/npm/dm/react-native-crosswalk-android.svg?style=flat-square)](https://npmjs.org/package/react-native-crosswalk-android "View this project on npm")
[![npm licence](http://img.shields.io/npm/l/react-native-crosswalk-android.svg?style=flat-square)](https://npmjs.org/package/react-native-crosswalk-android "View this project on npm")

### Installation

```bash
npm install react-native-crosswalk-android --save
cp ./node_modules/react-native-crosswalk-android/libs/xwalk_core_library-14.43.343.25.aar android/app/libs
```

### Add it to your android project

* In `android/setting.gradle`

```gradle
...
include ':react-native-crosswalk-android', ':app'
project(':react-native-crosswalk-android').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-crosswalk-android')
```

* In `android/build.gradle`

```gradle
...
allprojects {
    repositories {
        mavenLocal()
        jcenter()
        
        flatDir {             // <------ add this line
            dirs 'libs'       // <------ add this line
        }                     // <------ add this line
    }
}
```

* In `android/app/build.gradle`

```gradle
...
dependencies {
  ...
  compile project(':react-native-crosswalk-android')
}
```

* Register Module (in MainActivity.java)

```java
import cn.tuofeng.rnwebview.RNWebViewPackage;  // <--- import

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {
  ......

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mReactRootView = new ReactRootView(this);

    mReactInstanceManager = ReactInstanceManager.builder()
      .setApplication(getApplication())
      .setBundleAssetName("index.android.bundle")
      .setJSMainModuleName("index.android")
      .addPackage(new MainReactPackage())
      .addPackage(new RNWebViewPackage(this)) // <------ add this line to yout MainActivity class
      .setUseDeveloperSupport(BuildConfig.DEBUG)
      .setInitialLifecycleState(LifecycleState.RESUMED)
      .build();

    mReactRootView.startReactApplication(mReactInstanceManager, "AndroidRNSample", null);

    setContentView(mReactRootView);
  }

  ......

}
```

## Example
```javascript
var React = require('react-native');
var { StyleSheet } = React;

var WebViewAndroid = require('react-native-crosswalk-android');

var SITE_URL = "https://www.google.com";

var WebViewAndroidExample = React.createClass({
    getInitialState: function() {
      return {
        url: SITE_URL,
        status: 'No Page Loaded',
        backButtonEnabled: false,
        forwardButtonEnabled: false,
        loading: true,
      };
    },
    goBack: function() {
      this.refs.webViewAndroidSample.goBack(); // you can use this callbacks to control webview
    },
    goForward: function() {
      this.refs.webViewAndroidSample.goForward();
    },
    reload: function() {
      this.refs.webViewAndroidSample.reload();
    },
    onNavigationStateChange: function(event) {
      console.log(event);

      this.setState({
        backButtonEnabled: event.canGoBack,
        forwardButtonEnabled: event.canGoForward,
        url: event.url,
        status: event.title,
        loading: event.loading
      });
    },
    render: function() {
      return (
        <WebViewAndroid
          ref="webViewAndroidSample"
          javaScriptEnabled={true}
          geolocationEnabled={false}
          builtInZoomControls={false}
          onNavigationStateChange={this.onNavigationStateChange}
          url={SITE_URL}
          style={styles.containerWebView} />
      );

      // other attributes: html(string), htmlCharset(string), baseUrl(string), injectedJavaScript(string), disableCookies(bool), disablePlugins(bool), userAgent(string)
    }
});

var styles = StyleSheet.create({
  containerWebView: {
    flex: 1,
  }
});
```

## Tips for Video (HTML5) inside WebView

To work with some html5 video player inside your Webview, I recommend you to set the android:hardwareAccelerated="true" in your AndroidManifest.xml file.

More info here: http://stackoverflow.com/questions/17259636/enabling-html5-video-playback-in-android-webview

## License
MIT
