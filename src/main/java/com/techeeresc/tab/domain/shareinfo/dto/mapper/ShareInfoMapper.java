package com.techeeresc.tab.domain.shareinfo.dto.mapper;

import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoCreateRequestDto;
import com.techeeresc.tab.domain.shareinfo.entity.ShareInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ShareInfoMapper {
    public ShareInfo saveDataToEntity(ShareInfoCreateRequestDto shareInfoCreateRequestDto) {  //Entity를 저장하는 방식
        return ShareInfo.builder()
                .title(shareInfoCreateRequestDto.getTitle())
                .content(shareInfoCreateRequestDto.getContent())
                .link(shareInfoCreateRequestDto.getLink())
                .image(shareInfoCreateRequestDto.getImage())
                .hashtag(shareInfoCreateRequestDto.getHashtag())
                .build();
    }
    public ShareInfoCreateRequestDto getDataFromEntity(ShareInfo shareInfo) {    //Entity로 부터 값을 받아오는 방식이다.
        return ShareInfoCreateRequestDto.builder()
                .title(shareInfo.getTitle())
                .content(shareInfo.getContent())
                .link(shareInfo.getLink())
                .image(shareInfo.getImage())
                .hashtag(shareInfo.getHashtag())
                .build();
    }
}
