package com.janaka.kitchenslk.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import com.janaka.kitchenslk.entity.Token;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 27, 2013 - 10:51:24 PM
 * Project	: kitchenslk
 */
@Service(value = "customTokenRepository")
public class CustomTokenRepositoryImpl implements PersistentTokenRepository {
	
	@Autowired
	private CommonService commonService;

	@Override
	public void createNewToken(PersistentRememberMeToken rememberMeToken) {
		try {
			commonService.createEntity(new Token(rememberMeToken));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		Token token = null;
		try {
			token = commonService.getEntityByGivenFieldValue(Token.class,"series", seriesId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new PersistentRememberMeToken(token.getUsername(), token.getSeries(), token.getTokenValue(), token.getDate());
	}

	@Override
	public void removeUserTokens(String seriesId) {
		Token token = null;
		try {
			token = commonService.getEntityByGivenFieldValue(Token.class,"series", seriesId);
			commonService.deleteEntity(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		Token token = null;
		try {
			token = commonService.getEntityByGivenFieldValue(Token.class,"series", seriesId);
			token.setTokenValue(seriesId);
			token.setDate(lastUsed);
			commonService.deleteEntity(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
