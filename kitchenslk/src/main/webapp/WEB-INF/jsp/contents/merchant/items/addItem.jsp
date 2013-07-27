<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<div style="width: 100%; overflow: hidden;">
	
	<form:form action="createitem.htm"  modelAttribute="item">
		<table>
			<tr>
				<td>Item Name</td>
				<td> : </td>
				<td>
					<form:input path="itemName" id="itemName" name="itemName"/>
				</td>
			</tr>
			<tr>
				<td>Item Description</td>
				<td> : </td>
				<td>
					<form:input path="itemDescription" id="itemDescription" name="itemDescription"/>
				</td>
			</tr>
			<tr>
				<td colspan="3"><button id="button_additemtype">Add Item type</button></td>
			</tr>
			<tr>
				<td colspan="3" id="div_itemtypeappend">
					
				</td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Create Item" /> </td>
			</tr>
		</table>
	</form:form>
	
</div>

<script id="itemTypeTemplate" type="text/html">
	<table>
		<tr><td colspan="3">Item Type \${itemTypeIndex}</td></tr>
		<tr>
			<td>Attribute</td>
			<td>&nbsp;</td>
			<td>Value</td>
		</tr>
		<tr>							
			<td>
			<input type="text" name="itemAttributes[\${itemTypeIndex}].attribute.attributeName" id="select_attrubute_\${itemTypeIndex}" class="selectattribute" />
			<input type="hidden" name="itemAttributes[\${itemTypeIndex}].attribute" id="attrubuteid_\${itemTypeIndex}" />
		</td>
			<td>&nbsp;</td>							
			<td>
				<input type="text" name="itemAttributes[\${itemTypeIndex}].itemAttributeValues[\${itemTypeIndex}].value" id="input_attrubutevalue_1" />
			</td>
		</tr>						
		<tbody id="div_itemattributeappend_\${itemTypeIndex}"></tbody>
		<tr>
			<td colspan="2"></td>
			<td><a id="button_addatrribute_\${itemTypeIndex}" onclick="return appendNewAttribute(\${itemTypeIndex})">Add Attribute</a></td>
		</tr>
	</table>	
</script>

<script id="attributeTemplate" type="text/html">
	<tr>							
		<td>
			<input type="text" name="itemAttributes[\${idMap.typeIndex}].itemAttributeValues[0].itemAttributeValues[0].itemAttribute.attribute.attributeName" id="select_attrubute_1" class="selectattribute" />
			<input type="hidden" name="itemAttributes[0].itemAttributeValues[0].itemAttributeValues[0].itemAttribute.attribute"  id="attrubuteid_1" />
		</td>
		<td>&nbsp;</td>							
		<td>
			<input type="text" name="itemAttributes[0].itemAttributeValues[0].itemAttributeValues[0].value" id="input_attrubutevalue_1" />
		</td>
	</tr>
</script>    
    
<script type="text/javascript">
	var itemTypeIndex=0;
	var attributeInexArray=[];
	var itemTypeArray=[];
	$(document).ready(function(){
		$('#button_additemtype').click(function(e){
			e.preventDefault();
			appendItemtype();
			return false;
		});		
	});
	
	function appendItemtype(){
		$('#itemTypeTemplate').tmpl(itemTypeIndex).appendTo('#div_itemtypeappend');
		attributeInexArray=[0];
		itemTypeArray.push(new itemType(itemTypeIndex,attributeInexArray));
		itemTypeIndex=itemTypeIndex + 1;		
	}
	
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
	
	function addItemAttrArrayToItemType(index,array){
		$.each(itemTypeArray, function(idx,value){
			if(value.index==index){
				value.attributeInexArray=array;
			}
		});
	}
	
	function getNextIndexFromArray(array){
		var maxVal=0;
		$.each(array,function(index,value){
			if(value>maxVal){
				maxVal=value;
			}
		});
		return (maxVal+1);
	}
	
	function getItemtypeFromItemTypeArray(itemTypeIndex){
		var selectedItem=null;
		$.each(itemTypeArray, function(idx,value){
			if(value.index==itemTypeIndex){
				selectedItem=value;
			}
		});
		return selectedItem;
	}
	
	function itemType(index,attributeInexArray){
		this.index=index;
		this.attributeInexArray=attributeInexArray;
	}
	
</script>
    
