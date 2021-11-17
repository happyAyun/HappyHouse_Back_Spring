package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.ReplyDto;

public interface ReplyMapper {
	List<ReplyDto> listReply(int articleno) throws Exception;

	int modifyArticle(ReplyDto replyDto) throws Exception;

	int deleteArticle(int id) throws Exception;

	int writeArticle(ReplyDto replyDto) throws Exception;
}
