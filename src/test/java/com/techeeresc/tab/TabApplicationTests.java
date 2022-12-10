package com.techeeresc.tab;

import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import com.techeeresc.tab.domain.bookmark.repository.BookmarkRepository;
import org.hibernate.sql.Insert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.stream.IntStream;


@SpringBootTest
@Transactional // test에서 @Transcational을 사용하면 자동 롤백된다.
class TabApplicationTests {
	@Test
	public void contextLoads() {

	}

}
