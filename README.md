                                                    MagicBook 项目介绍

     在项目中集成 Jsoup 解析小说网站获取数据,并将数据封装用于建立用户行为.
     
     在本项目中实现的功能有：
        1.收藏书籍
        2.书架功能
        3.阅读书本详情,并记录阅读历史
        4.实现记录书本的阅读进度
        5.登录,注册
        6.排行榜
      
     项目中相关技术的使用：
        1.Mvp设计模式实现数据与视图分离
        2.网络框架使用 Rxjava+retrofit+okhttp 获取网络中的网页数据并使用 Jsoup 进行解析.
        3.使用LitePal 将数据存入数据库
        4.图片加载使用了Glide实现图片圆角化,高斯模糊,gif动图加载
        5.Activity,Fragment之间的通信使用了EventBus进行数据交换
        6.使用ImmersionBar实现沉浸式状态栏
        7.屏幕适配使用了今日头条适配方案
        8.RecyclerView的适配器使用BasequickAdapter
        
     
     项目实现图片
        1.书架
         ![书架](https://github.com/pressureKai/MagicBook/blob/master/pic/bookCase.jpg)      
   
   
