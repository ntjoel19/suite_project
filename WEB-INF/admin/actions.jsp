<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="charset=utf-8" />
	<link rel="stylesheet" type="text/css"  href="design/Design_Global.css" />
	<link rel="stylesheet" type="text/css"  href="design/jquery-ui.css" />
	<link rel="stylesheet" type="text/css"  href="design/css_programmes.css" />
	<link rel="stylesheet" type="text/css"  href="design/dataTables.jqueryui.css" />
	<link rel="stylesheet" type="text/css"  href="design/globaldesign.css" />
	<script src="jquery/jquery.js"></script>
	<script src="jquery/jquery-ui.js"></script>
	<script src="jquery/jquery-validate.js"></script>
	<script src="jscript/juizDropDownMenu-2.0.0.min.js"></script>
	<script src="jscript/design.js"></script>
	<title>Espace des actions</title>
	<style>
		#action{
			border: 1px solid #f6f5f5;
			background: #ffff;
		}
	</style>
</head>
<body>
	<% String option1=(String)request.getAttribute("option1");%>
	<% String option2=(String)request.getAttribute("option2");%>
	<% String option3=(String)request.getAttribute("option3");%>
	<% String option=(String)request.getAttribute("option");
	String impossibleDediter=(String)request.getAttribute("impossibleDetiter");
	if( impossibleDediter == null) impossibleDediter="";
	%>
		<% 
			String edit=(String)request.getAttribute("edit");
			String add=(String)request.getAttribute("add");
			String del=(String)request.getAttribute("del");
		%>
		
		<% String disp1=(String)request.getAttribute("display1");%>
		<% String disp2=(String)request.getAttribute("display2");%>
		<% String disp3=(String)request.getAttribute("display3");%>
		<% String disp4=(String)request.getAttribute("display4");%>
		
	<% String designation=(String)request.getAttribute("designation");%>
	<% String intitule=(String)request.getAttribute("intitule");%>
	<% String indicateur=(String)request.getAttribute("indicateur");%>
	<% String objectifs=(String)request.getAttribute("objectif");%>
	<% String valRef=(String)request.getAttribute("valRef");%>
	<% String valCible=(String)request.getAttribute("valCible");%>
	<% String data=(String)request.getAttribute("data");%>
	<% String valeur=(String)request.getAttribute("value");%>
	<% String display=(String)request.getAttribute("display");%>
	<% String dataList=(String)request.getAttribute("dataList"); %>
	<% String progrPere=(String)request.getAttribute("progrPere");
		String ajouter=(String)request.getAttribute("ajout");
		String act="";
		int test=1;
		System.out.println("ajout = "+ajouter);
		if(progrPere==null){
			test=0;
			progrPere="<td width='12%'>Programme pere <span class ='oblige'>* </span></td> <td><select class='in' id='ProgrammePere' name='programmepere'>"+dataList+"</select></br></td>";
			act="<input class='in' type='text' name='designation' value='"+designation+"' />";
		}else{
			progrPere="<td width='12%'>Programme pere <span class ='oblige'>* </span></td> <td><input type='text' class='in' id='ProgrammePere' name='programmepere' value='"+progrPere+"' disabled/></br><input type='hidden' name='progrPere' value='"+progrPere+"'/></td>";
			act="<input class='in' type='text' name='designation' value='"+designation+"' disabled/><input type='hidden' name='designatHidden' value='"+designation+"'/>";
		}
	%>
	<!-- On inclut l'entete (baniere + menu) -->
		<section id="entete">
			<jsp:include page="entete_des_pages.jsp"/>  
			<table id="menu_gestion">
				<tr><% out.print(disp3);%><form id="formTab" method='get' action='/suite_project/action'><% out.print(disp4);%>
					<td id="menu_gestiontitre"><h2>Gestion des actions</h2></td>
					<td class="menu_gestiontitrebtn"><a title="aller à l'acceuil" href="/suite_project/admin"><img class="img" src="ressources/home.png"/></a></td>
					<td class="menu_gestiontitrebtn"><input title="actualiser" id="btnReload" type="image" src="././ressources/actualiser.png" name="enregistrerProgr" value="reload"/></td>
					<%if(test==0 && ajouter==null){
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
		<% out.print(disp3);%>
			<table class="filtreprogr" align="center">
				<tr>
					<td>
						<label for="choix_programme">Programmes</label>
						<select name="choix_programme" id="choix_programme">
							<option value="All" selected>--All--</option>
							<% out.print(option); %>
						</select>
					</td>
					<td>
						<input list="designation" type="text" id="choix_designation" placeholder="designation action">
						<datalist id="designation">
							  <% out.print(option1); %>
						</datalist>
					</td>
					<td>
						<input list="intitule" type="text" id="choix_intitule" placeholder="Intitulé action">
						<datalist id="intitule">
							  <% out.print(option2); %>
						</datalist>
					</td>
					
					
				</tr>
			</table>
			<table id="corpsprogr" class="display"  cellspacing="1">
				<thead>
					<% String nb=(String)request.getAttribute("nb");%>
					<tr><th colspan="5" id="titreTab"><h2>Liste des actions (<% out.print(nb);%>)</h2></th></tr>
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
						 <th id="lastShild">
						 	Programme
						 </th>
					</tr>
				</thead>
				<tbody id="tbody">
					<% 
			            String tabrow = (String)request.getAttribute("tr");
			            out.println( tabrow );
		            %>
				</tbody>
				</form>
			</table>
			<% out.print(disp4);%>
	
	
	<% out.print(disp1);%><div title="Ajouter une nouvel action">
				<form id="dialog" method="post" action="/suite_project/action">
					<table border="0">
						<tr> <th colspan="3" style="color:red;">* : Champs obligatoires </th> </tr>
						<tr>
							<% out.print(progrPere);%>															
						</tr>
						<tr> 
							<td width="12%">Désignation <span class ="oblige"> *</span> </td> <td> <% out.print(act); %> </br></td> 
							<td>&nbsp;&nbsp;</td>
						</tr>
						<tr> 
							<td>Intitulé <span class ="oblige">* </span></td> <td><textarea class='in' name="intitule"><% out.print(intitule);%></textarea></br></td> 
							<td>&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td>Objectifs <span class ="oblige">* </span></td> <td><textarea class='in'  name="objectifs"><% out.print(objectifs);%></textarea></br></td> 	
							<td>&nbsp;&nbsp;</td>
						</tr>
						<tr>  
							<td>Indicateurs <span class ="oblige">* </span></td> <td><textarea class='in' type="text" name="indicateur"><% out.print(indicateur);%></textarea></br></td> 
							<td>&nbsp;&nbsp;</td>	
						</tr>
						<tr>
							<td>Valeur Cible <span class ="oblige">* </span></td> <td><input class='in' value="<% out.print(valCible);%>" type="text" name="valCible"/></br></td> 						
							<td>&nbsp;&nbsp;</td>
						</tr>
						<tr> 
							<td>Valeur de référence <span class ="oblige">* </span></td> <td><input class='in' value="<% out.print(valRef);%>" type="text" name="valRef"/></br></td>
							<td>&nbsp;&nbsp;</td>
						</tr>

						<tr> <td colspan="3" style="text-align:center; "> <br/><input type="reset" value="cancel" name="effacer"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="<% out.print(valeur);%>" name="enregistrerAction"/></td></tr>
					</table>
				</form>
			</div><% out.print(disp2);%>
		</section>
			<script>
				
				$(function(){
					jQuery.extend(jQuery.validator.messages,{
						required  : "Ce champ est requis !" ,
						minlength : "Entrer au moins 5 characters svp" ,
						maxlength : "Dépassement de capacité"
					});
					
					jQuery.validator.addMethod(
					  "regex",
					   function(value, element, regexp) {
						   if (regexp.constructor != RegExp)
							  regexp = new RegExp(regexp);
						   else if (regexp.global)
							  regexp.lastIndex = 0;
							  return this.optional(element) || regexp.test(value);
					   },"Format incorrect !!!"
					);
				});
				$(document).ready(function(){
					$("#btnDelete").live('click',function(e){
						e.preventDefault();
						var donnees=$("#formTab").serialize();
						donnees+="&enregistrerAction=delete";
						if(confirm("Etes-vous sures de vouloir supprimer?")){
							
							$.ajax({
								url : '/suite_project/action',
								type : 'POST',
								data : donnees,
								dataType : 'html',
								success : function(data, result, jqXHR){
									datas=data.split("??");
									datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
									$("#tbody").replaceWith(datas[0]);
									datas[1]="<th colspan='6' id='titreTab'><h2>Liste des actions ("+datas[1]+")</h2></th>";
									$("#titreTab").replaceWith(datas[1]);	
								},
								error: function(XMLHttpRequest, textStatus, errorThrown) { 
						            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
						        } 
							});
						}
					});
			});
				
				$(document).ready(function(){
					$("#choix_designation").blur(function(e){
						e.preventDefault();
						var donnees="";
						
							donnees="designation="+$("#choix_designation").val();
							donnees+="&intitule="+$("#choix_intitule").val();
							donnees+="&programme="+$("#choix_programme").val();
							donnees+="&enregistrerAction=filtrer";
							$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='5' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody>");
							
							$.ajax({
								url : '/suite_project/action',
								type : 'POST',
								data : donnees,
								dataType : 'html',
								success : function(data, result, jqXHR){
									datas=data.split("??");
									datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
									$("#tbody").replaceWith(datas[0]);
									datas[1]="<th colspan='6' id='titreTab'><h2>Liste des actions ("+datas[1]+")</h2></th>";
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
							donnees+="&programme="+$("#choix_programme").val();
							donnees+="&enregistrerAction=filtrer";
						$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='5' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody>");
							
						$.ajax({
							url : '/suite_project/action',
							type : 'POST',
							data : donnees,
							dataType : 'html',
							success : function(data, result, jqXHR){
								datas=data.split("??");
								datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
								$("#tbody").replaceWith(datas[0]);
								datas[1]="<th colspan='6' id='titreTab'><h2>Liste des actions ("+datas[1]+")</h2></th>";
								$("#titreTab").replaceWith(datas[1]);
								
							},
							error: function(XMLHttpRequest, textStatus, errorThrown) { 
					            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
					        } 
						});
					});
					$("#choix_programme").change(function(e){
						e.preventDefault();
						var donnees="";

							donnees="designation="+$("#choix_designation").val();
							donnees+="&intitule="+$("#choix_intitule").val();
							donnees+="&programme="+$("#choix_programme option:selected").text();
							donnees+="&enregistrerAction=filtrer";

						$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='5' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody>");
							
						
						$.ajax({
							url : '/suite_project/action',
							type : 'POST',
							data : donnees,
							dataType : 'html',
							success : function(data, result, jqXHR){
								datas=data.split("??");
								datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
								$("#tbody").replaceWith(datas[0]);
								datas[1]="<th colspan='6' id='titreTab'><h2>Liste des actions ("+datas[1]+")</h2></th>";
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
							donnees+="&objectif="+$("#choix_objectif").val();
							donnees+="&annee="+$("#choix_annee").val();
							donnees+="&enregistrerAction=filtrer";

						$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='5' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody>");	
						
						$.ajax({
							url : '/suite_project/action',
							type : 'POST',
							data : donnees,
							dataType : 'html',
							success : function(data, result, jqXHR){
								datas=data.split("??");
								datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
								$("#tbody").replaceWith(datas[0]);
								datas[1]="<th colspan='6' id='titreTab'><h2>Liste des actions ("+datas[1]+")</h2></th>";
								$("#titreTab").replaceWith(datas[1]);
								
							},
							error: function(XMLHttpRequest, textStatus, errorThrown) { 
					            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
					        } 
						});
					});
			});
				$(document).ready(function(){
					$("#btnReload").on('click',function(e){
						e.preventDefault();
						var donnees="enregistrerAction=reload";
						$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='5' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody>");
						
						$.ajax({
							url : '/suite_project/action',
							type : 'POST',
							data : donnees,
							dataType : 'html',
							success : function(data, result, jqXHR){
								datas=data.split("??");
								datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
								$("#tbody").replaceWith(datas[0]);
								datas[1]="<th colspan='5' id='titreTab'><h2>Liste des actions ("+datas[1]+")</h2></th>";
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
	<div id="pieds">
		<jsp:include page="pieds_des_pages.jsp"/>
	</div>
	
	<script src="jquery/jquery.js"></script>
	<script src="jquery/jquery-ui.js"></script>
	<script src="jquery/jquery-validate.js"></script>
	<script src="jscript/juizDropDownMenu-2.0.0.min.js"></script>
	<script src="jscript/design.js"></script>
</body>
</html>