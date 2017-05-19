#include <jni.h>

#ifndef _RENDERERHELPER_H
#define _RENDERERHELPER_H
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL Java_com_vicky_renderer_jni_RendererHelperJni_readMD5Mesh
        (JNIEnv *env, jclass obj, jbyteArray buf);

#ifdef __cplusplus
}
#endif
#endif