package com.blgr.blogapp.service;


import com.blgr.blogapp.domain.BlogEntry;
import com.blgr.blogapp.domain.User;
import com.blgr.blogapp.repository.BlogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogEntryService {

    private BlogEntryRepository blogEntryRepository;

    @Autowired
    public void setBlogEntryRepository(BlogEntryRepository blogEntryRepository) {
        this.blogEntryRepository = blogEntryRepository;
    }

    public List<BlogEntry> getAllEntries() {
        return blogEntryRepository.findAll();
    }

    public BlogEntry getFirstEntry(){
        return blogEntryRepository.findFirstByOrderByPostedDesc();
    }

    public BlogEntry getSpecificEntry(String title) { return blogEntryRepository.findByTitle(title); }

    public BlogEntry getSpecificEntry(Long id){ return blogEntryRepository.findBlogEntryById(id);}

    public List<BlogEntry> getSpecificEntryByUser(String username) {return blogEntryRepository.findAllByUserUsername(username);}

    public void saveBlogEntry(BlogEntry blogEntry){ blogEntryRepository.save(blogEntry); }

    public void deleteBlogEntry(Long id){blogEntryRepository.deleteById(id);}

    public void editBlogEntry(String title, String content, Long user_Id, Long id){ blogEntryRepository.updateTitleAndContent(title,content,user_Id,id);}

   public List<BlogEntry> findLatestBlogEntries(){
       List<BlogEntry> latestTen = new ArrayList<>();
        List<BlogEntry> all = blogEntryRepository.findAllByOrderByIdDesc();
        if(all.size()<=10)
            return all;

        for(int i = 0; i<10; i++)
            latestTen.add(all.get(i));

        return latestTen;
   }

}

