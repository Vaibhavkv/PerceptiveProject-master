package com.example.perceptive;

public class user {
    String id;
    String name;
    String password;
    String email;
    String phone;
    static int count=0;

    user(){
        count++;
    }

    user(String id,String name,String email,String password,String phone){
        count++;
        this.id=id;
        this.name=name;
        this.password=password;
        this.email=email;
        this.phone=phone;
    }

    void register(String id,String name,String email,String password,String phone){
        this.id=id;
        this.name=name;
        this.password=password;
        this.email=email;
        this.phone=phone;
    }


}
