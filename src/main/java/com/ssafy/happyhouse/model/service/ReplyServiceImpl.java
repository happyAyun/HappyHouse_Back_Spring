package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.ReplyDto;
import com.ssafy.happyhouse.model.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ReplyDto> listReply(int articleno) throws Exception {
		return sqlSession.getMapper(ReplyMapper.class).listReply(articleno);
	}

	@Override
	public boolean modifyArticle(ReplyDto replyDto) throws Exception {
		return sqlSession.getMapper(ReplyMapper.class).modifyArticle(replyDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticle(int id) throws Exception {
		return sqlSession.getMapper(ReplyMapper.class).deleteArticle(id) == 1;
	}

	@Override
	@Transactional
	public boolean writeArticle(ReplyDto replyDto) throws Exception {
		return sqlSession.getMapper(ReplyMapper.class).writeArticle(replyDto) == 1;
	}

}
