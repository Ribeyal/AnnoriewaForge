
#include <Windows.h>
#include "jni.h"

#include "loader.h"
#include "classes.h"

DWORD WINAPI MainThread(CONST LPVOID lpParam)
{
    HMODULE jvmDll = GetModuleHandleA("jvm.dll"); //get jvm.dll module
    if (!jvmDll)
    {

        MessageBoxA(NULL, "jvm failed", "SternLoader", MB_OK | MB_ICONERROR);
        ExitThread(0);

    }
    FARPROC getJvmsVoidPtr = GetProcAddress(jvmDll, "JNI_GetCreatedJavaVMs");
    if (!getJvmsVoidPtr)
    {
        MessageBoxA(NULL, "jvm failed", "SternLoader", MB_OK | MB_ICONERROR);
        ExitThread(0);
    }

    typedef jint(JNICALL* GetCreatedJavaVMs)(JavaVM**, jsize, jsize*);
    GetCreatedJavaVMs jni_GetCreatedJavaVMs = (GetCreatedJavaVMs)getJvmsVoidPtr;
    JavaVM* jvm = NULL;
    jni_GetCreatedJavaVMs(&jvm, 1, NULL);
    if (jvm == NULL)
    {
        MessageBoxA(NULL, "jvm handle failed", "SternLoader", MB_OK | MB_ICONERROR);
        ExitThread(0);
    }

    JNIEnv* jniEnv = NULL;
    jvm->AttachCurrentThread((void**)(&jniEnv), 0);
    jvm->GetEnv((void**)(&jniEnv), JNI_VERSION_1_8);
    if (!jniEnv)
    {
        MessageBoxA(NULL, "jvm handle failed", "SternLoader", MB_OK | MB_ICONERROR);
        jvm->DetachCurrentThread();
        ExitThread(0);
    }
    jclass classLoaderClazz = jniEnv->DefineClass(NULL, NULL, (jbyte*)classLoaderClass, sizeof(classLoaderClass));
    if (!classLoaderClazz)
    {
        MessageBoxA(NULL, "jvm handle failed", "SternLoader", MB_OK | MB_ICONERROR);
        jvm->DetachCurrentThread();
        ExitThread(0);
    }
    jobjectArray classesData = (jobjectArray)jniEnv->CallStaticObjectMethod(classLoaderClazz, jniEnv->GetStaticMethodID(classLoaderClazz, "getByteArray", "(I)[[B"), (jint)classCount);//jniEnv->NewObjectArray(classCount, jniEnv->FindClass("[B"), NULL);
    int cptr = 0;
    for (jsize j = 0; j < classCount; j++)
    {
        jbyteArray classByteArray = jniEnv->NewByteArray(classSizes[j]);
        jniEnv->SetByteArrayRegion(classByteArray, 0, classSizes[j], (jbyte*)(classes + cptr));
        cptr += classSizes[j];
        jniEnv->SetObjectArrayElement(classesData, j, classByteArray);
    }
    jint injectResult = jniEnv->CallStaticIntMethod(classLoaderClazz, jniEnv->GetStaticMethodID(classLoaderClazz, "injectCP", "([[B)I"), classesData);
    if (injectResult)
    {
        MessageBoxA(NULL, "jvm handle failed", "SternLoader", MB_OK | MB_ICONERROR);
        jvm->DetachCurrentThread();
        ExitThread(0);
    }
    Beep(999, 333); //if injected
    jvm->DetachCurrentThread();
    ExitThread(0);
}

BOOL APIENTRY DllMain(HMODULE hModule, DWORD dwReason, LPVOID lpReserved)
{
    switch (dwReason)
    {
    case DLL_PROCESS_ATTACH: {
        CreateThread(NULL, 0, &MainThread, NULL, 0, NULL);
    }
    case DLL_THREAD_ATTACH:
    case DLL_THREAD_DETACH:
    case DLL_PROCESS_DETACH:
        break;
    }
    return TRUE;
}