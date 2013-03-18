<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="spot-filter-bar"
	class="filter-bar subnav board row-fluid content-wrapper"
	style="width: 1150px">
	<div class="span10 row-fluid">
		<div class="p-5 f-l fs-16">
			<span>新XX建设：</span>
		</div>
		<form id="spot-search" class="navbar-search mt-0" action="">
			<c:forEach var="filter" items="${filters}">
				<c:choose>
					<c:when test="${filter.type eq 'category'}">
						<div class="btn-group ${filter.type} bin fs-14 mr-10 f-l">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<span class="c-666">${filter.typeLabel}:</span> <strong>${filter.label}</strong>
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<c:forEach var="item" items="${filter.items}">
									<li><a href="#" data-value="${item.id}">${item.name}</a></li>
								</c:forEach>
							</ul>
						</div>
					</c:when>
				</c:choose>
			</c:forEach>
		</form>
	</div>
	<div class="span2 view-t">
		<div class="btn-group f-r" data-toggle="buttons-radio">
			<button
				class="btn wf <c:if test="${param.viewType eq 'wf'}">active</c:if>"
				data-href="<c:url value="/" />" data-original-title="瀑布布局"
				rel="tooltip">
				<i class="icon-th"></i>
			</button>
			<button
				class="btn mv <c:if test="${param.viewType eq 'mv'}">active</c:if>"
				data-href="<c:url value="/map" />" data-original-title="地图布局"
				rel="tooltip">
				<i class="icon-map-marker"></i>
			</button>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$('.btn-group.category li a').click(function() {
			var data_value = $(this).attr('data-value');
			var p_container = $(this).parents('.btn-group.category');
			var s_label = p_container.find('a strong');
			s_label.text($(this).text());
			
			$('#explore-map').html("<p>Loading...</p>");
			getMapAndHotSpot(data_value);
			
		});

		$('#spot-search').on('submit', function() {
			
			
		});
		$('.view-t button').click(function() {
			var href = $(this).attr('data-href');
			window.location.href = href;
		});
		$('[rel="tooltip"]').tooltip();
	});
</script>