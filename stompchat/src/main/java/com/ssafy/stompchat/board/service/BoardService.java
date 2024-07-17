package com.ssafy.stompchat.board.service;

import com.ssafy.stompchat.board.dto.request.BoardRequestDto;
import com.ssafy.stompchat.board.dto.response.BoardResponseDto;
import com.ssafy.stompchat.board.entity.Board;
import org.hibernate.query.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface BoardService {
    BoardResponseDto createBoard(BoardRequestDto boardRequestDto);
    BoardResponseDto getBoard(Long boardId);
    List<BoardResponseDto> getAllBoards();
    BoardResponseDto updateBoard(Long boardId, BoardRequestDto boardRequestDto);
    void deleteBoard(Long boardId);
//    void likeBoard(Long boardId, Long userId);
//    void unlikeBoard(Long boardId, Long userId);
//    void saveBoard(Long boardId, Long userId);
//    void unsaveBoard(Long boardId, Long userId);
//    void addComment(Long boardId, Long userId, String content);

}
