# MVVM模式

* 特点：
model层不变，使用databinding把View和model进行绑定， ViewModel修改界面的时候只需要修改对应属性的值即可

* 使用步骤：
* * 1.在model的build.gradle中android节点下添加：
    
    ''' 
    dataBinding{
        enabled = true
    }
    '''
* * 2.xml中使用databinding标签，见activity_main.xml;
    注意：
    <br>1).databinding 需要将所有标签放在<layout>标签下,这样才会有提示;
    <br>2).一个xml标签只能有一个data标签

* * 3.在view中设置viewModel 见MainActivity，这里不用setContentView(),也没有findVeiwById了

关于databinding的详细介绍，参考 [DataBinding的简单使用和高级用法](https://www.jianshu.com/p/ccf406bafff2)