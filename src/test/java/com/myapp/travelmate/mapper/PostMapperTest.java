package com.myapp.travelmate.mapper;

import com.myapp.travelmate.model.Post;
import com.myapp.travelmate.model.User;
import com.myapp.travelmate.viewmodel.PostViewModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
class PostMapperTest {

    private static Post post;
    private static PostViewModel mappedPostViewModel;

    @BeforeAll
    static void setUp() {
        User author = new User.Builder()
                .name("author name")
                .build();

        post = new Post.Builder()
                .title("title")
                .description("description")
                .travelDateFrom(LocalDateTime.now())
                .travelDateTo(LocalDateTime.now())
                .budgetValueFrom(10)
                .budgetValueTo(100)
                .author(author)
                .countries(Collections.singleton("country"))
                .build();

        mappedPostViewModel = PostMapper.INSTANCE.postViewModel(post);
    }

    @Test
    void shouldMapAuthorToPostViewModel() {
        assertThat(post.getAuthor()).isEqualTo(mappedPostViewModel.getAuthor());
    }

    @Test
    void shouldMapTitleToPostViewModel() {
        assertThat(post.getTitle()).isEqualTo(mappedPostViewModel.getTitle());
    }

    @Test
    void shouldMapDescriptionToPostViewModel() {
        assertThat(post.getDescription()).isEqualTo(mappedPostViewModel.getDescription());
    }

    @Test
    void shouldMapTravelDateFromToPostViewModel() {
        assertThat(post.getTravelDateFrom()).isEqualTo(mappedPostViewModel.getTravelDateFrom());
    }

    @Test
    void shouldMapTravelDateToToPostViewModel() {
        assertThat(post.getTravelDateTo()).isEqualTo(mappedPostViewModel.getTravelDateTo());
    }

    @Test
    void shouldMapBudgetValueFromToPostViewModel() {
        assertThat(post.getBudgetValueFrom()).isEqualTo(mappedPostViewModel.getBudgetValueFrom());
    }

    @Test
    void shouldMapBudgetValueToToPostViewModel() {
        assertThat(post.getBudgetValueTo()).isEqualTo(mappedPostViewModel.getBudgetValueTo());
    }

    @Test
    void shouldMapCountriesToPostViewModel() {
        assertThat(post.getCountries()).isEqualTo(mappedPostViewModel.getCountries());
    }
}

