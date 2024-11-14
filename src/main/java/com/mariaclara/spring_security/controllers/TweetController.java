package com.mariaclara.spring_security.controllers;

import com.mariaclara.spring_security.entities.dtos.FeedItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mariaclara.spring_security.entities.Tweet;
import com.mariaclara.spring_security.entities.dtos.FeedDTO;
import com.mariaclara.spring_security.entities.dtos.TweetRequestDTO;
import com.mariaclara.spring_security.services.TweetService;



@RestController
@RequestMapping("tweets")
public class TweetController {
    
    @Autowired
    private TweetService tweetService;

    @GetMapping("/feed")
    public ResponseEntity<FeedDTO> feed(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        var tweets = tweetService.getAllTweets(page, pageSize);
        return ResponseEntity.ok(new FeedDTO(tweets, page, pageSize, tweets.getTotalPages(), tweets.getTotalElements()));
    }

    @PostMapping("/newTweet")
    public ResponseEntity<FeedItemDTO> createNewTweet(@RequestBody TweetRequestDTO data, JwtAuthenticationToken token) {
        var tweet = tweetService.createNewTweet(data, token);
        return ResponseEntity.ok(tweet);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTweet(@PathVariable Long id, JwtAuthenticationToken token) {
        tweetService.deleteTweet(id, token);
        return ResponseEntity.ok("Tweet deletado!");
    }
    
}
