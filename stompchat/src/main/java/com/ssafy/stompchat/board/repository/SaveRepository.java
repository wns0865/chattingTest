package com.ssafy.stompchat.board.repository;

import com.ssafy.stompchat.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveRepository extends JpaRepository<Board,Long> {
}
