package com.example.soup;

public class item_info {
    int item_image_id;
    String item_name;
    String item_info;
    //String item_pic;
    public item_info(int item_image_id,String name,String info){
        this.item_image_id = item_image_id;
        this.item_name = name;
        this.item_info = info;
    }
    public String get_item_name(){
        return item_name;
    }
    public String get_item_info(){
        return item_info;
    }
    public int getResourcepath() { return item_image_id;}
    public void set_item_name(String name){
        this.item_name = name;
    }
    public void set_item_info(String info){
        this.item_info = info;
    }
}
