<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%   
String path = request.getContextPath();   
String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;   
%>   

<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>" > 
    <meta charset="utf-8">
    <title>Home</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">

    <!-- Bootstrap styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!-- Font-Awesome -->
    <link rel="stylesheet" href="css/font-awesome/css/font-awesome.min.css">

    <!-- Google Webfonts -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600|PT+Serif:400,400italic' rel='stylesheet'
          type='text/css'>

    <!-- Styles -->
    <link rel="stylesheet" href="css/style.css" id="theme-styles">

    <!--[if lt IE 9]>
    <script src="js/vendor/google/html5-3.6-respond-1.1.0.min.js"></script>
    <![endif]-->

</head>
<body>

<%@ include file="page/header.jsp" %>

<div class="widewrapper main">

<!-- 填充 -->
	<!-- <div class="container" >
	    <div class="row">
	      <div class="col-md-8 blog-main">
	        <div class="row">
	        	<div class="col-md-6 col-sm-6" >
		            <article class=" blog-teaser">
		              <header>
		                <img src="img/5.jpg" alt="">
		                <h3><a href="single.jsp">诗歌</a></h3>
		                <span class="meta">《鹅》 骆宾王</span>
		                <hr>
		              </header>
		              <div class="body">
		                鹅鹅鹅，</br>
		                曲项向天歌。</br>
		                白毛浮绿水，</br>
		                红掌拨清波。</br>
		              </div>
		              <div class="clearfix">
		                <a href="single.jsp" class="btn btn-clean-one">Read more</a>
		              </div>
		            </article>
		        </div>
		        
		        <div class="col-md-6 col-sm-6">
		            <article class=" blog-teaser">
		              <header>
		                <img src="img/5.jpg" alt="">
		                <h3><a href="single.jsp">诗歌</a></h3>
		                <span class="meta">《鹅》 骆宾王</span>
		                <hr>
		              </header>
		              <div class="body">
		                鹅鹅鹅，</br>
		                曲项向天歌。</br>
		                白毛浮绿水，</br>
		                红掌拨清波。</br>
		              </div>
		              <div class="clearfix">
		                <a href="single.jsp" class="btn btn-clean-one">Read more</a>
		              </div>
		            </article>
		        </div>
		    </div>
		    
		  </div>
	    </div>
	</div> -->
<!-- 填充 end -->

  <div class="container">
    <div class="row">
      
      <div class="col-md-8 blog-main" id="fill">
		<!-- 诗歌--动态填充到这里 -->
      </div>
      
      <aside class="col-md-4 blog-aside">
        <div class="aside-widget">
          <header>
            <h3>Featured Post</h3>
          </header>
          <div class="body">
            <ul class="clean-list">
              <li><a href="">Clean - Responsive HTML5 Template</a></li>
              <li><a href="">Responsive Pricing Table</a></li>
              <li><a href="">Yellow HTML5 Template</a></li>
              <li><a href="">Blackor Responsive Theme</a></li>
              <li><a href="">Portfolio Bootstrap Template</a></li>
              <li><a href="">Clean Slider Template</a></li>
            </ul>
          </div>
        </div>

        <div class="aside-widget">
          <header>
            <h3>Related Post</h3>
          </header>
          <div class="body">
            <ul class="clean-list">
              <li><a href="">Blackor Responsive Theme</a></li>
              <li><a href="">Portfolio Bootstrap Template</a></li>
              <li><a href="">Clean Slider Template</a></li>
              <li><a href="">Clean - Responsive HTML5 Template</a></li>
              <li><a href="">Responsive Pricing Table</a></li>
              <li><a href="">Yellow HTML5 Template</a></li>
            </ul>
          </div>
        </div>


        <div class="aside-widget">
          <header><!-- 朝代--动态填充到这里 -->
            <h3>朝代</h3>
          </header>
          <div class="body clearfix" id="dynasty">
            <ul class="tags">
              <li><a href="">HTML5</a></li>
              <li><a href="#">CSS3</a></li>
              <li><a href="#">COMPONENTS</a></li>
              <li><a href="#">TEMPLATE</a></li>
              <li><a href="#">PLUGIN</a></li>
              <li><a href="#">BOOTSTRAP</a></li>
              <li><a href="#">TUTORIAL</a></li>
              <li><a href="#">UI/UX</a></li>
            </ul>
          </div>
        </div>

      </aside>
    </div>
  </div>
