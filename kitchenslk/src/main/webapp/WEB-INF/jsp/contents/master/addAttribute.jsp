<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<div style="width: 100%; overflow: hidden;">
	
	<form:form action="submitattribute.htm"  modelAttribute="attribute">
		<form:hidden path="attributeId"/>
		<table>
			<tr>
				<td>Attribute Name</td>
				<td> : </td>
				<td>
					<form:input path="attributeName" id="attributeName" name="attributeName"/>
				</td>
			</tr>
			<tr>
				<td>Attribute Description</td>
				<td> : </td>
				<td>
					<form:textarea path="attributeDescription" id="attributeDescription" name="attributeDescription"/>
				</td>
			</tr>
			<tr>
				<td>Status</td>
				<td> : </td>
				<td>
					<form:radiobutton path="status"   id="status_active" name="status" value="ACTIVE"/> Active
					&nbsp;&nbsp;
					<form:radiobutton path="status" id="status_inactive" name="status" value="INACTIVE"/> Inactive
				</td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Create Attribute" /> </td>
			</tr>
		</table>
	</form:form>
	
</div>

  
    
<script type="text/javascript">


	
	$(document).ready(function(){
		$('#button_additemtype').click(function(e){
			e.preventDefault();
			appendItemtype();
			return false;
		});		
	});
	
	
	
	function appendNewAttribute(itemTypeIndex){
		console.log('itemTypeIndex :' + itemTypeIndex);
		var itType=getItemtypeFromItemTypeArray(itemTypeIndex);
		console.log(itType)
		attributeInexArray=itType.attributeInexArray;
		console.log(attributeInexArray)
		var nextIndex=getNextIndexFromArray(attributeInexArray);
		var idMap={'typeIndex':itemTypeIndex,"attrIndex":nextIndex};
		$('#attributeTemplate').tmpl(idMap).appendTo('#div_itemattributeappend_' + itemTypeIndex);
		attributeInexArray.push(nextIndex);
		addItemAttrArrayToItemType();
		return false;
	}
	var cache = {};
	function createAttributeAutoComplete(){    
	    $("#attributeName").autocomplete({
			source : function(request, response) {
				var term = request.term;
			    if ( term in cache ) {
			       response($.map(cache[ term ], function(item) {
						return {
							value : item.attributeName,
							id : item.attributeId
						};
				  }));
			      return;
			    }			    
			    $.getJSON( "listattributebyterm.htm", request, function( data, status, xhr ) {
			          cache[ term ] = data;
			          response($.map(data, function(item) {
							return {
								value : item.attributeName,
								id : item.attributeId
							};
					  }));
			    });
				
			},
			minLength : 2,
			autoFocus : true,
			formatItem : function(row, i, total) {
				return row.value;
			},
			select : function(event, ui) {
				var attributeId = ui.item.id;
			    getSelectedAttribute(attributeId);		
			}
		});
		
	}
	
	function getSelectedAttribute(id){
		 $.getJSON( "getselectedattribute.htm", {id:id}, function( data, status, xhr ) {
	        $('#attributeId').val(data.attributeId);
	        $('#attributeName').val(data.attributeName);
	        $('#attributeDescription').val(data.attributeDescription);
	        if(data.status=='ACTIVE'){
	        	 $('#status_active').attr('checked','checked');
	        }else{
	        	$('#status_inactive').attr('checked','checked');
	        }
	    });
	}
	
	
	
</script>
    
