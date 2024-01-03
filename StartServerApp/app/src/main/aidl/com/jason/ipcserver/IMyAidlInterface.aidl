// IMyAidlInterface.aidl
package com.jason.ipcserver;

// Declare any non-default types here with import statements

interface IMyAidlInterface {

String getName();
void setName(String name);

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}