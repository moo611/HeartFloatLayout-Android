# HeartFloatLayout
2行代码实现点赞飘心动画


## Sample示例
#### step1:添加依赖
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```
dependencies {
	        implementation 'com.github.moo611:HeartFloatLayout:1.0.1'
	}
```

#### step2:在layout中添加HeartFloatLayout
```xml
<atech.com.heartfloat.HeartFloatLayout
        android:layout_gravity="center_horizontal"
        app:float_duration="5000"
        app:scaleable = "false"
        android:id="@+id/heart"
        android:layout_width="100dp"
        android:layout_height="0dp"
        android:layout_weight="1"/>
```

#### step3 : Activity中
```java
HeartFloatLayout heartFloatLayout = findViewById(R.id.heart);
Button btn = findViewById(R.id.btn);
btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                heartFloatLayout.launchHeart();

            }
        });
```

## 自定义customize
#### 设置漂浮的图片
```java
 heartFloatLayout.setResources(new int[]{R.drawable.a1,R.drawable.a2,R.drawable.a3});

```
####设置图片的宽高(单位:dp)

```java
heartFloatLayout.setSize(20,20);
```

#### 设置飘动时间
```java
 heartFloatLayout.setFloatDuration(5000);
```
#### 设置飘动速度
```java
//匀速
 heartFloatLayout.setFloatSpeed(new LinearInterpolator());
 //先慢后快
 heartFloatLayout.setFloatSpeed(new AccelerateInterpolator());
 //先快后慢
 heartFloatLayout.setFloatSpeed(new DecelerateInterpolator());
```
#### 设置是否需要开场缩放和透明度效果
```java
 heartFloatLayout.setScaleable(false);
```

#### 在xml中设置

```xml
<atech.com.heartfloat.HeartFloatLayout
       ...
       app:dWidth="20dp"
        app:dHeight="20dp"
        app:float_duration="5000"
        app:scaleable = "false"/>

```

