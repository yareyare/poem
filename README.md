直接启动 com.ivy.Launch.java
然后调用接口
http://127.0.0.1:8888/poem/crawler/poem?url=http://so.gushiwen.org/gushi/tangshi.aspx&urlPage=http://so.gushiwen.org/shiwen/tags.aspx&id=view_58914
id=view_58914该参数是制定上次爬取到的位置，这次从这个位置继续爬

佚名和没有作者图片的都爬取失败了，修改代码补爬。