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

* #### xml中使用,默认显示ContentView里面的视图
* #### StatusRelativeLayout可以根据需求更改为StatusFrameLayout或者StatusLinearLayout
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

or
//当StatusRelativeLayout布局里面同时存在child view和 app:rContentView="@layout/status_content"时，默认显示rContentView的内容，如果
只存在child view则显示child view。
<com.enlogy.statusview.StatusRelativeLayout
        android:id="@+id/status_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:rEmptyView="@layout/status_empty"
        app:rErrorView="@layout/status_error"
        app:rExtendView="@layout/status_extend"
        app:rLoadingView="@layout/status_loading"
        app:rNoNetworkView="@layout/status_no_network">
	<TextView
	android:id="@+id/tv"	  
	android:layout_width="wrap_content"
        android:layout_height="wrap_content"
	android:text="Hello World"/>
	
	<Button
        android:layout_below="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:text="OK"/>
		
</com.enlogy.statusview.StatusRelativeLayout>
```
* #### 代码中切换布局  
```java
 //声明
 private StatusView statusView;
 //使用,单一调用直接切换视图
 statusView.showContent();//主视图
 statusView.showEmptyContent();//空视图
 statusView.showErrorContent();//错误视图
 statusView.showNoNetworkContent();//无网络视图
 statusView.showLoadingContent();//加载中视图
 statusView.showExtendContent();//扩展视图
```      
* #### 自定义属性说明
```xml
使用StatusRelativeLayout时，自定义属性对应r开头
rContentView 主视图
rEmptyView 空视图
rErrorView 错误视图
rExtendView 扩展视图
rLoadingView 加载中视图
rNoNetworkView 无网络视图

or

使用StatusFrameLayout时，自定义属性对应f开头
fContentView 主视图
fEmptyView 空视图
fErrorView 错误视图
fExtendView 扩展视图
fLoadingView 加载中视图
fNoNetworkView 无网络视图

or

使用StatusLinearLayout时，自定义属性对应l开头
lContentView 主视图
lEmptyView 空视图
lErrorView 错误视图
lExtendView 扩展视图
lLoadingView 加载中视图
lNoNetworkView 无网络视图
```
