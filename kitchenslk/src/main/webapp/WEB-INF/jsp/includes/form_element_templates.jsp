	<script id="formRowLabelTemplate" type="text/html">
        <li class="form_row" id="\${uniqueId}" onclick="selectRow('\${uniqueId}')">
            <div class="seperatorClass"></div>
            <div class="inputClass">
                <div class="labelVal" style="font-size: 15px;">\${label}</div>
                <input type="hidden" size="\${size}" name="\${name}" id="\${id}" value="\${label}" class="inputField" />
            </div>
            <div class="cursor">                
                <input type="hidden" id="inputType" name="inputType" value="\${type}" />
				<input type="hidden" id="inputFieldPk" name="inputFieldPk" value="\${formFieldId}" />
            </div>
        </li>
    </script>
    <script id="formRowTemplate" type="text/html">
        <li class="form_row" id="\${uniqueId}" onclick="selectRow('\${uniqueId}')">
            <label class="labelClass">\${label}</label>
            <div class="seperatorClass">:</div>
            <div class="inputClass">
                <input type="\${type}" size="\${size}" name="\${name}" id="\${id}" class="inputField" />
            </div>
            <div class="cursor">                
                <input type="hidden" id="inputType" name="inputType" value="\${type}" /> 
				<input type="hidden" id="inputFieldPk" name="inputFieldPk" value="\${formFieldId}" />               
            </div>
        </li>
    </script>
    <script id="formRowSelectTemplate" type="text/html">
        <li class="form_row" id="\${uniqueId}" onclick="selectRow('\${uniqueId}')">
            <label class="labelClass">\${label}</label>
            <div class="seperatorClass">:</div>
            <div class="inputClass">
                <select name="\${name}" id="\${id}" class="inputField">
                    {{each options}}
                        <option id="\${id}" value="\${value}" alt="\${formFieldOptionId}">\${display}</option>
                    {{/each}}
                </select>
            </div>
            <div class="cursor">                
                <input type="hidden" id="inputType" name="inputType" value="\${type}" />
				<input type="hidden" id="inputFieldPk" name="inputFieldPk" value="\${formFieldId}" />
            </div>
        </li>
    </script>
    <script id="formRowRadioTemplate" type="text/html">
        <li class="form_row" id="\${uniqueId}" onclick="selectRow('\${uniqueId}')">
            <label class="labelClass">\${label}</label>
            <div class="seperatorClass">:</div>
            <div class="inputClass">
                {{each options}}
                    <input style="\${display}" type="radio" name="\${$data.name}" id="\${id}" value="\${value}" class="inputField" alt="\${formFieldOptionId}" />
                    <label class="inputDisplay">\${display}</label>
                {{/each}}
            </div>
            <div class="cursor">                
                <input type="hidden" id="inputType" name="inputType" value="\${type}" />
				<input type="hidden" id="inputFieldPk" name="inputFieldPk" value="\${formFieldId}" />
            </div>
        </li>
    </script>
    <script id="formRowCheckboxTemplate" type="text/html">        
        <li class="form_row" id="\${uniqueId}" onclick="selectRow('\${uniqueId}')">
            <label class="labelClass">\${label}</label>
            <div class="seperatorClass">:</div>
            <div class="inputClass">
                <table style="width:100%">
                    {{each options}}
                        <tr>
                            <td>
                                <input style="\${display}" type="checkbox" name="\${$data.name}" id="\${id}" value="\${value}" class="inputField" alt="\${formFieldOptionId}" />
                                <label class="inputDisplay">\${display}</label>
                            </td>
                        </tr>
                    {{/each}}
                </table>
            </div>
            <div class="cursor">               
                <input type="hidden" id="inputType" name="inputType" value="\${type}" />
				<input type="hidden" id="inputFieldPk" name="inputFieldPk" value="\${formFieldId}" />
            </div>
        </li>
    </script>
    <script id="formRowTextAreaTemplate" type="text/html">        
        <li class="form_row" id="\${uniqueId}" onclick="selectRow('\${uniqueId}')">
            <label class="labelClass">\${label}</label>
            <div class="seperatorClass">:</div>
            <div class="inputClass">
                <textarea id="\${id}" name="\${name}" class="inputField"></textarea>                            
            </div>
            <div class="cursor">                
                <input type="hidden" id="inputType" name="inputType" value="\${type}" />
				<input type="hidden" id="inputFieldPk" name="inputFieldPk" value="\${formFieldId}" />
            </div>
        </li>
    </script>
    <script id="formRowDatepickerTemplate" type="text/html">        
        <li class="form_row" id="\${uniqueId}" onclick="selectRow('\${uniqueId}')">
            <label class="labelClass">\${label}</label>
            <div class="seperatorClass">:</div>
            <div class="inputClass">
                <input type="text" size="10" name="\${name}" id="\${id}" class="inputField" />
                <script type="text/javascript">
                    $('#\${id}').datepicker({ dateFormat: '\${format}' })
                {{html "</sc"+"ript>"}}
            </div>
            <div class="cursor">                
                <input type="hidden" id="inputType" name="inputType" value="\${type}" />
                <input type="hidden" id="format" name="format" value="\${format}" />
				<input type="hidden" id="inputFieldPk" name="inputFieldPk" value="\${formFieldId}" />
            </div>
        </li>  
    </script>
    <script id="formRowButtonTemplate" type="text/html">
        <li class="form_row" id="\${uniqueId}" onclick="selectRow('\${uniqueId}')">
            <label class="labelClass">&nbsp;</label>
            <div class="seperatorClass">&nbsp;</div>
            <div class="inputClass">
                <input type="\${type}" size="\${size}" name="\${name}" id="\${id}" value="\${label}" class="inputField" />
            </div>
            <div class="cursor">                
                <input type="hidden" id="inputType" name="inputType" value="\${type}" />
				<input type="hidden" id="inputFieldPk" name="inputFieldPk" value="\${formFieldId}" />
            </div>
        </li>
    </script>
    <script id="formEditDialogTemplate" type="text/html">
        <div id="dialog-form-edit" title="Edit form field">
            <p class="validateTips">All form fields are required.</p>       
            <form>
                <fieldset>
                  <table>                
                    <tr>
                        <td><label for="id">Id</label></td>
                        <td>:</td>
                        <td>
							<input type="text" name="id" id="idEdit" value="\${id}" class="text ui-widget-content ui-corner-all" />							
						</td>
                    </tr>
                    <tr>
                        <td><label for="name">Name</label></td>
                        <td>:</td>
                        <td><input type="text" name="name" id="nameEdit" value="\${name}" class="text ui-widget-content ui-corner-all" /></td>
                    </tr>
                    <tr>
                        <td><label for="label">Label</label></td>
                        <td>:</td>
                        <td><input type="text" name="label" id="labelEdit" value="\${label}" class="text ui-widget-content ui-corner-all" /></td>
                    </tr>
                    <tr id="size_fieldEdit">
                        <td><label for="size">Size</label></td>
                        <td>:</td>
                        <td><input type="text" name="size" id="sizeEdit" value="\${size}" class="text ui-widget-content ui-corner-all" /></td>
                    </tr>
                    <tr style="display:none" id="option_rowEdit">
                        <td>
                            <label>
                                <a href="javascript:addSelectOption('edit')">Add Options</a>
                            </label>
                        </td>
                        <td>:</td>
                        <td>                        
                            <div id="form_option_appending_div_edit">
                                {{if options != null}} 
                                    {{each options}}
                                        <div id="option_\${id}">\${display}
                                            <span>
                                                <a href="javascript:removeOption(\${id})">X</a>
                                            </span>
                                        </div>
                                    {{/each}}
                                {{/if}}
                            </div>
                        </td>
                    </tr>                
                    <tr style="display:none" id="format_rowEdit">
                        <td><label for="format">Format</label></td>
                        <td>:</td>
                        <td>                       
                            <select id="formatEdit" name="format" class="text ui-widget-content ui-corner-all">
                                {{if format == "mm/dd/yy"}}   -
                                   <option value="mm/dd/yy" selected="selected">Default - mm/dd/yy</option>
                                {{else}}
                                   <option value="mm/dd/yy">Default - mm/dd/yy</option>
                                {{/if}}
                                {{if format == "yy-mm-dd"}}   -
                                   <option value="yy-mm-dd" selected="selected">ISO 8601 - yy-mm-dd</option>
                                {{else}}
                                   <option value="yy-mm-dd">ISO 8601 - yy-mm-dd</option>
                                {{/if}}
                                {{if format == "d M, y"}}   -
                                   <option value="d M, y" selected="selected">Short - d M, y</option>
                                {{else}}
                                   <option value="d M, y">Short - d M, y</option>
                                {{/if}}
                                {{if format == "d MM, y"}}   -
                                  <option value="d MM, y" selected="selected">Medium - d MM, y</option>
                                {{else}}
                                   <option value="d MM, y">Medium - d MM, y</option>
                                {{/if}}
                                {{if format == "DD, d MM, yy"}}   -
                                  <option value="DD, d MM, yy" selected="selected">Full - DD, d MM, yy</option>
                                {{else}}
                                   <option value="DD, d MM, yy">Full - DD, d MM, yy</option>
                                {{/if}}                                  
                            </select>
                        </td>
                    </tr>
                  </table>
                  <input type="hidden" name="type" id="typeEdit" value="\${type}" />
                  <input type="hidden" name="uniqueId" id="uniqueIdEdit" value="\${uniqueId}" />
				  <input type="hidden" name="formFieldIdEdit" id="formFieldIdEdit" value="\${formFieldId}" />
                </fieldset>
            </form>
        </div>
    </script>