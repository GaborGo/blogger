package com.blgr.blogapp.controller;


import com.blgr.blogapp.domain.BlogEntry;
import com.blgr.blogapp.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("search")
public class ResultsController {

    private HomeController homeController;
    private SearchService searchService;

    @Autowired
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    @Autowired
    public void setSearchService(SearchService searchService){ this.searchService = searchService; }

//    @GetMapping("results")
//        public String showResults(Model model){
//        String search = homeController.getSearch();
//        List<BlogEntry> results = searchService.getSearchedEntries(search);
//            model.addAttribute("results", results);
//            if(results.isEmpty())
//                model.addAttribute("noresult", null);
//            else
//                model.addAttribute("noresult", "result");
//            return "search/results";
//
//    }

//    @RequestMapping("results")
//    public String resultsQuery(@RequestParam("query") String query,  Model model) {
//        List<BlogEntry> results = searchService.getSearchedEntries(query);
//        if(results.isEmpty())
//               model.addAttribute("results", null);
//            else
//        model.addAttribute("results", results);
//        return "search/results";
//    }



}

