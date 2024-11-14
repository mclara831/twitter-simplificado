package com.mariaclara.spring_security.services;

import com.mariaclara.spring_security.entities.Tweet;
import com.mariaclara.spring_security.entities.User;
import com.mariaclara.spring_security.entities.UserRole;
import com.mariaclara.spring_security.entities.dtos.FeedDTO;
import com.mariaclara.spring_security.entities.dtos.FeedItemDTO;
import com.mariaclara.spring_security.entities.dtos.TweetRequestDTO;
import com.mariaclara.spring_security.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class TweetService {
    
    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserService userService;

    public Page<FeedItemDTO> getAllTweets(int page, int pageSize) {
        var tweets = tweetRepository.findAll(
                        PageRequest.of(page, pageSize, Sort.Direction.DESC, "creationTimeStamp"))
                        .map(tweet -> new FeedItemDTO(tweet.getUser().getUsername(), tweet.getContent()));
        return tweets;
    }

    public void deleteTweet(Long tweetId, JwtAuthenticationToken token) {
        User user = userService.findUserById(UUID.fromString(token.getName()));
        Tweet tweet = tweetRepository.findById(tweetId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        boolean isAdmin = user.getRoles()
                        .stream()
                        .anyMatch(role -> role.getName()
                                        .equalsIgnoreCase(UserRole.Values.ADMIN.name())
                                );

        if (isAdmin || tweet.getUser().getUserId().equals(user.getUserId())) {
            tweetRepository.delete(tweet);
        }
    }

    public FeedItemDTO createNewTweet(TweetRequestDTO tweet, JwtAuthenticationToken token) {
        User user = userService.findUserById(UUID.fromString(token.getName()));
        Tweet newTweet = new Tweet(tweet.content(), user);
        tweetRepository.save(newTweet);
        return new FeedItemDTO(user.getUsername(), tweet.content());
    }
}
