LOCAL_PATH := $(call my-dir)
#ifneq ($(BOARD_HAVE_BLUETOOTH_RTK),)
include $(CLEAR_VARS)

BDROID_DIR := $(TOP_DIR)external/bluetooth/bluedroid

LOCAL_SRC_FILES := \
        src/bt_vendor_rtk.c src/upio.c

LOCAL_C_INCLUDES += \
        $(LOCAL_PATH)/include \
        $(BDROID_DIR)/hci/include

LOCAL_SHARED_LIBRARIES := \
        libcutils

LOCAL_MODULE := libbt-vendor
LOCAL_MODULE_TAGS := eng optional
LOCAL_MODULE_CLASS := SHARED_LIBRARIES
LOCAL_MODULE_OWNER := hci
LOCAL_MODULE_PATH := $(TARGET_OUT_VENDOR_SHARED_LIBRARIES)

include $(LOCAL_PATH)/vnd_buildcfg.mk

include $(BUILD_SHARED_LIBRARY)

#endif # BOARD_HAVE_BLUETOOTH_RTK
