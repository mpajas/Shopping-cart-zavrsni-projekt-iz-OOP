package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountDecorator20 extends ProductDecorator {

    Product product;

    public DiscountDecorator20(Product product)
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
        return new BigDecimal(product.getPrice()*0.8).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
