package me.dm7.barcodescanner.core;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

public class CameraUtils {
    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;

        int front = -1;
        int back = -1;

        int  mNumberOfCameras = Camera.getNumberOfCameras();

        // Find the ID of the back-facing ("default") camera
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < mNumberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                back = i;
            }
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                front = i;
            }
        }

        try {
            if (back != -1) {
                c = Camera.open(back);
            }
            if (c == null && front != -1) {
                c = Camera.open(front);
            }
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    public static boolean isFlashSupported(Context context){
        PackageManager packageManager = context.getPackageManager();
        // if device support camera flash?
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            return true;
        }
        return false;
    }
}
