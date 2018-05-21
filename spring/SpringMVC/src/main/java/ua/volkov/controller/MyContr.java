package ua.volkov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyContr {
    @RequestMapping(value = "/home")
    public String param(Model model){
        model.addAttribute("message" , "world!\nAzazaza");
        return "homeWithParam";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writeGet(){
        return "write";
    }

    @RequestMapping(value = "/write" ,method = RequestMethod.POST)
    public String showPost(@RequestParam String name, Model model){
        model.addAttribute("name", name);
        return "show";
    }


}
