package com.highgroup.highgroup.common.controller.filemn;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.highgroup.highgroup.common.controller.DefaultController;
import com.highgroup.highgroup.common.model.BaseModel;
import com.highgroup.highgroup.common.utils.FileCoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.highgroup.highgroup.common.controller.filemn.service.fileService;

import lombok.extern.slf4j.Slf4j;

/**
 * 파일 관리 컨트롤러
 * 
 * @author 
 * @since 2020.12.30
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2020.12.30  LCY            최초 생성
 *
 * </pre>
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/file")
public class fileController extends DefaultController {
    //파일 저장 경로 
    @Value("${file.url}")
    private String FILE_STORAGE_PATH;

    @Resource(name = "fileService")
    private fileService fileService;

    @RequestMapping(value = "/test")
    public BaseModel saveSell() throws Exception {

        return setStatus(commandMap);
    }
    /* 파일 저장 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseModel saveSell(@RequestParam("filename") MultipartFile files) throws Exception {
        //파일 저장 경로
        String baseDir = FILE_STORAGE_PATH;
        Date dt = new Date();
        
        //날짜별 폴더 생성
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String datefolder =sdf.format(dt).toString();
        File dir = new File(baseDir, datefolder);
        if(!dir.exists()){
            dir.mkdirs();
        };

        //파일명 변경 - > 시분초 + 파일 명
        SimpleDateFormat simpl = new SimpleDateFormat("hhmmss");
        String time =simpl.format(dt).toString();
        String filePath = dir +"\\"+time+"_"+files.getOriginalFilename();
        String filePath2 = datefolder +"/"+time+"_"+files.getOriginalFilename();

        //파일 저장
        files.transferTo(new File(filePath));

        //String encryFileNm = filePath+"_encry";
        //FileCoder.encryptFile(filePath , encryFileNm);
        
        //DB저장
        commandMap.put("file_name", files.getOriginalFilename() );
        commandMap.put("file_path", filePath2);
        int result = fileService.insertFile(commandMap);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("msg", result == 1 ? "OK" : "NG"  );
        return setStatus(resultMap);
     };
};