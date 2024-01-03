// IMyAidlInterface.aidl
package com.jason.aidl;

// Declare any non-default types here with import statements
import com.jason.aidl.ICallBackInterface;
interface IMyAidlInterface {

String getName();
void setName(String name);

void setCallbacl(ICallBackInterface callback);


    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}