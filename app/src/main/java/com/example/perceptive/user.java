package com.example.perceptive;

public class user {
    int id;
    String name;
    String password;
    String email;
    int phone;

    user(int id,String name,String email,String password,int phone){
        this.id=id;
        this.name=name;
        this.password=password;
        this.email=email;
        this.phone=phone;
    }

    void register(int id,String name,String email,String password,int phone){
        this.id=id;
        this.name=name;
        this.password=password;
        this.email=email;
        this.phone=phone;
    }


}
