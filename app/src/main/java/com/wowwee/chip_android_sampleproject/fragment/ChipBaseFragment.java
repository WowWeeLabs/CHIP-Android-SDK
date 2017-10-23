package com.wowwee.chip_android_sampleproject.fragment;

import android.support.v4.app.Fragment;

import com.wowwee.bluetoothrobotcontrollib.BluetoothRobotConstants;
import com.wowwee.bluetoothrobotcontrollib.chip.ChipRobot;

/**
 * Created by davidchan on 24/3/2017.
 */

public class ChipBaseFragment extends Fragment implements ChipRobot.ChipRobotInterface {

    @Override
    public void chipDeviceReady(ChipRobot chip) {

    }

    @Override
    public void chipDeviceDisconnected(ChipRobot chip) {

    }

    @Override
    public void chipDidReceiveVolumeLevel(ChipRobot chip, byte volume) {

    }

    @Override
    public void chipDidReceivedBatteryLevel(ChipRobot chip, float batteryLevel, byte chargingStatus, byte chargerType) {

    }

    @Override
    public void chipDidReceiveDogVersionWithBodyHardware(int chipVerBodyHardwareVer, int chipVerHeadHardware, int chipVerMechanic, int chipVerBleSpiFlash, int chipVerNuvotonSpiFlash, int chipVerBleBootloader, int chipVerBleApromFirmware, int chipVerNuvotonBootloaderFirmware, int chipVerNuvotonApromFirmware, int chipVerNuvotonVR) {

    }

    @Override
    public void chipDidSendAdultOrKidSpeed() {

    }

    @Override
    public void chipDidReceiveAdultOrKidSpeed(byte speed) {

    }

    @Override
    public void chipDidReceiveEyeRGBBrightness(byte value) {

    }

    @Override
    public void chipDidReceiveCurrentClock(int year, int month, int day, int hour, int minute, int seconds, int dayOfWeek) {

    }

    @Override
    public void chipDidReceiveCurrentAlarm(int year, int month, int day, int hour, int minute) {

    }

    @Override
    public void chipDidReceiveBodyconStatus(int status) {

    }
}
