package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.QnaDto;
import com.ssafy.happyhouse.model.QnaParameterDto;
import com.ssafy.util.PageNavigation;

public interface QnaService {

	public boolean writeArticle(QnaDto qnaDto) throws Exception;

	public List<QnaDto> listArticle(QnaParameterDto qnaParameterDto) throws Exception;

	public PageNavigation makePageNavigation(QnaParameterDto qnaParameterDto) throws Exception;

	public QnaDto getArticle(int articleno) throws Exception;

	public void updateHit(int articleno) throws Exception;

	public boolean modifyArticle(QnaDto qnaDto) throws Exception;

	public boolean deleteArticle(int articleno) throws Exception;
}
