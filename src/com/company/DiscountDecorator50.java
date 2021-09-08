package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountDecorator50 extends ProductDecorator {

    Product product;

    public DiscountDecorator50(Product product)
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
        return new BigDecimal(product.getPrice()*0.5).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
