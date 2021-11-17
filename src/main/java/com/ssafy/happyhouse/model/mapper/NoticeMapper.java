package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.NoticeDto;

public interface NoticeMapper {

	void registerArticle(NoticeDto noticeDto) throws Exception;

	void registerFile(NoticeDto noticeDto) throws Exception;

	List<NoticeDto> listArticle(Map<String, Object> param) throws Exception;

	NoticeDto getArticle(int articleNo) throws Exception;

	void updateArticle(NoticeDto noticeDto) throws Exception;

	void deleteArticle(int articleNo) throws Exception;

	int getTotalCount(Map<String, String> map) throws Exception;
}
