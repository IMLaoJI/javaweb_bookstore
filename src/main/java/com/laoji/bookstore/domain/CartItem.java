package com.laoji.bookstore.domain;

public class CartItem {

    private Product product;
    private int buyNum;
    private double subtotal; //商品小计  单价*购买数量

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public double getSubtotal() {
        this.subtotal = this.buyNum*this.product.getShop_price();
        return this.subtotal;
    }
    //public void setSubtotal(double subtotal) {
    //	this.subtotal = subtotal;
    //}


}
