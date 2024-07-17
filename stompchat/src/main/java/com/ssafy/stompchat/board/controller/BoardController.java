package com.ssafy.stompchat.board.controller;

import com.ssafy.stompchat.board.dto.request.BoardRequestDto;
import com.ssafy.stompchat.board.dto.response.BoardResponseDto;
import com.ssafy.stompchat.board.service.BoardService;
import com.ssafy.stompchat.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping
    public ApiResponse<BoardResponseDto> createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        BoardResponseDto createdBoard = boardService.createBoard(boardRequestDto);
        System.out.println(createdBoard.toString());
        return ApiResponse.createSuccess(createdBoard);
    }

    @GetMapping("/{boardId}")
    public ApiResponse<BoardResponseDto> getBoard(@PathVariable Long boardId) {
        BoardResponseDto board = boardService.getBoard(boardId);
        return ApiResponse.createSuccess(board);
    }

    @GetMapping
    public ApiResponse<List<BoardResponseDto>> getAllBoards() {
        List<BoardResponseDto> boards = boardService.getAllBoards();
        return ApiResponse.createSuccess(boards);
    }
    @PutMapping("/{boardId}")
    public ApiResponse<BoardResponseDto> updateBoard(@PathVariable Long boardId, @RequestBody BoardRequestDto boardRequestDto) {
        BoardResponseDto updatedBoard = boardService.updateBoard(boardId, boardRequestDto);
        return ApiResponse.createSuccess(updatedBoard);
    }
    @DeleteMapping("/{boardId}")
    public ApiResponse<?> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ApiResponse.createSuccessWithNoContent();
    }
}
