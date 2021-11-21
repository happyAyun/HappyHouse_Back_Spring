package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.BikeDto;
import com.ssafy.happyhouse.model.CoronaInspectorDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
	}

	@Override
	public String getHangjung(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getHangjung(dong);
	}

	@Override
	public List<BikeDto> getBike(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getBike(gugun);
	}

	@Override
	public List<CoronaInspectorDto> getInspector(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getInspector(gugun);
	}

	@Override
	public List<HouseInfoDto> getPastAptList(String aptCode) {
		return sqlSession.getMapper(HouseMapMapper.class).getPastAptList(aptCode);
	}

}
