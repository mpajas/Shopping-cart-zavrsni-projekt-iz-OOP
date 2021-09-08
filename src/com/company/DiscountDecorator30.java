package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountDecorator30 extends ProductDecorator {

    Product product;

    public DiscountDecorator30(Product product)
    {
        this.product = product;
    }

    public String getName() {
        return product.getName();
    }


    public String getOrigin() {
        return product.getOrigin();
    }

    public String getExpiry() {
        return product.getExpiry();
    }

    public Double getPrice()
    {
        return new BigDecimal(product.getPrice()*0.7).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
