package com.techeeresc.tab.domain.bookmark.repository;

import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//해당 클래스가 데이터 접근계층이나 DAO라는 것을 알리는 annotation
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    /*J paRepository : 기본적인 CRUD가 가능하게 해주는 스프링부트에서
        제공해주는 인터페이스. entity 클래스와 primary key의 type이 들어간다.
     */
}
