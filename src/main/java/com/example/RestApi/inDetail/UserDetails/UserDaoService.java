package com.example.RestApi.inDetail.UserDetails;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User("Ayon",24,"Bogura"));
        users.add(new User("zenin" , 29 , "Bogura"));
        users.add(new User("nihal",24,"New York"));
    }

    public List<User> getAllUsers(){
        return this.users;
    }

    public User getParticularUsers(String name){

        for( User user : users){
            if(user.getName().equalsIgnoreCase(name))
                return user;
        }
        return null;
    }

    public void addUser(User user){
        users.add(user);
    }

    public void deleteByName(String name){
        User user = null;
        for(User temp : users){
            if(temp.getName().equalsIgnoreCase(name))
                user = temp;
        }
        users.remove(user);
    }
}
