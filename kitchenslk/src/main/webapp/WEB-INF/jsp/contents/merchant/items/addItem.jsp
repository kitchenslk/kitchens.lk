<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<div style="width: 100%; overflow: hidden;">
	
	<form:form action="createitem.htm"  modelAttribute="item" method="post">
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
				<td>Item Image</td>
				<td> : </td>
				<td>
					<input type="text" id="itemImage" />
					<form:hidden path="itemImage.uploadedFileName" id="uploadedFileName" />
					<form:hidden path="itemImage.constructedFileName" id="constructedFileName" />
					<form:hidden path="itemImage.fileUrl" id="fileUrl" />
					<form:hidden path="itemImage.physicalFilePath" id="physicalFilePath" />
					<form:hidden path="itemImage.thumbnailPath" id="thumbnailPath" />
					<span id="uploadingImageLoader" style="display:none;"><img src="../images/ajax-image-loader.gif" /></span>
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
			<td>Name</td>
			<td>:</td>
			<td><input name="itemTypes[\${itemTypeIndex}].itemTypeName" id="itemTypeName_\${itemTypeIndex}" /></td>
		</tr>
		<tr>
			<td>Description</td>
			<td>:</td>
			<td><input name="itemTypes[\${itemTypeIndex}].itemTypeDescription" id="itemTypeDescription_\${itemTypeIndex}" /></td>
		</tr>
		<tr>
			<td>Image</td>
			<td>:</td>
			<td>
				<input type="text" id="itemTypeImage_\${itemTypeIndex}" />
				<input type="hidden" name="itemTypes[\${itemTypeIndex}].itemTypeImage.uploadedFileName" id="uploadedFileName_\${itemTypeIndex}" />
				<input type="hidden" name="itemTypes[\${itemTypeIndex}].itemTypeImage.constructedFileName" id="constructedFileName_\${itemTypeIndex}" />
				<input type="hidden" name="itemTypes[\${itemTypeIndex}].itemTypeImage.fileUrl" id="fileUrl_\${itemTypeIndex}" />
				<input type="hidden" name="itemTypes[\${itemTypeIndex}].itemTypeImage.physicalFilePath" id="physicalFilePath_\${itemTypeIndex}" />
				<input type="hidden" name="itemTypes[\${itemTypeIndex}].itemTypeImage.thumbnailPath" id="thumbnailPath_\${itemTypeIndex}" />
				<span id="uploadingImageLoader_\${itemTypeIndex}" style="display:none;"><img src="../images/ajax-image-loader.gif" /></span>
			</td>
		</tr>
		<tr>
			<td>Attributes</td>
			<td>:</td>
			<td><button id="addItemTypeAttribute_\${itemTypeIndex}" >Add Attribute</button></td>
		</tr>					
		<tr>
			<td colspan="3">
				<table id="itemTypeAttributeTable_\${itemTypeIndex}" style="display:none;">
					<thead>
					 	<tr>									 
							<th>Attribute Name</th>
							<th>&nbsp;</th>
							<th>Value</th>
							<th>&nbsp;</th>
							<th>Description</th>
						</tr>
					</thead>
					<tbody id="itemTypeAttributeAppendTable_\${itemTypeIndex}">										
					</tbody>
				</table>
			</td>
		</tr>						
	</table>	
</script>

<script id="attributeTemplate" type="text/html">
	<tr>
		<td>
			<input type="text" id="attributename_\${itemTypeIndex}_\${itemTypeAttributeValueIndex}" class="attributeAutoComplete" />
			<input type="hidden" name="itemTypes[\${itemTypeIndex}].itemAttributeValues[\${itemTypeAttributeValueIndex}].attribute" id="attribute_\${itemTypeIndex}_\${itemTypeAttributeValueIndex}"/>
		</td>
		<td></td>
		<td>
			<input type="text" name="itemTypes[\${itemTypeIndex}].itemAttributeValues[\${itemTypeAttributeValueIndex}].value" id="value_\${itemTypeIndex}_\${itemTypeAttributeValueIndex}"/>
		</td>
		<td>
			<textarea name="itemTypes[\${itemTypeIndex}].itemAttributeValues[\${itemTypeAttributeValueIndex}].description" id="description_\${itemTypeIndex}_\${itemTypeAttributeValueIndex}" ></textarea>
		</td>
	</tr>
</script>    
    
