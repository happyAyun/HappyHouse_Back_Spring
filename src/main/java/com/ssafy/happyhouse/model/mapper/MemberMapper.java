package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;

	public MemberDto userInfo(String userid) throws SQLException;

	public int deleteUser(String userid) throws SQLException;

	public int updateUser(MemberDto memberDto) throws SQLException;

	public int joinUser(MemberDto memberDto) throws SQLException;

	public int checkId(String userid) throws SQLException;
}
