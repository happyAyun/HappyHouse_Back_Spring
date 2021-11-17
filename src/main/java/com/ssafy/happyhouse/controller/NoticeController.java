package com.ssafy.happyhouse.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.FileInfoDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.NoticeService;
import com.ssafy.util.PageNavigation;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private ServletContext servletContext;

	@GetMapping("/list")
	private ModelAndView list(@RequestParam Map<String, String> map) throws Exception {
		ModelAndView mav = new ModelAndView();
		String spp = map.get("spp");
		map.put("spp", spp != null ? spp : "10");
		List<NoticeDto> list = noticeService.listArticle(map);
		PageNavigation pageNavigation = noticeService.makePageNavigation(map);
		mav.addObject("articles", list);
		mav.addObject("navigation", pageNavigation);
		mav.addObject("key", map.get("key"));
		mav.addObject("word", map.get("word"));
		mav.setViewName("notice/list");
		return mav;
	}

	@GetMapping("/desc")
	private ModelAndView desc(@RequestParam("articleNo") int articleNo) throws Exception {
		ModelAndView mav = new ModelAndView();
		NoticeDto noticeDto = noticeService.getArticle(articleNo);
		mav.addObject("article", noticeDto);
		mav.setViewName("notice/desc");
		return mav;
	}

	@GetMapping("/regist")
	private String regist() {
		return "notice/write";
	}

	@PostMapping("/regist")
	private String regist(NoticeDto noticeDto, @RequestParam("upfile") MultipartFile[] files, HttpSession session)
			throws Exception {
		UserDto userDto = (UserDto) session.getAttribute("userinfo");
		noticeDto.setUserId(userDto.getUserId());
		if (!files[0].isEmpty()) {
			String realPath = servletContext.getRealPath("/upload");
			String imgRealPath = servletContext.getRealPath("/resources/img"); // 추가
			String today = new SimpleDateFormat("yyMMdd").format(new Date());

			List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
			for (MultipartFile mfile : files) {
				FileInfoDto fileInfoDto = new FileInfoDto();
				String originalFileName = mfile.getOriginalFilename();
				if (!originalFileName.isEmpty()) {
					String imgType = originalFileName.substring(originalFileName.lastIndexOf('.') + 1); // 확장자
					String saveFileName = UUID.randomUUID().toString()
							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
					String saveFolder = "";
					// 파일 확장자 확인
					if (imgType.equals("png") || imgType.equals("jpg") || imgType.equals("gif")) { // 이미지 확장자
						saveFolder = imgRealPath + File.separator + today;
					} else { // 이미지 외의 파일
						saveFolder = realPath + File.separator + today;
					}
					logger.debug("저장 폴더 : {}", saveFolder);
					File folder = new File(saveFolder);
					if (!folder.exists())
						folder.mkdirs();
					fileInfoDto.setSaveFolder(today);
					fileInfoDto.setOriginFile(originalFileName);
					fileInfoDto.setSaveFile(saveFileName);
					mfile.transferTo(new File(folder, saveFileName));
				}
				fileInfos.add(fileInfoDto);
			}
			noticeDto.setFileInfos(fileInfos);
		}
		noticeService.registerArticle(noticeDto);
		return "redirect:/notice/list?pg=1&key=&word=";
	}

	@GetMapping(value = "/download")
	public ModelAndView downloadFile(@RequestParam("sfolder") String sfolder, @RequestParam("ofile") String ofile,
			@RequestParam("sfile") String sfile, HttpSession session) throws Exception {
		UserDto userDto = (UserDto) session.getAttribute("userinfo");
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		fileInfo.put("sfolder", sfolder);
		fileInfo.put("ofile", ofile);
		fileInfo.put("sfile", sfile);
		return new ModelAndView("fileDownLoadView", "downloadFile", fileInfo);
	}
}
