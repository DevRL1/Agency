package com.ruslanlyalko.agency.data;

/*
 * Created by Ruslan Lyalko
 * Date 09.4.2017
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class Memory {

    private static final Memory INSTANCE = new Memory();

    private static final String TAG = Memory.class.getSimpleName();

    /**
     * Lock to ensure that only one disk write happens at a time.
     */
    private static final Object SHARED_PREFS_LOCK = new Object();
    /**
     * The name of the shared preferences file to use.
     */
    private static String mSharedPrefsName;
    /**
     * Flag that ensures this store is initialized before using.
     */
    private volatile boolean mWasInitialized = false;
    /**
     * The context to use.
     */
    private volatile Context mAppContext;
    /**
     * Our data. This is a write-through cache of the data we're storing in SharedPreferences.
     */
    private ConcurrentMap<String, Object> mData;

    /**
     * Constructor.
     */
    private Memory() {
        // Nothing to do here.
    }

    /**
     * Initializes the store.
     *
     * @param context         the context to use. Using the application context is fine here.
     * @param sharedPrefsName the name of the shared prefs file to use
     * @return the singleton instance that was initialized.
     */
    public static synchronized Memory init(Context context, String sharedPrefsName) {
        // Defensive checks
        if (context == null || TextUtils.isEmpty(sharedPrefsName)) {
            throw new RuntimeException(
                    "You must provide a valid context and shared prefs name when initializing Memory");
        }

        // Initialize ourselves
        if (!INSTANCE.mWasInitialized) {
            INSTANCE.initWithContext(context, sharedPrefsName);
        }

        return INSTANCE;
    }

    /**
     * @return the singleton instance to use.
     */
    private static Memory getInstance() {
        if (!INSTANCE.mWasInitialized) {
            throw new RuntimeException(
                    "Memory was not initialized! You must run Memory.init() before using this.");
        }
        return INSTANCE;
    }

    /**
     * Clears all data from this store.
     */
    public static void forgetAll() {
        forgetAll(null);
    }

    /**
     * Clears all data from this store.
     *
     * @param callback the callback to fire when done. The callback will be fired on the UI thread,
     *                 and will be passed 'true' if successful, 'false' if not.
     */
    public static void forgetAll(final OnMemoryCallback callback) {
        getInstance().mData.clear();
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                synchronized (SHARED_PREFS_LOCK) {
                    SharedPreferences.Editor editor = getInstance().getSharedPreferences().edit();
                    editor.clear();
                    return editor.commit();
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                if (callback != null) {
                    callback.apply(success);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     * Removes the mapping indicated by the given key.
     */
    public static void forget(String key) {
        forget(key, null);
    }

    /**
     * Removes the mapping indicated by the given key.
     *
     * @param callback the callback to fire when done. The callback will be fired on the UI thread,
     *                 and will be passed 'true' if successful, 'false' if not.
     */
    public static void forget(final String key, final OnMemoryCallback callback) {
        getInstance().mData.remove(key);
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                synchronized (SHARED_PREFS_LOCK) {
                    SharedPreferences.Editor editor = getInstance().getSharedPreferences().edit();
                    editor.remove(key);
                    return editor.commit();
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                if (callback != null) {
                    callback.apply(success);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     * Put a float. This saves to memory immediately and saves to disk asynchronously.
     */
    public static Memory rememberFloat(final String key, final float value) {
        return getInstance().saveAsync(key, value, null);
    }

    /**
     * Put an int. This saves to memory immediately and saves to disk asynchronously.
     */
    public static Memory rememberInt(String key, int value) {
        return getInstance().saveAsync(key, value, null);
    }

    /**
     * Put a long. This saves to memory immediately and saves to disk asynchronously.
     */
    public static Memory rememberLong(String key, long value) {
        return getInstance().saveAsync(key, value, null);
    }

    /**
     * Put a String. This saves to memory immediately and saves to disk asynchronously.
     */
    public static Memory rememberString(String key, String value) {
        return getInstance().saveAsync(key, value, null);
    }

    /**
     * Put a boolean. This saves to memory immediately and saves to disk asynchronously.
     */
    public static Memory rememberBoolean(String key, boolean value) {
        return getInstance().saveAsync(key, value, null);
    }

    /**
     * Put a float. This saves to memory immediately and saves to disk asynchronously.
     *
     * @param callback the callback to fire when done. The callback will be fired on the UI thread,
     *                 and will be passed 'true' if successful, 'false' if not.
     */
    public static Memory rememberFloat(final String key, final float value, final OnMemoryCallback callback) {
        return getInstance().saveAsync(key, value, callback);
    }

    /**
     * Put an int. This saves to memory immediately and saves to disk asynchronously.
     *
     * @param callback the callback to fire when done. The callback will be fired on the UI thread,
     *                 and will be passed 'true' if successful, 'false' if not.
     */
    public static Memory rememberInt(String key, int value, OnMemoryCallback callback) {
        return getInstance().saveAsync(key, value, callback);
    }

    /**
     * Put a long. This saves to memory immediately and saves to disk asynchronously.
     *
     * @param callback the callback to fire when done. The callback will be fired on the UI thread,
     *                 and will be passed 'true' if successful, 'false' if not.
     */
    public static Memory rememberLong(String key, long value, OnMemoryCallback callback) {
        return getInstance().saveAsync(key, value, callback);
    }

    /**
     * Put a String. This saves to memory immediately and saves to disk asynchronously.
     *
     * @param callback the callback to fire when done. The callback will be fired on the UI thread,
     *                 and will be passed 'true' if successful, 'false' if not.
     */
    public static Memory rememberString(String key, String value, OnMemoryCallback callback) {
        return getInstance().saveAsync(key, value, callback);
    }

    /**
     * Put a boolean. This saves to memory immediately and saves to disk asynchronously.
     *
     * @param callback the callback to fire when done. The callback will be fired on the UI thread,
     *                 and will be passed 'true' if successful, 'false' if not.
     */
    public static Memory rememberBoolean(String key, boolean value, OnMemoryCallback callback) {
        return getInstance().saveAsync(key, value, callback);
    }

    /**
     * Gets a float with the given key. Defers to the fallback value if the mapping didn't exist, wasn't a float,
     * or was null.
     */
    public static float remindFloat(String key, float fallback) {
        Float value = getInstance().get(key, Float.class);
        return value != null ? value : fallback;
    }

    /**
     * Gets an int with the given key. Defers to the fallback value if the mapping didn't exist, wasn't an int,
     * or was null.
     */
    public static int remindInt(String key, int fallback) {
        Integer value = getInstance().get(key, Integer.class);
        return value != null ? value : fallback;
    }

    /**
     * Gets a long with the given key. Defers to the fallback value if the mapping didn't exist, wasn't a long,
     * or was null.
     */
    public static long remindLong(String key, long fallback) {
        Long value = getInstance().get(key, Long.class);
        return value != null ? value : fallback;
    }

    /**
     * Gets a String with the given key. Defers to the fallback value if the mapping didn't exist, wasn't a String,
     * or was null.
     */
    public static String remindString(String key, String fallback) {
        String value = getInstance().get(key, String.class);
        return value != null ? value : fallback;
    }

    /**
     * Gets a boolean with the given key. Defers to the fallback value if the mapping didn't exist, wasn't a boolean,
     * or was null.
     */
    public static boolean remindBoolean(String key, boolean fallback) {
        Boolean value = getInstance().get(key, Boolean.class);
        return value != null ? value : fallback;
    }

    /**
     * @return true if we have a mapping for the given key
     */
    public static boolean isRememberKey(String key) {
        return getInstance().mData.containsKey(key);
    }

    /**
     * Initializes this store with the given context.
     */

    private void initWithContext(Context context, String sharedPrefsName) {
        // Time ourselves
        long start = SystemClock.uptimeMillis();

        // Set vars
        mAppContext = context.getApplicationContext();
        mSharedPrefsName = sharedPrefsName;

        // Read from shared prefs
        SharedPreferences prefs = getSharedPreferences();
        mData = new ConcurrentHashMap<>();
        mData.putAll(prefs.getAll());
        mWasInitialized = true;

        long delta = SystemClock.uptimeMillis() - start;
        Log.i(TAG, "Memory took " + delta + " ms to init");
    }

    /**
     * Gets the shared preferences to use
     */
    private SharedPreferences getSharedPreferences() {
        return mAppContext.getSharedPreferences(mSharedPrefsName, Context.MODE_PRIVATE);
    }

    /**
     * Saves the given (key,value) pair to disk.
     *
     * @return true if the save-to-disk operation succeeded
     */
    private boolean saveToDisk(final String key, final Object value) {
        boolean success = false;
        synchronized (SHARED_PREFS_LOCK) {
            // Save it to disk
            SharedPreferences.Editor editor = getSharedPreferences().edit();
            boolean didPut = true;
            if (value instanceof Float) {
                editor.putFloat(key, (Float) value);

            } else if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);

            } else if (value instanceof Long) {
                editor.putLong(key, (Long) value);

            } else if (value instanceof String) {
                editor.putString(key, (String) value);

            } else if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);

            } else {
                didPut = false;
            }

            if (didPut) {
                success = editor.commit();
            }
        }

        return success;
    }

    /**
     * Saves the given (key,value) pair to memory and (asynchronously) to disk.
     *
     * @param key      the key
     * @param value    the value to put. This MUST be a type supported by SharedPreferences. Which is to say: one of (float,
     *                 int, long, String, boolean).
     * @param callback the callback to fire. The callback will be fired on the UI thread,
     *                 and will be passed 'true' if successful, 'false' if not.
     * @return this instance
     */
    private <T> Memory saveAsync(final String key, final T value, final OnMemoryCallback callback) {
        // Put it in memory
        mData.put(key, value);

        // Save it to disk
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                return saveToDisk(key, value);
            }

            @Override
            protected void onPostExecute(Boolean success) {
                // Fire the callback
                if (callback != null) {
                    callback.apply(success);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        return this;
    }

    /**
     * Gets the value mapped by the given key, casted to the given class. If the value doesn't exist or isn't of the
     * right class, return null instead.
     */
    private <T> T get(String key, Class<T> clazz) {
        Object value = mData.get(key);
        T castedObject = null;
        if (clazz.isInstance(value)) {
            castedObject = clazz.cast(value);
        }
        return castedObject;
    }

    public interface OnMemoryCallback {
        void apply(boolean successful);
    }
}