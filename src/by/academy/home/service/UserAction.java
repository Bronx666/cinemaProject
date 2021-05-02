package by.academy.home.service;

import by.academy.home.model.User;

public class UserAction extends AllAction{

    public void actionWithIvent (User user){
        showAllFilms(user);
    }
    public void actionWithTicket (User user){
        getUserTicket(user);
    }


}
