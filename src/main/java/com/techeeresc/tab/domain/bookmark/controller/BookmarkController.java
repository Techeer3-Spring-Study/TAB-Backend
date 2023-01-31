package com.techeeresc.tab.domain.bookmark.controller;
import com.techeeresc.tab.domain.bookmark.dto.mapper.BookmarkMapper;
import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkCreateRequestDto;
import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkPagingDto;
import com.techeeresc.tab.domain.bookmark.dto.response.BookmarkResponseDto;
import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import com.techeeresc.tab.domain.bookmark.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmark")
public class BookmarkController {
  private final BookmarkService BOOKMARK_SERVICE;
  private final BookmarkMapper BOOKMARK_MAPPER;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookmarkResponseDto createBookmark(
      @RequestBody BookmarkCreateRequestDto bookmarkCreateRequestDto) {
    Bookmark insertBookmarkResult = BOOKMARK_SERVICE.save(bookmarkCreateRequestDto);
    return BOOKMARK_MAPPER.getDataFromEntity(insertBookmarkResult);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookmarkResponseDto> findBookmark(@PathVariable Long id) {
    Bookmark findBookmarkResult = BOOKMARK_SERVICE.findBookmarkById(id);
    return new ResponseEntity<>(
        BOOKMARK_MAPPER.getDataFromEntity(findBookmarkResult), HttpStatus.OK);
  }

  @DeleteMapping("/{id}") // TODO: 해당 부분도 반환타입이 리스트가 아님 확인
  @ResponseStatus(HttpStatus.OK)
  public Long BookmarkDelete(@PathVariable Long id) {
    BOOKMARK_SERVICE.deleteBookmark(id);
    return id;
  }

  @GetMapping
  public ResponseEntity<PageImpl<Bookmark>> findAllBookmark(BookmarkPagingDto bookmarkPagingDTO) {
    Pageable pageable = bookmarkPagingDTO.of();
    PageImpl<Bookmark> bookmarks = BOOKMARK_SERVICE.findAllBookmark(pageable);
    return new ResponseEntity<>(bookmarks, HttpStatus.OK);
  }

  @GetMapping("/sever")
  public static void openChrome() throws MalformedURLException {

    URL address = new URL("http://localhost:4444");
    ChromeOptions options = new ChromeOptions();
    options.setHeadless(true);
    options.addArguments("--disable-gpu", "--disable-dev-shm-usage");
    options.addArguments("--headless");


    RemoteWebDriver driver = new RemoteWebDriver(address, options);
    try {
      driver.get("https://career.programmers.co.kr/");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("from")));
      System.out.println(driver.getTitle());
    } finally {
      driver.quit();
    }
  }
}
