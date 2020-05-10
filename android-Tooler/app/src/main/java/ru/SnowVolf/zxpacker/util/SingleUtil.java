package ru.SnowVolf.zxpacker.util;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SingleUtil {

    private static Activity getActivity() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);

            Map<Object, Object> activities = (Map<Object, Object>) activitiesField.get(activityThread);
            if (activities == null)
                return null;

            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    return (Activity) activityField.get(activityRecord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void init(String assetArchiveName, String destinationPath){
        Decompress d = new Decompress();
        d.unzipFromAssets(assetArchiveName, destinationPath);
    }

    public static class Decompress {
        private static final int BUFFER_SIZE = 1024 * 10;
        private static final String TAG = "Decompress";

        void unzipFromAssets(String zipFile, String destination) {
            try {
                assert getActivity() != null;
                if (destination == null || destination.length() == 0) {
                    destination = getActivity().getFilesDir().getAbsolutePath();
                }
                InputStream stream = getActivity().getAssets().open(zipFile);
                unzip(stream, destination);
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        public void unzip(String zipFile, String location) {
            try {
                FileInputStream fin = new FileInputStream(zipFile);
                unzip(fin, location);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        private void unzip(InputStream stream, String destination) {
            dirChecker(destination, "");
            byte[] buffer = new byte[BUFFER_SIZE];
            try {
                ZipInputStream zin = new ZipInputStream(stream);
                ZipEntry ze;

                while ((ze = zin.getNextEntry()) != null) {
                    Log.v(TAG, "Unzipping " + ze.getName());

                    if (ze.isDirectory()) {
                        dirChecker(destination, ze.getName());
                    } else {
                        File f = new File(destination + ze.getName());
                        if (!f.exists()) {
                            FileOutputStream fout = new FileOutputStream(destination + ze.getName());
                            int count;
                            while ((count = zin.read(buffer)) != -1) {
                                fout.write(buffer, 0, count);
                            }
                            zin.closeEntry();
                            fout.close();
                        }
                    }

                }
                zin.close();
            } catch (Exception e) {
                Log.e(TAG, "unzip", e);
            }

        }

        private void dirChecker(String destination, String dir) {
            File f = new File(destination + dir);
            File d = new File(destination);

            if (!d.exists() || !d.isDirectory()){
                d.mkdirs();
            }

            if (!f.isDirectory()) {
                boolean success = f.mkdirs();
                if (!success) {
                    Log.w(TAG, "Failed to create folder " + f.getName());
                }
            }
        }
    }
}
