package com.janaka.kitchenslk.util;

import com.janaka.kitchenslk.dto.EmailTemplateDTO;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 22, 2013 - 2:44:17 PM
 * Project	: kitchenslk
 */
public interface Sender {
	
	public void sendEmailMessage(EmailTemplateDTO emailTemplateDTO)throws Exception;

}
