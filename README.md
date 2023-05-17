# SupportCore

### 用法

        allprojects {
            repositories {
                ...
                maven { url 'https://jitpack.io' }
            }
        }

        dependencies {
            implementation 'com.github.buhuiming:SupportCore:1.0.0-beta01'
        }

#### 1、说明

        common-->
                BaseActivity：Activity基类
                BaseApplication：Application基类
                BaseFragment：Fragment基类
                BaseVBActivity：基于viewBinding的Activity基类
                BaseVBFragment：基于viewBinding的Fragment基类
                BaseViewModel：viewModel基类
                DefaultViewPagerAdapter：常用的ViewPagerAdapter

        constants-->
                AppConstants：基本常量类（持续增加）
                PermissionConstants：权限常量类（持续增加）

        core-->
                AppTheme：主题管理
                GlideCircleTransform：Glide圆形
                GlideRoundTransform：Glide圆角
                GridSpacingItemDecoration：分割线
                MyStaggeredGridLayoutManager：GridLayoutManager
                WeakHandler：Handler管理

        entity-->
                MessageEvent：EventBus发送的对象

        interfaces-->
                ARCallBack：打开activity的回调
                PermissionCallBack：权限申请回调

        interfaces-->
                ActivityUtil：Activity栈管理
                DateUtil：日期
                DisplayUtil：尺寸转换
                NotificationUtil：通知栏
                SPUtil：SharedPreferences存储
                ViewUtil：view操作类

        widget-->
                ChoseView：选择控件
                


### TO DO

#### 持续更新

## License

```
Copyright (c) 2023 Bekie

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
