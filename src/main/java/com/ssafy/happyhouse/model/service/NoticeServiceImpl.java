package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.NoticeParameterDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;
import com.ssafy.util.PageNavigation;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writeArticle(NoticeDto noticeDto) throws Exception {
		if (noticeDto.getSubject() == null || noticeDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(NoticeMapper.class).writeArticle(noticeDto) == 1;
	}

	@Override
	public List<NoticeDto> listArticle(NoticeParameterDto noticeParameterDto) throws Exception {
		int start = noticeParameterDto.getPg() == 0 ? 0
				: (noticeParameterDto.getPg() - 1) * noticeParameterDto.getSpp();
		noticeParameterDto.setStart(start);
		return sqlSession.getMapper(NoticeMapper.class).listArticle(noticeParameterDto);
	}

	@Override
	public PageNavigation makePageNavigation(NoticeParameterDto noticeParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(noticeParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(NoticeMapper.class).getTotalCount(noticeParameterDto);// 총글갯수 269
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / noticeParameterDto.getSpp() + 1;// 27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = noticeParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < noticeParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public NoticeDto getArticle(int articleno) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).getArticle(articleno);
	}

	@Override
	public void updateHit(int articleno) throws Exception {
		sqlSession.getMapper(NoticeMapper.class).updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticle(NoticeDto noticeDto) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).modifyArticle(noticeDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticle(int articleno) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).deleteArticle(articleno) == 1;
	}

}
