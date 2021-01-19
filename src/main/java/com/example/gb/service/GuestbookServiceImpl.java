package com.example.gb.service;

import com.example.gb.dto.GuestbookDTO;
import com.example.gb.entity.Guestbook;
import com.example.gb.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor  //의존성 자동주
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository repository;  //반드시 final 왜?

    @Override
    public Long register(GuestbookDTO dto) {

        log.info("DTO ------------------");
        log.info(dto);

        Guestbook entity = dtoToEntity(dto);

        log.info(entity);
        // 여기까지 하고 테스트 해본 후, 문제가 없을 때 넘어간다.

        repository.save(entity);

        return entity.getGno();
    }
}
