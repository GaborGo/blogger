package com.blgr.blogapp.controller.blogger;

import com.blgr.blogapp.domain.BlogEntry;
import com.blgr.blogapp.domain.User;
import com.blgr.blogapp.repository.UserRepository;
import com.blgr.blogapp.sec.UserPrincipalDetailsService;
import com.blgr.blogapp.service.BlogEntryService;
import com.blgr.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("blogger")
public class WelcomeController {

    private UserService userService;
    private BlogEntryService blogEntryService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBlogEntryService(BlogEntryService blogEntryService){ this.blogEntryService  = blogEntryService;}

    @Secured("ROLE_USER")
    @GetMapping("welcome")
    public String welcome(Model model, Principal principal){
        model.addAttribute("blogger", userService.getBloggerByUsername(principal.getName()));
        return "blogger/welcome";
    }

    @GetMapping("newBlogEntry")
    public String addBlogEntryForm(Model model){
       model.addAttribute("blogEntry", new BlogEntry());
       return "blogger/newBlogEntry";
    }

    @RequestMapping(value = "newBlogEntry", method = RequestMethod.POST)
    public String submitBlogEntry(@ModelAttribute BlogEntry blogEntry, Principal principal){
        User currentUser = userService.getBloggerByUsername(principal.getName());
        blogEntry.setUser(currentUser);
        blogEntry.setPosted(new Date());
        currentUser.getStories().add(blogEntry);
        blogEntryService.saveBlogEntry(blogEntry);
        userService.saveUser(currentUser);
        return "redirect:/blogger/welcome";
    }

    @Transactional
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteBlogEntry(@PathVariable ("id") Long id, RedirectAttributes redirectAttribute){
        blogEntryService.deleteBlogEntry(id);
        redirectAttribute.addFlashAttribute("deleteSuccess","Post succesfully deleted.");
        return "redirect:../../blogger/welcome";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("blogEntry", blogEntryService.getSpecificEntry(id));
        return "blogger/editBlogEntry";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid BlogEntry blogEntry,
                             BindingResult result, Principal principal, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            blogEntry.setId(id);
            return "blogger/editBlogEntry";
        }
        blogEntryService.editBlogEntry
                (blogEntry.getTitle(),blogEntry.getContent(),userService.getBloggerByUsername(principal.getName()).getId(),id);
        redirectAttributes.addFlashAttribute("updateSuccess","Story updated succesfully");
        return "redirect:../../blogger/welcome";
    }
}
