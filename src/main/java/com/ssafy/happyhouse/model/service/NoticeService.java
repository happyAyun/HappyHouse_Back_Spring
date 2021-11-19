package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.NoticeParameterDto;
import com.ssafy.util.PageNavigation;

public interface NoticeService {

	public boolean writeArticle(NoticeDto noticeDto) throws Exception;

	public List<NoticeDto> listArticle(NoticeParameterDto noticeParameterDto) throws Exception;

	public PageNavigation makePageNavigation(NoticeParameterDto noticeParameterDto) throws Exception;

	public NoticeDto getArticle(int articleno) throws Exception;

	public void updateHit(int articleno) throws Exception;

	public boolean modifyArticle(NoticeDto noticeDto) throws Exception;

	public boolean deleteArticle(int articleno) throws Exception;

}
