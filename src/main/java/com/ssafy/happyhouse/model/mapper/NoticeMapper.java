package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.NoticeParameterDto;

public interface NoticeMapper {

	public int writeArticle(NoticeDto noticeDto) throws SQLException;

	public List<NoticeDto> listArticle(NoticeParameterDto noticeParameterDto) throws SQLException;

	public int getTotalCount(NoticeParameterDto noticeParameterDto) throws SQLException;

	public NoticeDto getArticle(int articleno) throws SQLException;

	public void updateHit(int articleno) throws SQLException;

	public int modifyArticle(NoticeDto noticeDto) throws SQLException;

	public int deleteArticle(int articleno) throws SQLException;
}
