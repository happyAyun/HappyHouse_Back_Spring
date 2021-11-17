package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.QnaDto;
import com.ssafy.happyhouse.model.QnaParameterDto;

public interface QnaMapper {

	public int writeArticle(QnaDto qnaDto) throws SQLException;

	public List<QnaDto> listArticle(QnaParameterDto qnaParameterDto) throws SQLException;

	public int getTotalCount(QnaParameterDto qnaParameterDto) throws SQLException;

	public QnaDto getArticle(int articleno) throws SQLException;

	public void updateHit(int articleno) throws SQLException;

	public int modifyArticle(QnaDto qnaDto) throws SQLException;

	public void deleteMemo(int articleno) throws SQLException;

	public int deleteArticle(int articleno) throws SQLException;

}