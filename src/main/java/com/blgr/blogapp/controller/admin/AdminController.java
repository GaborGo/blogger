package com.blgr.blogapp.controller.admin;

import com.blgr.blogapp.domain.User;
import com.blgr.blogapp.service.BlogEntryService;
import com.blgr.blogapp.service.SearchService;
import com.blgr.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin")
public class AdminController {

    private UserService userService;
    private BlogEntryService blogEntryService;
    private SearchService searchService;

    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    @Autowired
    public void setBlogEntryService(BlogEntryService blogEntryService) {
        this.blogEntryService = blogEntryService;
    }

    @Autowired
    public void setSearchService(SearchService searchService){ this.searchService = searchService; }

    @Secured("ROLE_ADMIN")
    @GetMapping("welcomeadmin")
    public String admin(Model model){
        model.addAttribute("stories", blogEntryService.getAllEntries());
        model.addAttribute("bloggers", userService.getAllBloggers());
        return "admin/welcomeadmin";
    }

    @Transactional
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteBlogger(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("deleteSuccess", "Story deleted succesfully!");
        blogEntryService.deleteBlogEntry(id);
        return "redirect:../../admin/welcomeadmin";
    }

    @Transactional
    @RequestMapping(value = "blockUser/{id}", method = RequestMethod.GET)
    public String blockBlogger(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        User user = userService.getUserById(id);

        if(user.isEnabled()==true){
      user.setEnabled(false);
        redirectAttributes.addFlashAttribute("blockUser", user.getUsername()+"'s account set to inactive!");}
        else {
            user.setEnabled(true);
        redirectAttributes.addFlashAttribute("blockUser", user.getUsername() + "'s account set to active!");}

        return "redirect:../../admin/welcomeadmin";
    }

    @RequestMapping("search")
    public String indexWithQuery(@RequestParam("query") String query, @RequestParam("field") String field, Model model) {
        if (field.equals("user"))
        model.addAttribute("results", searchService.searchByUsername(query));
        else if(field.equals("title"))
            model.addAttribute("results", searchService.searchByTitle(query));
        else if(field.equals("content"))
            model.addAttribute("results", searchService.searchByContent(query));
        model.addAttribute("stories", blogEntryService.getAllEntries());
        model.addAttribute("bloggers", userService.getAllBloggers());
        model.addAttribute("field", field);
        model.addAttribute("query", query);
        return "admin/welcomeAdmin";
    }

    }