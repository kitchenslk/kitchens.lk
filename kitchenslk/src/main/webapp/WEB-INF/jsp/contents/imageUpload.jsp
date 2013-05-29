<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script id="uploadedFileTemplate" type="text/html">
		<td id="uploadedFileNode\${uniqueId}">
    				<table>
    					<tr>
    						<td><a href="\${fileUrl}" id="imageFinderUrl\${uniqueId}"><img src="images/docIcon.png" style="height:135px;width:105px" /></a></td>
    					</tr>
    					<tr>
    						<td><a href="\${fileUrl}" id="imageFinderUrlName\${uniqueId}">\${fileName}<span id="image_name\${uniqueId}"></span></a></td>
    					</tr>
    					<tr>
    						<td><a herf="javascript:void(0)" onclick="chnageDiv('\${uniqueId}','\${fileUrl}');">Delete</a></td>
    					</tr>
    					<tr>
    						<td>
    						  	<input type="hidden" id="documentUrl\${uniqueId}" value="\${fileUrl}" />
    						  	<input type="hidden" id="uploadedDocumentName\${uniqueId}"  value="\${fileName}/>
    						  	<input type="hidden" id="createdDocumentName\${uniqueId}"  value="\${createdFileName}/>
    						  	<input type="hidden" id="physicalDocumentPath\${uniqueId}"  value="\${filePath}/>
    						</td>
    					</tr>
    				</table>    			
    	</td>    		
</script>    
    
    
<script type="text/javascript">
var uploadedFileIdArray=new Array(); 
var uniqueId=0;

function uploadedFile(uniqueId, filePath, fileName,fileUrl,createdFileName) {
    this.uniqueId=uniqueId;
    this.filePath=filePath;
    this.fileName=fileName;
    this.fileUrl=fileUrl;
    this.createdFileName=createdFileName;
}
      
$(document).ready(function(){
	
	ajaxCallBuilt();
	

});

var reponseFilepath;

function ajaxCallBuilt(){    	
    /* example 1 */
    var button = $('#uploadFile'), interval;
    new AjaxUpload(button,{
        action: 'uploadFile.htm',
        name: 'file',
        onSubmit : function(file, ext){
            if(file!=''){
                var checkimg = file.toLowerCase();
                if (checkimg.match(/(\.pdf|\.PDF|\.txt|\.TXT|\.rtf|\.RTF|\.jpg|\.JPG|\.png|\.PNG|\.gif|\.GIF|\.DOC|\.doc|\.DOCX|\.docx|\.JPEG)$/)){
					
                    
                    this.setData({ fileName: file.toLowerCase()});                 	
                    

                    // change button text, when user selects file
                    //button.text('Uploading');

                    //$(this).css('background-image', 'url( images/ajax-image-loader.gif )');
					$('#uploadingImageLoader').show();

                    // If you want to allow uploading only 1 file at time,
                    // you can disable upload button
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
        	uniqueId=uniqueId + 1;
        	var contId="const_" + uniqueId;
        	var uploadedFileObj=new uploadedFile(contId, filePath, fileName,fileUrl,createdFileName);        	
        	$("#uploadedFileTemplate").tmpl(uploadedFileObj).appendTo("#imageUploadedAppendDiv"); 
            // enable upload button
            $('#uploadingImageLoader').hide();
            this.enable();         

        }
    });

}

function chnageDiv(id,filepath){
	console.log(id);
    $.ajaxSetup({ cache: false });   
    $.post('deleteFile.htm',{ pathVar:filepath }, function(data) {
    	console.log(data);
    	 $('#uploadedFileNode' + id).remove(); 
    });     
    
}


      
</script>

<div style="width: 100%; overflow: hidden;">

	<h3>Upload Document</h3>
	
	<div>
		<span>Select File</span> 
		<span id="uploadFile"><input type="text"/><input type="button" value="Browse" /></span>
		<span id="uploadingImageLoader" style="display:none;"><img src="images/ajax-image-loader.gif" /></span>
	</div>
    <div>
    	<table>
    		<tr id="imageUploadedAppendDiv">
    			
    		</tr>
    	</table>
    </div>
</div>

    
    
