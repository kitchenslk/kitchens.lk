package com.janaka.kitchenslk.util;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.CharEncoding;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.janaka.kitchenslk.dto.EmailTemplateDTO;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 22, 2013 - 2:40:42 PM
 * Project	: kitchenslk
 */
@Component(value="emailSender")
public class EmailSender implements Sender{
	
	//private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmailSender.class);

    private final VelocityEngine velocityEngine;
    private final JavaMailSender mailSender;

    /**
     * Constructor
     */
    @Autowired
    public EmailSender(VelocityEngine velocityEngine,JavaMailSender mailSender) {
        this.velocityEngine = velocityEngine;
        this.mailSender = mailSender;
    }

	@Override
	public void sendEmailMessage(final EmailTemplateDTO emailTemplateDTO)throws Exception {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
               MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
               message.setTo(emailTemplateDTO.getToEmailAddressList());
               message.setFrom(emailTemplateDTO.getFromEmailAddress());
               message.setSubject(emailTemplateDTO.getTitle());
               
               String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailTemplateDTO.getVelocityTemplateLocation(), CharEncoding.UTF_8, emailTemplateDTO.toBasicMap());
               
               System.out.println("body={}" + body);

               message.setText(body, true);
            }
         };
         
         mailSender.send(preparator);
		
	}

}
