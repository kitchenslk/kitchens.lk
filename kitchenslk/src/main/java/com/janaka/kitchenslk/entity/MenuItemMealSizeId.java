package com.janaka.kitchenslk.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 25, 2013 - 2:17:21 PM
 * Project	: kitchenslk
 */
@Embeddable
public class MenuItemMealSizeId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private MenuItem menuItem;
	private MealSize mealSize;
	
	@ManyToOne()
	@Cascade(CascadeType.MERGE)
	public MenuItem getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	@ManyToOne()
	@Cascade(CascadeType.MERGE)
	public MealSize getMealSize() {
		return mealSize;
	}
	public void setMealSize(MealSize mealSize) {
		this.mealSize = mealSize;
	}
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; 
        MenuItemMealSizeId that = (MenuItemMealSizeId) o; 
        if (menuItem != null ? !menuItem.equals(that.menuItem) : that.menuItem != null) return false;
        if (mealSize != null ? !mealSize.equals(that.mealSize) : that.mealSize != null)
            return false; 
        return true;
    }
 
    public int hashCode() {
        int result;
        result = (menuItem != null ? menuItem.hashCode() : 0);
        result = 31 * result + (mealSize != null ? mealSize.hashCode() : 0);
        return result;
    }
}
