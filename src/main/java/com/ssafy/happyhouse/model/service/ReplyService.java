package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.ReplyDto;

public interface ReplyService {
	List<ReplyDto> listReply(int articleno) throws Exception;

	boolean modifyArticle(ReplyDto replyDto) throws Exception;

	boolean deleteArticle(int id) throws Exception;

	boolean writeArticle(ReplyDto replyDto) throws Exception;
}
