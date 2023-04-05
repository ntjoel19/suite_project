<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="charset=utf-8" />
	<link rel="stylesheet" type="text/css"  href="design/Design_Global.css" />
	<link rel="stylesheet" type="text/css"  href="design/jquery-ui.css" />
	<link rel="stylesheet" type="text/css"  href="design/css_Projets.css" />
	<link rel="stylesheet" type="text/css"  href="design/dataTables.jqueryui.css" />
	<link rel="stylesheet" type="text/css"  href="design/globaldesign.css" />
			<script src="jquery/jquery.js"></script>
			<script src="jquery/jquery-ui.js"></script>
			<script src="jquery/jquery-validate.js"></script>
			<script src="jscript/juizDropDownMenu-2.0.0.min.js"></script>
		    <script src="jscript/design.js"></script>
		    <style>
				td  span  {
					color : red ;
					}
				textarea {
					width : 99%;
					border-radius : 2px ;
				}
				input[type='text'] , select {
					width : 99.75%;
					border-radius : 2px ;

				}
				input[type='submit'],input[type='reset']{
					width : 7em;
					border-radius : 2px ;
					
				}
				#projet{
					border: 1px solid #f6f5f5;
					background: #ffff;
				} 	
			</style>
	<title>Espace des Projets</title>
</head>
<body>
		<!-- On inclut l'entete (baniere + menu) -->
		<section id="entete">
			<jsp:include page="entete_des_pages.jsp"/>  
			<table id="menu_gestion">
				<tr><form action="/suite_project/programme" method="post">
					<td id="menu_gestiontitre"><h2>Gestion des projets</h2></td>
					<td class="menu_gestiontitrebtn"><a href="/suite_project/admin"><img class="img" src="ressources/home.png"/></a></td>
					<td class="menu_gestiontitrebtn"><a href="/suite_project/projet?btn=creerproj"><img class="img" src="ressources/add.jpg"/></a></td>
					<td class="menu_gestiontitrebtn"><a href=""><img class="img" src="ressources/modifier.jpg"/></a></td>
					<td class="menu_gestiontitrebtn"><input id="btnDelete" type="submit" name="enregistrerProgr" value="save1"/></td>
				</tr>
			</table>
		</section>	
		<section id="contenu">
			<table class="filtreprogr" align="center">
				<tr>
					<td>
						<input list="designation" type="text" id="choix_bieres" placeholder="designation">
						<datalist id="designation">
							  <option value="Projets">
							  <option value="Pils">
							  <option value="Kronenbourg">
							  <option value="Grimbergen">
						</datalist>
					</td>
					<td>
						<input list="intitule" type="text" id="choix_bieres" placeholder="Intitulé">
						<datalist id="intitule">
							  <option value="Meteor">
							  <option value="Pils">
							  <option value="Kronenbourg">
							  <option value="Grimbergen">
						</datalist>
					</td>
				</tr>
			</table>
			<table id="corpsprogr" class="display"  cellspacing="1" width="100%">
				<thead>
					<tr><th colspan="5" id="titreTab"><h2>Liste des Projets</h2></th></tr>
					<tr>
						 <th class="corpsprogrcheckBox">
						 	
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
						 	Objectifs
						 </th>
					</tr>
				</thead>
				<tbody>
					<tr>
						 <td class="corpsprogrcheckBox">
						 	<input type="checkbox" name="selectedRow" />
						 </td>
						 <td>
						 	Désignation
						 </td>
						 <td>
						 	Intitulé
						 </td>
						 <td>
						 	Objectifs
						 </td>
						 <td>
						 	Objectifs
						 </td>
					</tr>
					<tr >
						 <td class="corpsprogrcheckBox">
						 	<input type="checkbox" name="selectedRow" />
						 </td>
						 <td>
						 	Désignation
						 </td>
						 <td>
						 	Intitulé
						 </td>
						 <td>
						 	Objectifs
						 </td>
						 <td>
						 	Objectifs
						 </td>
					</tr>
					<tr >
						 <td class="corpsprogrcheckBox">
						 	<input type="checkbox" name="selectedRow" />
						 </td>
						 <td>
						 	Désignation
						 </td>
						 <td>
						 	Intitulé
						 </td>
						 <td>
						 	Objectifs
						 </td>
						 <td>
						 	Objectifs
						 </td>
					</tr>
					<tr >
						 <td class="corpsprogrcheckBox">
						 	<input type="checkbox" name="selectedRow" />
						 </td>
						 <td>
						 	Désignation
						 </td>
						 <td>
						 	Intitulé
						 </td>
						 <td>
						 	Objectifs
						 </td>
						 <td>
						 	Objectifs
						 </td>
					</tr>
					<tr >
						 <td class="corpsprogrcheckBox">
						 	<input type="checkbox" name="selectedRow" />
						 </td>
						 <td>
						 	Désignation
						 </td>
						 <td>
						 	Intitulé
						 </td>
						 <td>
						 	Objectifs
						 </td>
						 <td>
						 	Objectifs
						 </td>
					</tr>
					<tr >
						 <td class="corpsprogrcheckBox">
						 	<input type="checkbox" name="selectedRow" />
						 </td>
						 <td>
						 	Désignation
						 </td>
						 <td>
						 	Intitulé
						 </td>
						 <td>
						 	Objectifs
						 </td>
						 <td>
						 	Objectifs
						 </td>
					</tr>
				</tbody>
			</table>
	</section>	
	
	<div id="dialog" title="Formulaire d'information de projet ">
				<form id="formProjet" method="post" action="/suite_project/projet">
					<table border="0">
						<tr> <th colspan="5" style="color:red;">* : Champs obligatoires </th> </tr>
						<tr> 
							<td width="12%" > Désignation <span class ="oblige"> *</span> </td> <td colspan=4"><input type="text" name="designation" required="required"/></td> 
						
						</tr>
						<tr> 
							<td>Intitulé <span class ="oblige">* </span></td> 
							<td width="12%">
								<textarea required="required" name=""></textarea>
							</td> 
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td width="12%">Nature du financement </td> <td><textarea name="cadreInstitutionel"></textarea></td> 
						</tr>
						<tr> 
							<td>Promoteur <span class ="oblige">* </span></td> 
							
							<td width="35%">
								<select>
									<option> promoteur 1 </option>
									<option> promoteur 2 </option>
									<option> promoteur 3 </option>
								</select>
							</td> 
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>Extrants escomptés <span class ="oblige"> * </span></td> <td><textarea name="cadreInstitutionel"  required="required"></textarea></td> 
						</tr>
						<tr> 
							<td>Contexte de mise en oeuvre </td> <td><textarea></textarea></td> 
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>Effets attendues </td> <td><textarea name="cadreInstitutionel"></textarea></td> 
						</tr>
						<tr> 
							 <td>Programme concerné <span class ="oblige">* </span></td> 
							 
							 <td>
								<select>
									<option> prog 1 </option>
									<option> prog 2 </option>
									<option> prog 3 </option>
								</select>
							 </td> 
							 <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							 <td>Impactes du projet</td> <td><textarea name="cadreInstitutionel"></textarea></td> 
						
						</tr>
						<tr> 
							<td>Action associée <span class ="oblige">* </span></td> 
							<td>
								<select>
									<option> act 1 </option>
									<option> act 2 </option>
									<option> act 3 </option>
								</select>
							</td> 
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>Objectifs visés <span class ="oblige"> * </span></td> <td><textarea name="axeStrategiq"  required="required"></textarea></td> 
						</tr>
						
						<tr> 
							<td>Justifications des besoins  </td> <td><textarea name="fonction"></textarea></td> 
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>Ancrage du projet </td> <td><textarea name="respoProgr"></textarea></td>
						</tr>
						
						<tr> 
							<td>Cout du projet <span class ="oblige"> * </span></td> <td><textarea name="strategiProgr"  required="required"></textarea></td> 
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>Typologie du projet</td> <td><textarea name="cadreInstitutionel"></textarea></td>
						</tr>
						
						<tr>
						<td>Nom du fichier(chemin)</td> 
							<td>
								<input type="file" name="pathFicheProjet">
							</td> 
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>Chemin TDR </td> 
							<td>
								<input type="file" name="pathTDR">
							</td> 
						</tr>
						
						<tr> <td colspan="5" style="text-align:center;"> <br/><input type="reset" value="cancel" name="effacer"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="save" name="enregistrer"/></td></tr>
					</table>
				</form>
			</div>
			<script>
				$('#dialog').dialog({
					modal : true ,
					width : 1200  
				});
				
				$(function(){
					$('#formProjet').validate({
						rules: {
							"designation" : {
								"required" : true ,
								"minlength": 5,
								"maxlength": 60000 ,
							},
							/*"telephone": {
								"required" : true ,
								"regex": /^(237)[0-9]{8}$/
							}*/
							"intitule" : {
								"required" : true ,
								"minlength": 5,
								"maxlength": 60000
							},
							"objectifs" : {
								"required" : true ,
								"minlength": 5,
								"maxlength": 60000
							},
							"indicateurs" : {
								"required" : true ,
								"minlength": 5,
								"maxlength": 60000
							},
							"valRef" : {
								"required" : true ,
								"minlength": 5,
								"maxlength": 60000
							},
							"valCible" : {
								"required" : true ,
								"minlength": 5,
								"maxlength": 60000
							},
							"sousSecteur" : {
								"required" : true ,
								"minlength": 5,
								"maxlength": 60000
							},
							"respoProgram" : {
								"required" : true ,
								"minlength": 5,
								"maxlength": 60000
							}
						}
					});
					
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
				
			</script>
	
	<!-- Inclusion de footer -->
	<div id="pieds">
		<jsp:include page="pieds_des_pages.jsp"/>
	</div>
	
	<script type="text/javascript" src="jscript/jquery.js"></script>
    <script type="text/javascript" src="jscript/design.js"></script>
    <script type="text/javascript" src="jscript/jquery-ui.min.js"></script>
</body>
</html>