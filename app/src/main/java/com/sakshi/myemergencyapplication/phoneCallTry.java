package com.sakshi.myemergencyapplication;

import static android.provider.Settings.System.getString;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class phoneCallTry extends AppCompatActivity {

    TelephonyManager mTelephonyManager;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call_try);

        mTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        if (isTelephonyEnabled()) {
            //Log.d(TAG, getString(R.string.telephony_enabled));
            //  Todo: Check for permission here.
            checkForPhonePermission();
            // Todo: Register the PhoneStateListener.

        } else {
            //Toast.makeText(this, R.string.telephony_not_enabled, Toast.LENGTH_LONG).show();
            //Log.d(TAG, getString(R.string.telephony_not_enabled));
            // Disable the call button.
            //disableCallButton();
        }

    }

    private boolean isTelephonyEnabled() {
        if (mTelephonyManager != null) {
            if (mTelephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY) {
                return true;
            }
        }
        return false;
    }

    private void checkForPhonePermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            // Permission not yet granted. Use requestPermissions().
            //Log.d(TAG, getString(R.string.permission_not_granted));
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            // Permission already granted.
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (permissions[0].equalsIgnoreCase(Manifest.permission.CALL_PHONE) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted.
                } else {
                    // Permission denied. Stop the app.
                    //Log.d(TAG, getString(R.string.failure_permission));
                    Toast.makeText(this, getString(R.string.failure_permission), Toast.LENGTH_SHORT).show();
                    // Disable the call button
                    //disableCallButton();
                }
            }
        }
    }
}

class MyPhoneCallListener extends PhoneStateListener {
    private boolean returningFromOffHook = false;

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        // Define a string for the message to use in a toast.
        String message = getString(R.string.phone_status);
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                // Incoming call is ringing
                message = message + getString(R.string.ringing) + incomingNumber;
                //Toast.makeText(AppCompatActivity.this, message, Toast.LENGTH_SHORT).show();
               // Log.i(TAG, message);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                // Phone call is active -- off the hook
                message = message + getString(R.string.offhook);
              //  Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                //Log.i(TAG, message);
                returningFromOffHook = true;
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                // Phone is idle before and after phone call.
                // If running on version older than 19 (KitKat),
                // restart activity when phone call ends.
                message = message + getString(R.string.idle);
               // Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                //Log.i(TAG, message);
                if (returningFromOffHook) {
                    // No need to do anything if >= version K
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                        //Log.i(TAG, getString(R.string.restarting_app));
                        // Restart the app.
                        Intent intent = getPackageManager().getLaunchIntentForPackage(this.getActivity().getApplicationContext().getPackageName());
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
                break;
            default:
                message = message + "Phone off";
               // Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                //Log.i(TAG, message);
                break;
        }
    }

    private Context getActivity() {

        return null;
    }

    private PackageManager getPackageManager() {

        return null;
    }

    private void startActivity(Intent intent) {
    }

    private String getString(int phone_status) {

        return null;
    }
}