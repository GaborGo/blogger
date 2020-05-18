//package com.blgr.blogapp.repository;
//
//import com.blgr.blogapp.domain.BlogEntry;
//import com.blgr.blogapp.domain.User;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class DbInit implements CommandLineRunner {
//
//
//    private PasswordEncoder passwordEncoder;
//    private UserRepository userRepository;
//    private BlogEntryRepository blogEntryRepository;
//
//    public DbInit(PasswordEncoder passwordEncoder, UserRepository userRepository, BlogEntryRepository blogEntryRepository) {
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
//        this.blogEntryRepository = blogEntryRepository;
//    }
//
//    @Override
//    public void run(String... args)   {
//     this.blogEntryRepository.deleteAll();
//        this.userRepository.deleteAll();
//        User usher = new User("usher", passwordEncoder.encode("pass"), "USER", "user@user.com",true);
//        usher.setId((long)1);
//        User ablogger = new User("admin", passwordEncoder.encode("admin"), "ADMIN,USER", "admin@admin.com",true);
//        ablogger.setId((long)2);
//        BlogEntry blogEntry = new BlogEntry("userTitle", "the lady daddy", new Date(), usher);
//        blogEntry.setId((long)1);
//        BlogEntry ablogEntry = new BlogEntry("adminTitle", "the BIG lady daddy", new Date(), ablogger);
//        ablogEntry.setId((long)2);
//
//        userRepository.save(usher);
//        userRepository.save(ablogger);
//        blogEntryRepository.save(blogEntry);
//        blogEntryRepository.save(ablogEntry);
//
//    }
//}



