package com.janaka.kitchenslk.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.servlet.ModelAndView;

import com.janaka.kitchenslk.entity.Attribute;
import com.janaka.kitchenslk.entity.Item;
import com.janaka.kitchenslk.entity.ItemAttribute;
import com.janaka.kitchenslk.entity.ItemAttributeValue;
import com.janaka.kitchenslk.enums.Status;
import com.janaka.kitchenslk.service.CommonService;
import com.janaka.kitchenslk.service.ItemService;
import com.janaka.kitchenslk.springeditor.AttributeEditor;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 14, 2013 - 12:04:13 AM
 * Project	: kitchenslk
 */
@Controller("itemController")
@RequestMapping(value="/items")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CommonService commonService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Attribute.class, new AttributeEditor(commonService));	
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
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
			map.put("attributes", itemService.listAllAttributes(Status.ACTIVE));
			map.put("item", new Item());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("addItem",map);
	}
	
	
	@RequestMapping(value="/createitem", method=RequestMethod.POST)
	public String crateItem(HttpServletRequest request, @ModelAttribute("item") Item item){
		System.out.println("itemName"+item.getItemName());	
		System.out.println(item.getItemAttributes());
		for (ItemAttribute itemAttribute : item.getItemAttributes()) {
			System.out.println(itemAttribute.getItemAttributeValues());
			for (ItemAttributeValue itemAttributeValue : itemAttribute.getItemAttributeValues()) {
				System.out.println(itemAttributeValue.getValue());
				System.out.println(itemAttributeValue.getItemAttributeValues());
			}
		}
		return "redirect:/items.htm";
	}

}
