package com.whitecloak.training.post.service.impl;

import com.whitecloak.training.common.error.ApiException;
import com.whitecloak.training.post.persistence.entity.PostEntity;
import com.whitecloak.training.post.persistence.repository.PostRepository;
import com.whitecloak.training.post.request.PostForm;
import com.whitecloak.training.post.response.PostResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void showPaginated_shouldReturnEverything_whenGivenANullPageNumber() {
        Integer pageNumber = null;
        Integer size = null;

        service.showPaginated(pageNumber, size);

        then(postRepository)
            .should(atLeastOnce())
            .findAll();
    }

    @Test
    public void showPaginated_shouldPaginate_whenGivenValidParameters() {
        int pageNumber = 1;
        int size = 10;

        Sort sort = new Sort(Sort.Direction.ASC, "title");
        Pageable pageable =  PageRequest.of(pageNumber - 1, size, sort);

        PostEntity entity = mock(PostEntity.class);
        List<PostEntity> entities = Collections.singletonList(entity);
        Page<PostEntity> page = new PageImpl<>(entities);
        when(postRepository.findAll(pageable))
            .thenReturn(page);

        service.showPaginated(pageNumber, size);

        then(postRepository)
            .should(atLeastOnce())
            .findAll(pageable);
    }

    @Test(expected = ApiException.class)
    public void showPaginated_shouldThrowAnException_whenGivenSizeIsLessThanOne() {
        Integer pageNumber = 1;
        Integer size = 0;

        service.showPaginated(pageNumber, size);
    }

    @Test(expected = ApiException.class)
    public void showPaginated_shouldThrownAnException_whenGivenPageNumberIsLessThanOne() {
        Integer pageNumber = 0;
        Integer size = 10;

        service.showPaginated(pageNumber, size);
    }

    @Test
    public void showOne_shouldReturnResource_whenGivenAValidId() {
        Long postId = 1L;

        PostEntity entity = new PostEntity();
        entity.setId(postId);
        when(postRepository.findOneById(postId))
            .thenReturn(Optional.of(entity));

        PostResource result = service.showOne(postId);
        assertEquals(result.getId(), postId);
    }

    @Test
    public void update_shouldUpdatePost_whenGivenIdIsValid() {
        Long postId = 1L;
        String updatedTitle = "Updated Title";
        String updatedDescription = "Update Description";
        PostForm form = new PostForm();
        form.setTitle(updatedTitle);
        form.setDescription(updatedDescription);

        PostEntity entity = new PostEntity();
        entity.setId(postId);
        when(postRepository.findOneById(postId))
            .thenReturn(Optional.of(entity));

        PostEntity updatedEntity = new PostEntity();
        updatedEntity.setId(postId);
        updatedEntity.setTitle(updatedTitle);
        updatedEntity.setDescription(updatedDescription);
        when(postRepository.save(entity))
            .thenReturn(updatedEntity);

        PostResource result = service.update(postId, form);

        assertEquals(result.getId(), postId);
        assertEquals(result.getTitle(), updatedTitle);
        assertEquals(result.getDescription(), updatedDescription);
    }

    @Test(expected = ApiException.class)
    public void update_shouldThrowAnException_whenGivenIdDoesNotExist() {
        Long postId = 1L;
        PostForm form = mock(PostForm.class);
        PostEntity entity = null;

        when(postRepository.findOneById(postId))
            .thenReturn(Optional.ofNullable(entity));

        service.update(postId, form);
    }

    @Test(expected = ApiException.class)
    public void showOne_shouldThrowAnException_whenGivenIdDoesNotExist() {
        Long postId = 1L;
        PostEntity entity = null;
        when(postRepository.findOneById(postId))
            .thenReturn(Optional.ofNullable(entity));

        service.showOne(postId);
    }
}