<script type="text/javascript">

	var itemTypeIndex=0;
	var itemTypeArray=[];
	
	$(document).ready(function(){
		$('#button_additemtype').click(function(e){
			e.preventDefault();
			appendItemtype();
			return false;
		});
		
		var map=new imageFieldMap('itemImage','uploadedFileName','constructedFileName','fileUrl','physicalFilePath','thumbnailPath','uploadingImageLoader');
		initializeImageUploading(map);
	});
	
	function appendItemtype(){
		var itemTypeAttributeArry=[];
		var itType=new itemType(itemTypeIndex,itemTypeAttributeArry);	
		
		itemTypeArray.push(itemTypeIndex,itType);
		$('#itemTypeTemplate').tmpl(itType).appendTo('#div_itemtypeappend');
		initializeAddItemAttribute(itemTypeIndex);
		var i=itemTypeIndex;
		var map=new imageFieldMap('itemTypeImage_' + i ,'uploadedFileName_' + i,'constructedFileName_' + i,'fileUrl_' + i,'physicalFilePath_' + i,'thumbnailPath_' + i,'uploadingImageLoader_' + i);
		initializeImageUploading(map);
		itemTypeIndex=itemTypeIndex + 1;
	}
	
	function addItemTypeAttribute(itemTypeIndex){
		console.log('itemTypeIndex :' + itemTypeIndex);
		console.log('itemTypeIndex :' + itemTypeIndex);
		var itType=getItemtypeFromItemTypeArray(itemTypeIndex);
		console.log(itType);
		var attributeArray=itType.attributeInexArray;
		console.log(attributeArray);
		var nextAttrIndex=getNextIndexFromArray(attributeArray);
		console.log("nextAttrIndex" + nextAttrIndex);
		attributeArray.push(nextAttrIndex,nextAttrIndex);
		console.log(attributeArray);
		addItemAttrArrayToItemType(itemTypeIndex,attributeArray);
		var obj=new indexObject(itemTypeIndex,nextAttrIndex);
		$('#attributeTemplate').tmpl(obj).appendTo('#itemTypeAttributeAppendTable_' + itemTypeIndex);
		initializeAttributeAutoComplete(itemTypeIndex,nextAttrIndex);
		$('#itemTypeAttributeTable_' + itemTypeIndex).show();
		return false;
	}
	
	function initializeAddItemAttribute(itemTypeIndex){
		$('#addItemTypeAttribute_' + itemTypeIndex).click(function(e){
			e.preventDefault();
			addItemTypeAttribute(itemTypeIndex);
			return false;
		});	
		
	}
	
	function indexObject(itemTypeIndex,itemTypeAttributeValueIndex){
		this.itemTypeIndex=itemTypeIndex;
		this.itemTypeAttributeValueIndex=itemTypeAttributeValueIndex;
	}
	
	
    
	
	function addItemAttrArrayToItemType(index,array){
		$.each(itemTypeArray, function(idx,value){
			if(value.itemTypeIndex==index){
				value.attributeInexArray=array;
			}
		});
	}
	
	function getNextIndexFromArray(array){
		if(array.length<=0){
			return 0;
		}
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
			if(value.itemTypeIndex==itemTypeIndex){
				selectedItem=value;
			}
		});
		return selectedItem;
	}
	
	function itemType(itemTypeIndex,attributeInexArray){
		this.itemTypeIndex=itemTypeIndex;
		this.attributeInexArray=attributeInexArray;
	}
	
	function initializeAttributeAutoComplete(itemTypeIndex,attributeIndex){
		var cache = {};
	    $( "#attributename_" + itemTypeIndex + "_" + attributeIndex ).autocomplete({
	      minLength: 2,
	      source: function( request, response ) {
	        var term = request.term;
	        if ( term in cache ) {
	          var data=cache[ term ] ;
	          response(
	        	$.map(data, function(item) {
					return {
						value : item.attributeName,
						id : item.attributeId
					};
			  }));
	          return;
	        }	 
	        $.getJSON( "listattributebyterm.htm", request, function( data, status, xhr ) {
	          cache[ term ] = data;
	          response(
	            $.map(data, function(item) {
					return {
						value : item.attributeName,
						id : item.attributeId
					};
			   }));
	        });
	      },	      
		  select : function(event, ui) {						
			$('#attribute_' + itemTypeIndex + "_" + attributeIndex ).val(ui.item.id);			
		  },
	    });
	}
	
	function imageFieldMap(mainField,uploadedFileName,constructedFileName,fileUrl,physicalFilePath,thumbnailPath,uploadingImageLoader){
		this.mainField=mainField;
		this.uploadedFileName=uploadedFileName;
		this.constructedFileName=constructedFileName;
		this.fileUrl=fileUrl;
		this.physicalFilePath=physicalFilePath;
		this.thumbnailPath=thumbnailPath;
		this.uploadingImageLoader=uploadingImageLoader;
	}
	
	function initializeImageUploading(imageFieldMap){    	
	    /* example 1 */
	    var button = $('#' + imageFieldMap.mainField), interval;
	    new AjaxUpload(button,{
	        action: '../uploadFile.htm',
	        name: 'file',
	        onSubmit : function(file, ext){
	            if(file!=''){
	                var checkimg = file.toLowerCase();
	                if (checkimg.match(/(\.pdf|\.PDF|\.txt|\.TXT|\.rtf|\.RTF|\.jpg|\.JPG|\.png|\.PNG|\.gif|\.GIF|\.DOC|\.doc|\.DOCX|\.docx|\.JPEG|\.xls|\.XLS|\.xlsx|\.XLSX)$/)){
						this.setData({ fileName: file.toLowerCase()});
						$('#' + imageFieldMap.uploadingImageLoader).show();
						$('#' + imageFieldMap.mainField).val(file);
	                    this.disable();
					}else{
	                    alert("Please enter Files with Extensions-  .jpg, .png, .gif, .pdf, .doc. txt");
	                    $(this).focus();
	                    return false;
	                }
	            }
	        },
	        onComplete: function(file, response){         	        	
	        	var obj = jQuery.parseJSON(response);
	        	var filePath=obj.filePath;
	        	var fileName=obj.fileName;
	        	var fileUrl=obj.fileUrl;
	        	var createdFileName=obj.createdFileName;  
	        	var thumbnailPath=obj.thumbnailPath;
	        	$('#' + imageFieldMap.mainField).val(file);
	        	$('#' + imageFieldMap.uploadedFileName).val(fileName);
	        	$('#' + imageFieldMap.constructedFileName).val(createdFileName);
	        	$('#' + imageFieldMap.fileUrl).val(fileUrl);
	        	$('#' + imageFieldMap.physicalFilePath).val(filePath);
	        	$('#' + imageFieldMap.thumbnailPath).val(thumbnailPath);	        	
	        	$('#' + imageFieldMap.uploadingImageLoader).hide();
	            this.enable();         

	        }
	    });

	}
	
</script>
    