</div>
<%@ include file="page/footer.jsp" %>

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/modernizr.js"></script>
<script src="js/poem.js"></script>
<script src="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.js" type="text/javascript"></script>
<script language="javascript" for="window" event="onload">

// 诗歌查询
$.ajax({
    url:"http://127.0.0.1:8080/poem/index.do",
    type:'GET', //POST
    async:false,    //或false,是否异步
    data:{
       // name:'yang',age:25
    },
    timeout:0,    //超时时间
    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
    beforeSend:function(xhr){
        console.log(xhr)
        console.log('发送前')
    },
    success:function(data,textStatus,jqXHR){
        console.log(data)
        console.log(data.note)
        console.log(textStatus)
        console.log(jqXHR)
        if(data.code!=null && data.code==10200){
        	var list = data.list;

        	var divRow= $("<div class=\"row\"></div>");
        	$(list).each(function (index) {
                var val = list[index];
                //console.log(list[index]);

                var contentdiv=$("<div class=\"body\"> <h3><a href=\"single.jsp\">"+val.title+"</a> </br></br> "+ replaceBr(val.content)+"</div>");
                var single=$('<div class="clearfix"><a href="single.jsp" class="btn btn-clean-one">详情</a></div>');
                var header = ("<div class=\"col-md-3 col-sm-3\" ><header><img src=\"poetPic/"+val.poemDynastyName+"_"+val.poetName+".jpg\" alt=\"\"></h3><span class=\"meta\">"+val.poetName+"</span><hr></header></div>");
                var div = $("<div class=\"col-md-12 col-sm-12\"></div>");

                var parent = $("<article class=\"blog-teaser\"></article>")

                parent.append(header);
                parent.append(contentdiv);
                parent.append(single);

                div.append(parent);
                divRow.append(div)

				if(index%2 == 1){
					$('#fill').append(divRow);
					divRow= $("<div class=\"row\"></div>");
                }
            });
        }
    },
    error:function(xhr,textStatus){
        console.log('错误')
        console.log(xhr)
        console.log(textStatus)
    },
    complete:function(){
        console.log('结束')
    }

})

// 朝代查询
$.ajax({
    url:"http://127.0.0.1:8080/poem/index.do",
    type:'GET', //POST
    async:false,    //或false,是否异步
    data:{
        // name:'yang',age:25
    },
    timeout:0,    //超时时间
    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
    beforeSend:function(xhr){
        console.log(xhr)
        console.log('发送前')
    },
    success:function(data,textStatus,jqXHR){
        console.log(data)
        console.log(data.note)
        console.log(textStatus)
        console.log(jqXHR)
        if(data.code!=null && data.code==10200){
            var list = data.list;

            var divRow= $("<div class=\"row\"></div>");
            $(list).each(function (index) {
                var val = list[index];
                //console.log(list[index]);

                var contentdiv=$("<div class=\"body\"> <h3><a href=\"single.jsp\">"+val.title+"</a> </br></br> "+ replaceBr(val.content)+"</div>");
                var single=$('<div class="clearfix"><a href="single.jsp" class="btn btn-clean-one">详情</a></div>');
                var header = ("<div class=\"col-md-3 col-sm-3\" ><header><img src=\"poetPic/"+val.poemDynastyName+"_"+val.poetName+".jpg\" alt=\"\"></h3><span class=\"meta\">"+val.poetName+"</span><hr></header></div>");
                var div = $("<div class=\"col-md-12 col-sm-12\"></div>");

                var parent = $("<article class=\"blog-teaser\"></article>")

                parent.append(header);
                parent.append(contentdiv);
                parent.append(single);

                div.append(parent);
                divRow.append(div)

                if(index%2 == 1){
                    $('#fill').append(divRow);
                    divRow= $("<div class=\"row\"></div>");
                }
            });
        }
    },
    error:function(xhr,textStatus){
        console.log('错误')
        console.log(xhr)
        console.log(textStatus)
    },
    complete:function(){
        console.log('结束')
    }

})
</script>
</body>
</html>
