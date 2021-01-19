package com.example.gb.repository;

import com.example.gb.entity.GuestBook;
import com.example.gb.entity.QGuestBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {
    @Autowired
    private GuestbookRepository guestbookRepository;

//    @Test
//    public void insertDummies(){
//        IntStream.rangeClosed(1, 300).forEach(i -> {
//
//            GuestBook guestBook = GuestBook.builder()
//                    .title("Title..." + i)
//                    .content("Content..." + i)
//                    .writer("user" + (i % 10))
//                    .build();
//            System.out.println(guestbookRepository.save(guestBook));
//        });
//    }

    @Test
    public void updateTest() {
        Optional<GuestBook> result = guestbookRepository.findById(300L);

        if(result.isPresent()){

            GuestBook guestBook = result.get();
            guestBook.changeContent("ChangedContent");
            guestBook.changeTitle("ChangedTitle");

            guestbookRepository.save(guestBook);
        }
    }

    @Test
    public void testQuery1(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestBook qGuestBook = QGuestBook.guestBook;  // 동적 처리를 위해 q도메인 클래스를 얻어옴.

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression = qGuestBook.title.contains(keyword);

        builder.and(expression);

        Page<GuestBook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });

    }

    @Test
    public void testQuery2(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestBook qGuestBook = QGuestBook.guestBook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qGuestBook.title.contains(keyword);
        BooleanExpression exContent = qGuestBook.content.contains(keyword);
        BooleanExpression exAll = exTitle.or(exContent);

        builder.and(exAll);

        builder.and(qGuestBook.gno.gt(0L));  // greater than

        Page<GuestBook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(System.out::println);
    }
}
