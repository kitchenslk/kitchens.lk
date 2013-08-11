package com.janaka.kitchenslk.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.janaka.kitchenslk.commons.CommonFunctions;
import com.janaka.kitchenslk.entity.Attribute;
import com.janaka.kitchenslk.entity.Item;
import com.janaka.kitchenslk.entity.ItemAttribute;
import com.janaka.kitchenslk.entity.ItemAttributeValue;
import com.janaka.kitchenslk.entity.ItemType;
import com.janaka.kitchenslk.entity.SystemUser;
import com.janaka.kitchenslk.enums.Status;
import com.janaka.kitchenslk.service.CommonService;
import com.janaka.kitchenslk.service.ItemService;
import com.janaka.kitchenslk.service.MasterService;
import com.janaka.kitchenslk.springeditor.AttributeEditor;
import com.janaka.kitchenslk.util.SessionUtil;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 14, 2013 - 12:04:13 AM
 * Project	: kitchenslk
 */
@Controller("itemController")
@RequestMapping(value="/merchant")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private MasterService masterService;
	
	@Autowired
	private SessionUtil sessionUtil;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Attribute.class, new AttributeEditor(commonService));	
		SimpleDateFormat format = CommonFunctions.getGlobalSimpleDateFormat();
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}
	
	
	@RequestMapping(value="/items", method=RequestMethod.GET)
	public ModelAndView getItemList(HttpServletRequest request){		
		ModelMap map=new ModelMap();
		try {
			map.put("itemList", itemService.listAllItems(Status.ACTIVE));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("listItems",map);
	}
	
	
	@RequestMapping(value="/createitem", method=RequestMethod.GET)
	public ModelAndView getCreateItem(HttpServletRequest request){
		ModelMap map=new ModelMap();
		try {
			map.put("attributes", masterService.listAllAttributesByStatus(Status.ACTIVE));
			map.put("\\" + "${itemTypeIndex}", 0);
			map.put("\\" + "${itemTypeAttributeValueIndex}", 0);
			map.put("item", new Item());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("addItem",map);
	}
	
	
	@RequestMapping(value="/createitem", method=RequestMethod.POST)
	public String crateItem(HttpServletRequest request, @ModelAttribute("item") Item item){
		try {
			SystemUser systemUser=sessionUtil.getCurentSystemUser();
			itemService.createItem(item, systemUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:items.htm";
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

}
