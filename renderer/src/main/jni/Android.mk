LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)


LOCAL_MODULE    := renderer_core
LOCAL_SRC_FILES := renderer_helper.cpp \
								 md5_mesh.cpp



LOCAL_CPPFLAGS := -std=c++11
LOCAL_LDLIBS    += -lm -llog 
			
include $(BUILD_SHARED_LIBRARY) 