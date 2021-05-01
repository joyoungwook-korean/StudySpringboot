package com.jojoldu.book.springboot.web.domain.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void call_write(){
        String title = "게시글 저장";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
        .title(title).content(content).author("author").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getId()).isEqualTo(1L);
        assertThat(posts.getTitle()).isEqualTo(title);
    }

    @Test
    public void BaseTimeEntity_add(){
        LocalDateTime now = LocalDateTime.of(2021,5,1,0,0,0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        List<Posts> postsLists = postsRepository.findAll();

        Posts posts = postsLists.get(0);

        System.out.println(">>>>>>>>>>>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate" +
                "=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}
