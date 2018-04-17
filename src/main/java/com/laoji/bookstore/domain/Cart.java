package com.laoji.bookstore.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

	//该购物车中存储的n个购物项
	private Map<String,CartItem> cartItems = new LinkedHashMap<String, CartItem>();

	//商品的总计
	private double total;

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(LinkedHashMap<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Collection<CartItem> getCartItemsValue(){
		return  cartItems.values();
	}
	public double getTotal() {
		total = 0;
		for (Map.Entry<String, CartItem> entry : cartItems.entrySet()) {
            CartItem cartItem = entry.getValue();
            total += cartItem.getSubtotal();
        }
		return total;
	}

    /**
     * 添加商品到购物车
     * @param product
     * @param count
     */
    public void addCart(Product product, int count) {
        if (product == null) {
            return;
        }

        CartItem cartItem = cartItems.get(product.getPid());
        //第一次购买
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setBuyNum(count);
            cartItems.put(product.getPid(), cartItem);
        }else {
            cartItem.setBuyNum(cartItem.getBuyNum() + count);

        }

    }

    public  void removeCart(String id){
        CartItem cartItem = cartItems.remove(id);

    }

    public  void clearCart(){
        cartItems.clear();
    }

	//public void setTotal(double total) {
	//	this.total = total;
	//}
	
	
	
	
}
