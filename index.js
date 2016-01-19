/**
 * @providesModule WebViewAndroid
 */
'use strict';

var React = require('react-native');
var { requireNativeComponent, PropTypes } = React;
var RCTUIManager = React.NativeModules.UIManager;

var WEBVIEW_REF = 'androidWebView';

var WebViewAndroid = React.createClass({
  propTypes: {
    url: PropTypes.string,
    baseUrl: PropTypes.string,
    html: PropTypes.string,
    htmlCharset: PropTypes.string,
    userAgent: PropTypes.string,
    injectedJavaScript: PropTypes.string,
    disablePlugins: PropTypes.bool,
    disableCookies: PropTypes.bool,
    javaScriptEnabled: PropTypes.bool,
    geolocationEnabled: PropTypes.bool,
    builtInZoomControls: PropTypes.bool,
    onNavigationStateChange: PropTypes.func
  },
  _onNavigationStateChange: function(event) {
  },
  goBack: function() {
  },
  goForward: function() {
  },
  reload: function() {
  },
  render: function() {
    return <RNWebViewAndroid ref={WEBVIEW_REF} {...this.props} onNavigationStateChange={this._onNavigationStateChange} />;
  },
  _getWebViewHandle: function() {
    return React.findNodeHandle(this.refs[WEBVIEW_REF]);
  },
});

var RNWebViewAndroid = requireNativeComponent('RNWebViewAndroid', null);

module.exports = WebViewAndroid;
