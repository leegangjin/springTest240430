package com.ezen.www.handler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ezen.www.domain.FileVO;
import com.ezen.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@EnableScheduling
@Slf4j
@Component
@RequiredArgsConstructor
public class FileSweeper {
    //직접 DB접속을 해서 처리
	
	private final FileDAO fdao;
	
	private final String BASE_PATH="C:\\myProject\\java\\fileUpload\\";
	
	//초 분 시 일 월 요일 년도(생략가능)
	//매일 정해진 시간에 스케줄러를 실행
	//매일 등록된 파일과 (DB) <-> 해당일의 폴더에 있는 파일이 일치하는 파일은 남기고. 일치하지 않는 파일 삭제
	@Scheduled(cron="0 47 14 * * *") //13시 정각에 실행
	public void fileSweeper() {
		log.info(">>>FileSweeper Running Start~!! : {}",LocalDateTime.now());
		
		//DB에 등록된 파일 목럭 가져오기.
		List<FileVO>dbList = fdao.selectListAllFile();
		
		//저장소를 검색할 때 필요한 파일 경로 리스트 (실제 존재해야하는 리스트)
		List<String> currFiles = new ArrayList<String>();
		
		for(FileVO fvo : dbList) {
			String filePath = fvo.getSaveDir()+File.separator+fvo.getUuid();
			String fileName = fvo.getFileName();
			currFiles.add(BASE_PATH+filePath+"_"+fileName);
			
			//이미지라면 섬네일 경로도 추거
			if(fvo.getFileType()>0) {
				currFiles.add(BASE_PATH+"th"+fileName);
			}
		}
		
		log.info(">>>currFile>>{}", currFiles);
		
		//오늘 날짜를 반영한 폴더구조 경로 만들기
		LocalDate now = LocalDate.now();
		String today = now.toString();
		today= today.replace("-",File.separator);
		
		//경로를 기반으로 저장되어있는 파일을 검색
		
		File dir = Paths.get(BASE_PATH+today).toFile();
		log.info(">>>dir>>{}", dir);
		
		//listFiles() : 경로에 있는 모든 파일을 배열로 리턴
		File[] allFileObject = dir.listFiles();
		
		
		//실제 저장되어 있는 파일과 db에 존재하는 파일을 비교하여 없는 파일을 삭제 진행
		for(File file : allFileObject) {
			String storedFileName = file.toPath().toString();
			log.info(">>>storedFileName>>{}", storedFileName);
			//없으면 삭제
			if(!currFiles.contains(storedFileName)) {
				file.delete(); //파일 삭제
				log.info(">>>delete files >>{}", storedFileName);
			}
		}
		
		
		
		
		
		
		
		
		
		
		log.info(">>>FileSweeper Running End~!! : {}",LocalDateTime.now());
	}
}
