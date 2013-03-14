<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="google-site-verification" content="H2zVxvFz-RVHvy7W7wnYiOEjbhep1ngnBK-05GDcJNs" />
	<title>Demo of XXX</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/myTest-base.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/myTest-theme.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery.pnotify.default.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui-1.8.18.custom.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery.pnotify.myTest.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/validationEngine.bootstrap.css" />" />
	<script type="text/javascript" src="<c:url value="/js/jquery.1.7.1.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/js/myTest.init.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/js/myTest.op.js" />" ></script>
</head>
<body class="front">
	<jsp:include page="/WEB-INF/jsp/comp/header.jsp">
		<jsp:param name="tab" value="home"/>
	</jsp:include>
	<%-- <jsp:include page="/WEB-INF/jsp/comp/side.nav.jsp" /> --%>
	<jsp:include page="/WEB-INF/jsp/comp/back.top.jsp" />
	<div id="water-fall-wrapper" class="main-wrapper">
		<jsp:include page="/WEB-INF/jsp/comp/spot.filter.bar.jsp" >
			<jsp:param name="filters" value="${filters}"/>
			<jsp:param name="viewType" value="wf"/>
		</jsp:include>
		<div id="water-fall" class="content-wrapper">
			<c:import url="/list"></c:import>
		</div>
		<script type="text/javascript">
			adjustWebWidth();
		</script>
	</div>
	<div id="page-nav">
		<a href="<c:url value="/spots/search/list" />"></a>
	</div>
<script type="text/javascript" src="<c:url value="/js/bootstrap.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/bootstrapx-popoverx.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.8.18.custom.min.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/jquery.timeago.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/jquery.pnotify.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/jquery.scrollTo.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/jquery.imagesloaded.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/jquery.masonry.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/jquery.infinitescroll.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/jquery.form.js" />"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=zh_cn"></script>
<script type="text/javascript" src="<c:url value="/js/gmap3.js" />"></script>
<script type="text/javascript">
	$(function(){
		
		$(".timeago").timeago();
		$('.pin').each(function(){
			op.pin_bind_event($(this));
		});
		
		$('#signInForm').bind('sign-in-success', function(e, pinyin, label){
			$.get('<c:url value="/signin/writeconvo" />', function(data){
				$('.pin').each(function(){
					if($(this).has('.write.convo').length==0){
						var wc = $(data).clone();
						wc.find('input[name="actId"]').val($(this).attr('data-act-id'));
						$(this).find('.caption + .convo').after(wc);
					}
				})
			})
		});
		
		var $wf = $('#water-fall');
		
		$wf.masonry({
			itemSelector : '.pin',
		    columnWidth : 222,
		    gutterWidth: 15,
		    isAnimated: false,
		    animationOptions: {
		    	queue: false
		    },
		    isFitWidth: false
		});
		
		$wf.infinitescroll(
			{
				navSelector  : '#page-nav', // selector for the paged navigation
				nextSelector : '#page-nav a', // selector for the NEXT link (to page 2)
				itemSelector : '.pin', // selector for all items you'll retrieve
				debug        : false,
				animate	 	 : false,
				animationOptions: {
				    duration: 750,
				    easing: 'linear',
				    queue: false
				},
				loading: {
					selector: '#water-fall-wrapper',
					finishedMsg: '没有更多了',
					msgText: '八卦加载中...',
					img: '<c:url value="/img/big-loading.gif" />',
					speed: 0
				},
				state : {
					currPage: 0
				},
				pathParse: function() {
			        return ['<c:url value="/spots/search/list?${qStr}no=" />',''];
			    }
			},
			// trigger Masonry as a callback
			function( newElements ) {
				// hide new items while they are loading
				var $newElems = $( newElements ).hide();
				$newElems.find(".timeago").timeago();
				$newElems.each(function(){
					op.pin_bind_event($(this));
				});
				// ensure that images load before adding to masonry layout
				//$newElems.imagesLoaded(function(){
					// show elems now they're ready
					$wf.append( $newElems ).masonry( 'appended', 
							$newElems, false, function(){
						$newElems.fadeIn('slow');
					});
				//}); 
			}
		);
	});
</script>
<script type="text/javascript" src="<c:url value="/js/ga.js" />" ></script>
</body>
</html>