# CSScenariosSDK
This SDK allows you to post events into Česká spořitelna a.s. [Scenarios API](http://docs.ext0csasscenarioseventsv1.apiary.io/#).

# [CHANGELOG](CHANGELOG.md)

# Requirements
- Android 4.1+
- Gradle 2.8+
- Android Studio 1.5+

# ScenariosSDK Installation

## Install
You can install CsTransparentAccSDK using the following gradle settings.

TBD

# Usage

After you've installed the SDK you will be able to use the module in your project.

## Configuration
Before using ScenariosSDK in your application, you need to initialize it by providing it your WebApiKey:

```java
    // Set your WebApi key
    CoreSDK.getInstance().useWebApiKey( "YourApiKey" )
    // Initialize transparent-acc
    // Only if you haven't set up WebApiConfiguration for CoreSDK
    TransparentAccounts.getInstance().init(WebApiConfiguration);
    //Obtain your TransparentAccountsClient
    TransparentAccountsClient client = TransparentAccounts.getInstance().getTransparentAccountsClient();

```

## Usage
**See [Usage Guide](./docs/scenarios.md)** for usage instructions.

## Proguard
The ScenariosSDK Proguard additions are necessary to be specified besides the recommended Android Proguard configuration.

**See [consumer-proguard-rules.pro](./transparent_acc/consumer-proguard-rules.pro).


# Contributing
Contributions are more than welcome!

Please read our [contribution guide](CONTRIBUTING.md) to learn how to contribute to this project.

# Terms and License
Please read our [terms & conditions in license](LICENSE.md)
