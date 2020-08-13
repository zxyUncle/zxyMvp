# Kotlin+MVP+Okhttp+Retrifit+Rxjava+lifecy+多语言
1、只想使用MVP
-----
[![](https://jitpack.io/v/zxyUncle/zxyMvp.svg)](https://jitpack.io/#zxyUncle/zxyMvp)
Gradle
-----
Step 1


     allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	dependencies {
	        com.github.zxyUncle.zxyMvp:zxymvp:1.0
	}

2、MVP+网路访问封装
-----
Module:
1、zxyhttp:网路module
2、zxymvp:mvp框架module
3、zxymultilingual:多语言module

效果图：
![Image text](https://github.com/zxyUncle/zxyMvp/blob/master/picture/aaa.png)
![Image text](https://github.com/zxyUncle/zxyMvp/blob/master/picture/bbb.png)
![Image text](https://github.com/zxyUncle/zxyMvp/blob/master/picture/mvp.gif)

