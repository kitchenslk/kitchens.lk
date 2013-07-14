package com.janaka.kitchenslk.springeditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;

import com.janaka.kitchenslk.entity.Attribute;
import com.janaka.kitchenslk.service.CommonService;

public class AttributeEditor extends PropertyEditorSupport {

    private final CommonService commonService;

    public AttributeEditor(CommonService commonService) {
        this.commonService = commonService;
    }


	@Override
    public void setAsText(String attributeId) throws IllegalArgumentException {
       Attribute attribute=new Attribute();
		try {
			if(StringUtils.isNotEmpty(attributeId) && StringUtils.isNumeric(attributeId)){
				attribute = commonService.getEntityById(Attribute.class, Long.parseLong(attributeId));
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        setValue(attribute);
    }
}
