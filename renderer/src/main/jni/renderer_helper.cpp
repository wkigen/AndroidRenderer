#include "renderer_helper.h"
#include "log.h"

extern "C" {

JNIEXPORT void JNICALL Java_com_vicky_renderer_jni_RendererHelperJni_readMD5Mesh(JNIEnv *env, jclass obj, jbyteArray buf)
{
        jbyte* data = (jbyte*) env->GetPrimitiveArrayCritical( buf, 0);


        env->ReleasePrimitiveArrayCritical(buf, data, 0);

}


}
