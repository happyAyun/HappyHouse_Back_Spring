package com.ssafy.happyhouse.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if (memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}

	@Override
	public boolean updateUser(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).updateUser(memberDto) == 1;
	}

	@Override
	public boolean deleteUser(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).deleteUser(userid) == 1;
	}

	@Override
	public boolean joinUser(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).joinUser(memberDto) == 1;
	}

	@Override
	public boolean checkId(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).checkId(userid) == 0;
	}

}
