package com.janaka.kitchenslk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.janaka.kitchenslk.entity.Attribute;
import com.janaka.kitchenslk.entity.SystemUser;
import com.janaka.kitchenslk.enums.Status;
import com.janaka.kitchenslk.service.MasterService;
import com.janaka.kitchenslk.util.ApplicationConstants;
import com.janaka.kitchenslk.util.SessionUtil;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 21, 2013 - 7:20:26 PM
 * Project	: kitchenslk
 */
@Controller("attributeController")
@RequestMapping(value="/master")
public class AttributeController {
	
	
	@Autowired
	private MasterService masterService;
	
	@Autowired
	private SessionUtil sessionUtil;
	
	@RequestMapping(value="/attributes",method=RequestMethod.GET)
	public ModelAndView getAttributeList(HttpServletRequest request){		
		ModelMap map=new ModelMap();
		try {
			map.put("attributeList", masterService.listAllAttributesByStatus(Status.ACTIVE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("listAttributes",map);
	}
	
	@RequestMapping(value="/addattribute", method=RequestMethod.GET)
	public ModelAndView addAttribute(HttpServletRequest request){
		ModelMap map=new ModelMap();
		try {
			map.put("attribute", new Attribute());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("addAttribute",map);
	}
	
	@RequestMapping(value="/modifyattribute", method=RequestMethod.GET)
	public ModelAndView modifyAttribute(HttpServletRequest request,
			@RequestParam("id")String id){
		ModelMap map=new ModelMap();
		try {
			map.put("attribute", masterService.getAttributeById(Long.parseLong(id)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("addAttribute",map);
	}
	
	@RequestMapping(value="/submitattribute", method=RequestMethod.POST)
	public String crateAttribute(HttpServletRequest request, @ModelAttribute("attribute") Attribute attribute){
		System.out.println("attribute name : "+attribute.getAttributeName());
		String message=ApplicationConstants.SUCCESS;
		try {
			SystemUser systemUser=sessionUtil.getCurentSystemUser();
			masterService.saveOrUpdateAttribute(attribute, systemUser.getUserId());
		} catch (Exception e) {
			message=ApplicationConstants.SYSTEM_ERROR;
			e.printStackTrace();
		}
		return "redirect:/attributes.htm?message=" + message;
	}
	
	@RequestMapping(value="/listattributebyterm", method=RequestMethod.GET)
	public @ResponseBody List<Map<String,Object>> listAttributeByTerm(
			@RequestParam("term") String term,HttpServletRequest request){
		List<Map<String,Object>> list=new ArrayList<>();
		try {
			return masterService.listAttributesByTerm(term);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value="/getselectedattribute", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getSelectedAttribute(
			@RequestParam("id") String attributeId,HttpServletRequest request){
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			Attribute attribute= masterService.getAttributeById(Long.parseLong(attributeId));
			return attribute.toBasicMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	

}
