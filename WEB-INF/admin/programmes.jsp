<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css"  href="design/Design_Global.css" />
	<link rel="stylesheet" type="text/css"  href="design/css_programmes.css" />
	<link rel="stylesheet" type="text/css"  href="design/jquery-ui.css" />
	<script src="jquery/jquery.js"></script>
	<script src="jquery/jquery-ui.js"></script>
	<script src="jquery/jquery-validate.js"></script>
	<script src="jscript/juizDropDownMenu-2.0.0.min.js"></script>
	<script src="jscript/design.js"></script>
	<title>Programmes Space</title>
	<style>
		
		#progr{
			border: 1px solid #f6f5f5;
			background: #ffff;
		}
		#dialog table{
			align:center;
		}
	</style>
</head>
<body>
		<% String designation=(String)request.getAttribute("designation");%>
		<% String intitule=(String)request.getAttribute("intitule");%>
		<% String indicateur=(String)request.getAttribute("indicateur");%>
		<% String objectifs=(String)request.getAttribute("objectifs");%>
		<% String valRef=(String)request.getAttribute("valRef");%>
		<% String valCible=(String)request.getAttribute("valCible");%>
		<% String sousSecteur=(String)request.getAttribute("sousSecteur");%>
		<% String respoProgramme=(String)request.getAttribute("respoProgramme");%>
		<% String cadre=(String)request.getAttribute("cadre");%>
		<% String strategie=(String)request.getAttribute("strategie");%>
		<% String axe_strategique=(String)request.getAttribute("axe_strategique");%>
		<% String valeur=(String)request.getAttribute("value");%>
		<% String dataList=(String)request.getAttribute("data");%>
		<% String display=(String)request.getAttribute("display");%>
		<% String option1=(String)request.getAttribute("option1");%>
		<% String option2=(String)request.getAttribute("option2");%>
		<% 
			String edit=(String)request.getAttribute("edit");
			String add=(String)request.getAttribute("add");
			String del=(String)request.getAttribute("del");
			String impossibleDediter=(String)request.getAttribute("impossibleDetiter");
			if( impossibleDediter == null) impossibleDediter="";
		%>
		
		<% String disp1=(String)request.getAttribute("display1");%>
		<% String disp2=(String)request.getAttribute("display2");%>
		<% String disp3=(String)request.getAttribute("display3");%>
		<% String disp4=(String)request.getAttribute("display4");%>
		<% String design="<input class='in' type='text' name='design' value='"+designation+"' id='designation' disabled/><input type='hidden' name='designation' value='"+designation+"'/>";
		
			String edition=(String)request.getAttribute("edition");
			if(edition != null){
				design="<input class='in' type='text' name='design' value='"+designation+"' id='designation' disabled/><input type='hidden' name='designation' value='"+designation+"'/>";
			}else{
				design="<input class='in' type='text' name='designation' value='' id='designation'/>";
			}
			String ajouter=(String)request.getAttribute("ajout");
			System.out.println("edition="+edition);
		%>
		
		<!-- On inclut l'entete (baniere + menu) -->
		<section id="entete">
			<jsp:include page="entete_des_pages.jsp"/>  
			<table id="menu_gestion">
				<tr><% out.print(disp3); %><form id="formTab"><% out.print(disp4); %>
					<td id="menu_gestiontitre"><h2>Gestion des programmes</h2></td>
					<td class="menu_gestiontitrebtn"><a href="/suite_project/admin"><img class="img" src="ressources/home.png"/></a></td>
					<td class="menu_gestiontitrebtn"><input id="btnReload" type="image" src="././ressources/actualiser.png" name="enregistrerProgr" value="reload"/></td>
					<% if(edition == null && ajouter == null){
							out.print(add);
							out.print(edit);
						}
					%>
					<% if(ajouter==null)out.print(del); %>
				</tr>
			</table> 
		</section>	
		<div align='center' style='color:red;' color='color'><span ><% out.print(impossibleDediter); %></span></div>
		<section id="contenu">
		
		
		<% out.print(disp3); %>
			<table class="filtreprogr" align="center">
				<tr>
					<td>
						<input list="designation" type="text" id="choix_designation" placeholder="designation">
						<datalist id="designation">
							  <% out.print(option1); %>
						</datalist>
					</td>
					<td>
						<input list="intitule" type="text" id="choix_intitule" placeholder="Intitulé">
						<datalist id="intitule">
							  <% out.print(option2); %>
						</datalist>
					</td>
					<td>
						<input type="number" id="choix_annee" placeholder="annee">
					</td>
				</tr>
			</table>
			<%
				//String bd=(String)request.getAttribute("bd");
				//out.print(bd);
			%>
			<table id="corpsprogr" class="display"  cellspacing="1">
				<thead>
					<% String nb=(String)request.getAttribute("nb");%>
					<tr><th colspan="7" id="titreTab"><h2>Liste des programmes (<% out.print(nb);%>)</h2></th></tr>
					<tr>
						 <th class="corpsprogrcheckBox">
						 	<input type="checkbox" id='all'/><label for='all'></label>
						 </th>
						 <th>
						 	Désignation
						 </th>
						 <th>
						 	Intitulé
						 </th>
						 <th>
						 	Objectifs
						 </th>
						 <th>
						 	Indicateurs
						 </th>
						 <th  id="lastShild">
						 	Valeur cible
						 </th>
						 <th>
						 	valeur ref
						 </th>
					</tr>
				</thead>
				<tbody id='tbody'>
					<%
			            String tabrow = (String)request.getAttribute("tr");
			            out.println( tabrow );
		            %>
				</tbody>
				</form>
				
			</table>
			<% out.print(disp4); %>
			<!-- Sections temporelles -->
	
	
	<% out.print(disp1);%><div title="Ajouter un nouveau programme">
				<form id="dialog" method="post" action="/suite_project/programme" name="" >
					<table border="0">
						<tr> <th colspan="5" style="color:red;">* : Champs obligatoires </th> </tr>
						<tr> 
							<td width="12%"><label>Désignation </label><span class ="oblige"> * </span> </td> <td> <% out.print(design); System.out.println(design); %> </td> 
							<td>&nbsp;&nbsp;</td>
							<td width="12%"><label>Sous-secteur</label> <span class ="oblige"> * </span></td> <td><input type="text" name="sousSecteur" class='in' value="<% out.print(sousSecteur);%>" id="sousSecteur"></td>							
						</tr>
						<tr> 
							<td><label>Intitulé </label><span class ="oblige">* </span></td> <td><textarea name="intitule" id="intitule" class='in'><% out.print(intitule);%></textarea></td> 
							<td>&nbsp;&nbsp;</td>
							<td><label>Axe stratégique</label> </td> <td><textarea name="axeStrategiq" id="axeStrategiq" class='in'><% out.print(axe_strategique);%></textarea></td>	
						</tr>
						<tr> 
							<td><label>Objectifs</label> <span class ="oblige">* </span></td> <td><textarea name="objectifs" id="objectifs" class='in'><% out.print(objectifs);%></textarea></td> 
							<td>&nbsp;&nbsp;</td>
							<td><label>Valeur Cible</label> <span class ="oblige">* </span></td> <td><input id="valCible" class='in' value="<% out.print(valCible);%>" type="text" name="valCible"/></td> 							
						</tr>
						<tr> 
							<td><label>Indicateurs</label> <span class ="oblige">* </span></td> <td><textarea id="indicateurs" class='in' type="text" name="indicateurs"><% out.print(indicateur);%></textarea></td> 
							<td>&nbsp;&nbsp;</td>
							<td><label>Valeur de référence</label> <span class ="oblige">* </span></td> <td><input id="valRef" class='in' value="<% out.print(valRef);%>" type="text" name="valRef"/></td> 	
						</tr>
						<tr> 
							<td><label>Stratégie programme</label> </td> <td><textarea name="strategiProgr" id="strategiProgr" class='in'><% out.print(strategie);%></textarea></td>
								<td>&nbsp;&nbsp;</td>
							<td><label>Responsable programme</label> <span class ="oblige"> * </span></td> <td><textarea class='in' id="respoProgr" name="respoProgr"><% out.print(respoProgramme);%></textarea></td>							
						</tr>
						
						<tr> 
							<td><label>Fonction</label> </td> <td><textarea name="fonction" class='in'></textarea></td> 
							<td>&nbsp;&nbsp;</td>
							<td><label>Cadre institutionnelle de mise en oeuvre</label> </td> <td><textarea class='in' id="cadreInstitutionel" name="cadreInstitutionel"><% out.print(cadre);%></textarea></td>
							
						</tr>
						<tr> <td colspan="5" style="text-align:center; "> <br/><input type="reset" value="cancel" name="effacer"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="envoie" type="submit" value="<% out.print(valeur);%>" name="enregistrerProgr"/></td></tr>
					</table>
				</form>
			</div><% out.print(disp2);%>
	</section>	
	<div id="confirmerSupr" style="display:none;" title="Confirmer la suppresion">
		Voulez-vous supprimer?
	</div>
			
			<script>
				var listProgramme="";
			    $(window).on('load',function(){
			    	var traitement1 = $("#bd").text().split("-endLn-");
			    	//alert(traitement1[1]);
			    });
				
				$(document).ready(function(){
						
						$("#designationSelect").blur(function(e){
							e.preventDefault();
							//alert($("#bd").text());
							var donnees='designation='+$('#designationSelect').val()+'&enregistrerProgr=editerTop1';
							$.ajax({
								url : '/suite_project/programme',
								type : 'POST',
								data : donnees,
								dataType : 'html',
								success : function(data, result, jqXHR){
									//$(codeHtml).appendTo('#tbody');
									
									var liste = new String(data);
									listeCoupe=liste.split(';');
									$("#objectifs").val(listeCoupe[1]);
									$("#indicateurs").val(listeCoupe[2]);
									$("#valRef").val(listeCoupe[3]);
									$("#valCible").val(listeCoupe[4]);
									$("#sousSecteur").val(listeCoupe[5]);
									$("#respoProgr").val(listeCoupe[6]);
									$("#cadreInstitutionel").val(listeCoupe[7]);
									$("#strategiProgr").val(listeCoupe[8]);
									$("#axeStrategiq").val(listeCoupe[9]);
									$("#intitule").val(listeCoupe[0]);
								},
								error: function(XMLHttpRequest, textStatus, errorThrown) { 
						            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
						        } 
							});
						});
				});
				$(document).ready(function(){
					$("#btnDelete").click(function(e){
						e.preventDefault();
						var donnees=$("#formTab").serialize();
						
						if(donnees === ""){
							alert("vous devez selectionner au moins un programme");
						}else{
							donnees+="&enregistrerProgr=delete";
							if(confirm("Etes-vous sures de vouloir supprimer?")){
								$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='10' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody");
								$.ajax({
									url : '/suite_project/programme',
									type : 'POST',
									data : donnees,
									dataType : 'html',
									success : function(data, result, jqXHR){
										datas=data.split("??");
										datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
										$("#tbody").replaceWith(datas[0]);
										datas[1]="<th colspan='7' id='titreTab'><h2>Liste des programmes ("+datas[1]+")</h2></th>";
										$("#titreTab").replaceWith(datas[1]);
										
									},
									error: function(XMLHttpRequest, textStatus, errorThrown) { 
							            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
							        } 
								});
							}
						}
						
					});
			});
				$(document).ready(function(){
					$("#btnReload").click(function(e){
						e.preventDefault();
						var donnees="enregistrerProgr=reload";
						$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='10' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody");
						$.ajax({
							url : '/suite_project/programme',
							type : 'POST',
							data : donnees,
							dataType : 'html',
							success : function(data, result, jqXHR){
								datas=data.split("??");
								datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
								$("#tbody").replaceWith(datas[0]);
								datas[1]="<th colspan='7' id='titreTab'><h2>Liste des programmes ("+datas[1]+")</h2></th>";
								$("#titreTab").replaceWith(datas[1]);
								
							},
							error: function(XMLHttpRequest, textStatus, errorThrown) { 
					            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
					        } 
						});
					});
				});
				$(document).ready(function(){
					$("#choix_designation").blur(function(e){
						e.preventDefault();
						var donnees="";
							donnees="designation="+$("#choix_designation").val();
							donnees+="&intitule="+$("#choix_intitule").val();
							donnees+="&annee="+$("#choix_annee").val();
							donnees+="&enregistrerProgr=filtrer";
							$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='10' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody");
							$.ajax({
								url : '/suite_project/programme',
								type : 'POST',
								data : donnees,
								dataType : 'html',
								success : function(data, result, jqXHR){
									datas=data.split("??");
									datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
									$("#tbody").replaceWith(datas[0]);
									datas[1]="<th colspan='8' id='titreTab'><h2>Liste des programmes ("+datas[1]+")</h2></th>";
									$("#titreTab").replaceWith(datas[1]);
									
								},
								error: function(XMLHttpRequest, textStatus, errorThrown) { 
						            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
						        } 
							});
					});
					$("#choix_intitule").blur(function(e){
						e.preventDefault();
						var donnees="";

							donnees="designation="+$("#choix_designation").val();
							donnees+="&intitule="+$("#choix_intitule").val();
							donnees+="&annee="+$("#choix_annee").val();
							donnees+="&enregistrerProgr=filtrer";
							$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='10' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody");
						
						$.ajax({
							url : '/suite_project/programme',
							type : 'POST',
							data : donnees,
							dataType : 'html',
							success : function(data, result, jqXHR){
								datas=data.split("??");
								datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
								$("#tbody").replaceWith(datas[0]);
								datas[1]="<th colspan='8' id='titreTab'><h2>Liste des programmes ("+datas[1]+")</h2></th>";
								$("#titreTab").replaceWith(datas[1]);
								
							},
							error: function(XMLHttpRequest, textStatus, errorThrown) { 
					            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
					        } 
						});
					});
					$("#choix_annee").blur(function(e){
						e.preventDefault();
						var donnees="";

							donnees="designation="+$("#choix_designation").val();
							donnees+="&intitule="+$("#choix_intitule").val();
							donnees+="&annee="+$("#choix_annee").val();
							donnees+="&enregistrerProgr=filtrer";
							$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='10' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody");
						
						$.ajax({
							url : '/suite_project/programme',
							type : 'POST',
							data : donnees,
							dataType : 'html',
							success : function(data, result, jqXHR){
								datas=data.split("??");
								datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
								$("#tbody").replaceWith(datas[0]);
								datas[1]="<th colspan='8' id='titreTab'><h2>Liste des programmes ("+datas[1]+")</h2></th>";
								$("#titreTab").replaceWith(datas[1]);
								
							},
							error: function(XMLHttpRequest, textStatus, errorThrown) { 
					            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
					        } 
						});
					});
				});
			</script>   
	<!-- Inclusion de footer -->
	<section id="pieds">
		<jsp:include page="pieds_des_pages.jsp"/>
	</section>
	
</body>
</html>