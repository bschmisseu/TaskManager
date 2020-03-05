<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix= "form"%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"></link>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap" rel="stylesheet"></link>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<spring:url value="/resources/pictures/logo.png" var="logo" />
<div style="font-family: 'Open Sans', sans-serif;">
<table class="fullTable">
	<tr style="vertical-align: top;">
		<td class="sideBar fullPageTD">
			<table style="width: 100%; color: white;">
				<tr>
					<td align="left"><a style="padding-bottom:0px;" href="HomePage.xhtml"><img src="${logo}" style="width:310px;height:34px;padding-left:10px;padding-right:10px;padding-bottom:0px"></img></a></td>
					<td style="padding-right:10px;" align="right">
						<div id="addList"><i style="font-size: 34px; color: #f3aa3c; cursor: pointer" class="material-icons">add</i></div>
					</td>
				</tr>
				<tr>
					<!-- USERS LIST -->
					<td style="padding-top: 15; width: 100%" colspan="2">
						<c:forEach var="list" items="${lists}" varStatus="loop">
							<table class="userListTable" style="color: white; font-size: 26px; width: 100%">
								<tr style="vertical-align: middle; width: 100%">
									<td style="color: ${list.color}; padding: 15px; width: 25px;"><i class="large material-icons">adjust</i></td>
									<td><a style="color: white; text-decoration: none;" href="/home/changeList?listId=${loop.index}">${list.name}</a></td>
									<td style="text-align: right; padding-right: 20px; color: #333">
										<a style="color: inherit; text-decoration: none;" href="javascript:deleteListFunction(${loop.index})">
											<i class="large material-icons">clear</i>
										</a>
									</td>
								</tr>		
							</table>
						</c:forEach>
					</td>
				</tr>
			</table>
		</td>
		<td class="rightPageTD">
			<table style="width: 100%">
				<tr>
					<td style="padding-right: 15px;">
						<table style="width: 100%;">
							<tr style="text-align: right;">
								<td valign="middle">
									<div class="dropdown">
						            <span style="cursor: pointer;">
						                <h4 style="display: inline">Hi, ${currentUser.firstName}</h4>
						                <div style="width:26px; display: inline; vertical-align: middle">
						                    <i class="large material-icons">arrow_drop_down</i>
						                </div>
						            </span>
						            <div class="dropdown-content" style="text-align: left">
						                    <a href="/" style="color: black">Logout</a>
						            </div>
						        </div>
								</td>
							</tr>
							<tr>
								<td colspan="2" style="padding-top: 10px; padding-left: 15px">
									<table style="width: 100%">
										<tr style="color: ${currentList.color}">
											<td style="text-align: left">
												<h2><b>${currentList.name}</b></h2>
											</td>
											<td style="text-align: right; padding-right: 15px">
												<div id="addTask" style="cursor:pointer"><i style="font-size: 30px;" class="large material-icons">add</i></div>
											</td>
										</tr>
									</table>
									<hr>
									<div style="padding-left: 20px; padding-top: 5px;">
									<table style="width: 100%">
									<c:forEach var="task" items="${currentList.taskList}" varStatus="loop">
										<tr style="height: 35px; font-size: 20px;">
											<td>
												<table style="width: 100%" class="userTaskTable">
													<tr>
														<td>
															<label class="pure-material-checkbox">
																<input type="checkbox" ${task.completedString} onclick="toggleCompleted(${loop.index})">
																<span>
																	${task.description}
															  	</span>
															</label>
															<br>
															<p style="font-size: 9px; padding-left: 33px; margin: 0px;">${task.completedBy}</p>
														</td>
														<td style="width: 31px">
															<a style="color: inherit; text-decoration: none;" href="javascript:deleteTaskFunction(${loop.index})">
																<i style="color: black; font-size: 30px; padding: 0px; cursor: pointer;" class="large material-icons">clear</i>
															</a>
														</td>
													</tr>
												</table>
												<hr>
											</td>
										</tr>
									</c:forEach>
									</table>
									</div>
								</td>	
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>

<div id="addTaskForm">

  <h1>Add Reminder</h1>
  
  <form:form method="POST" modelAttribute="taskModel" action="/home/addTask">
    <form:input class="inputClass" path="description" placeholder="Description" type="text" required="required" />
    <form:input class="inputClass" path="completedBy" placeholder="Date (YYYY-MM-DD HH:MM:SS)" type="datetime-local" required="required" />
    <input class="formBtn" type="submit" />
  </form:form>
</div>

<div id="addListForm">
	<h1>Add List</h1>
	<form:form method="POST" action="/home/addList">
		<input class="inputClass" name="listName" placeholder="Name of List" type="text" required="required" />
		<input class="formBtn" type="submit" />
	</form:form>
</div>

<script>
function deleteListFunction(listId)
{
	if (confirm("Are your sure you want to delete this list!")) {
		var url = "/home/deleteList?listId=" + listId;
		document.location.href=url;
	} else {
	  
	}
}

function deleteTaskFunction(taskId){
	if (confirm("Are your sure you want to delete this task!")) {
		var url = "/home/deleteTask?taskId=" + taskId;
		document.location.href=url;
	} else {
	  
	}
}

function toggleCompleted(taskNumber){
	var url = "/home/toggleCompleted?taskNumber=" + taskNumber;
	document.location.href=url;
}

$(function() {
	  // contact form animations
	  $('#addTask').click(function() {
	    $('#addTaskForm').fadeToggle();
	  })
	  $(document).mouseup(function (e) {
	    var container = $("#addTaskForm");

	    if (!container.is(e.target) // if the target of the click isn't the container...
	        && container.has(e.target).length === 0) // ... nor a descendant of the container
	    {
	        container.fadeOut();
	    }
	  });
	  
	});
	
$(function() {
	  // contact form animations
	  $('#addList').click(function() {
	    $('#addListForm').fadeToggle();
	  })
	  $(document).mouseup(function (e) {
	    var container = $("#addListForm");

	    if (!container.is(e.target) // if the target of the click isn't the container...
	        && container.has(e.target).length === 0) // ... nor a descendant of the container
	    {
	        container.fadeOut();
	    }
	  });
	  
	});
	
</script>