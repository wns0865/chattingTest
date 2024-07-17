package com.ssafy.stompchat.board.service;
import com.ssafy.stompchat.board.dto.request.BoardRequestDto;
import com.ssafy.stompchat.board.dto.response.BoardResponseDto;
import com.ssafy.stompchat.board.entity.Board;
import com.ssafy.stompchat.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;


    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto) {
        Board board= Board.builder()
                .userId(boardRequestDto.getUserId())
                .userId(boardRequestDto.getUserId())
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .category(boardRequestDto.getCategory())
                .imageURL(boardRequestDto.getImageURL())
                .isBoardOpen(boardRequestDto.isBoardOpen())
                .build();
        Board savedBoard = boardRepository.save(board);
        return convertToDto(savedBoard);
    }

    @Override
    public BoardResponseDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        return convertToDto(board);
    }

    @Override
    public List<BoardResponseDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        board.setTitle(boardRequestDto.getTitle());
        board.setContent(boardRequestDto.getContent());
        board.setCategory(boardRequestDto.getCategory());
        board.setImageURL(boardRequestDto.getImageURL());
        board.setBoardOpen(boardRequestDto.isBoardOpen());

        Board updatedBoard = boardRepository.save(board);
        return convertToDto(updatedBoard);
    }


    @Override
    @Transactional
    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    private BoardResponseDto convertToDto(Board boardEntity) {
        BoardResponseDto dto = new BoardResponseDto();
        dto.setBoardId(boardEntity.getBoardId());
        dto.setUserId(boardEntity.getUserId());
        dto.setTitle(boardEntity.getTitle());
        dto.setContent(boardEntity.getContent());
        dto.setCategory(boardEntity.getCategory());
        dto.setImageURL(boardEntity.getImageURL());
        dto.setBoardOpen(boardEntity.isBoardOpen());
        dto.setCreatedAt(boardEntity.getCreatedAt());
        return dto;
    }

}

