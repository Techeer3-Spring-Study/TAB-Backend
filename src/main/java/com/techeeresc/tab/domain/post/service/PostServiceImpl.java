package com.techeeresc.tab.domain.post.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.dto.request.PostUpdateRequestDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.entity.QPost;
import com.techeeresc.tab.domain.post.repository.PostQueryDslRepository;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import com.techeeresc.tab.global.exception.customexception.RequestNotFoundException;
import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService, PostQueryDslRepository {
  private final PostRepository POST_REPOSITORY;
  private final PostMapper POST_MAPPER;
  private final JPAQueryFactory JPA_QUERY_FACTORY;
  private final int NULL_SIZE = 0;
  private final AmazonS3 AMAZON_S3;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  @Transactional
  @Override
  public Post insertPost(PostCreateRequestDto postCreateRequestDto, List<MultipartFile> multipartFiles) {
    List<String> fileNames = new ArrayList<>();

    multipartFiles.forEach(
            file -> {
              String fileName = createFileName(file.getOriginalFilename());
              ObjectMetadata objectMetadata = new ObjectMetadata();
              objectMetadata.setContentLength(file.getSize());
              objectMetadata.setContentType(file.getContentType());

              try (InputStream inputStream = file.getInputStream()) {
                AMAZON_S3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

              } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "file upload fail.");
              }

              fileNames.add(fileName);
            }
    );

    return POST_REPOSITORY.save(POST_MAPPER.saveDataToEntity(postCreateRequestDto));
  }

  @Transactional
  @Override
  public Post updatePost(PostUpdateRequestDto postUpdateRequestDto) {
    try {
      Post post = isPostExistedById(postUpdateRequestDto.getId());
      return post.updatePost(postUpdateRequestDto);
    } catch (NullPointerException exception) {
      throw new RequestNotFoundException(
          StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
    }
  }

  @Transactional
  @Override
  public Post increaseLikeNumbers(Long id) {
    try {
      Post post = isPostExistedById(id);
      return post.increaseLikeNumbers(post.getLikeNumbers());
    } catch (NullPointerException exception) {
      throw new RequestNotFoundException(
          StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
    }
  }

  @Transactional
  @Override
  public void deletePost(Long id) {
    try {
      Post post = isPostExistedById(id);
      POST_REPOSITORY.deleteById(post.getId());
    } catch (NullPointerException exception) {
      throw new RequestNotFoundException(
          StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
    }
  }

  @Transactional
  @Override
  public Post findPostByIdAndIncreaseViews(Long id) {
    try {
      Post post = isPostExistedById(id);
      post = increaseViews(post);
      return post;
    } catch (NullPointerException exception) {
      throw new RequestNotFoundException(
          StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
    }
  }

  @Transactional
  @Override
  public List<Post> findByTitleContainsWordWithQueryDsl(String word, Pageable pageable) {
    QPost qPost = QPost.post;

    try {
      List<Post> postSearchResults =
          JPA_QUERY_FACTORY
              .selectFrom(qPost)
              .offset(pageable.getOffset())
              .limit(pageable.getPageSize())
              .where(qPost.title.contains(word))
              .fetch();

      isPostExistedByList(postSearchResults);

      return postSearchResults;
    } catch (NullPointerException exception) {
      throw new RequestNotFoundException(
          StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
    }
  }

  @Transactional
  @Override
  public List<Post> findAllPostListWithQueryDsl(Pageable pageable) {
    QPost qPost = QPost.post;

    try {
      List<Post> posts =
          JPA_QUERY_FACTORY
              .selectFrom(qPost)
              .offset(pageable.getOffset())
              .limit(pageable.getPageSize())
              .fetch();

      isPostExistedByList(posts);
      return posts;
    } catch (NullPointerException exception) {
      throw new RequestNotFoundException(
          StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
    }
  }

  private Post increaseViews(Post post) {
    return post.increaseViews(post.getViews());
  }

  private Post isPostExistedById(Long id) {
    Post post = POST_REPOSITORY.findById(id).orElseThrow(() -> new NullPointerException());

    return post;
  }

  private void isPostExistedByList(List<Post> postSearchResults) {
    if (postSearchResults.size() == NULL_SIZE) {
      throw new NullPointerException();
    }
  }

  private String createFileName(String fileName) {
    return UUID.randomUUID().toString().concat(getFileExtension(fileName));
  }

  private String getFileExtension(String fileName) {
    try {
      return fileName.substring(fileName.lastIndexOf("."));
    } catch (StringIndexOutOfBoundsException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일: " + fileName);
    }
  }
}
