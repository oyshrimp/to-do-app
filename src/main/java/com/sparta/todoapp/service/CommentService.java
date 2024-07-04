package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.CommentRequest;
import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.entity.Schedule;
import com.sparta.todoapp.repository.CommentRepository;
import com.sparta.todoapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    // 댓글 작성
    public void createComment(CommentRequest commentRequest) {
        Schedule schedule = scheduleRepository.findById(commentRequest.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스케줄 ID입니다."));

        Comment comment = new Comment(commentRequest, schedule);
        commentRepository.save(comment);
    }

    // 댓글 수정
    public Comment updateComment(Long id, CommentRequest commentRequest, String password) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID입니다."));

        if (!comment.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        comment.update(commentRequest);
        return commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long id, String password) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID입니다."));

        if (!comment.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        commentRepository.delete(comment);
    }
}
