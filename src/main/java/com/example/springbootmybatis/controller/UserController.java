package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.pojo.User;
import com.example.springbootmybatis.pojo.query.UserQuery;
import com.example.springbootmybatis.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model, UserQuery userQuery){
        PageInfo<User> userPageInfo = userService.listUserByName(userQuery);
        model.addAttribute("page", userPageInfo);
        return "index";
    }

    @PostMapping("/")
    public String listUserByName(Model model, UserQuery userQuery){
        PageInfo<User> userPageInfo = userService.listUserByName(userQuery);
        model.addAttribute("page", userPageInfo);
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes attributes){
        boolean success = userService.deleteUserById(id);
        if (success){
            attributes.addFlashAttribute("message", "Delete Success!");
            return "redirect:/";
        }else {
            attributes.addFlashAttribute("message", "Failed to delete the item!");
            return "redirect:/";
        }
    }

    @GetMapping("/edit/{id}")
    public String toEdit(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.queryUserById(id));
        return "editUser";
    }

    @PostMapping("/edit")
    public String edit(User user, RedirectAttributes attributes, UserQuery userQuery){
        userQuery.setName(user.getName());
        PageInfo<User> userPageInfo = userService.listUserByName(userQuery);
        if (userPageInfo.getSize() == 0){
            boolean success = userService.updateUser(user);
            if (success){
                attributes.addFlashAttribute("message", "Update Success!");
                return "redirect:/";
            }else {
                attributes.addFlashAttribute("message", "Failed to update the item!");
                return "redirect:/";
            }
        }else {
            attributes.addFlashAttribute("message", "Duplicate username!");
            return "redirect:/edit/" + user.getId();
        }
    }

}
