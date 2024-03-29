package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.ReplyDto;
import com.ssafy.happyhouse.model.service.ReplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/reply")
@Api("답글 컨트롤러  API V1")
@CrossOrigin
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private ReplyService replyService;

	@ApiOperation(value = "답변작성", notes = "새로운 답변 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeArticle(
			@RequestBody @ApiParam(value = "답변 정보.", required = true) ReplyDto replyDto) throws Exception {
		logger.info("writeArticle - 호출");
		if (replyService.writeArticle(replyDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "해당 게시글 답변목록", notes = "선택된 게시글의 답변정보를 반환한다.", response = List.class)
	@GetMapping("/{articleno}")
	public ResponseEntity<List<ReplyDto>> listArticle(
			@PathVariable("articleno") @ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) int articleno)
			throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<ReplyDto>>(replyService.listReply(articleno), HttpStatus.OK);
	}

	@ApiOperation(value = "답변 글수정", notes = "답변글 정보를 수정한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyArticle(
			@RequestBody @ApiParam(value = "수정할 글정보.", required = true) ReplyDto replyDto) throws Exception {
		logger.info("modifyArticle - 호출");

		if (replyService.modifyArticle(replyDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	@ApiOperation(value = "답변 글삭제", notes = "답변번호에 해당하는 답변의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteArticle(
			@PathVariable("id") @ApiParam(value = "살제할 글의 글번호.", required = true) int id) throws Exception {
		logger.info("deleteArticle - 호출");
		if (replyService.deleteArticle(id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
