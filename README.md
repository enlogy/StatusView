<div style="display: flex;flex-direction: row;justify-content: center" width="100%">
      <img src="./img/logo.png"></img>
</div>

# StatusView 介绍
可以帮助android开发者减轻多种状态页面切换的代码量，如NoNetwork、Error、Empty等页面轻松切换。

## 引入


* Gradle 
   
   ```
   compile 'com.enlogy:statusview:1.0.0'
   ```
   
* Maven
	
	```
      <dependency>
        <groupId>com.enlogy</groupId>
        <artifactId>statusview</artifactId>
        <version>1.0.0</version>
        <type>pom</type>
      </dependency>
	
	```
      
## 使用
#### 基础用法

* #### xml中使用
```xml
 <com.enlogy.statusview.StatusRelativeLayout
        android:id="@+id/status_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:rContentView="@layout/status_content"
        app:rEmptyView="@layout/status_empty"
        app:rErrorView="@layout/status_error"
        app:rExtendView="@layout/status_extend"
        app:rLoadingView="@layout/status_loading"
        app:rNoNetworkView="@layout/status_no_network" />

```
      
      
