package com.blgr.blogapp.repository;


import com.blgr.blogapp.domain.BlogEntry;
import com.blgr.blogapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BlogEntryRepository extends JpaRepository<BlogEntry, Long> {

    List<BlogEntry> findAll();

    List<BlogEntry> findAllByOrderByIdDesc();

    BlogEntry findFirstByOrderByPostedDesc();

    BlogEntry findByTitle(String title);

    BlogEntry findBlogEntryById(Long id);

    List<BlogEntry>findAllByTitleContainingOrContentContainingOrUserUsernameContaining(String title, String content,String username);

    List<BlogEntry>findAllByUserUsername(String username);

    void deleteById(Long id);

    List<BlogEntry>findAllByUserUsernameContaining(String username);
    List<BlogEntry>findAllByTitleContaining(String username);
    List<BlogEntry>findAllByContentContaining(String content);

     @Modifying
     @Transactional
    @Query(value = "update blog_Entry set title=?1,content=?2,user_id=?3 where id=?4", nativeQuery = true)
    void updateTitleAndContent(@Param("title")String title, @Param("content")String content, @Param("userId")Long user_id, @Param("id")Long id);

}
