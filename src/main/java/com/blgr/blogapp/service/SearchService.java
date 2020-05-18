package com.blgr.blogapp.service;

import com.blgr.blogapp.domain.BlogEntry;
import com.blgr.blogapp.repository.BlogEntryRepository;
import com.blgr.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private BlogEntryRepository blogEntryRepository;
    private UserRepository userRepository;

    public List<BlogEntry> getSearchedEntries(String search){
//        List<BlogEntry> temp = new ArrayList<>();
//        temp.addAll(blogEntryRepository.findAll());
//     for(BlogEntry blogEntry : temp){
//         if(!blogEntry.getTitle().contains(search) && !blogEntry.getContent().contains(search))
//             temp.remove(blogEntry);
//     }
//     System.out.println("Searching for: " + search + " found so far: " + temp);
     //   return temp;
     //    System.out.println("getsearchentries Searching for: "+search + blogEntryRepository.findAllByTitleContainingOrContentContaining(search, search,search));
        return blogEntryRepository.findAllByTitleContainingOrContentContainingOrUserUsernameContaining(search, search,search);
    }


//    public List<BlogEntry> findEntriesByBloggerOrTitle(String search){
//        List<BlogEntry> temp = new ArrayList<>();
//        temp.addAll(blogEntryRepository.findAllByUser_UsernameOrderByPostedDesc(search));
//        temp.addAll(blogEntryRepository.findAllByTitleOrderByPostedDesc(search));
//        List<BlogEntry> searchResults = new ArrayList<>();
//        for (BlogEntry blogEntry: temp) {
//            if(!searchResults.contains(blogEntry))
//                searchResults.add(blogEntry);
//        }
//        return searchResults;
//    }


@Autowired
    public void setBlogEntryRepository(BlogEntryRepository blogEntryRepository) {
        this.blogEntryRepository = blogEntryRepository;
    }

@Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<BlogEntry> searchByUsername(String username){return blogEntryRepository.findAllByUserUsernameContaining(username);}

    public List<BlogEntry> searchByTitle(String title){ return blogEntryRepository.findAllByTitleContaining(title);}

    public List<BlogEntry> searchByContent(String content){ return blogEntryRepository.findAllByContentContaining(content);}


}
