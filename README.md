# Enroll and Provision a device (DemoApp)

This project serves as a demonstration of an Application that can be enrolled with on of the [Android Enterprise Work Profiles](https://www.android.com/enterprise/work-profile/)

- [Company-Owned devices for work use only](https://developers.google.com/android/management/provision-device#company-owned_devices_for_work_use_only): Full device management is suitable for company-owned devices intended exclusively for work purposes. Enterprises can manage all apps on the device and can enforce the full spectrum of Android Management API's policies and commands
- [Company-Owned devices for work and personal usage](https://developers.google.com/android/management/provision-device#company-owned_devices_for_work_and_personal_use): Setting up a company-owned device with a work profile enables the device for both work and personal use.
- [Personally-Owned Devices (BYOD)](https://developers.google.com/android/management/provision-device#personally-owned_devices) **NOT SUPPORTED YET**: Devices owned by employees can be set up with a work profile. A work profile provides a self-contained space for work apps and data, separate from personal apps and data. Most app, data, and other management policies apply to the work profile only, while the employee's personal apps and data remain private.

## Compilation
Manifest [android:testOnly="false"](https://developer.android.com/guide/topics/manifest/application-element#testOnly) property indicates whether this application is only for testing purposes. With this flag set as true, we can set it as Device Owner using the following command
```bash
# Make App Device Owner
adb shell dpm set-device-owner com.device.owner.sample.provisioning/com.device.owner.sample.provisioning.DeviceOwnerReceiver
# Remove App Device Owner permission
adb shell dpm remove-active-admin com.device.owner.sample.provisioning/com.device.owner.sample.provisioning.DeviceOwnerReceiver
```
On a real scenario, the enroll is performed using one of the following methods, under [QR Code Enroll](#qr-code-enroll) section I will explain the QR Code method

- Company-owned devices for work use only
  - Zero-touch enrollment
  - QR Code
  - Sign-in URL
  - NFC
  - DPC identifier
- Company-owned devices for work and personal use
  - Zero-touch enrollment
  - QR Code
  - Sign-in URL
  - DPC identifier

See more [Enroll and provision a device](https://developers.google.com/android/management/provision-device#company-owned_devices_for_work_use_only)

## QR Code Enroll
This is how a provision QR Code content should look like this:
```json
{
    "android.app.extra.PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME": "com.device.owner.sample.provisioning/com.device.owner.sample.provisioning.DeviceOwnerReceiver",
    "android.app.extra.PROVISIONING_DEVICE_ADMIN_SIGNATURE_CHECKSUM": "<APK_CHECKSUM>",
    "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION": "<APK_DIRECT_DOWNLOAD_LINK>"
}
```

* PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME: A ComponentName extra indicating the device admin receiver of the mobile device management application that will be set as the profile owner or device owner and active admin.
* PROVISIONING_DEVICE_ADMIN_SIGNATURE_CHECKSUM: A String extra holding the URL-safe base64 encoded SHA-256 checksum of any signature of the android package archive at the download location (For more information, read [Generate APK Checksum](#generate-apk-checksum))
* PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION: A String extra holding a url that specifies the download location of the device admin package. When not provided it is assumed that the device admin package is already installed.

See more [Enroll and provision a device: About QR codes](https://developers.google.com/android/management/provision-device#about_qr_codes)

## Generate APK Checksum

```bash
# Create a keystore in case you don't have one yet
keytool -genkey -v -keystore <KEYSTORE_NAME>.keystore -alias <ALIAS> -keyalg RSA -keysize 2048 -validity 10000
# Sign the Apk
apksigner sign -ks <KEYSTORE_NAME>.keystore --v1-signing-enabled true --v2-signing-enabled true <APK_NAME>.apk
# Retrieve the Checksum
keytool -printcert -jarfile <APK_NAME>.apk | grep -Po "(?<=SHA256:) .*" | xxd -r -p | openssl base64 | tr -d '=' | tr -- '+/=' '-_'
```
> Note: [APK Signature Scheme v2](https://source.android.com/docs/security/features/apksigning/v2) requires you to run the archive alignment tool ([ZipAlign](https://developer.android.com/tools/zipalign)) before signing the APK.
