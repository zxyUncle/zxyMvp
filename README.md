# Kotlin+MVP+Okhttp+Retrifit+Rxjava+lifecy+多语言
1、加入      
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
      
2、可以只使用其中的一个或者多个      
-----   
Module:
 1. zxyhttp:网路module           
    implementation 'com.github.zxyUncle:zxyMvp:Tag'  
 2. zxymvp:mvp框架module
    implementation 'com.github.zxyUncle.zxyMvp:zxyhttp:Tag'    
 3. zxymultilingual:多语言module
implementation 'com.github.zxyUncle.zxyMvp:zxymultilingual:Tag'
                
效果图： 
-----   
![Image text](https://github.com/zxyUncle/zxyMvp/blob/master/picture/aaa.png)     
-----   
![Image text](https://github.com/zxyUncle/zxyMvp/blob/master/picture/bbb.png)     
-----   
![Image text](https://github.com/zxyUncle/zxyMvp/blob/master/picture/mvp.gif)   
-----   

