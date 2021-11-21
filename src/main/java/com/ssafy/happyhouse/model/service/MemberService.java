package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;

	public MemberDto userInfo(String userid) throws Exception;

	public boolean updateUser(MemberDto memberDto) throws Exception;

	public boolean deleteUser(String userid) throws Exception;

	public boolean joinUser(MemberDto memberDto) throws Exception;

	public boolean checkId(String userid) throws Exception;

}
