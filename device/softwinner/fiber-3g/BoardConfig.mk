# BoardConfig.mk
#
# Product-specific compile-time definitions.
#

include device/softwinner/fiber-common/BoardConfigCommon.mk

# bt default config
#BOARD_BLUETOOTH_BDROID_BUILDCFG_INCLUDE_DIR := device/softwinner/fiber-evb/bluetooth

#recovery
TARGET_RECOVERY_UI_LIB := librecovery_ui_fiber_3g
SW_BOARD_TOUCH_RECOVERY :=true
SW_BOARD_RECOVERY_MISC_PARTITION :=\"nandc\"

TARGET_NO_BOOTLOADER := true
TARGET_NO_RECOVERY := false
TARGET_NO_KERNEL := false

# wifi and bt configuration
# rtl8723as combo@fiber-evb board
# 1. Wifi Configuration
# 1.1 realtek wifi support
BOARD_WIFI_VENDOR := realtek
ifeq ($(BOARD_WIFI_VENDOR), realtek)
    WPA_SUPPLICANT_VERSION := VER_0_8_X
    BOARD_WPA_SUPPLICANT_DRIVER := NL80211
    BOARD_WPA_SUPPLICANT_PRIVATE_LIB := lib_driver_cmd_rtl
    BOARD_HOSTAPD_DRIVER        := NL80211
    BOARD_HOSTAPD_PRIVATE_LIB   := lib_driver_cmd_rtl

    #SW_BOARD_USR_WIFI := rtl8192cu
    #BOARD_WLAN_DEVICE := rtl8192cu

    #SW_BOARD_USR_WIFI := rtl8188eu
    #BOARD_WLAN_DEVICE := rtl8188eu

    #SW_BOARD_USR_WIFI := rtl8189es
    #BOARD_WLAN_DEVICE := rtl8189es

    SW_BOARD_USR_WIFI := rtl8723as
    BOARD_WLAN_DEVICE := rtl8723as

    #SW_BOARD_USR_WIFI := rtl8723au
    #BOARD_WLAN_DEVICE := rtl8723au
endif

# 1.2 broadcom wifi support
#BOARD_WIFI_VENDOR := broadcom
ifeq ($(BOARD_WIFI_VENDOR), broadcom)
    BOARD_WPA_SUPPLICANT_DRIVER := NL80211
    WPA_SUPPLICANT_VERSION      := VER_0_8_X
    BOARD_WPA_SUPPLICANT_PRIVATE_LIB := lib_driver_cmd_bcmdhd
    BOARD_HOSTAPD_DRIVER        := NL80211
    BOARD_HOSTAPD_PRIVATE_LIB   := lib_driver_cmd_bcmdhd
    BOARD_WLAN_DEVICE           := bcmdhd
    WIFI_DRIVER_FW_PATH_PARAM   := "/sys/module/bcmdhd/parameters/firmware_path"

    #SW_BOARD_USR_WIFI := AP6181
    SW_BOARD_USR_WIFI := AP6210
    WIFI_DRIVER_FW_PATH_STA    := "/system/vendor/modules/fw_bcm40181a2.bin"
    WIFI_DRIVER_FW_PATH_P2P    := "/system/vendor/modules/fw_bcm40181a2_p2p.bin"
    WIFI_DRIVER_FW_PATH_AP     := "/system/vendor/modules/fw_bcm40181a2_apsta.bin"

    #SW_BOARD_USR_WIFI := AP6330
    #WIFI_DRIVER_FW_PATH_STA    := "/system/vendor/modules/fw_bcm40183b2_ag.bin"
    #WIFI_DRIVER_FW_PATH_P2P    := "/system/vendor/modules/fw_bcm40183b2_ag_p2p.bin"
    #WIFI_DRIVER_FW_PATH_AP     := "/system/vendor/modules/fw_bcm40183b2_ag_apsta.bin"

endif

# 2. Bluetooth Configuration
# make sure BOARD_HAVE_BLUETOOTH is true for every bt vendor
#BOARD_HAVE_BLUETOOTH := true
#BOARD_HAVE_BLUETOOTH_BCM := true
#SW_BOARD_HAVE_BLUETOOTH_NAME := ap6210
#SW_BOARD_HAVE_BLUETOOTH_NAME := ap6330

# 3. radio Configuration
#SW_BOARD_HAVE_3G := true
#SW_MODEM_PRODUCT := yuga_cwm600