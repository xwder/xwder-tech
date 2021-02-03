
1.JRebel简介
----------

JRebel是一款JAVA虚拟机插件，它使得JAVA程序员能在不进行重部署的情况下，即时看到代码的改变对一个应用程序带来的影响。JRebel使你能即时分别看到代码、类和资源的变化，你可以一个个地上传而不是一次性全部部署。

当程序员在开发环境中对任何一个类或者资源作出修改的时候，这个变化会直接反应在部署好的应用程序上，从而跳过了构建和部署的过程，可以省去大量的部署用的时间。

JRebel实现SpringBoot热部署的方式：

其实SpringBoot 项目引入依赖  **【springboot-devtools】**也可以实现热部署，但是springboot-devtools方式的热部署在功能上有限制。如在方法内的修改可以实现热部署，但是新增的方法或者修改方法参数之后热部署是不生效的。

JRebel更加强大，不需要引入**【springboot-devtools】**也可以实现热部署，JReble支持 Idea 和 Eclipse。

2.JRebel安装
----------

### 2.1 Idea安装JRebel

Idea安装JRebel方式很简单，直接在插件市场搜索 jrebel 即可安装。

![https://cdn.xwder.com/image/blog/xwder/1-20201230162404316.png](https://cdn.xwder.com/image/blog/xwder/1-20201230162404316.png "1-20201230162404316.png")

### 2.2 Eclipse安装JRebel

Eclipse安装JRebel可以通过 Eclipse Marketplaces 搜索 jreble安装，此安装方式可能比较慢。

![https://cdn.xwder.com/image/blog/xwder/1-20201230163450563.png](https://cdn.xwder.com/image/blog/xwder/1-20201230163450563.png "1-20201230163450563.png")    Eclipse可以直接本地安装，首页打开JRbel官网 [https://www.jrebel.com/products/jrebel/download](https://www.jrebel.com/products/jrebel/download "JReble下载地址") ，然后点击 页面的 I have a 冷license，然后点击** [What are my alternatives?](https://www.jrebel.com/software/jrebel/quickstart/eclipse/#!/installation) 。**

下载到本地后 具体安装方式可以参考 [https://blog.csdn.net/kmswilliam/article/details/109395470](https://blog.csdn.net/kmswilliam/article/details/109395470 "jrebel Eclipse本地安装")

**![https://cdn.xwder.com/image/blog/xwder/1-20201230163419042.png](https://cdn.xwder.com/image/blog/xwder/1-20201230163419042.png "1-20201230163419042.png")**



3.JRebel激活
----------

JRebel激活地址 [https://xwder..com](https://xwder..com "https://xwder..com")/{GUID}

首先你需要获取一个GUID，获取方式在线生成。  
GUID获取地址：  
[    https://www.guidgen.com](https://www.guidgen.com/)  
[    http://www.ofmonkey.com/transfer/guid](http://www.ofmonkey.com/transfer/guid)  
[    https://www.guidgenerator.com/online-guid-generator.aspx](https://www.guidgenerator.com/online-guid-generator.aspx)

Idea和Eclipse激活方式一致： -> help -> JRebel -> activetion   
![https://cdn.xwder.com/image/blog/xwder/1-20201230164558279.png](https://cdn.xwder.com/image/blog/xwder/1-20201230164558279.png "1-20201230164558279.png")

注意:  JRebel使用过程中可能会自动联网检测激活情况，如果激活服务挂了，联系管理员处理[ ](http://mailto:admin@xwder.com) [admin@xwder.com](mailto:admin@xwder.com "admin@xwder.com")。

激活后：

![https://cdn.xwder.com/image/blog/xwder/1-20201230165830649.png](https://cdn.xwder.com/image/blog/xwder/1-20201230165830649.png "1-20201230165830649.png")

4.JRebel配置和使用
-------------

勾选上 Build project automatically

![https://cdn.xwder.com/image/blog/xwder/1-20201230170042142.png](https://cdn.xwder.com/image/blog/xwder/1-20201230170042142.png "1-20201230170042142.png")

在你项目名称上右键 ->JReble -> enable Jrebel。 然后启动项目的时候

![https://cdn.xwder.com/image/blog/xwder/1-20201230171139864.png](https://cdn.xwder.com/image/blog/xwder/1-20201230171139864.png "1-20201230171139864.png")

springboot项目使用JReble启动效果：

![https://cdn.xwder.com/image/blog/xwder/1-20201230171331911.png](https://cdn.xwder.com/image/blog/xwder/1-20201230171331911.png "1-20201230171331911.png")



4.JRebel热部署快捷键
--------------

修改完代码 后使用  **ctrl+F9 或者 ** **Ctrl + Shift + F9** 实现Jrebel热部署。新修改后代码生效，打的断点也会生效，快速调试。

![https://cdn.xwder.com/image/blog/xwder/1-20201230171758933.png](https://cdn.xwder.com/image/blog/xwder/1-20201230171758933.png "1-20201230171758933.png")

写了半天，这篇文章终于写完了，有疑问可以发表评论，看到后会及时回复处理的哟。

![](https://cdn.xwder.com/image/blog/xwder/1-20201012231827910.jpg)
