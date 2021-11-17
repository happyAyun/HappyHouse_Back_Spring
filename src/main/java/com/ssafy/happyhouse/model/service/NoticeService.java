package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.util.PageNavigation;

public interface NoticeService {

	void registerArticle(NoticeDto noticeDto) throws Exception;

	List<NoticeDto> listArticle(Map<String, String> map) throws Exception;

	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;

	NoticeDto getArticle(int articleNo) throws Exception;

	void updateArticle(NoticeDto noticeDto) throws Exception;

	void deleteArticle(int articleNo) throws Exception;

}
