package com.FoodDeliveryApplication.CommanUtil;

import java.util.regex.Pattern;

public class ValidationClass {
    // Define patterns for validation
    public static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_.@\\- '()]*$");
    public static final Pattern ADDRESS_PATTERN = Pattern.compile("^[a-zA-Z0-9 ,.'-]+$");
    public static final Pattern CUISINE_PATTERN = Pattern.compile("^[a-zA-Z0-9- ]+$");
    public static final Pattern RATING_PATTERN = Pattern.compile("^[0-5](\\.[0-9])?$");
    public static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10}$");
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
    public static final Pattern CITY_PATTERN = Pattern.compile("^[a-zA-Z]+$");
    public static final Pattern PINCODE_PATTERN = Pattern.compile("^[0-9]{6}$");
    public static final Pattern MENU_ITEM_PATTERN = Pattern.compile("^[a-zA-Z0-9_.@\\- '()]*$");
    public static final Pattern QUANTITY_PATTERN = Pattern.compile("^[0-9]+$");
    public static final Pattern PRICE_PATTERN = Pattern.compile("^[0-9]+(\\.[0-9]{1,2})?$");
    public static final Pattern ORDER_STATUS_PATTERN = Pattern.compile("^(Pending|Processing|Completed|Cancelled)$");
    public static final Pattern ORDER_TYPE_PATTERN = Pattern.compile("^(Dine-in|Delivery)$");
    public static final Pattern DELIVERY_CHARGE_PATTERN = Pattern.compile("^[0-9]+(\\.[0-9]{1,2})?$");
    public static final Pattern DESCRIPTION_PATTERN = Pattern.compile("^[a-zA-Z0-9 ,.:'\"\\-()!]*$");
}
