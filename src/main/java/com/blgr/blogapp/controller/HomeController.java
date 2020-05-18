package com.blgr.blogapp.controller;

import com.blgr.blogapp.domain.User;
import com.blgr.blogapp.service.BlogEntryService;
import com.blgr.blogapp.service.EmailService;
import com.blgr.blogapp.service.SearchService;
import com.blgr.blogapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class HomeController {

    private BlogEntryService blogEntryService;
    private UserService userService;
    private EmailService emailService;

    private SearchService searchService;
@Autowired
    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private  String search;


    public void setSearch(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setBlogEntryService(BlogEntryService blogEntryService) {
        this.blogEntryService = blogEntryService;
    }

    @Autowired
    public void setEmailService(EmailService emailService){this.emailService = emailService;}

    @RequestMapping("/")
    public String stories(Model model, Locale locale) {
        model.addAttribute("pageTitle", "Blogger!");
        model.addAttribute("entries", blogEntryService.findLatestBlogEntries());
        System.out.println(String.format("Request received. Language: %s, Country: %s %n", locale.getLanguage(),
                locale.getDisplayCountry()));
        return "blogEntries";
    }

    @RequestMapping("/search")
    public String resultsQuery(@RequestParam("query") String query,  Model model) {
        model.addAttribute("results", searchService.getSearchedEntries(query));
        model.addAttribute("query", query);

        return "search/results";
    }

    @RequestMapping("/title/{title}")
    public String searchForTitle(@PathVariable(value = "title") Long title, Model model) throws Exception {
        if (title == null)
            throw new Exception("No story with that title!");
        model.addAttribute("blogEntry", blogEntryService.getSpecificEntry(title));
        return "title/blogEntry";
    }

    @RequestMapping("/title/id/{entryId}")
    public String searchForTitlebuId(@PathVariable(value = "entryId") Long id, Model model) throws Exception {
        model.addAttribute("blogEntry", blogEntryService.getSpecificEntry(id));
        return "title/blogEntry";
    }

    @RequestMapping("/blogs/{blogger}")
    public String searchForUser(@PathVariable(value = "blogger") String username, Model model) throws Exception {
        if (username == null)
            throw new Exception("No story from that blogger!");
        model.addAttribute("userEntries", blogEntryService.getSpecificEntryByUser(username));
        return "blogs/blogEntry";
    }

    @RequestMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

@RequestMapping(value = "/reg", method = RequestMethod.POST) // or @Postmappinng("/reg")
    public String registerSubmit(@ModelAttribute User user, RedirectAttributes attributes){
        logger.info("New user!");
        logger.debug(user.getUsername());
        logger.debug(user.getPassword());
        String info = userService.registerUser(user);
        if(info.equals("wrongUsername"))
            attributes.addFlashAttribute("regInfo", "Username is already taken!");
        else  if(info.equals("wrongEmail"))
            attributes.addFlashAttribute("regInfo", "There is already an user registered with this email address!");
        else  if(info.equals("ok"))
            attributes.addFlashAttribute("regInfo", "Registration success!");
        emailService.sendMessage(user.getEmail(), user.getUsername());
         return "redirect:/login";
}


//    @ExceptionHandler(Exception.class)
//    public String exceptionHandler(HttpServletRequest rA, Exception ex, Model model) {
//        model.addAttribute("errMessage", ex.getMessage());
//        return "/error";
//    }




}

