package com.myapp.travelmate.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.myapp.travelmate.model.Country;
import com.myapp.travelmate.model.Post;
import com.myapp.travelmate.model.User;
import com.myapp.travelmate.viewmodel.PostViewModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;

@RunWith(SpringRunner.class)
class PostMapperTest {

    private static Post post;
    private static PostViewModel mappedPostViewModel;

    @BeforeAll
    static void setUp() {
        User author = new User();
        author.setName("author name");

        Country country = new Country();
        country.setName("country name");

        post = new Post();
        post.setTitle("title");
        post.setDescription("description");
        post.setTravelDateFrom(LocalDateTime.now());
        post.setTravelDateTo(LocalDateTime.now());
        post.setBudgetValueFrom(10);
        post.setBudgetValueTo(100);
        post.setAuthor(author);
        post.setCountries(Collections.singletonList(country));

        mappedPostViewModel = PostMapper.INSTANCE.postViewModel(post);
    }

    @Test
    void shouldMapTitleToPostViewModel() {
        assertThat(post.getAuthor()).isEqualTo(mappedPostViewModel.getAuthor());
    }

}
