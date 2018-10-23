# YLBFOBTest 软件设计大赛
要求根据给定的FOB卡的图片来判断卡片的性质（阴、阳、弱阳、异常）
关于FOB卡的介绍 http://www.silangsci.com/liujianzhong-Products-20914269/
# 主要是软件部分
 软件使用Eclipse+Swing编写（界面风格略丑），使用IOS的扁平化风格的UI样式；这种风格详情https://blog.csdn.net/vison155142/article/details/52469116 自己写了类来实现背景图的切换。
 
 检测算法使用深度学习（我不负责算法）文件夹里面的.pb文件是深度学习模型。
 
 主要采用封装好的exe来调用检测算法文件夹里面的classify.exe封装调用模型的API，imagenet_slim_labels.txt是分类的详细情况。
 
 软件的说明，软件附带详细的使用说明。
 
 检测部分，拖入图片或者图片的文件夹可以实现单张或者批量检测。
 
 记录部分，检测完成后或生成检测记录，可以通过面板上的搜索查询。
 
 详细记录部分，点击每一条记录可以查看检测记录的详细情况，包括检测结果和原图。
 
 检测阶段，默认开启4个线程，可以通过面板上面选择2、4或者6个线程并发检测，可以根据CPU的负载情况自由调整线程数。
 
 云计算，可以通过面板开启云计算。后端使用阿里云最基础的服务器（1CPU/2GB），通过本地将服务器传到服务器，处理之后返回结果然后在本地解析。
 # 主界面UI
 ![Image text](https://raw.githubusercontent.com/MacRylynn/YLBFOBTest/master/image/UI.png)
 # 记录查询页
  ![Image text](https://raw.githubusercontent.com/MacRylynn/YLBFOBTest/master/image/LOG.png)
# 记录详情页
(https://raw.githubusercontent.com/MacRylynn/YLBFOBTest/master/image/LOGDETAIL.png)
