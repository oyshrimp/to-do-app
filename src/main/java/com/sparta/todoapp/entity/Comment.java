package com.sparta.todoapp.entity;

import com.sparta.todoapp.dto.CommentRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    @Column(nullable = false)
    private String password;

    public Comment(CommentRequest commentRequest, Schedule schedule) {
        this.content = commentRequest.getContent();
        this.password = commentRequest.getPassword();
        this.schedule = schedule;
    }


    public void update(CommentRequest commentRequest) {
        this.content = commentRequest.getContent();
    }
}
