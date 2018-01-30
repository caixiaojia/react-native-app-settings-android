import React from 'react';
import {
  NativeModules
} from 'react-native';

const RCTAppSettings = NativeModules.AndroidAppSettings;

const AndroidAppSettings = {
  open: function (): void {
    RCTAppSettings.openAuthoritySetting();
  },
  openGPS: function (): void {
    RCTAppSettings.openGPSSetting();
  },
  isLocationEnabled: function (cb): void {
    RCTAppSettings.isLocationEnabled(enabled => cb(enabled));
  }
};

module.exports = AndroidAppSettings;
