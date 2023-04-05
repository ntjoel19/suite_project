<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="charset=utf-8" />
	<link rel='icon' type='image/png'  href='/suite_project/ressources/iconeSP.png' />
	<link href="design/styles.css" rel="stylesheet" type="text/css" />
	<link href="design/fullcalendar.css" rel="stylesheet" type="text/css" />
	<link href="design/style_formulaire.css" rel="stylesheet" type="text/css" />
	<link href="design/juizDropDownMenu.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css"  href="design/css_programmes.css" />
	<title>Insert title here</title>
</head>
<body>
		<% 
			String top1=(String)request.getAttribute("top1");
			String top2=(String)request.getAttribute("top2");
			String top3=(String)request.getAttribute("top3");
			String top4=(String)request.getAttribute("top4");
			String top5=(String)request.getAttribute("top5");
			String top6=(String)request.getAttribute("top6");
		%>
		<div id="banniere">
    		<img width="100%" height="100%" src="ressources/suite_project_barn_adm.png"/>
    	</div>
    	<ul id="dropdown">
				<li><a id='site' href='/suite_project/admin'>Site</a>
					<ul id='dropdown_sub1'>
                        <!-- li id='dropdown_sub_sub11'><a href='#'>Administration</a></li>-->
                        <li id='dropdown_sub_sub12'><a href='/suite_project/utilisateur?btn=gereruser'>Utilisateurs</a></li>
                        <li id='dropdown_sub_sub13'><a href='#'>Medias</a></li>
                        <!-- li id='dropdown_sub_sub14'><a href='#'>Journal</a></li-->
                    </ul>
				</li>
				<li><% out.print(top1); %>
					<!--  ul id='dropdown_sub2'>
						<li id='dropdown_sub_sub21'><a href='/suite_project/programme?btn=creerprogr'>Nouveau</a></li>
                        <li id='dropdown_sub_sub22' ><a href='/suite_project/programme?btn=editerTop'>Editer</a></li>
					</ul>-->
				</li>
				<li><% out.print(top2); %>
					<!--  ul id='dropdown_sub3'>
						<li id='dropdown_sub_sub31'><a href='/suite_project/action?btn=creeraction'>Nouveau</a></li>
                        <li id='dropdown_sub_sub32'><a href='/suite_project/action?btn=editerTop'>Editer</a></li>
					</ul>-->
				</li>
				<li><% out.print(top3); %>
					<ul id='dropdown_sub4'>
	                    <li id='dropdown_sub_sub41'><% out.print(top4); %></li>
	                    <!-- li id='dropdown_sub_sub42'><a href='#'>Editer</a></li>-->
	                    <li id='dropdown_sub_sub45'><% out.print(top5); %></li>
                    </ul>
                </li>
				<li id='emptyLi'><% out.print(top6); %>
					<!-- ul id='dropdown_sub5'>
						<li id='dropdown_sub_sub51'><a href='#'>Article</a></li>
                        <li id='dropdown_sub_sub52'><a href='#'>Media</a></li>
					</ul> -->
				</li>
			</ul>
			
			<ul id="dropdown1">

					<li id="prevew" class="topLeftMainMenu"><a href="/suite_project/index"><img title="Previsualiser" class="deconex" src="././ressources/previsualiser.png" /></a></li>
					<li class="topLeftMainMenu" id="notification_li1"><span id="notification_count1">...</span><a href="#comp" id="notificationLink1"><img title="message" class="deconex" src="././ressources/messages.png" /></a>
						<div id="notificationContainer1">
							<div id='notificationTitle1'>Notifications messages</div>
							<div id="notificationsBody1" class="notifications"></div>
							<div id="notificationFooter1"><a href="#">See All</a></div>
						</div>
					</li>
					<li class="topLeftMainMenu" id="notification_li2"><span id="notification_count2"></span><a href="#comp" id="notificationLink2"><img title="notifications" class="deconex" src="././ressources/notificationSyst.png" /></a>
						<div id="notificationContainer2">
							<div id='notificationTitle2'>Notifications taches</div>
							<div id="notificationsBody2" class="notifications"></div>
							<div id="notificationFooter2"><a href="#">See All</a></div>
						</div>
					</li>
					<li class="topLeftMainMenu" id="notification_li3"><span id="notification_count3">...</span><a href="#comp" id="notificationLink3"><img title="Nombre d'utilisateurs" class="deconex" src="././ressources/users.png" /></a>
						<div id="notificationContainer3">
							<div id='notificationTitle3'>Notifications utilisateur</div>
							<div id="notificationsBody3" class="notifications"></div>
							<div id="notificationFooter3"><a href="#">See All</a></div>
						</div>
					</li>
					<li><a href="/suite_project/index?deconnexion=decnx"><img title="deconnexion" class="deconex" src="././ressources/connexion.png" /></a></li>

			</ul>
    		

		<!-- p>Try the <strong>Tabulation</strong> key on your keyboard</p -->
		<script src="jquery/jquery.js"></script>
		<script src="jquery/jquery-ui.js"></script>
		<script src="jquery/jquery-validate.js"></script>
		<script src="jscript/juizDropDownMenu-2.0.0.min.js"></script>
		<script src="jscript/design.js"></script>
		<script src="jscript/fullcalendar.js"></script>
		<script src="jscript/moment.min.js"></script>
		<script type="text/javascript">
		$(function(){
			$("#dropdown").juizDropDownMenu({
				'showEffect' : 'fade',
				'hideEffect' : 'slide'
			});
		});
		
		function positionNotif(btn,formulaire){
				var coord = $(btn).offset();
				$(formulaire).css("display","block");
				$(formulaire).css("position","absolute");
				$(formulaire).css("top",coord.top+$(btn).height()-10);
				$(formulaire).css("left",coord.left+($(formulaire).width()/3));
		}
		function notificationTaches(){
			//$(document).ready(function(){
					//var donnees=$("#formTab").serialize();
					var donnees="enregistrerProj=notifTache";
						$.ajax({
							url : '/suite_project/projet',
							type : 'POST',
							data : donnees,
							dataType : 'html',
							success : function(data, result, jqXHR){
								var datas=data.split("??");
								$("#notificationsBody2").html(datas[0]);	
								if(datas[1]!=0)
									$("#notification_count2").html(datas[1]);
								else
									$("#notification_count2").html("");
							},
							error: function(XMLHttpRequest, textStatus, errorThrown) { 
					            //alert("Status: " + textStatus); alert("Error: " + errorThrown); 
					        } 
						});
			//});
		}
		function notificationUser(){
			//$(document).ready(function(){
					//var donnees=$("#formTab").serialize();
					var donnees="enregistrerProj=notifUser";
						$.ajax({
							url : '/suite_project/projet',
							type : 'POST',
							data : donnees,
							dataType : 'html',
							success : function(data, result, jqXHR){
								var datas=data.split("??");
								$("#notificationsBody3").html(datas[0]);
								if(datas[1]!=0)
									$("#notification_count3").html(datas[1]);
								else
									$("#notification_count3").replaceWith("<span></span>");
							},
							error: function(XMLHttpRequest, textStatus, errorThrown) { 
					            //alert("Status: " + textStatus); alert("Error: " + errorThrown); 
					        } 
						});
			//});
		}
		$(window).load(function(){
			notificationUser();
			notificationTaches();
		});
		setInterval(notificationTaches, 86400000);// 86400000 toutes les 24h, charger les notifications projets
		setInterval(notificationUser, 600000);//toutes les 10 minutes, consulter les comptes en attente d'activation
		
		function aficherNotif(notificationLink2,notificationContainer2,notification_count2){
				$(notificationLink2).click(function()
				{
					$(notificationContainer2).fadeToggle(300);
					$(notification_count2).fadeOut("slow");
					return false;
				});

				//Document Click hiding the popup
				$(document).click(function()
				{
					$(notificationContainer2).hide();
				});

				//Popup on click
				$(notification_count2).click(function()
				{
					return false;
				});
				

			}
		aficherNotif("#notificationLink1","#notificationContainer1","#notification_count1");
		aficherNotif("#notificationLink2","#notificationContainer2","#notification_count2");
		aficherNotif("#notificationLink3","#notificationContainer3","#notification_count3");
		</script>
     <!--Fin du menu superieure horizontal debut du corps de la page-->
</body>
</html>