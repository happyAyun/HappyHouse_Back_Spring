package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.FileInfoDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;
import com.ssafy.util.PageNavigation;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	@Transactional
	public void registerArticle(NoticeDto noticeDto) throws Exception {
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		noticeMapper.registerArticle(noticeDto);
		List<FileInfoDto> fileInfos = noticeDto.getFileInfos();
		if (fileInfos != null && !fileInfos.isEmpty()) {
			noticeMapper.registerFile(noticeDto);
		}
	}

	@Override
	public List<NoticeDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if ("userid".equals(key))
			key = "n.userid";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pg") == null ? "1" : map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		param.put("start", start);
		//
//		int articleNum = sqlSession.getMapper(NoticeMapper.class).getTotalCount(map);
//		if (sizePerPage > articleNum)
//			sizePerPage = articleNum;
		param.put("spp", sizePerPage);
		return sqlSession.getMapper(NoticeMapper.class).listArticle(param);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = 10;
		int currentPage = Integer.parseInt(map.get("pg"));
		System.out.println(currentPage);
		int sizePerPage = Integer.parseInt(map.get("spp"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(NoticeMapper.class).getTotalCount(map);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

	@Override
	public NoticeDto getArticle(int articleNo) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).getArticle(articleNo);
	}

	@Override
	public void updateArticle(NoticeDto noticeDto) throws Exception {
		sqlSession.getMapper(NoticeMapper.class).updateArticle(noticeDto);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		sqlSession.getMapper(NoticeMapper.class).deleteArticle(articleNo);
	}

}
