package com.highgroup.highgroup.task.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.highgroup.highgroup.common.controller.DefaultController;
import com.highgroup.highgroup.common.model.BaseModel;
import com.highgroup.highgroup.task.board.service.BoardService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
/**
 * 글 컨트롤러
 * @author
 * @since 2021.01.26
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2021.03.03  LCY            최초 생성
 *
 *      </pre>
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/board")
public class BoardController extends DefaultController {

  @Resource(name = "BoardService")
  private BoardService boardService;
    
  //게시글 조회
  @RequestMapping(value = "/view")
  public BaseModel boardList() throws Exception {
    Map<String, Object> result = boardService.getBoard(commandMap);
    return setStatus(result);
  };
  //댓글 조회
  @RequestMapping(value = "/replyList")
  public BaseModel replyList() throws Exception {
    List<Map<String, Object>> resultList = boardService.getReplyList(commandMap);
    String totalCnt = boardService.getCount(commandMap);
    Map<String,Object> obj = new HashMap<String,Object>();
    obj.put("resultList", resultList);
    obj.put("totalCnt", totalCnt);

    return setStatus(obj);
  };
  //첨부파일 조회
  @RequestMapping(value = "/fileList")
  public BaseModel fileList() throws Exception {
    List<Map<String, Object>> resultList = boardService.getBoardList(commandMap);
    Map<String,Object> obj = new HashMap<String,Object>();
    obj.put("resultList", resultList);
    return setStatus(obj);
  };
  
  //게시글 생성
  @RequestMapping(value = "/create")
  public BaseModel create() throws Exception {
    int result = boardService.insertBoard(commandMap);
    return setStatus(result);
  };
  //게시글 생성
  @RequestMapping(value = "/update")
  public BaseModel update() throws Exception {
    int result = boardService.updateBoard(commandMap);
    return setStatus(result);
  };
}
