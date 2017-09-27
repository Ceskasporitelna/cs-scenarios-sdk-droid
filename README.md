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

1. Check your project build.gradle file that it contains `JCenter` repository:
```gradle
    allprojects {
        repositories {
            ...
            jcenter()
            ...
        }
    }
```

2. Insert these lines into your module build.gradle file to compile CSScenariosSDK  (change x.y.z to the version you want to use):
```gradle
    dependencies {
        ...
        compile 'cz.csas:cs-scenarios-sdk:x.y.z@aar'
        ...
    }
```

# Usage

After you've installed the SDK you will be able to use the module in your project.

## Configuration
Before using ScenariosSDK in your application, you need to initialize it by providing it your WebApiKey:

```java
    
    // Initialize scenarios
    WebApiConfiguration webApiConfiguration = new WebApiConfiguration.Builder().setWebApiKey("your-api-key")
                    .setEnvironment("your-environment") // default is Environment.SYSTEM_TEST
                    .setAuthorizationToken("your-auth-token")
                    .setLanguage("your-language") // default is "cs-CZ"
                    .create();
    Scenarios.getInstance().init(webApiConfiguration);
    
    // Obtain your ScenariosClient
    ScenariosClient client = Scenarios.getInstance().getScenariosClient();

```

## Usage
**See [Usage Guide](./docs/scenarios.md)** for usage instructions.

## Proguard
The ScenariosSDK Proguard additions are necessary to be specified besides the recommended Android Proguard configuration.

**See [consumer-proguard-rules.pro](./scenarios/consumer-proguard-rules.pro).


# Contributing
Contributions are more than welcome!

Please read our [contribution guide](CONTRIBUTING.md) to learn how to contribute to this project.

# Terms and License
Please read our [terms & conditions in license](LICENSE.md)
