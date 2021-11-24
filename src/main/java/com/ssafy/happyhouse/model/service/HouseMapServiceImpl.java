package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.BikeDto;
import com.ssafy.happyhouse.model.BusDto;
import com.ssafy.happyhouse.model.CoronaInspectorDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SchoolDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.SubwayDto;
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
	public List<BikeDto> getBike(Map<String, String> latlng) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getBike(latlng);
	}

	@Override
	public List<CoronaInspectorDto> getInspector(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getInspector(gugun);
	}

	@Override
	public List<HouseInfoDto> getPastAptList(String aptCode) {
		return sqlSession.getMapper(HouseMapMapper.class).getPastAptList(aptCode);
	}

	@Override
	public List<BusDto> getBus(Map<String, String> latlng) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getBus(latlng);
	}

	@Override
	public List<SubwayDto> getSubway(Map<String, String> latlng) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSubway(latlng);
	}

	@Override
	public List<HouseInfoDto> getAptInRadius(Map<String, String> latlng) {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInRadius(latlng);
	}

	@Override
	public List<SubwayDto> getdSubway() {
		return sqlSession.getMapper(HouseMapMapper.class).getdSubway();
	}

	@Override
	public List<SchoolDto> getSchool(Map<String, String> latlng) {
		return sqlSession.getMapper(HouseMapMapper.class).getSchool(latlng);
	}

}
