// IMyAidlInterface.aidl
package com.jason.aidl;

// Declare any non-default types here with import statements
import com.jason.aidl.ICallBackInterface;
interface IMyAidlInterface {

String getName();
void setName(String name);

void setCallbacl(ICallBackInterface callback);

}