package com.ezen.test.controller;


import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.test.domain.BoardDTO;
import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.FileVO;
import com.ezen.test.domain.PagingVO;
import com.ezen.test.handler.FileHandler;
import com.ezen.test.handler.PagingHandler;
import com.ezen.test.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/*")
@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {
	@Inject
	private final BoardService bsv;
	@Inject
	private final FileHandler fhd;
	
	@GetMapping("/register")
	public void register() {
	}
	
	//@RequestParam("name") String name : 파라미터를 받을 때
	//required  : 필수여부 false : 파라미터가 ㅇ벗어도 예외가 발생하지않음
	@PostMapping("/insert")
	public String insert(BoardVO bvo , @RequestParam(name= "files",required= false)MultipartFile[] files) {
		log.info(">>>>>bvo >>> {}",bvo);
		log.info(">>>>>files >>> {}", files);
		//파일 핸들러 처리
		//파일 저장처리 => fileList 리턴 
		List<FileVO> flist = null;
		
		//파일이 있을 경우메만 핸들러 호출
		if(files[0].getSize() >0) {
			 //핸들러 호출
			flist = fhd.uploadFiles(files);
			log.info(">>>>>flist >>> {}", flist);
			
		}
		
		BoardDTO bdto = new BoardDTO(bvo,flist);
		
		int isOk = bsv.insert(bdto);
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/list")
	public String list(Model m, PagingVO pgvo) { //PagingVO 파라미터가 없으면 기본 생성자 값이 뜨게된다. (없으면 null)
		
		log.info(">>>>pgvo의 객체 값>>>>{}",pgvo);
		
		//리턴타입은 목적지 경로에 대한 타입 (destPage가 리턴)
		//Model 객체 => request.setAttribute랑 동일
		
		//cmt,qty , has_file update 후 리스트 가져오기
		bsv.cmtFileUpdate();
		List <BoardVO> list = bsv.getList(pgvo);
		
		int totalCount = bsv.getTotal(pgvo);	
		log.info(">>>토탈카운트 값 >>> {}",totalCount);
		
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		log.info(">>>>PagineHandler 객체 값>>>>>{}",ph);
		
		m.addAttribute("list",list);
		m.addAttribute("ph", ph);
		return "/board/list";
	}
	
	//detail => / board/detail => return /board/detail
	//modify => /board/modify => return / board/modify
	//controller로 들어오는 경로와 jsp로 나가는 경로와 일치하면  return을 생략하고 void 처리를 할 수있다.
	@GetMapping({"/detail","/modify"})
	public void detail(Model m, @RequestParam("bno")int bno) {
		log.info(">>>>bno{}",bno);
		BoardDTO bdto = bsv.getDetail(bno);
		log.info(">>>>bdto{}",bdto);
		m.addAttribute("bdto",bdto);
	}
	
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo ,@RequestParam(name="files", required = false)MultipartFile[] files) {
		log.info(">>>>>Modify bvo >>> {}",bvo);
		List<FileVO> flist = null;
		
		// fileHandler multipartfile[] => flist
		if(files[0].getSize() > 0) {
			flist = fhd.uploadFiles(files);
		}
		BoardDTO  bdto = new BoardDTO(bvo, flist);
		bsv.update(bdto);
		// /board/detail.jsp : 새로운 데이터를 가지고 가야함.
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")int bno) {
		log.info(">>>>>remove bno>>>>{}",bno);
		bsv.remove(bno);
		return "redirect:/board/list";
	}

//	@DeleteMapping(value = "/{cno}", produces = MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> remove(@PathVariable("cno")int cno){
//		int isOk = csv.delete(cno);
//		return  isOk>0 ? new ResponseEntity<String>("1",HttpStatus.OK) : 
//			new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	@DeleteMapping(value = "/{uuid}", produces= MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> removeFile(@PathVariable("uuid")String uuid){
		log.info(">>>>>uuid>>>>{}",uuid);
		
		int isOk = bsv.removeFile(uuid);
		
		return isOk > 0? new ResponseEntity<String>("1",HttpStatus.OK) :
			new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
